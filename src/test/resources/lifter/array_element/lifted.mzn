array[int] of int: a = [1, 2];

array[int] of var int: a_lifted;
a_lifted = array1d(index_set(a), [_, 2]);
solve minimize abs(a_lifted[1] - a[1]);
output ["\n\n"] ++ ["a = \(a_lifted)", "\n"];
