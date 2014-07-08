package pl.slawas.test.entities;

/**
 * 
 * TEntity
 * 
 * @author Slawomir Cichy &lt;slawas@slawas.pl&gt;
 * @version $Revision: 1.2 $
 * 
 */
public class TIndex extends TEntity {

	private static final long serialVersionUID = 2307295317451772566L;

	private String key;

	public TIndex() {
		super();
	}

	/**
	 * @param key
	 * @param id
	 * @param name
	 * @param date
	 * @param price
	 * @param user
	 */
	public TIndex(String key, String id, String name, String date, String price, String user) {
		super(id, name, date, price, user);
		this.key = key;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key
	 *           the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

}
