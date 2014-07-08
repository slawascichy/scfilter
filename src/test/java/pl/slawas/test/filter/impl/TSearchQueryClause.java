package pl.slawas.test.filter.impl;

import java.util.Map;
import java.util.Set;

import pl.slawas.filter.OperationType;
import pl.slawas.filter.SearchQueryClause;
import pl.slawas.filter.beans.Accuracy;
import pl.slawas.filter.beans.IndexFieldDefinition;

/**
 * 
 * TQuery
 * 
 * @author Slawomir Cichy &lt;slawas@slawas.pl&gt;
 * @version $Revision: 1.2 $
 * 
 */
public class TSearchQueryClause extends
		SearchQueryClause<TSearchQueryClause, TQueryCondition> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 838757467411432311L;

	public TSearchQueryClause(Map<String, IndexFieldDefinition> paramsMap) {
		super(paramsMap);
	}

	@Override
	protected void addEQQueryClause(String fieldName, String fieldValue,
			Accuracy accuracy) {
		this.addClause(new TQueryCondition(OperationType.EQ, fieldName,
				new String[] { fieldValue, null }));
	}

	@Override
	protected void addGEQQueryClause(String fieldName, String fieldValue,
			Accuracy accuracy) {
		this.addClause(new TQueryCondition(OperationType.GEQ, fieldName,
				new String[] { fieldValue, null }));
	}

	@Override
	protected void addGTQueryClause(String fieldName, String fieldValue,
			Accuracy accuracy) {
		this.addClause(new TQueryCondition(OperationType.GT, fieldName,
				new String[] { fieldValue, null }));
	}

	@Override
	protected void addLEQQueryClause(String fieldName, String fieldValue,
			Accuracy accuracy) {
		this.addClause(new TQueryCondition(OperationType.LEQ, fieldName,
				new String[] { fieldValue, null }));

	}

	@Override
	protected void addLTQueryClause(String fieldName, String fieldValue,
			Accuracy accuracy) {
		this.addClause(new TQueryCondition(OperationType.LT, fieldName,
				new String[] { fieldValue, null }));
	}

	@Override
	protected void addRSEQueryClause(String fieldName, String fieldValueFrom,
			String fieldValueTo, Accuracy accuracy) {
		this.addClause(new TQueryCondition(OperationType.RSE, fieldName,
				new String[] { fieldValueFrom, fieldValueTo }));
	}

	@Override
	protected void addRSIQueryClause(String fieldName, String fieldValueFrom,
			String fieldValueTo, Accuracy accuracy) {
		this.addClause(new TQueryCondition(OperationType.RSI, fieldName,
				new String[] { fieldValueFrom, fieldValueTo }));
	}

	@Override
	protected void addWSQueryClause(String fieldName, String fieldValue,
			Accuracy accuracy) {
		this.addClause(new TQueryCondition(OperationType.WS, fieldName,
				new String[] { fieldValue, null }));
	}

	@Override
	public TSearchQueryClause getQueryClause() {
		return this;
	}

	@Override
	protected SearchQueryClause<TSearchQueryClause, TQueryCondition> createNewInstace(
			Map<String, IndexFieldDefinition> paramsMap) {
		return new TSearchQueryClause(paramsMap);
	}

	@Override
	public SearchQueryClause<TSearchQueryClause, TQueryCondition> addSubClauses(
			Set<TQueryCondition> subClauses, Accuracy accuracy) {
		throw new UnsupportedOperationException("Brak implementacji podzapytań");
	}

	@Override
	protected void addSimpleQueryClause(String fieldName, String fieldValue,
			Accuracy accuracy) {
		this.addClause(new TQueryCondition(OperationType.EQ, fieldName,
				new String[] { fieldValue, null }));
	}

}
