package pl.slawas.test.entities;

/**
 * @author slawas
 */
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="FATHERS")
public class TFather implements Serializable {
	
	private static final long serialVersionUID = 4481281618855143881L;

	@EmbeddedId
	private TFatherPK id;
	
	@Column(name="PARENT_NAME")
	private String parentName;
	
	public TFather() {}
	
	/**
	 * @param id
	 * @param parentName
	 */
	public TFather(TFatherPK id, String parentName) {
		super();
		this.id = id;
		this.parentName = parentName;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public TFatherPK getId() {
		return id;
	}

	public void setId(TFatherPK pk) {
		this.id = pk;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((parentName == null) ? 0 : parentName.hashCode());
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
		TFather other = (TFather) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (parentName == null) {
			if (other.parentName != null)
				return false;
		} else if (!parentName.equals(other.parentName))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TFather [id=" + id.toString() + ", parentName=" + parentName + "]";
	}

	
}
