package pl.slawas.test.entities;

import java.io.Serializable;

import javax.persistence.Id;

import pl.slawas.entities._ICopyable;

/**
 * 
 * TEntity
 * 
 * @author Slawomir Cichy &lt;slawas@slawas.pl&gt;
 * @version $Revision: 1.2 $
 * 
 */
public class TEntity implements Serializable, _ICopyable<TEntity>, _ITEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3469459252245120477L;

	@Id
	private String id;

	private String name = "n/a";

	private String date = "n/a";

	private String price = "n/a";

	private String user = "n/a";

	public TEntity() {
	}

	/**
	 * @param id
	 * @param name
	 * @param date
	 * @param price
	 * @param user
	 */
	public TEntity(String id, String name, String date, String price, String user) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.price = price;
		this.user = user;
	}

	/* (non-Javadoc)
	 * @see pl.wp.andro.test.filter.mock._ITEntity#getId()
	 */
	public String getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see pl.wp.andro.test.filter.mock._ITEntity#setId(java.lang.String)
	 */
	public void setId(String id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see pl.wp.andro.test.filter.mock._ITEntity#getName()
	 */
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see pl.wp.andro.test.filter.mock._ITEntity#setName(java.lang.String)
	 */
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see pl.wp.andro.test.filter.mock._ITEntity#getDate()
	 */
	public String getDate() {
		return date;
	}

	/* (non-Javadoc)
	 * @see pl.wp.andro.test.filter.mock._ITEntity#setDate(java.lang.String)
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/* (non-Javadoc)
	 * @see pl.wp.andro.test.filter.mock._ITEntity#getPrice()
	 */
	public String getPrice() {
		return price;
	}

	/* (non-Javadoc)
	 * @see pl.wp.andro.test.filter.mock._ITEntity#setPrice(java.lang.String)
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/* (non-Javadoc)
	 * @see pl.wp.andro.test.filter.mock._ITEntity#getUser()
	 */
	public String getUser() {
		return user;
	}

	/* (non-Javadoc)
	 * @see pl.wp.andro.test.filter.mock._ITEntity#setUser(java.lang.String)
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.wp.ares.core.entities._ICopyable#copy()
	 */
	public TEntity copy() {
		TEntity newCopy = new TEntity();
		return newCopy.copy(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.wp.ares.core.entities._ICopyable#copy(java.lang.Object)
	 */
	public TEntity copy(TEntity source) {
		this.id = source.getId();
		this.name = source.getName();
		this.date = source.getDate();
		this.price = source.getPrice();
		this.user = source.getUser();
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.wp.andro.entities._ICopyable#copyTo(java.lang.Object)
	 */
	public void copyTo(TEntity target) {
		target.setDate(this.date);
		target.setId(this.id);
		target.setName(this.name);
		target.setPrice(this.price);
		target.setUser(this.user);
	}

}
