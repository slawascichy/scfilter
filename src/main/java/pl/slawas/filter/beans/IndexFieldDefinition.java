package pl.slawas.filter.beans;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * IndexFieldDefinition definicja pola w indeksie
 * 
 * @author Slawomir Cichy &lt;slawas@slawas.pl&gt;
 * @version $Revision: 1.1 $
 * 
 */
public class IndexFieldDefinition implements Serializable {

	private static final long serialVersionUID = 1L;

	/** nazwa pola w indeksie z wartością do przeszukiwania */
	private final String indexValueFieldName;

	/**
	 * nazwa pola w indeksie z wartością do sortowania (nazwa pola moze być taka
	 * sama jak {@link #indexValueFieldName})
	 */
	private final String indexSortFieldName;

	/**
	 * @param indexValueFieldName
	 *           nazwa pola w indeksie
	 * @param indexSortFieldName
	 *           nazwa pola sortowania w indeksie
	 */
	public IndexFieldDefinition(String indexValueFieldName, String indexSortFieldName) {
		super();
		this.indexValueFieldName = indexValueFieldName;
		this.indexSortFieldName = indexSortFieldName;
	}

	/**
	 * Konstruktor obiektu definicji pola indeksu, którego pole sortowania ma
	 * taką samą nazwę jak pole wartości.
	 * 
	 * @param indexValueFieldName
	 *           nazwa pola w indeksie
	 */
	public IndexFieldDefinition(String indexValueFieldName) {
		super();
		this.indexValueFieldName = indexValueFieldName;
		this.indexSortFieldName = null;
	}

	/**
	 * @return the {@link #indexValueFieldName}
	 */
	public String getIndexValueFieldName() {
		return indexValueFieldName;
	}

	/**
	 * @return the {@link #indexSortFieldName}
	 */
	public String getIndexSortFieldName() {
		return (StringUtils.isNotBlank(indexSortFieldName) ? indexSortFieldName : indexValueFieldName);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "IndexFieldDefinition [indexValueFieldName=" + indexValueFieldName
				+ ", indexSortFieldName=" + indexSortFieldName + "]";
	}

}
