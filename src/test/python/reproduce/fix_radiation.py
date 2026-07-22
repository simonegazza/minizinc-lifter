#!/usr/bin/env python3

from pathlib import Path
import re

def extract_bracket_content(text):
    """
    Extracts the full [...] content after Intensity = array2d(...)
    using a bracket depth counter (robust for large arrays).
    """
    start = text.find("Intensity")
    if start == -1:
        return None

    # Find first '[' after Intensity
    open_bracket = text.find("[", start)
    if open_bracket == -1:
        return None

    depth = 0
    for i in range(open_bracket, len(text)):
        if text[i] == "[":
            depth += 1
        elif text[i] == "]":
            depth -= 1
            if depth == 0:
                return text[open_bracket:i+1]

    return None


for dzn_file in Path(".").glob("*.dzn"):
    text = dzn_file.read_text(encoding="utf-8")

    bracket_str = extract_bracket_content(text)

    if not bracket_str:
        print(f"Skipping {dzn_file}: Intensity array not found")
        continue

    # Extract all integers (handles large arrays safely)
    values = list(map(int, re.findall(r"-?\d+", bracket_str)))

    if not values:
        print(f"Skipping {dzn_file}: no integers found")
        continue

    bt_max = max(values)

    with dzn_file.open("a", encoding="utf-8") as f:
        f.write(f"\nBt_max = {bt_max};")

    print(f"{dzn_file}: Bt_max = {bt_max}")