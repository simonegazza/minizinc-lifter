include "globals.mzn";

int: n; % number of nodes

set of int: Nodes = 1..n;
set of int: Colours = 1..n;

array[Nodes,Nodes] of bool: graph; % adjacency matrix of graph

array[Nodes] of var Colours: x;                  % colour of node
var Colours: colours;               % number of colours required

constraint forall (i, j in Nodes where graph[i,j] /\ i != j) (x[i] != x[j]);

constraint forall (i in Nodes) (colours >= x[i]);

include "value_precede_chain.mzn";

constraint value_precede_chain(Colours, x);

solve minimize colours;
