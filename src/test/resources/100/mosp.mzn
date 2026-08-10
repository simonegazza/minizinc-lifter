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

include "globals.mzn";

int: c; % number of customers

set of int: Custs = 1..c;


array[Custs, Custs] of var bool: graph_lifted :: doc_comment("graph_lifted"); % graph describing which pairs of customers share at least one product

array[Custs] of var Custs: s;                  % customer start time
array[Custs] of var Custs: e;     % customer end time
var Custs: stacks;                % number of stacks required

constraint all_different(e);

constraint forall (i, j in Custs where graph_lifted[i,j]) (s[i] <= e[j]);

constraint forall (t in Custs) (stacks >= sum (i in Custs) (bool2int((s[i] <= t) /\ (e[i] >= t))));

solve :: int_search(e, smallest, indomain_min, complete) minimize stacks;





include "experimental/assume/fzn_assume.mzn";

int: max_instances = 1;
set of int: instances = 1..max_instances;
var instances: current_instance;

array[int] of array[int] of bool: params_bool = [
	[graph_instances[i, i0, i1] | i0 in Custs, i1 in Custs]
	| i in instances];

array[int] of var bool: params_bool_lifted =
	[graph_lifted[i0, i1] | i0 in Custs, i1 in Custs];

array[int] of array[int] of var bool: assumed = [
	[params_bool_lifted[i0] = params_bool[i][i0] | i0 in index_set(params_bool_lifted)]
	| i in instances];

constraint nogood_learning(
	[assumed[i][i0] | i in instances, i0 in index_set(assumed[i])],
	length(instances),
	current_instance
);

array[instances] of var int: objective_lifted :: output_var = [
	sum([abs(graph_lifted[i0, i1] - graph_instances[i, i0, i1]) | i0 in Custs, i1 in Custs])
	| i in instances
];



output ["current_instance = \(current_instance)"] ++ ["\noriginal graph = \(graph_instances[fix(current_instance), .., ..])\nlifted   graph = \(graph_lifted)\n\n"];


array[1..35, 1..35] of bool: graph;
array[instances, 1..35, 1..35] of bool: graph_instances = array3d(instances, 1..35, 1..35, graph);
