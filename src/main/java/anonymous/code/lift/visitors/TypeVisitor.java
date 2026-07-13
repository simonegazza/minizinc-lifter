package anonymous.code.lift.visitors;

import anonymous.code.antlr.minizinc.MiniZincBaseVisitor;
import anonymous.code.antlr.minizinc.MiniZincParser.ArrayTiExprContext;
import anonymous.code.antlr.minizinc.MiniZincParser.BaseTiExprContext;
import anonymous.code.antlr.minizinc.MiniZincParser.BaseTiExprTailContext;
import anonymous.code.antlr.minizinc.MiniZincParser.BaseTypeContext;
import anonymous.code.antlr.minizinc.MiniZincParser.IdentContext;
import anonymous.code.antlr.minizinc.MiniZincParser.TiExprContext;
import anonymous.code.lift.expressions.MiniZincIdentifier;
import anonymous.code.lift.types.MiniZincArrayType;
import anonymous.code.lift.types.MiniZincBasicType;
import anonymous.code.lift.types.MiniZincCompositeType;
import anonymous.code.lift.types.MiniZincExpressionType;
import anonymous.code.lift.types.MiniZincSetType;
import anonymous.code.lift.types.MiniZincType;
import anonymous.code.lift.utils.exception.UnimplementedException;
import java.util.ArrayList;
import java.util.List;

class TypeVisitor extends MiniZincBaseVisitor<MiniZincType> {
	@Override
	public MiniZincType visitTiExpr(TiExprContext ctx) {
		if (ctx.arrayTiExpr() != null)
			return visitArrayTiExpr(ctx.arrayTiExpr());
		else
			return visitBaseTiExpr(ctx.baseTiExpr());
	}

	@Override
	public MiniZincType visitBaseTiExpr(BaseTiExprContext ctx) {
		if (ctx.getText().startsWith("any"))
			throw new IllegalStateException("Unimplemented type parsing for annotations and templates");

		// there's a rule in the grammar that says
		// ... baseTiExprTail ('++' baseTiExpr)?
		// but I do not think that the "'++' baseTiExpr" part is legal
		// if (ctx.baseTiExpr() != null)
		// throw new UnimplementedException("Found a \"++\" symbol after type.
		// This is not implemented");
		// After some consideration, I changed the grammar

		if (ctx.getText().contains("set of")) {
			MiniZincType innerType = visitBaseTiExprTail(ctx.baseTiExprTail());
			return new MiniZincSetType(innerType);
		} else
			return visitBaseTiExprTail(ctx.baseTiExprTail());
	}

	@Override
	public MiniZincType visitArrayTiExpr(ArrayTiExprContext ctx) {
		List<MiniZincType> dimensions = new ArrayList<>();
		if (ctx.tiExpr() != null) {
			for (TiExprContext range : ctx.tiExpr().subList(0, ctx.tiExpr().size() - 1)) {
				// Note here that the grammar consider a tiExpr in the dimension
				// expression too, even though it does not seem to be legal
				// e.g., type expression like
				// array[set of 1..3] of int
				// or
				// array[array[1..2] of 1..2] of int
				// should be legal but not accepted in play.minizinc.dev
				// I will assume that this will always lead (maybe via an expr)
				// to an ident, an enum or a base type
				MiniZincType rangeType = visitTiExpr(range);
				if (rangeType instanceof MiniZincCompositeType)
					throw new UnimplementedException(
						"Unable to parse a composite type as index for another composite type");

				dimensions.add(rangeType);
			}
		}

		return new MiniZincArrayType(
			dimensions,
			visitTiExpr(ctx.tiExpr().getLast()));
	}

	@Override
	public MiniZincType visitBaseTiExprTail(BaseTiExprTailContext ctx) {
		if (ctx.ident() != null)
			return new MiniZincIdentifier(ctx.ident().getText());
		if (ctx.baseType() != null)
			return visitBaseType(ctx.baseType());
		if (ctx.expr() != null) {
			MiniZincExpressionType idsHolder = new MiniZincExpressionType(ctx.expr());
			new TypeExpressionVisitor(idsHolder).visitExpr(ctx.expr());
			return idsHolder;
		}

		throw new UnimplementedException("Unimplemented type parsing for annotations and templates");
	}

	@Override
	public MiniZincType visitBaseType(BaseTypeContext ctx) {
		if (ctx.getText().equals("int"))
			return MiniZincBasicType.INT;
		else if (ctx.getText().equals("string"))
			return MiniZincBasicType.STRING;
		else if (ctx.getText().equals("float"))
			return MiniZincBasicType.FLOAT;
		else
			return MiniZincBasicType.BOOL;
	}

	/**
	 * Visitor used to extract identifier dependencies from type expressions.
	 * <p>
	 * This visitor does not attempt to evaluate or interpret expressions. Its
	 * sole purpose is to collect identifiers appearing inside a type definition
	 * (e.g. array bounds or dependent types).
	 * <p>
	 * Each encountered identifier is registered into the associated
	 * {@link MiniZincExpressionType}, allowing the system to track parameter
	 * dependencies without fully parsing the expression semantics.
	 * <p>
	 * This is part of the dependency analysis phase used to build the parameter
	 * graph.
	 */
	private class TypeExpressionVisitor extends MiniZincBaseVisitor<Void> {

		/**
		 * Internal expression used for visiting.
		 */
		private final MiniZincExpressionType et;

		/**
		 * Creates a visitor bound to a specific expression type.
		 *
		 * @param et the expression type where identifiers will be collected
		 */
		public TypeExpressionVisitor(MiniZincExpressionType et) {
			this.et = et;
		}

		@Override
		public Void visitIdent(IdentContext ctx) {
			et.addIdentifier(new MiniZincIdentifier(ctx.getText()));
			return null;
		}

	}

}
