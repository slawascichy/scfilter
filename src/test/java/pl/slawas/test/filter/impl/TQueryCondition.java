package pl.slawas.test.filter.impl;

import java.io.Serializable;
import java.util.Arrays;

import pl.slawas.filter.OperationType;

public class TQueryCondition implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8084266788954382598L;

	private OperationType opType;

	private String fieldName;

	private String[] values;

	/**
	 * @return the opType
	 */
	OperationType getOpType() {
		return opType;
	}

	/**
	 * @param opType
	 *           the opType to set
	 */
	void setOpType(OperationType opType) {
		this.opType = opType;
	}

	/**
	 * @return the values
	 */
	String[] getValues() {
		return values;
	}

	/**
	 * @param values
	 *           the values to set
	 */
	void setValues(String[] values) {
		this.values = values;
	}

	/**
	 * @return the fieldName
	 */
	String getFieldName() {
		return fieldName;
	}

	/**
	 * @param fieldName
	 *           the fieldName to set
	 */
	void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	@Override
	public String toString() {

		try {
			String fieldValue = values[0];
			String fieldValueFrom = values[0];
			String fieldValueTo = values[1];

			switch (opType) {
			case EQ:
				return fieldName + ":= \"" + fieldValue + "\"";
			case GEQ:
				return fieldName + ":>= \"" + fieldValue + "\"";
			case GT:
				return fieldName + ":> \"" + fieldValue + "\"";
			case LEQ:
				return fieldName + ":<= \"" + fieldValue + "\"";
			case LT:
				return fieldName + ":< \"" + fieldValue + "\"";
			case RSE:
				return fieldName + ":< \"" + fieldValueFrom + "\" \"" + fieldValueTo + "\"";
			case RSI:
				return fieldName + ":<= \"" + fieldValueFrom + "\" \"" + fieldValueTo + "\"";
			case WS:
				return fieldName + ":~ \"" + fieldValue + "\"";
			default:
				break;
			}
		} catch (Exception e) {
			return e.getMessage();
		}

		return super.toString();
	}

	/**
	 * @param opType
	 * @param fieldName
	 * @param values
	 */
	public TQueryCondition(OperationType opType, String fieldName, String[] values) {
		super();
		this.opType = opType;
		this.fieldName = fieldName;
		this.values = values;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fieldName == null) ? 0 : fieldName.hashCode());
		result = prime * result + ((opType == null) ? 0 : opType.hashCode());
		result = prime * result + Arrays.hashCode(values);
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TQueryCondition other = (TQueryCondition) obj;
		if (fieldName == null) {
			if (other.fieldName != null)
				return false;
		} else if (!fieldName.equals(other.fieldName))
			return false;
		if (opType == null) {
			if (other.opType != null)
				return false;
		} else if (!opType.equals(other.opType))
			return false;
		if (!Arrays.equals(values, other.values))
			return false;
		return true;
	}

	
}
