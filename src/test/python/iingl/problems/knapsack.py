import random

NUM_ITEMS = 35
MIN_VALUE = 1
MAX_VALUE = 10


def generate_base_instance():
    size = [random.randint(MIN_VALUE, MAX_VALUE) for _ in range(NUM_ITEMS)]
    value = [random.randint(MIN_VALUE, MAX_VALUE) for _ in range(NUM_ITEMS)]
    return size, value

def apply_changes(size, value, percentage):
    new_size = list(size)
    new_value = list(value)

    total_elements = NUM_ITEMS * 2
    num_changes = max(1, round(total_elements * percentage / 100))

    positions = random.sample(range(total_elements), num_changes)

    for pos in positions:
        if pos < NUM_ITEMS:
            current = new_size[pos]
            choices = [v for v in range(MIN_VALUE, MAX_VALUE + 1) if v != current]
            new_size[pos] = random.choice(choices)
        else:
            idx = pos - NUM_ITEMS
            current = new_value[idx]
            choices = [v for v in range(MIN_VALUE, MAX_VALUE + 1) if v != current]
            new_value[idx] = random.choice(choices)

    return new_size, new_value

def write_dzn(path, size, value):
    content = (
        f"n = {NUM_ITEMS};\n"
        f"k = 100;\n\n"
        f"size = {size};\n"
        f"value = {value};\n"
    )
    path.write_text(content, encoding="utf-8")

def generate_percentage_folder(base_dir, percentage, n_instances):
    folder = base_dir / str(percentage)
    folder.mkdir(parents=True, exist_ok=True)

    size, value = generate_base_instance()
    write_dzn(folder / "00.dzn", size, value)

    current_size = size
    current_value = value

    for i in range(1, n_instances):
        current_size, current_value = apply_changes(
            current_size, current_value, percentage
        )
        write_dzn(folder / f"{i:02d}.dzn", current_size, current_value)
