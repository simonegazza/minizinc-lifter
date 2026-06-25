include "globals.mzn";

int: c; % number of customers

set of int: Custs = 1..c;

array[Custs,Custs] of bool: graph; % graph describing which pairs of customers share at least one product

array[Custs] of var Custs: s;                  % customer start time
array[Custs] of var Custs: e;     % customer end time
var Custs: stacks;                % number of stacks required

constraint all_different(e);

constraint forall (i, j in Custs where graph[i,j]) (s[i] <= e[j]);

constraint forall (t in Custs) (stacks >= sum (i in Custs) (bool2int((s[i] <= t) /\ (e[i] >= t))));

solve :: int_search(e, smallest, indomain_min, complete) minimize stacks;
