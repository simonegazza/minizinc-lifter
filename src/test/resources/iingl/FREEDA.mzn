predicate global_cardinality(
    array [$X] of var $$E: x,
    array [int] of var $$E: cover,
    array [int] of var int: lbound,
    array [int] of var int: ubound
) = forall(i in 1..length(cover))(
    sum([e = cover[i] | e in x]) in lbound[i]..ubound[i]
);

predicate cumulative(
    array[$$E] of var opt int: s,
    array[$$E] of var opt int: d,
    array[$$E] of var opt int: r,
    var int: b
) = assert(
    % They all must have same length
    index_set(s) == index_set(d) /\ index_set(s) == index_set(r),
    "cumulative redefinition: arrays must have identical index sets",

    let {
        array[index_set(s)] of var 0..ub(max(d)): d_deopt;
        array[index_set(s)] of var 0..ub(max(r)): r_deopt;
    } in
    % If s occurs than also d and r must occur, and viceversa
    forall(i in index_set(s))(occurs(s[i]) = occurs(d[i]) /\ occurs(s[i]) = occurs(r[i]))
    /\
    % "channeling" from d to define d_deopt
    d_deopt = [
        if occurs(d[i]) then d_deopt[i] = deopt(d[i]) else d_deopt[i] = 0 endif
        | i in index_set(s)]
    /\
    % "channeling" from r to define r_deopt
    r_deopt = [
        if occurs(r[i]) then r_deopt[i] = deopt(r[i]) else r_deopt[i] = 0 endif
        | i in index_set(s)]
    /\
    % Nonnegativity only when present
    forall(i in index_set(s))(occurs(d[i]) -> d[i] >= 0 /\ occurs(r[i]) -> r[i] >= 0)
    /\
    cumulative(s, d_deopt, r_deopt, b)
);

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%% MiniZinc parametric model for FREEDA %%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

include "globals.mzn";

%%%%%%%%%%%%%%%%%%%%%%
%%%%% Parameters %%%%%
%%%%%%%%%%%%%%%%%%%%%%

%%%%% Components and Flavours %%%%%

% All the components.
enum Comps;
% Components that *must* always be deployed.
set of Comps: mustComps;

% All the flavours.
enum Flavs;
% Flav[c] is the set of all flavours offered by component c.
array[Comps] of set of Flavs: Flav;

% CompFlavs is the index set for the rows of the deployment matrices.
set of int: CompFlavs = 1..sum(c in Comps)(length(Flav[c]));
% Idx(c,f) is the row of component c in flavour f in the deployment matrices.
% It maps pairs from Comps × Flavs to indices of CompFlavs.
function int:
Idx(Comps: c, Flavs: f) =
  sum (i in 1..c-1)(length(Flav[Comps[i]])) + arg_max([f = i | i in Flav[c]]);

% Importance of a component in a given flavour.
% FIXME: we assume f ⪯c f′ iff imp(c,f) ≤ imp(c,f′).
array[Comps, Flavs] of int: imp;

% Uses[Idx(c,f),Idx(c',f')] = 1 iff component c in flavour f requires component
% c' in a flavour at least as powerful as f′.
array[CompFlavs, CompFlavs] of 0..1: Uses;

