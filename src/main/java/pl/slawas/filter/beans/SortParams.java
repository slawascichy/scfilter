package pl.slawas.filter.beans;

import java.io.Serializable;

/**
 * 
 * SortParams
 *
 * @author Slawomir Cichy &lt;slawas@slawas.pl&gt;
 * @version $Revision: 1.1 $
 *
 */
public class SortParams implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3531404513765211754L;

	private String sortParam; 
	
	private boolean asc;

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
	 * @param sortParam the sortParam to set
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
	 * @param asc the asc to set
	 */
	public void setAsc(boolean asc) {
		this.asc = asc;
	}
	

	
}
