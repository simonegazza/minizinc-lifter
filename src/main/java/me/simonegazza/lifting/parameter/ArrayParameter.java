package me.simonegazza.lifting.parameter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArrayParameter extends Parameter {
	List<String> dimensions;

	public ArrayParameter(String name, String elementType, Optional<String> bounds, List<String> dimensions) {
		super(name, elementType, bounds);
		this.dimensions = dimensions;
	}

	@Override
	public String getDiff() {
		List<String> indices = IntStream
			.rangeClosed(1, dimensions.size())
			.mapToObj(i -> "i" + i)
			.toList();

		String combinedIndeces = indices.stream().collect(Collectors.joining(", "));
		StringBuilder sb = new StringBuilder();
		sb.append("sum([abs(" + name + "[" + combinedIndeces + "]");
		sb.append(" - " + getLiftedName() + "[" + combinedIndeces + "])");

		String iterators = IntStream
			.range(0, dimensions.size())
			.mapToObj(i -> indices.get(i) + " in " + dimensions.get(i))
			.collect(Collectors.joining(", "));
		sb.append(" | " + iterators + "])");

		return sb.toString();
	}

	@Override
	public String getInitialDeclaration() {
		return "array[" +
			dimensions.stream().collect(Collectors.joining(", ")) +
			"] of " + type + ": " + name + ";";
	}

	@Override
	public String getLiftedDeclaration() {
		return "array[" +
			dimensions.stream().collect(Collectors.joining(", ")) +
			"] of var " + (bounds.isPresent() ? bounds.get() : type) +
			": " + getLiftedName();
	}
}
