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

int: m;  % Rows
int: n;  % Columns

set of int: Rows    = 1..m;
set of int: Columns = 1..n;

	% Intensity matrix

array[Rows, Columns] of var int: Intensity_lifted :: doc_comment("Intensity_lifted");

set of int: BTimes = 1..Bt_max;

int: Bt_max;

%-----------------------------------------------------------------------------%
% Variables
%-----------------------------------------------------------------------------%

	% Total beam-on time
int: Beamtime;

	% Number of shape matrices
var 0..Beamtime: K;

	% N[b] is the number of shape matrices with associated beam-on time b
array[BTimes] of var 0..m*n: N;

	% Q[i,j,b] is the number of shape matrices with associated beam-on time
	% b that expose cell (i,j)
array[Rows, Columns, BTimes] of var 0..m*n: Q;

%-----------------------------------------------------------------------------%
% Constraints
%-----------------------------------------------------------------------------%

constraint
	Beamtime = sum(b in BTimes) (b * N[b])
	/\
	K = sum(b in BTimes) (N[b])
	/\
	forall(i in Rows, j in Columns)
		( Intensity_lifted[i,j] = sum([b * Q[i,j,b] | b in BTimes]) )
	/\
	forall(i in Rows, b in BTimes)
		( upper_bound_on_increments(N[b], [Q[i,j,b] | j in Columns]) );


predicate upper_bound_on_increments(var int: N_b, array[int] of var int: L) =
	N_b >= L[1] + sum([ max(L[j] - L[j-1], 0) | j in 2..n ]);


%-----------------------------------------------------------------------------%
% Objective
%-----------------------------------------------------------------------------%

solve :: int_search(
		N ++
		[Q[i,j,b] | i in Rows, j in Columns, b in BTimes ],
		input_order, indomain_split, complete)
	minimize K;

%-----------------------------------------------------------------------------%
%-----------------------------------------------------------------------------%




include "experimental/assume/fzn_assume.mzn";

int: max_instances = 1;
set of int: instances = 1..max_instances;
var instances: current_instance;

array[int] of array[int] of int: params_int = [
	[Intensity_instances[i, i0, i1] | i0 in Rows, i1 in Columns]
	| i in instances];

array[int] of var int: params_int_lifted =
	[Intensity_lifted[i0, i1] | i0 in Rows, i1 in Columns];

array[int] of array[int] of var bool: assumed = [
	[params_int_lifted[i0] = params_int[i][i0] | i0 in index_set(params_int_lifted)]
	| i in instances];

constraint nogood_learning(
	[assumed[i][i0] | i in instances, i0 in index_set(assumed[i])],
	length(instances),
	current_instance
);

array[instances] of var int: objective_lifted :: output_var = [
	sum([abs(Intensity_lifted[i0, i1] - Intensity_instances[i, i0, i1]) | i0 in Rows, i1 in Columns])
	| i in instances
];



output ["current_instance = \(current_instance)"] ++ ["\noriginal Intensity = \(Intensity_instances[fix(current_instance), .., ..])\nlifted   Intensity = \(Intensity_lifted)\n\n"];


array[1..15, 1..12,] of int: Intensity;
array[instances, 1..15, 1..12] of int: Intensity_instances = array3d(instances, 1..15, 1..12, Intensity);
