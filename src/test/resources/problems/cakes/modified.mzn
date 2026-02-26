var 0..100: b; % no. of banana cakes
var 0..100: c; % no. of chocolate cakes

% flour
var int: flour_b = 200;
var int: flour_c = 250;
var int: flour_budget = 4000;
constraint flour_b * b + flour_c * c <= flour_budget;

% bananas
var int: bananas_b = 2;
var int: bananas_c = 0;
var int: bananas_budget = 6;
constraint bananas_b * b + bananas_c * c <= bananas_budget;

% sugar
var int: sugar_b = 75;
var int: sugar_c = 150;
var int: sugar_budget = 2000;
constraint sugar_b * b + sugar_c * c <= sugar_budget;

% butter
var int: butter_b = 100;
var int: butter_c = 150;
var int: butter_budget = 500;
constraint butter_b * b + butter_c * c <= butter_budget;

% cocoa
var int: cocoa_b = 0;
var int: cocoa_c = 75;
var int: cocoa_budget = 500;
constraint cocoa_b * b + cocoa_c * c <= cocoa_budget;

% maximize our profit
var int: profit_b = 400;
var int: profit_c = 450;
solve maximize profit_b * b + profit_c * c;

output ["no. of banana cakes = \(b)\n",
         "no. of chocolate cakes = \(c)\n"];
