package pl.slawas.filter.beans;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * SortParams
 * 
 * @author Slawomir Cichy &lt;slawas@slawas.pl&gt;
 * @version $Revision: 1.1 $
 * 
 */
public class SortParams implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3531404513765211754L;

	private String sortParam;

	private boolean asc = true;

	/**
	 * @param sortParam
	 * @param asc
	 */
	public SortParams(String sortParam, boolean asc) {
		super();
		this.sortParam = sortParam;
		this.asc = asc;
	}

	/**
	 * @return the sortParam
	 */
	public String getSortParam() {
		return sortParam;
	}

	/**
	 * @param sortParam
	 *            the sortParam to set
	 */
	public void setSortParam(String sortParam) {
		this.sortParam = sortParam;
	}

	/**
	 * @return the asc
	 */
	public boolean isAsc() {
		return asc;
	}

	/**
	 * @param asc
	 *            the asc to set
	 */
	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	/* Overridden (non-Javadoc) */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (asc ? 1231 : 1237);
		result = prime * result
				+ (StringUtils.isBlank(sortParam) ? 0 : sortParam.hashCode());
		return result;
	}

	/* Overridden (non-Javadoc) */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SortParams other = (SortParams) obj;
		if (asc != other.asc)
			return false;
		if (StringUtils.isBlank(sortParam)) {
			if (StringUtils.isNotBlank(other.sortParam))
				return false;
		} else if (!sortParam.equals(other.sortParam))
			return false;
		return true;
	}

}
