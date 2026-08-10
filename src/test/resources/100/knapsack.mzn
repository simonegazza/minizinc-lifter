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


int: n;                          % The number of items.
int: k;                          % The size of the knapsack.

array[1..n] of var int: size_lifted :: doc_comment("size_lifted");       % size[i] is the size of item i.

array[1..n] of var int: value_lifted :: doc_comment("value_lifted");      % value[i] is the value of item i.

array [1..n] of var 0..1: x;
                                % x[i] = 1 iff item i is included.
var 0..10000: v;

constraint sum (i in 1..n) (size_lifted[i] * x[i]) <= k;
constraint sum (i in 1..n) (value_lifted[i] * x[i]) >= v;

solve :: int_search(x, input_order, indomain_max, complete) maximize v;





include "experimental/assume/fzn_assume.mzn";

int: max_instances = 1;
set of int: instances = 1..max_instances;
var instances: current_instance;

array[int] of array[int] of int: params_int = [
	[size_instances[i, i0] | i0 in 1..n]
	 ++ [value_instances[i, i0] | i0 in 1..n]
	| i in instances];

array[int] of var int: params_int_lifted =
	[size_lifted[i0] | i0 in 1..n]
	 ++ [value_lifted[i0] | i0 in 1..n];

array[int] of array[int] of var bool: assumed = [
	[params_int_lifted[i0] = params_int[i][i0] | i0 in index_set(params_int_lifted)]
	| i in instances];

constraint nogood_learning(
	[assumed[i][i0] | i in instances, i0 in index_set(assumed[i])],
	length(instances),
	current_instance
);

array[instances] of var int: objective_lifted :: output_var = [
	sum([abs(size_lifted[i0] - size_instances[i, i0]) | i0 in 1..n])
	+ sum([abs(value_lifted[i0] - value_instances[i, i0]) | i0 in 1..n])
	| i in instances
];



output ["current_instance = \(current_instance)"] ++ ["\noriginal size = \(size_instances[fix(current_instance), ..])\nlifted   size = \(size_lifted)\n\n"] ++ ["\noriginal value = \(value_instances[fix(current_instance), ..])\nlifted   value = \(value_lifted)\n\n"];


array[1..n] of int: size;
array[instances, 1..n] of int: size_instances = array2d(instances, 1..n, size);

array[1..n] of int: value;
array[instances, 1..n] of int: value_instances = array2d(instances, 1..n, size);
