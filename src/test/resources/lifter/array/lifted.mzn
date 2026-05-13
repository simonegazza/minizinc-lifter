array[int] of int: a;

a = [1];

array[int] of var int: a_lifted;
solve minimize sum([abs(a_lifted[i0] - a[i0]) | i0 in index_set(a)]);
output ["\n\n"] ++ ["a = \(a_lifted)", "\n"];
