predicate global_cardinality(
    array [$X] of var $$E: x,
    array [int] of var $$E: cover,
    array [int] of var int: lbound,
    array [int] of var int: ubound
) = forall(i in 1..length(cover))(
    sum([e = cover[i] | e in x]) in lbound[i]..ubound[i]
);

predicate cumulative(
    array[$$E] of var opt int: s,
    array[$$E] of var opt int: d,
    array[$$E] of var opt int: r,
    var int: b
) = assert(
    % They all must have same length
    index_set(s) == index_set(d) /\ index_set(s) == index_set(r),
    "cumulative redefinition: arrays must have identical index sets",

    let {
        array[index_set(s)] of var 0..ub(max(d)): d_deopt;
        array[index_set(s)] of var 0..ub(max(r)): r_deopt;
    } in
    % If s occurs than also d and r must occur (d and r may be non-optional,
    % so an absent task must not force them to be absent)
    forall(i in index_set(s))(occurs(s[i]) -> (occurs(d[i]) /\ occurs(r[i])))
    /\
    % "channeling" from d to define d_deopt
    forall(i in index_set(s))(
        if occurs(d[i]) then d_deopt[i] = deopt(d[i]) else d_deopt[i] = 0 endif)
    /\
    % "channeling" from r to define r_deopt
    forall(i in index_set(s))(
        if occurs(r[i]) then r_deopt[i] = deopt(r[i]) else r_deopt[i] = 0 endif)
    /\
    % Nonnegativity only when present
    forall(i in index_set(s))((occurs(d[i]) -> d[i] >= 0) /\ (occurs(r[i]) -> r[i] >= 0))
    /\
    cumulative(s, d_deopt, r_deopt, b)
);
