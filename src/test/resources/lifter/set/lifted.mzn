set of 0..1: a = {1};

var set of 0..1: a_lifted;
solve minimize (card(a_lifted symdiff a));
output ["\n\n"] ++ ["a = \(a_lifted)", "\n"];
