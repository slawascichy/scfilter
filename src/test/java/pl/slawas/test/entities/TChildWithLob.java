package pl.slawas.test.entities;

/**
 * @author slawas
 */

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "CHILDREN_WITH_LOB")
public class TChildWithLob implements Serializable {

	private static final long serialVersionUID = -2124449423299405159L;

	/**
	 * kod wartości "podpisał"
	 */
	public final static String SIGNED_CODE = "1";

	/**
	 * kod wartości "nie podpisał"
	 */
	public final static String NOT_SIGNED_CODE = "0";
	
	@Transient
	final static private Logger logger = LoggerFactory
			.getLogger(TChildWithLob.class);

	@Id
	@Column(name = "CHILD_ID")
	private String id;

	private String name;

	private Integer age;

	private Calendar birthDay;

	private Date nameDay;

	@Lob
	private byte[] photo;

	@Lob
	private String biography;

	@Lob
	private Blob photo2;

	@Lob
	private Clob biography2;

	@Lob
	private Clob nationalbiography;

	@ManyToOne
	@JoinColumn(name = "CH_MOTHER_ID", referencedColumnName = "MOTHER_ID")
	private TMother mother;

	@ManyToOne
	@JoinColumns(
		{
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

	/**
	 * @return the biography
	 */
	public String getBiography() {
		return biography;
	}

	/**
	 * @param biography
	 *           the biography to set
	 */
	public void setBiography(String biography) {
		this.biography = biography;
	}

	/**
	 * @return the photo
	 */
	public byte[] getPhoto() {
		return photo;
	}

	/**
	 * @param photo
	 *           the photo to set
	 */
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	/**
	 * @return the nameDay
	 */
	public Date getNameDay() {
		return nameDay;
	}

	/**
	 * @param nameDay
	 *           the nameDay to set
	 */
	public void setNameDay(Date nameDay) {
		this.nameDay = nameDay;
	}

	/**
	 * @return the photo2
	 */
	public Blob getPhoto2() {
		return photo2;
	}

	/**
	 * @param photo2
	 *           the photo2 to set
	 */
	public void setPhoto2(Blob photo2) {
		this.photo2 = photo2;
	}

	/**
	 * @return the biography2
	 */
	public Clob getBiography2() {
		return biography2;
	}

	/**
	 * @param biography2
	 *           the biography2 to set
	 */
	public void setBiography2(Clob biography2) {
		this.biography2 = biography2;
	}

	/**
	 * @return the nationalbiography
	 */
	public Clob getNationalbiography() {
		return nationalbiography;
	}

	/**
	 * @param nationalbiography
	 *           the nationalbiography to set
	 */
	public void setNationalbiography(Clob nationalbiography) {
		this.nationalbiography = nationalbiography;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((biography == null) ? 0 : biography.hashCode());
		result = prime * result + ((biography2 == null) ? 0 : biography2.hashCode());
		result = prime * result + ((birthDay == null) ? 0 : birthDay.hashCode());
		result = prime * result + ((father == null) ? 0 : father.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mother == null) ? 0 : mother.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nameDay == null) ? 0 : nameDay.hashCode());
		result = prime * result + ((nationalbiography == null) ? 0 : nationalbiography.hashCode());
		result = prime * result + Arrays.hashCode(photo);
		result = prime * result + ((photo2 == null) ? 0 : photo2.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		
		TChildWithLob other = (TChildWithLob) obj;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;

		if (biography == null) {
			if (other.biography != null)
				return false;
		} else if (!biography.equals(other.biography))
			return false;

		if (biography2 == null) {
			if (other.biography2 != null)
				return false;
		} else if (biography2 != null && other.biography2 != null) {
			try {
				String strVal1 = biography2.getSubString(1, (int) biography2.length());
				String strVal2 = other.biography2.getSubString(1, (int) other.biography2.length());
				logger.trace("Porownuje wartosci: '{}' '{}'", new Object[]
					{ strVal1, strVal2 });
				if (!strVal1.equals(strVal2)) {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}

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
		if (nameDay == null) {
			if (other.nameDay != null)
				return false;
		} else if (!nameDay.equals(other.nameDay))
			return false;
		if (nationalbiography == null) {
			if (other.nationalbiography != null)
				return false;
		} else if (nationalbiography != null && other.nationalbiography != null) {
			try {
				String strVal1 = nationalbiography.getSubString(1, (int) nationalbiography.length());
				String strVal2 = other.nationalbiography.getSubString(1, (int) other.nationalbiography
						.length());
				logger.trace("Porownuje wartosci: '{}' '{}'", new Object[]
					{ strVal1, strVal2 });
				if (!strVal1.equals(strVal2))
					return false;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		if (!Arrays.equals(photo, other.photo))
			return false;
		if (photo2 == null) {
			if (other.photo2 != null)
				return false;
		} else if (photo2 != null && other.photo2 != null) {
			try {
				byte[] strVal1 = photo2.getBytes(1, (int) photo2.length());
				byte[] strVal2 = other.photo2.getBytes(1, (int) other.photo2.length());
				logger.trace("Porownuje wartosci: '{}' '{}'", new Object[]
					{ Arrays.toString(strVal1), Arrays.toString(strVal2) });
				if (!Arrays.equals(strVal1, strVal2))
					return false;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		SimpleDateFormat defaultDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		return "TChildWithLob [age=" + age + ", biography=" + biography + ", biography2="
				+ biography2 + ", birthDay=" + defaultDateFormat.format(birthDay.getTime())
				+ ", father=" + father.toString() + ", id=" + id
				+ ", mother=" + mother.toString() + ", name=" + name + ", nameDay="
				+ defaultDateFormat.format(nameDay)
				+ ", nationalbiography=" + nationalbiography + ", photo=" + Arrays.toString(photo)
				+ ", photo2=" + photo2 + "]";
	}

}
