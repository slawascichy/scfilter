package pl.slawas.test.entities;

/**
 * Obiekt do testowania, czy AresDAO wykryje zastosowanie nielegalnych
 * typow w encjach. W obiekcie zastosowano nielelgalny typ "java.util.Set"
 * przy adnotacji "ManyToOne" pola "father". 
 * @author slawas
 */

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "TEST_ERROR")
public class TChildSetError implements Serializable {

	private static final long serialVersionUID = -724084961554733328L;

	@Id
	@SequenceGenerator(name = "ErrorSeq", sequenceName = "CHILDREN_SEQ", initialValue = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ErrorSeq")
	@Column(name = "CHILD_ID")
	private String id;

	private String name;

	private Integer age;

	private Calendar birthDay;
	
	@ManyToOne
	@JoinColumn(name = "CH_MOTHER_ID")
	private TMother mother;

	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "CH_FATHER_ID", referencedColumnName = "FATHER_ID"),
			@JoinColumn(name = "CH_FATHER_TYPE", referencedColumnName = "FATHER_TYPE") })
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

}
