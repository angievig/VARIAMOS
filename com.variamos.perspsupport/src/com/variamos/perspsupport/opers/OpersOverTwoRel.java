package com.variamos.perspsupport.opers;

import java.util.List;

import com.variamos.hlcl.RangeDomain;
import com.variamos.perspsupport.opersint.IntOpersOverTwoRel;
import com.variamos.perspsupport.opersint.IntOpersRelType;
import com.variamos.perspsupport.syntaxsupport.AbstractAttribute;
import com.variamos.perspsupport.syntaxsupport.SemanticAttribute;
import com.variamos.semantic.types.AttributeType;

/**
 * A class to represent relations of more than two concepts at semantic level.
 * Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Mu�oz Fern�ndez <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-23
 * @see com.cfm.productline.
 */
public class OpersOverTwoRel extends OpersAbstractVertex implements
		IntOpersOverTwoRel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6309224856276191013L;
	private boolean exclusive;
	public static final String VAR_RELATIONTYPE_IDEN = "relationType",
			VAR_RELATIONTYPE_NAME = "Relation Type",
			VAR_RELATIONTYPE_CLASS = OpersRelType.class
					.getCanonicalName();

	// private ConditionalExpression conditionalExpression;
	private List<IntOpersRelType> semanticRelationTypes;

	public OpersOverTwoRel() {
	}

	public OpersOverTwoRel(String identifier,
			List<IntOpersRelType> list) {
		super(identifier);
		this.semanticRelationTypes = list;
		defineSemanticAttributes();
	}

	public OpersOverTwoRel(OpersAbstractVertex parent,
			String identifier, boolean exclusive,
			List<IntOpersRelType> semanticRelationTypes) {
		super(parent, identifier);
		this.semanticRelationTypes = semanticRelationTypes;
		this.exclusive = exclusive;
		defineSemanticAttributes();
	}

	public OpersOverTwoRel(OpersAbstractVertex parent,
			String identifier,
			List<IntOpersRelType> semanticRelationTypes) {
		this(parent, identifier, false, semanticRelationTypes);
	}

	public OpersOverTwoRel(String identifier, boolean exclusive,
			List<IntOpersRelType> semanticRelationTypes) {
		this(identifier, exclusive, semanticRelationTypes, false);
		defineSemanticAttributes();
	}

	public OpersOverTwoRel(String identifier, boolean exclusive,
			boolean conditionalExpression) {
		this(identifier, exclusive, null, conditionalExpression);
		defineSemanticAttributes();
	}

	public OpersOverTwoRel(String identifier, boolean exclusive,
			List<IntOpersRelType> semanticRelationTypes,
			boolean conditionalExpression) {
		super(identifier, true);
		this.exclusive = exclusive;
		this.semanticRelationTypes = semanticRelationTypes;
		// this.conditionalExpression = conditionalExpression;
		defineSemanticAttributes();
	}

	private void defineSemanticAttributes() {
		putSemanticAttribute(VAR_RELATIONTYPE_IDEN, new SemanticAttribute(
				VAR_RELATIONTYPE_IDEN, "Class", AttributeType.OPERATION, true,
				VAR_RELATIONTYPE_NAME, VAR_RELATIONTYPE_CLASS, null, null, 0,
				6, "", "", 6, "", ""));
		addPropEditableAttribute("06#" + VAR_RELATIONTYPE_IDEN);
		addPropVisibleAttribute("06#" + VAR_RELATIONTYPE_IDEN);
		addPanelVisibleAttribute("06#" + VAR_RELATIONTYPE_IDEN);
		addPanelSpacersAttribute("#" + VAR_RELATIONTYPE_IDEN + "#");

		putSemanticAttribute("LowRange", new SemanticAttribute("LowRange",
				"Integer", AttributeType.OPERATION, "Low Range", 1, false,
				new RangeDomain(0, 50), 0, 6, "", "", 6, "", ""));
		addPropEditableAttribute("08#" + "LowRange");
		addPropVisibleAttribute("08#" + "LowRange" + "#"
				+ VAR_RELATIONTYPE_IDEN + "#==#" + "range" + "#" + "1");
		addPanelVisibleAttribute("08#" + "LowRange" + "#"
				+ VAR_RELATIONTYPE_IDEN + "#==#" + "range");
		addPanelSpacersAttribute(" [#" + "LowRange" + "#");

		putSemanticAttribute("HighRange", new SemanticAttribute("HighRange",
				"Integer", AttributeType.OPERATION, "High Range", 1, false,
				new RangeDomain(0, 50), 0, 6, "", "", 6, "", ""));
		addPropEditableAttribute("09#" + "HighRange");
		addPropVisibleAttribute("09#" + "HighRange" + "#"
				+ VAR_RELATIONTYPE_IDEN + "#==#" + "range" + "#" + "1");
		addPanelVisibleAttribute("09#" + "HighRange" + "#"
				+ VAR_RELATIONTYPE_IDEN + "#==#" + "range");
		addPanelSpacersAttribute("-#" + "HighRange" + "#]");
	}

	public boolean isExclusive() {
		return exclusive;
	}

	public void setExclusive(boolean exclusive) {
		this.exclusive = exclusive;
	}

	// public ConditionalExpression getConditionalExpression() {
	// return conditionalExpression;
	// }

	// public void setConditionalExpression(
	// ConditionalExpression conditionalExpression) {
	// this.conditionalExpression = conditionalExpression;
	// }

	public AbstractAttribute getVariable(String name) {
		return getSemanticAttribute(name);
	}

	public String toString() {
		return " Exc:" + exclusive + "throughDep: "/* + throughDep.getType() */;
	}

	@Override
	public List<IntOpersRelType> getSemanticRelationTypes() {
		return semanticRelationTypes;
	}

	public Object getAllSemanticExpressions(String value) {

		return null;
	}
}