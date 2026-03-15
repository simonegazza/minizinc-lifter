array[int] of int: a = [1];

array[int] of var int: a_lifted;
solve minimize sum([abs(a_lifted[i0] - a[i0]) | i0 in int]);
output ["a = \(a_lifted)", "\n"];
