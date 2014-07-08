package pl.slawas.filter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import pl.slawas.entities.NameValuePair;
import pl.slawas.entities.NameValuePairUtils;

/**
 * 
 * OperationType - operatory logiczne towarzyszące wyszukiwaniu
 * 
 * @author Slawomir Cichy &lt;slawas@slawas.pl&gt;
 * @version $Revision: 1.6 $
 * 
 */
public enum OperationType {

	/**
	 * Greater There
	 * */
	GT(">", "większa"),
	/**
	 * Lower There
	 * */
	LT("<", "mniejsza"),
	/**
	 * Equals There
	 * */
	EQ("=", "równa"),
	/**
	 * Greater or Equals There
	 * */
	GEQ(">=", "większa/równa"),
	/**
	 * Lower or Equals There
	 * */
	LEQ("<=", "mniejsza/równa"),
	/**
	 * Range Search Inclusive
	 */
	RSI("[..]", "pomiędzy (z)"),
	/**
	 * Range Search Exclusive
	 */
	RSE("(..)", "pomiędzy (bez)"),
	/**
	 * Wildcard Searches
	 */
	WS("*", "pełnotekstowo"),
	/**
	 * Simple Text (proste, tekstowe)
	 */
	ST("==", "proste");

	private String description;

	private String name;

	private OperationType(String name, String description) {
		this.description = description;
		this.name = name;
	}

	private static List<NameValuePair> list;

	static {
		list = new ArrayList<NameValuePair>(values().length);
		for (OperationType opType : values()) {
			if (!opType.equals(WS) && !opType.equals(ST)) {
				/* tworze nową implemplementacje i instancje NameValuePair */
				NameValuePair nameValuePair = NameValuePairUtils
						.createNewInstance(opType.name, opType.toString());
				list.add(nameValuePair);
			}
		}
	}

	public static List<NameValuePair> getList() {
		return list;
	}

	/**
	 * Metoda zwraca typ operacji na podstawie symbolu operacji odpowiadającemu
	 * {@link #value}.
	 * 
	 * @param value
	 *            symbol operacji
	 * @return Jeżeli symbol nie odpowiada żadnej wartości lub symbol jest pusty
	 *         to zwrócony zostanie domyślny typ {@link #ST}, w przeciwnym
	 *         wypadku zwrócony zostanie typ odpowiadający temu symbolowi.
	 */
	public static OperationType getOperationType(String value) {
		if (StringUtils.isBlank(value)) {
			return OperationType.ST;
		}
		value = value.toUpperCase();
		try {
			return OperationType.valueOf(value);
		} catch (IllegalArgumentException e) {
			return OperationType.ST;
		}
	}

	/**
	 * @return the {@link #description}
	 */
	public String getDescription() {
		return description;
	}

}
