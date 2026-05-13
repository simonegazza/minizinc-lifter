int: max;

array[1..5] of set of 1..5: sets = [
  {max | a in 1..max} | _ in 1..5
];

max = 5;
