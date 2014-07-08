package pl.slawas.filter;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * QueryResult - pojedynczy obiekt wiersza w wyniku zapytania, który został
 * otrzymany od wyszukiwacza.
 * 
 * @author Slawomir Cichy &lt;slawas@slawas.pl&gt;
 * @version $Revision: 1.2 $
 * 
 */
public abstract class QueryResult<Row> implements Serializable {

	private static final long serialVersionUID = -5210221106589109534L;

	/**
	 * Obiekt orginalego rezultatu wyszukiwarki. Należy go wykorzystać do
	 * implementacji pozostałych metod abstrakcyjnych.
	 */
	protected Row origResult;

	/**
	 * Id dokumentu w indeksie
	 */
	private Object documentId;

	/**
	 * Trafność z jaką dokument spełnia zapytanie. Wartości od 0, bez
	 * ogranczenia do 1, bo jest to score nie przeskalowany.
	 */
	private float documentScore;

	/**
	 * Zbiór par, w których klucz to nazwa pola, a wartość to wartość pola
	 * znalezionego dokumentu.
	 */
	private Map<String, Object> fields;

	public QueryResult() {
	}

	/**
	 * @param documentId
	 * @param documentScore
	 */
	public QueryResult(Object documentId, float documentScore) {
		super();
		this.documentId = documentId;
		this.documentScore = documentScore;
	}

	public QueryResult(Row result) {
		this.origResult = result;
	}

	/**
	 * Pobieranie Id dokumentu w indeksie wyszukiwarki
	 */
	public Object getDocumentId() {
		return documentId;
	}

	/**
	 * Pobranie trafności z jaką dokument spełnia zapytanie. Wartości od 0, bez
	 * ogranczenia do 1, bo jest to score nie przeskalowany.
	 */
	public float getDocumentScore() {
		return documentScore;
	}

	/**
	 * Zwraca zbiór par, w których klucz to nazwa pola, a wartość to wartość
	 * pola znalezionego dokumentu.
	 */
	public Map<String, Object> getFields() {
		return fields;
	}

	/**
	 * Metoda pobierająca oryginalny obiekt rezultatu
	 * 
	 * @return the origResult
	 */
	public Row getOrigResult() {
		return origResult;
	}

	/**
	 * Metoda ustawiająca oryginalny obiekt rezultatu
	 * 
	 * @param result
	 *            oryginalny obiekt rezultatu
	 */
	public void setOrigResult(Row result) {
		this.origResult = result;
	}

	/**
	 * @param documentId
	 *            the documentId to set
	 */
	public void setDocumentId(Object documentId) {
		this.documentId = documentId;
	}

	/**
	 * @param documentScore
	 *            the documentScore to set
	 */
	public void setDocumentScore(float documentScore) {
		this.documentScore = documentScore;
	}

	/**
	 * @param fields
	 *            the fields to set
	 */
	public void setFields(Map<String, Object> fields) {
		this.fields = fields;
	}

	@Override
	public String toString() {
		return toString(false);
	}

	public String toString(boolean sort) {
		StringBuilder str = new StringBuilder();
		str.append("id=");
		str.append(this.getDocumentId().toString());
		str.append(", score=");
		str.append(this.getDocumentScore());
		str.append(": ");
		Map<String, Object> fields;
		if (sort) {
			fields = new TreeMap<String, Object>();
			fields.putAll(this.getFields());
		} else {
			fields = this.getFields();
		}
		if (fields != null) {
			for (Map.Entry<String, Object> entry : fields.entrySet()) {
				str.append(entry.getKey());
				str.append("=");
				str.append(entry.getValue());
				str.append(", ");
			}
		}
		return str.toString();
	}

}
