array[int] of int: a = [1];

array[int] of var int: a_lifted = array1d(int, [_]);
solve minimize abs(a_lifted[1] - a[1]);
output ["\n\n"] ++ ["a = \(a_lifted)", "\n"];
