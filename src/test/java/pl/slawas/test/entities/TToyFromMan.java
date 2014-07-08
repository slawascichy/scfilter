package pl.slawas.test.entities;

/**
 * @author slawas
 */

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MTOYS")
public class TToyFromMan implements Serializable {

	private static final long serialVersionUID = -740887419616901168L;

	@Id
	@Column(name = "TOY_ID")
	private String id;

	private String name;

	@ManyToOne
	@JoinColumn(name = "CHILDREN_CHILD_ID", referencedColumnName = "CHILD_ID")
	private TChild children;

	@ManyToOne
	@JoinColumns(
		{
				@JoinColumn(name = "FATHER_ID", referencedColumnName = "FATHER_ID"),
				@JoinColumn(name = "FATHER_TYPE", referencedColumnName = "FATHER_TYPE") })
	private TFather man;

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

	public TFather getMan() {
		return man;
	}

	public void setMan(TFather man) {
		this.man = man;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TToyFromMan [children=" + children + ", id=" + id + ", man=" + man + ", name=" + name
				+ "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((children == null) ? 0 : children.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((man == null) ? 0 : man.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TToyFromMan other = (TToyFromMan) obj;
		if (children == null) {
			if (other.children != null)
				return false;
		} else if (!children.equals(other.children))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (man == null) {
			if (other.man != null)
				return false;
		} else if (!man.equals(other.man))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
