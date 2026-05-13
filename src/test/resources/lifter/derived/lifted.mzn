int: max;

array[1..5] of set of 1..5: sets = [
  {max | a in 1..max} | _ in 1..5
];

max = 5;

array[1..5] of var set of 1..5: sets_lifted = [
  {max_lifted | a in 1..max_lifted} | _ in 1..5
];
var 1..5: max_lifted;
solve minimize sum([abs(card(sets_lifted[i0] symdiff sets[i0])) | i0 in 1..5]) + abs(max_lifted - max);
output ["\n\n"] ++ ["sets = \(sets_lifted)", "\n", "max = \(max_lifted)", "\n"];
