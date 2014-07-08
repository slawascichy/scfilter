package pl.slawas.test.entities;

/**
 * @author slawas
 */

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "WTOYS")
public class TToyFromWoman implements Serializable {

	private static final long serialVersionUID = 5311271745622404715L;

	@Id
	@Column(name = "TOY_ID")
	private String id;

	private String name;

	@ManyToOne
	@JoinColumn(name = "CHILD_ID", referencedColumnName = "CHILD_ID")
	private TChild children;

	@ManyToOne
	@JoinColumn(name = "MOTHER_ID", referencedColumnName = "MOTHER_ID")
	private TMother woman;

	public TChild getChildren() {
		return children;
	}

	public void setChildren(TChild children) {
		this.children = children;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TMother getWoman() {
		return woman;
	}

	public void setWoman(TMother woman) {
		this.woman = woman;
	}

}
