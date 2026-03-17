array[int, int] of int: a = [|1, 2 | 3, 4|];

array[int, int] of var int: a_lifted = array2d(int, int, [1, 2, 3, _]);
solve minimize abs(a_lifted[2, 2] - a[2, 2]);
output ["a = \(a_lifted)", "\n"];
