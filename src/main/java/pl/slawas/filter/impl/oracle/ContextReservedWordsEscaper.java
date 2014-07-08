package pl.slawas.filter.impl.oracle;

import org.apache.commons.lang.StringUtils;

import pl.slawas.filter._IReservedWordsEscaper;

/**
 * 
 * ContextReservedWordsEscaper implementacja dla Oracle
 * 
 * @author Slawomir Cichy &lt;slawas@slawas.pl&gt;
 * @version $Revision: 1.1 $
 * 
 */
public class ContextReservedWordsEscaper implements _IReservedWordsEscaper {

	private static final long serialVersionUID = -6682854703323933790L;

	/**
	 * Podmienia następujące znaki sterujące: <li>"'" na "''" <li>"*" na "%"
	 */
	public String escape(String source) {
		source = StringUtils.replace(source, "'", "''");
		source = StringUtils.replace(source, "*", "%");
		return source;
	}

}
