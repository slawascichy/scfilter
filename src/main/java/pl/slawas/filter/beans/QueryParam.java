package pl.slawas.filter.beans;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import pl.slawas.filter.OperationType;
import pl.slawas.filter.SearchQueryClause;

/**
 * 
 * QueryParam - wartość pola filtra
 * 
 * @author Slawomir Cichy &lt;slawas@slawas.pl&gt;
 * @version $Revision: 1.2 $
 * 
 */
public class QueryParam implements Serializable {

	private static final long serialVersionUID = -5210221106589109534L;

	private String fieldName;

	private OperationType operationType = OperationType.WS;

	/**
	 * wartość dolnej granicy kryterium, dla kryteriów {@link OperationType#EQ}
	 * to jest to po prostu wartość kryterium
	 */
	private String from;

	/**
	 * wartość górnej granicy kryterium, dla kryteriów {@link OperationType#EQ}
	 * to wartość kryterium przyjmuje {@code null}
	 */
	private String to;

	private Accuracy accuracy = Accuracy.MUST;
	
	/**
	 * Konstruktor wartości filtra. Po użyciu tego konstruktora, trafność wyniku
	 * zostanie ustwiona na {@link Accuracy#MUST}.
	 * 
	 * @param fieldName
	 *            nazwa pola (nie może przyjmować wartość {@code null}). Nazwa
	 *            pola to nazwa pola w filtrze. Dopiero później podczas
	 *            budowania klauzuli zapytania nastąpi zmapowanie jego nazwy na
	 *            nazwę pola w indeksie (zobacz implementację metody
	 *            {@link SearchQueryClause#addQueryParam(QueryParam)}).
	 * @param operationType
	 *            typ operacji (nie może przyjmować wartość {@code null})
	 * @param from
	 *            pierwsza wartość (nie może przyjmować wartość {@code null})
	 * @param to
	 *            druga wartość (może przyjmować wartość {@code null})
	 */
	public QueryParam(String fieldName, OperationType operationType,
			String from, String to) {
		super();
		if (StringUtils.isBlank(fieldName)) {
			throw new NullPointerException("Nazwa pola musi zostać podana");
		}
		this.fieldName = fieldName;
		if (operationType == null) {
			throw new NullPointerException(
					"Operacja sposóbu budowania wyszukiwania musi zostać podana");
		}
		this.operationType = operationType;
		if (StringUtils.isBlank(from)) {
			throw new NullPointerException("Wartość filtra musi zostać podana");
		}
		this.from = from;
		this.to = to;
	}

	/**
	 * Konstruktor wartości filtra
	 * 
	 * @param fieldName
	 *            nazwa pola (nie może przyjmować wartość {@code null}). Nazwa
	 *            pola to nazwa pola w filtrze. Dopiero później podczas
	 *            budowania klauzuli zapytania nastąpi zmapowanie jego nazwy na
	 *            nazwę pola w indeksie.
	 * @param operationType
	 *            typ operacji (nie może przyjmować wartość {@code null})
	 * @param from
	 *            pierwsza wartość (nie może przyjmować wartość {@code null})
	 * @param to
	 *            druga wartość (może przyjmować wartość {@code null})
	 * @param accuracy
	 *            określenie trafności spełnienia danego warunku przez wiersz
	 *            wyszukiwarki
	 */
	public QueryParam(String fieldName, OperationType operationType,
			String from, String to, Accuracy accuracy) {
		super();
		if (StringUtils.isBlank(fieldName)) {
			throw new NullPointerException("Nazwa pola musi zostać podana");
		}
		this.fieldName = fieldName;
		if (operationType == null) {
			throw new NullPointerException(
					"Operacja sposóbu budowania wyszukiwania musi zostać podana");
		}
		this.operationType = operationType;
		if (StringUtils.isBlank(from)) {
			throw new NullPointerException("Wartość filtra musi zostać podana");
		}
		this.from = from;
		this.to = to;
		if (accuracy != null) {
			this.accuracy = accuracy;
		}
	}

	/**
	 * @return the operationType
	 */
	public OperationType getOperationType() {
		return operationType;
	}

	/**
	 * @return wartość dolnej granicy kryterium, dla kryteriów
	 *         {@link OperationType#EQ} to jest to po prostu wartość kryterium
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @return wartość górnej granicy kryterium, dla kryteriów
	 *         {@link OperationType#EQ} to wartość kryterium przyjmuje
	 *         {@code null}
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @return nazwa pola, którego dotyczy kryterium. Nazwa pola to nazwa pola w
	 *         filtrze. Dopiero później podczas budowania klauzuli zapytania
	 *         nastąpi zmapowanie jego nazwy na nazwę pola w indeksie.
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * @return the accuracy
	 */
	public Accuracy getAccuracy() {
		return accuracy;
	}

	/**
	 * @param accuracy
	 *            the accuracy to set
	 */
	public void setAccuracy(Accuracy accuracy) {
		this.accuracy = accuracy;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QueryParam [accuracy=" + accuracy + ", fieldName=" + fieldName
				+ ", from=" + from + ", operationType=" + operationType
				+ ", to=" + to + "]";
	}

}
