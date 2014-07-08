package pl.slawas.filter.impl.oracle;

import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.slawas.filter.SearchBoxParser;
import pl.slawas.filter.beans.Accuracy;

/**
 * 
 * CtxcatReservedWordsEscaper
 * 
 * @author Slawomir Cichy &lt;slawas@slawas.pl&gt;
 * @version $Revision: 1.3 $
 * 
 */
public class CtxcatCredentialsPreparator extends CtxcatReservedWordsEscaper {

	private static final long serialVersionUID = -7828178917328559513L;

	final protected static Logger log = LoggerFactory
			.getLogger(CtxcatCredentialsPreparator.class);

	/**
	 * Podmienia następujące znaki sterujące:
	 * <p>
	 * <li>usuwa znak "+"
	 * <li>"|" na "| "
	 * </p>
	 * Na końcu wywołuje metodę klasy nadrzędnej
	 * {@link CtxcatReservedWordsEscaper#escape(String)}.
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
				target.append((i == 0 ? "" : " ") + searchCredential);
				break;
			case SHOULD:
				target.append((i == 0 ? "" : " | ") + searchCredential);
				break;
			case NOT:
				target.append(" -" + searchCredential);
				break;
			default:
				break;
			}
			i++;
		}
		return super.escape(target.toString());

	}

}
