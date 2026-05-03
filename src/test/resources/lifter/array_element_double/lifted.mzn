array[int] of int: a = [1, 2, 3];

array[int] of var int: a_lifted = array1d(int, [_, 2, _]);
solve minimize abs(a_lifted[1] - a[1]) + abs(a_lifted[3] - a[3]);
output ["\n\n"] ++ ["a = \(a_lifted)", "\n"];
