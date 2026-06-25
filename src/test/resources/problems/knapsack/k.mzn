
int: n;                          % The number of items.
int: k;                          % The size of the knapsack.
array [1..n] of int: size;       % size[i] is the size of item i.
array [1..n] of int: value;      % value[i] is the value of item i.

array [1..n] of var 0..1: x;
                                % x[i] = 1 iff item i is included.
var 0..10000: v;

constraint sum (i in 1..n) (size[i] * x[i]) <= k;
constraint sum (i in 1..n) (value[i] * x[i]) >= v;

solve :: int_search(x, input_order, indomain_max, complete) maximize v;
