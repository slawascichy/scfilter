package pl.slawas.filter;

import java.io.Serializable;

/**
 * 
 * _IReservedWordsEscaper obiekt maskujący znaki sterujące wyszukiwania
 * pełnotekstowego.
 * 
 * @author Slawomir Cichy &lt;slawas@slawas.pl&gt;
 * @version $Revision: 1.1 $
 * 
 */
public interface _IReservedWordsEscaper extends Serializable {

	/**
	 * Zamienianie, maskowanie znaków sterującyh.
	 * 
	 * @param source
	 *            wyrażenie przed zamaskowaniem
	 * @return wyrażenie po zamaskowaniu
	 */
	public String escape(String source);

}