% mayUse[c,Idx(c',f)] = 1 iff c' in flavour f may use c in some flavour.
array[Comps, CompFlavs] of 0..1: mayUse;

%%%%% Resources %%%%%

% Consumable resources.
enum CRes;
% Non-consumable resources.
enum NRes;
% All the resources.
enum Res = C(CRes) ++ N(NRes);

% Req_cf[Idx(c,f)] is the set of resources required by c in flavour f.
array[instances, CompFlavs] of set of Res: Req_cf = array2d(instances, CompFlavs, [
  {r | r in Res where comReq_instances[i0, cf, r] != worstBounds[r]} | i0 in instances, cf in CompFlavs
]);
array[CompFlavs] of var set of Res: Req_cf_lifted = [
  {r | r in Res where comReq_lifted[cf, r] != worstBounds[r]} | cf in CompFlavs
];

% Req_cfc[c,f,c'] is the set of resources required by the connection between
% "source" component c, deployed in flavour f, and "target" component c′.
array[instances, Comps, Flavs, Comps] of set of Res: Req_cfc = array4d(instances, Comps, Flavs, Comps,
  [{r | r in Res where depReq_instances[i0, c1, i, c2, r] != worstBounds[r]}
  | i0 in instances, c1 in Comps, i in Flavs, c2 in Comps
  ]);
array[Comps, Flavs, Comps] of var set of Res: Req_cfc_lifted = array3d(Comps, Flavs, Comps,
  [{r | r in Res where depReq_lifted[c1, i, c2, r] != worstBounds[r]}
  | c1 in Comps, i in Flavs, c2 in Comps
  ]);

% Lower bound for bestBounds and worseBounds values.
int: MIN_RBOUNDS;
% Upper bound for bestBounds and worseBounds values.
int: MAX_RBOUNDS;
% No value for a resource r can be worse than worstBounds[r].
array[Res] of MIN_RBOUNDS..MAX_RBOUNDS: bestBounds;
% No value for a resource r can be better than bestBounds[r].
array[Res] of MIN_RBOUNDS..MAX_RBOUNDS: worstBounds;

% comReq[Idx(c,f), r] defines the amount of resource r in Req_cf[Idx(c,f)]
% required by c to be executed in flavour f: if worstBounds[r] = MIN_RBOUNDS,
% then c requires at least comReq[Idx(c,f), r] units of r; otherwise, it
% requires at most comReq[Idx(c,f), r] units of r.

array[CompFlavs, Res] of var MIN_RBOUNDS..MAX_RBOUNDS: comReq_lifted :: doc_comment("comReq_lifted");

% depReq[c, f, c', r] defines the amount of resource r in Req_cfc[c,f,c']
% required by the link connecting the node of the source component c to the
% node of the target component c′: if worstBounds[r] = MIN_RBOUNDS,
% then the link requires at least depReq[c, f, c', r] units of r; otherwise,
% it requires at most depReq[c, f, c', r] units of r.

array[Comps, Flavs, Comps, Res] of var MIN_RBOUNDS..MAX_RBOUNDS: depReq_lifted :: doc_comment("depReq_lifted");

%%%%% Infrastructure, capacity and budget %%%%%

% All the nodes of the infrastructure.
enum Nodes;
% Node 0 is a dummy node added for convenience, to better handle the
% constraints below. No component can be deployed on node 0.
set of int: Nodes0 = {0} union Nodes;

% Req_n[n] is the set of resources offered by node n.
array[instances, Nodes] of set of Res: Req_n = array2d(instances, Nodes, [
  {r | r in Res where nodeCap_instances[i0, n, r] != worstBounds[r]} | i0 in instances, n in Nodes
]);
array[Nodes] of var set of Res: Req_n_lifted = [
  {r | r in Res where nodeCap_lifted[n, r] != worstBounds[r]} | n in Nodes
];

% nodeCap[n,r] is maximum amount of resource r available at node n.

array[Nodes0, Res] of var MIN_RBOUNDS..MAX_RBOUNDS: nodeCap_lifted :: doc_comment("nodeCap_lifted");
% If n = 0, it must be nodeCap[n,r] = bestBounds[r].
constraint forall(r in Res)(nodeCap_lifted[0,r] = bestBounds[r]);

% linkCap[n, n', r] is the maximum amount of r that the link between n and n′
% can handle.

array[Nodes0, Nodes0, Res] of var MIN_RBOUNDS..MAX_RBOUNDS: linkCap_lifted :: doc_comment("linkCap_lifted");
% If n = 0 or n' = 0, it must be linkCap[n, n', r] = bestBounds[r].
constraint forall(n in Nodes, r in Res)(
  linkCap_lifted[n,0,r] = bestBounds[r] /\ linkCap_lifted[0, n, r] = bestBounds[r]);

% cost[n,r] is the unit cost of using resource r node n.

array[Nodes0, Res] of var 0..10: cost_lifted :: doc_comment("cost_lifted");
% It must be cost[0,r] = 0.
constraint forall(r in Res)(cost_lifted[0,r] = 0);
% Money budget.

var 1..1000000: costBudget_lifted :: doc_comment("costBudget_lifted");

% carb[n] is the carbon emission per node n.

array[Nodes0] of var 0..10: carb_lifted :: doc_comment("carb_lifted");
% It must be carb[0] = 0.
constraint carb_lifted[0] = 0;
% Carbon budget.

var 1..2000000: carbBudget_lifted :: doc_comment("carbBudget_lifted");


%%%%%%%%%%%%%%%%%%%%%
%%%%% Variables %%%%%
%%%%%%%%%%%%%%%%%%%%%

% D[Idx(c,f),j] = 1 iff component c is deployed in flavour f on node j.
array [{0} union CompFlavs, Nodes0] of var 0..1: D;

% node[c] is the node where c is deployed, or node[c] = 0 if c not deployed.
array [Comps] of var Nodes0: node = [
  sum(i in Flav[c], j in Nodes)(j * D[Idx(c, i), j]) | c in Comps
];

% Total cost of the deployment.
var int: totCost;
constraint totCost = sum(c in Comps, i in Flav[c], r in Req_cf_lifted[Idx(c, i)])(
  comReq_lifted[Idx(c, i), r] * cost_lifted[node[c], r] * D[Idx(c, i), node[c]]
);

% Energy reuirements for each component-flavour

array[CompFlavs] of var 0..1000: energy_lifted :: doc_comment("energy_lifted");

% Energy consumption for the dependency between components

array[Comps, Flavs, Comps] of var 0..1000: energy_dependency_lifted :: doc_comment("energy_dependency_lifted");

% Total carbon emission of the deployment.
var int: totCarb;
constraint totCarb = sum(cs in Comps, is in Flav[cs], js in Nodes)(
  D[Idx(cs, is), js] * (
    energy_lifted[Idx(cs, is)] * carb_lifted[js] + sum(
      cd in Comps,
      id in Flav[cd] where mayUse[cd, Idx(cs, is)] = 1 /\ energy_dependency_lifted[cs, is, cd] > 0,
      jd in Nodes
    )(
      D[Idx(cd, id), jd] * energy_dependency_lifted[cs, is, cd] * (carb_lifted[js] + carb_lifted[jd]) div 2
    )
  )
);

%%%%%%%%%%%%%%%%%%%%%%%
%%%%% Constraints %%%%%
%%%%%%%%%%%%%%%%%%%%%%%

% Zero values for D[0,j] and D[i,0].
constraint forall(j in Nodes0)(D[0, j] = 0);
constraint forall(i in CompFlavs)(D[i, 0] = 0);

% Each component is deployed in at most one flavour, on at most one node.
constraint forall(c in Comps)(
  sum(i in Flav[c], j in Nodes)(D[Idx(c, i), j]) <= 1
);

% Components that must be deployed
constraint forall(m in mustComps)(
  sum (i in Flav[m], j in Nodes)(D[Idx(m, i), j]) > 0 /\ node[m] > 0
);

% If component c is deployed in flavour i, for each (c',i') in Uses[c,i]
% component c' must be deployed in a flavour bigger or equal than i'.
constraint forall(
  c in Comps,
  i in Flav[c],
  cu in Comps,
  iu in Flav[cu] where Uses[Idx(c, i), Idx(cu, iu)] = 1
)(
  sum(j in Nodes)(D[Idx(c, i), j]) <=
  sum(k in Flav[cu], j in Nodes where imp[cu,k] >= imp[cu,iu])(D[Idx(cu, k),j])
);

% If a "target" component c not in mustComps is deployed, at least one "source"
% component c' must be deployed in a flavour f requiring c.
constraint forall(c in Comps diff mustComps)(
  sum(i in Flav[c], j in Nodes)(D[Idx(c, i), j]) <=
  sum(
    cs in Comps where c != cs,
    is in Flav[cs] where mayUse[c, Idx(cs, is)] = 1,
    j in Nodes
  )(D[Idx(cs, is), j])
);

% Each node j has a sufficient quantity of a certain resource r to meet the
% demands of all the components deployed on it.
constraint forall(j in Nodes, r in Req_n_lifted[j] where r in C(CRes)) (
  if worstBounds[r] = MIN_RBOUNDS then
    sum(c in Comps, i in Flav[c] where r in Req_cf_lifted[Idx(c,i)])
      (comReq_lifted[Idx(c,i), r] * D[Idx(c,i), j]) <= nodeCap_lifted[j,r]
  else
    assert(false, "·Lower bound for 'maximization' resources unsupported.")
  endif
);

% If component c deployed in flavour i requires a certain amount of resource r,
% then node c must have capacity for r
constraint forall(
  c in Comps,
  i in Flav[c],
  r in Req_cf_lifted[Idx(c,i)] where r in N(NRes)
)(
  if worstBounds[r] = MIN_RBOUNDS then
    comReq_lifted[Idx(c,i), r] * D[Idx(c,i), node[c]] <= nodeCap_lifted[node[c], r]
  else
    comReq_lifted[Idx(c,i), r] >= nodeCap_lifted[node[c], r] * D[Idx(c,i), node[c]]
  endif
);

% Dependency from c to c' must be fulfilled only if there is an active
% dependency between c and c'.
% FIXME: so far, only defined on non-consumable resources.
constraint forall(
  cs in Comps,
  is in Flav[cs],
  cd in Comps where mayUse[cd, Idx(cs, is)] = 1,
  r in Req_cfc_lifted[cs, is, cd] where r in N(NRes),
)(
  if worstBounds[r] = MIN_RBOUNDS then
    depReq_lifted[cs, is, cd, r] * sum(j in Nodes)(D[Idx(cs, is), j]) <=
    linkCap_lifted[node[cs], node[cd], r]
  else
    depReq_lifted[cs, is, cd, r] >= sum(j in Nodes)(D[Idx(cs, is), j]) *
    linkCap_lifted[node[cs], node[cd], r]
  endif
);


% Budget requirements.
constraint totCost <= costBudget_lifted;
constraint totCarb <= carbBudget_lifted;


%%%%%%%%%%%%%%%%%%%%%
%%%%% Objective %%%%%
%%%%%%%%%%%%%%%%%%%%%

var int: obj = sum(c in Comps, i in Flav[c])(
  imp[c,i] * sum([D[Idx(c,i),j] | j in Nodes])
);
solve maximize obj;

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%% Output formatting %%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


% ++ [
%     "\n\(r) : \(cs)_\(is) -> \(cd) on \(node[cs]) -> \(node[cd]) -- \(depReq[cs, is, cd, r] * sum(j in Nodes)(D[Idx(cs, is), j])) >= \(linkCap[node[cs], node[cd], r])"
%   | cs in Comps,
%     is in Flav[cs] ,%where fix(D[Idx(cs, is), node[cs]]) > 0,
%     cd in Comps ,%where exists(id in Flav[cd])(Uses[Idx(cs, is), Idx(cd, id)] = 1 /\ fix(D[Idx(cd, id), node[cd]]) > 0),
%     r in Req_cfc[cs, is, cd] where r in N(NRes) /\ worstBounds[r] != MIN_RBOUNDS
% ]










include "experimental/assume/fzn_assume.mzn";

int: max_instances = 1;
set of int: instances = 1..max_instances;
var instances: current_instance;

array[int] of array[int] of set of int: params_set = [
	[Req_cf[i, i0] | i0 in CompFlavs]
	 ++ [Req_cfc[i, i0, i1, i2] | i0 in Comps, i1 in Flavs, i2 in Comps]
	 ++ [Req_n[i, i0] | i0 in Nodes]
	| i in instances];

array[int] of var set of int: params_set_lifted = 
	[Req_cf_lifted[i0] | i0 in CompFlavs]
	 ++ [Req_cfc_lifted[i0, i1, i2] | i0 in Comps, i1 in Flavs, i2 in Comps]
	 ++ [Req_n_lifted[i0] | i0 in Nodes];

array[int] of array[int] of int: params_int = [
	[carb_instances[i, i0] | i0 in Nodes0]
	 ++ [carbBudget_instances[i]]
	 ++ [comReq_instances[i, i0, i1] | i0 in CompFlavs, i1 in Res]
	 ++ [cost_instances[i, i0, i1] | i0 in Nodes0, i1 in Res]
	 ++ [costBudget_instances[i]]
	 ++ [depReq_instances[i, i0, i1, i2, i3] | i0 in Comps, i1 in Flavs, i2 in Comps, i3 in Res]
	 ++ [energy_instances[i, i0] | i0 in CompFlavs]
	 ++ [energy_dependency_instances[i, i0, i1, i2] | i0 in Comps, i1 in Flavs, i2 in Comps]
	 ++ [linkCap_instances[i, i0, i1, i2] | i0 in Nodes0, i1 in Nodes0, i2 in Res]
	 ++ [nodeCap_instances[i, i0, i1] | i0 in Nodes0, i1 in Res]
	| i in instances];

array[int] of var int: params_int_lifted = 
	[carb_lifted[i0] | i0 in Nodes0]
	 ++ [carbBudget_lifted]
	 ++ [comReq_lifted[i0, i1] | i0 in CompFlavs, i1 in Res]
	 ++ [cost_lifted[i0, i1] | i0 in Nodes0, i1 in Res]
	 ++ [costBudget_lifted]
	 ++ [depReq_lifted[i0, i1, i2, i3] | i0 in Comps, i1 in Flavs, i2 in Comps, i3 in Res]
	 ++ [energy_lifted[i0] | i0 in CompFlavs]
	 ++ [energy_dependency_lifted[i0, i1, i2] | i0 in Comps, i1 in Flavs, i2 in Comps]
	 ++ [linkCap_lifted[i0, i1, i2] | i0 in Nodes0, i1 in Nodes0, i2 in Res]
	 ++ [nodeCap_lifted[i0, i1] | i0 in Nodes0, i1 in Res];

array[int] of array[int] of var bool: assumed = [
	[params_set_lifted[i0] = params_set[i][i0] | i0 in index_set(params_set_lifted)]
	++[params_int_lifted[i0] = params_int[i][i0] | i0 in index_set(params_int_lifted)]
	| i in instances];

constraint nogood_learning(
	[assumed[i][i0] | i in instances, i0 in index_set(assumed[i])],
	length(instances),
	current_instance
);

array[instances] of var int: objective_lifted :: output_var = [
	0 %sum([abs(card(Req_cf_lifted[i0] symdiff Req_cf[i, i0])) | i0 in CompFlavs])
	+ 0 %sum([abs(card(Req_cfc_lifted[i0, i1, i2] symdiff Req_cfc[i, i0, i1, i2])) | i0 in Comps, i1 in Flavs, i2 in Comps])
	+ 0 %sum([abs(card(Req_n_lifted[i0] symdiff Req_n[i, i0])) | i0 in Nodes])
	+ abs(carbBudget_lifted - carbBudget_instances[i])
	+ sum([abs(carb_lifted[i0] - carb_instances[i, i0]) | i0 in Nodes0])
	+ sum([abs(comReq_lifted[i0, i1] - comReq_instances[i, i0, i1]) | i0 in CompFlavs, i1 in Res])
	+ abs(costBudget_lifted - costBudget_instances[i])
	+ sum([abs(cost_lifted[i0, i1] - cost_instances[i, i0, i1]) | i0 in Nodes0, i1 in Res])
	+ sum([abs(depReq_lifted[i0, i1, i2, i3] - depReq_instances[i, i0, i1, i2, i3]) | i0 in Comps, i1 in Flavs, i2 in Comps, i3 in Res])
	+ sum([abs(energy_dependency_lifted[i0, i1, i2] - energy_dependency_instances[i, i0, i1, i2]) | i0 in Comps, i1 in Flavs, i2 in Comps])
	+ sum([abs(energy_lifted[i0] - energy_instances[i, i0]) | i0 in CompFlavs])
	+ sum([abs(linkCap_lifted[i0, i1, i2] - linkCap_instances[i, i0, i1, i2]) | i0 in Nodes0, i1 in Nodes0, i2 in Res])
	+ sum([abs(nodeCap_lifted[i0, i1] - nodeCap_instances[i, i0, i1]) | i0 in Nodes0, i1 in Res])
	| i in instances
];



output ["current_instance = \(current_instance)"] ++ ["\noriginal Req_cf = \(Req_cf[fix(current_instance), ..])\nlifted   Req_cf = \(Req_cf_lifted)\n\n"] ++ ["\noriginal Req_cfc = \(Req_cfc[fix(current_instance), .., .., ..])\nlifted   Req_cfc = \(Req_cfc_lifted)\n\n"] ++ ["\noriginal Req_n = \(Req_n[fix(current_instance), ..])\nlifted   Req_n = \(Req_n_lifted)\n\n"] ++ ["\noriginal carb = \(carb_instances[fix(current_instance), ..])\nlifted   carb = \(carb_lifted)\n\n"] ++ ["\noriginal carbBudget = \(carbBudget_instances[fix(current_instance)])"] ++ ["\noriginal comReq = \(comReq_instances[fix(current_instance), .., ..])\nlifted   comReq = \(comReq_lifted)\n\n"] ++ ["\noriginal cost = \(cost_instances[fix(current_instance), .., ..])\nlifted   cost = \(cost_lifted)\n\n"] ++ ["\noriginal costBudget = \(costBudget_instances[fix(current_instance)])"] ++ ["\noriginal depReq = \(depReq_instances[fix(current_instance), .., .., .., ..])\nlifted   depReq = \(depReq_lifted)\n\n"] ++ ["\noriginal energy = \(energy_instances[fix(current_instance), ..])\nlifted   energy = \(energy_lifted)\n\n"] ++ ["\noriginal energy_dependency = \(energy_dependency_instances[fix(current_instance), .., .., ..])\nlifted   energy_dependency = \(energy_dependency_lifted)\n\n"] ++ ["\noriginal linkCap = \(linkCap_instances[fix(current_instance), .., .., ..])\nlifted   linkCap = \(linkCap_lifted)\n\n"] ++ ["\noriginal nodeCap = \(nodeCap_instances[fix(current_instance), .., ..])\nlifted   nodeCap = \(nodeCap_lifted)\n\n"];


array[Nodes0] of 0..10: carb;
array[instances, Nodes0] of 0..10: carb_instances = array2d(instances, Nodes0, carb);

int: carbBudget;
array[instances] of int: carbBudget_instances = [carbBudget];

array[CompFlavs, Res] of MIN_RBOUNDS..MAX_RBOUNDS: comReq;
array[instances, CompFlavs, Res] of MIN_RBOUNDS..MAX_RBOUNDS: comReq_instances = array3d(instances, CompFlavs, Res, comReq);

array[Nodes0, Res] of 0..10: cost;
array[instances, Nodes0, Res] of 0..10: cost_instances = array3d(instances, Nodes0, Res, cost);

int: costBudget;
array[instances] of int: costBudget_instances = [costBudget];

array[Comps, Flavs, Comps, Res] of MIN_RBOUNDS..MAX_RBOUNDS: depReq;
array[instances, Comps, Flavs, Comps, Res] of MIN_RBOUNDS..MAX_RBOUNDS: depReq_instances = array5d(instances, Comps, Flavs, Comps, Res, depReq);

array[CompFlavs] of 0..1000: energy;
array[instances, CompFlavs] of 0..1000: energy_instances = array2d(instances, CompFlavs, energy);

array[Comps, Flavs, Comps] of 0..1000: energy_dependency;
array[instances, Comps, Flavs, Comps] of 0..1000: energy_dependency_instances = array4d(instances, Comps, Flavs, Comps, energy_dependency);

array[Nodes0, Nodes0, Res] of MIN_RBOUNDS..MAX_RBOUNDS: linkCap;
array[instances, Nodes0, Nodes0, Res] of MIN_RBOUNDS..MAX_RBOUNDS: linkCap_instances = array4d(instances, Nodes0, Nodes0, Res, linkCap);

array[Nodes0, Res] of MIN_RBOUNDS..MAX_RBOUNDS: nodeCap;
array[instances, Nodes0, Res] of MIN_RBOUNDS..MAX_RBOUNDS: nodeCap_instances = array3d(instances, Nodes0, Res, nodeCap);
