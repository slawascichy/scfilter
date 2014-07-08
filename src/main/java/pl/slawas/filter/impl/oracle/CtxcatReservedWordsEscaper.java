package pl.slawas.filter.impl.oracle;

import org.apache.commons.lang.StringUtils;

import pl.slawas.filter._IReservedWordsEscaper;

/**
 * 
 * CtxcatReservedWordsEscaper
 * 
 * @author Slawomir Cichy &lt;slawas@slawas.pl&gt;
 * @version $Revision: 1.2 $
 * 
 */
public class CtxcatReservedWordsEscaper implements _IReservedWordsEscaper {

	private static final long serialVersionUID = -7828178917328559513L;

	/**
	 * Podmienia następujące znaki sterujące: <li>"'" na "''" <li>usuwa znalk
	 * '*' z przodu
	 */
	public String escape(String source) {
		source = StringUtils.replace(source, "'", "''");
		if (source.startsWith("*")) {
			source = source.replaceAll("^\\*+", "");
		}
		return source;
	}

}
