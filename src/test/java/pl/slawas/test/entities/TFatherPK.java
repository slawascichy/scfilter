package pl.slawas.test.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TFatherPK implements Serializable{
	
	private static final long serialVersionUID = -7427145781545089504L;

	public TFatherPK(){}
	
	/**
	 * @param fatherId
	 * @param type
	 */
	public TFatherPK(Integer fatherId, String type) {
		super();
		this.fatherId = fatherId;
		this.type = type;
	}



	@Column(name="FATHER_ID")
	private Integer fatherId;

	@Column(name="FATHER_TYPE")
	private String type;


	public Integer getFatherId() {
		return fatherId;
	}

	public void setFatherId(Integer id) {
		this.fatherId = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if ( ! (o instanceof TFatherPK)) {
			return false;
		}
		TFatherPK other = (TFatherPK) o;
		return this.fatherId.equals(other.fatherId)
			&& this.type.equals(other.type);
	}

	@Override
	public int hashCode() {
		return this.fatherId.hashCode()
			^ this.type.hashCode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TFatherPK [fatherId=" + fatherId + ", type=" + type + "]";
	}

}
