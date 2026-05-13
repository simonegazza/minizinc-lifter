array[int, int] of int: a = [|1, 2 | 3, 4|];

array[int, int] of var int: a_lifted = array2d(index_set_1of2(a), index_set_2of2(a), [1, 2, 3, _]);
solve minimize abs(a_lifted[2, 2] - a[2, 2]);
output ["\n\n"] ++ ["a = \(a_lifted)", "\n"];
