package pl.slawas.test.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import pl.slawas.entities._ICopyable;

/**
 * 
 * TOracleEntity
 * 
 * @author Slawomir Cichy &lt;slawas@slawas.pl&gt;
 * @version $Revision: 1.2 $
 * 
 */
@Entity
@Table(name = "TST_ORACLE_TABLE")
public class TOracleEntity implements Serializable, _ITEntity,
		_ICopyable<TOracleEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6837206619967602173L;

	@Id
	private String id;

	@Column(name = "row_name")
	private String name = "n/a";

	@Column(name = "date_of_row")
	private String date = "n/a";

	private String price = "n/a";

	@Column(name = "user_name")
	private String user = "n/a";

	public TOracleEntity() {
	}

	/**
	 * @param id
	 * @param name
	 * @param date
	 * @param price
	 * @param user
	 */
	public TOracleEntity(String id, String name, String date, String price,
			String user) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.price = price;
		this.user = user;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.wp.ares.core.entities._ICopyable#copy()
	 */
	public TOracleEntity copy() {
		TOracleEntity newCopy = new TOracleEntity();
		return newCopy.copy(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.wp.ares.core.entities._ICopyable#copy(java.lang.Object)
	 */
	public TOracleEntity copy(TOracleEntity source) {
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
	public void copyTo(TOracleEntity target) {
		target.setDate(this.date);
		target.setId(this.id);
		target.setName(this.name);
		target.setPrice(this.price);
		target.setUser(this.user);
	}

}
