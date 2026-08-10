include "globals.mzn";

int: n; % nodes
set of int: Nodes = 1..n;
set of int: Colours = 1..n;

array[1..n, 1..n] of bool: graph;

array[1..n] of var Colours: x;
var Colours: colours = max(x);

constraint forall(i, j in 1..n where i != j)(
    graph[i, j] -> x[i] != x[j]
);

constraint value_precede_chain(Colours, x);

solve minimize colours;
