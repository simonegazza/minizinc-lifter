int: a = 1;

var int: a_lifted;
solve minimize abs(a_lifted - a);
output ["a = \(a_lifted)", "\n"];
