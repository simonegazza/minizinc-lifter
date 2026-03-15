set of 0..1: a = 1;

var set of 0..1: a_lifted;
solve minimize abs(a_lifted - a);
output ["a = \(a_lifted)", "\n"];
