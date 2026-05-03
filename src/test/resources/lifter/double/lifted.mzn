int: a = 1;
int: b = 2;

var int: a_lifted;
var 0..1: b_lifted;
solve minimize abs(a_lifted - a) + abs(b_lifted - b);
output ["\n\n"] ++ ["a = \(a_lifted)", "\n", "b = \(b_lifted)", "\n"];
