package pl.slawas.filter.impl.oracle;

import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.slawas.filter.SearchBoxParser;
import pl.slawas.filter.beans.Accuracy;

/**
 * 
 * ContextCredentialsPreparator implementacja dla Oracle
 * 
 * @author Slawomir Cichy &lt;slawas@slawas.pl&gt;
 * @version $Revision: 1.3 $
 * 
 */
public class ContextCredentialsPreparator extends ContextReservedWordsEscaper {

	private static final long serialVersionUID = -6682854703323933790L;

	final protected static Logger log = LoggerFactory
			.getLogger(ContextCredentialsPreparator.class);

	/**
	 * Podmienia następujące znaki sterujące:
	 * <p>
	 * <li>"+" na " & "
	 * <li>"|" na " | "
	 * </p>
	 * Na końcu wywołuje metodę klasy nadrzędnej
	 * {@link ContextReservedWordsEscaper#escape(String)}.
	 */
	public String escape(String source) {

		SearchBoxParser parser = new SearchBoxParser(source.trim()
				.toLowerCase());
		Vector<String> tokens = parser.parseSearchText();
		StringBuffer target = new StringBuffer();
		int i = 0;
		for (String searchCredential : tokens) {
			log.info("searchCredential: {}", searchCredential);
			Accuracy occur = Accuracy.MUST;
			/* badanie występowania znaku sterującego */
			if (searchCredential.startsWith(SearchBoxParser.OR_OPERATOR)) {
				occur = Accuracy.SHOULD;
				searchCredential = searchCredential.substring(1);
			} else if (searchCredential
					.startsWith(SearchBoxParser.MINUS_OPERATOR)) {
				occur = Accuracy.NOT;
				searchCredential = searchCredential.substring(1);
			}

			switch (occur) {
			case MUST:
				target.append((i == 0 ? "" : " & ") + searchCredential);
				break;
			case SHOULD:
				target.append((i == 0 ? "" : " | ") + searchCredential);
				break;
			case NOT:
				target.append(" NOT " + searchCredential);
				break;
			default:
				break;
			}
			i++;
		}
		return super.escape(target.toString());
	}

}
