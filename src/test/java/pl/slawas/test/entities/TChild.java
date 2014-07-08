package pl.slawas.test.entities;

/**
 * @author slawas
 */

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CHILDREN")
public class TChild implements Serializable {


	private static final long serialVersionUID = -5933771171401443965L;

	@Id
	//@SequenceGenerator(name = "ChildSeq", sequenceName = "CHILDREN_SEQ", initialValue = 0)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ChildSeq")
	@GeneratedValue
	@Column(name = "CHILD_ID")
	private String id;

	private String name;

	private Integer age;

	private Calendar birthDay;
	
	@ManyToOne
	@JoinColumn(name = "CH_MOTHER_ID", referencedColumnName = "MOTHER_ID")
	private TMother mother;

	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "CH_FATHER_ID", referencedColumnName = "FATHER_ID", nullable = false),
			@JoinColumn(name = "CH_FATHER_TYPE", referencedColumnName = "FATHER_TYPE", nullable = false) })
	private TFather father;

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public TFather getFather() {
		return father;
	}

	public void setFather(TFather father) {
		this.father = father;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TMother getMother() {
		return mother;
	}

	public void setMother(TMother mother) {
		this.mother = mother;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Calendar getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Calendar birthDay) {
		this.birthDay = birthDay;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		SimpleDateFormat defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return "TChild [age=" + age + ", birthDay=" +  defaultDateFormat.format(birthDay.getTime()) + ", father=" + father + ", id=" + id
				+ ", mother=" + mother + ", name=" + name + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((birthDay == null) ? 0 : birthDay.hashCode());
		result = prime * result + ((father == null) ? 0 : father.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mother == null) ? 0 : mother.hashCode());
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
		TChild other = (TChild) obj;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (birthDay == null) {
			if (other.birthDay != null)
				return false;
		} else if (!birthDay.equals(other.birthDay))
			return false;
		if (father == null) {
			if (other.father != null)
				return false;
		} else if (!father.equals(other.father))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mother == null) {
			if (other.mother != null)
				return false;
		} else if (!mother.equals(other.mother))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
