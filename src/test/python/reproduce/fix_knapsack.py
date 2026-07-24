#!/usr/bin/env python3

from pathlib import Path

def load(path: Path):
    namespace = {}
    code = Path(path).read_text(encoding="utf-8")
    code = code.replace(";", "\n")
    exec(compile(code, path, "exec"), {}, namespace)

    return {name: value for name, value in namespace.items()}

for dzn_file in Path(".").glob("*.dzn"):
    minizinc_vars = load(dzn_file)
    old_dzn = Path(dzn_file).read_text(encoding="utf-8")

    modified = False
    n = minizinc_vars["n"]
    for label in ["size", "value"]:
        value = minizinc_vars[label]

        if len(value) != n:
            print(f"Instance {dzn_file} has {len(value)} elements in {label} while declaring {n}")

            modified = True
            if len(value) < n:
                value.extend(int(sum(value) / n) for _ in range(n - len(value)))
            elif len(value) > n:
                minizinc_vars[label] = value[:100]

    if modified:
        new_text = ""
        new_text += "\n".join(
            "% " + l
            for l in old_dzn.split("\n")
        ) + "\n\n"
        new_text += ";\n".join(
            str(n) + " = " + str(v)
            for n, v in minizinc_vars.items()
        ) + ";\n"

        dzn_file.write_text(new_text, encoding="utf-8")

    minizinc_vars.clear()
    del minizinc_vars
