package me.simonegazza.lift.visitors;

import java.util.ArrayList;
import java.util.List;
import me.simonegazza.antlr.minizinc.MiniZincBaseVisitor;
import me.simonegazza.antlr.minizinc.MiniZincParser.ArrayTiExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.BaseTiExprContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.BaseTiExprTailContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.BaseTypeContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.IdentContext;
import me.simonegazza.antlr.minizinc.MiniZincParser.TiExprContext;
import me.simonegazza.lift.expressions.MiniZincIdentifier;
import me.simonegazza.lift.types.MiniZincArrayType;
import me.simonegazza.lift.types.MiniZincBasicType;
import me.simonegazza.lift.types.MiniZincCompositeType;
import me.simonegazza.lift.types.MiniZincEnumType;
import me.simonegazza.lift.types.MiniZincExpressionType;
import me.simonegazza.lift.types.MiniZincNamedType;
import me.simonegazza.lift.types.MiniZincSetType;
import me.simonegazza.lift.types.MiniZincType;
import me.simonegazza.lift.utils.exception.UnimplementedException;

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
		if (ctx.baseTiExpr() != null)
			throw new UnimplementedException("Found a \"++\" symbol after type. This is not implemented");

		if (ctx.getText().contains("set of")) {
			MiniZincType innerType = visitBaseTiExprTail(ctx.baseTiExprTail());
			List<MiniZincNamedType> ids = new ArrayList<>();
			if (innerType instanceof MiniZincNamedType)
				ids.add((MiniZincNamedType) innerType);
			return new MiniZincSetType(ids);
		} else
			return visitBaseTiExprTail(ctx.baseTiExprTail());
	}

	@Override
	public MiniZincType visitArrayTiExpr(ArrayTiExprContext ctx) {
		List<String> ranges = new ArrayList<>();
		List<MiniZincNamedType> identifiers = new ArrayList<>();
		if (ctx.tiExpr() != null) {
			for (TiExprContext range : ctx.tiExpr()) {
				ranges.add(range.getText());

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

				if (rangeType instanceof MiniZincNamedType)
					identifiers.add((MiniZincNamedType) rangeType);
				// It is not necessary to do anything in case of a base type
			}
		}

		return new MiniZincArrayType(
			identifiers,
			ranges,
			visitBaseTiExpr(ctx.baseTiExpr()));
	}

	@Override
	public MiniZincType visitBaseTiExprTail(BaseTiExprTailContext ctx) {
		if (ctx.ident() != null)
			return new MiniZincEnumType(ctx.ident().getText());
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

	private class TypeExpressionVisitor extends MiniZincBaseVisitor<Void> {
		private final MiniZincExpressionType et;

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
