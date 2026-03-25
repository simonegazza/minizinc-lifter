set of 0..1: a = {1};

var set of 0..1: a_lifted;
solve minimize (card(a_lifted symdiff a) / card(a_lifted union a));
output ["a = \(a_lifted)", "\n"];
