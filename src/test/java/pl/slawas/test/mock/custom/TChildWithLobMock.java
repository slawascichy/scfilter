package pl.slawas.test.mock.custom;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import pl.slawas.test.entities.TChildWithLob;
import pl.slawas.test.entities.TFather;
import pl.slawas.test.entities.TMother;
import pl.slawas.test.mock.FakeObjects;
import pl.slawas.test.mock.TableInfoMock;

/**
 * Testowe dane dla obiektow:
 * 
 * @see pl.wp.ares.test.core.ejb3.data.TChild
 * 
 * @author slawas
 * 
 */
public class TChildWithLobMock implements TableInfoMock {

	private static final String tableName = "CHILDREN_WITH_LOB";

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	public Map<String, String> getColumnInfo() {
		Map<String, String> columns = new HashMap<String, String>();
		// 2007-08-29 17:09:50,122 DEBUG [AndroDAOSupportTest.java:156] : ->
		// CHILD_ID: String.getId.
		columns.put("CHILD_ID", "String.getId");
		// 2007-08-29 17:09:50,122 DEBUG [AndroDAOSupportTest.java:156] : ->
		// name: String.getName.
		columns.put("name", "String.getName");
		// 2007-08-29 17:09:50,122 DEBUG [AndroDAOSupportTest.java:156] : ->
		// age: Integer.getAge.
		columns.put("age", "Integer.getAge");
		// 2007-08-29 17:09:50,123 DEBUG [AndroDAOSupportTest.java:156] : ->
		// birthDay: Calendar.getBirthDay.Date.getTime.
		columns.put("birthDay", "Calendar.getBirthDay.Date.getTime");
		// 2007-08-29 17:09:50,123 DEBUG [AndroDAOSupportTest.java:156] : ->
		// CH_MOTHER_ID: TMother.getMother.String.getId.
		columns.put("CH_MOTHER_ID", "TMother.getMother.String.getId");
		// 2007-08-29 17:09:50,123 DEBUG [AndroDAOSupportTest.java:156] : ->
		// CH_FATHER_ID: TFather.getFather.PK.getPk.String.getFatherId.
		columns.put("CH_FATHER_ID", "TFather.getFather.TFatherPK.getId.String.getFatherId");
		// 2007-08-29 17:09:50,123 DEBUG [AndroDAOSupportTest.java:156] : ->
		// CH_FATHER_TYPE: TFather.getFather.PK.getPk.String.getType.
		columns.put("CH_FATHER_TYPE", "TFather.getFather.TFatherPK.getId.String.getType");

		return columns;
	}

	// 2007-08-29 17:09:50,123 INFO [AndroDAOSupportTest.java:159] : InsertSQL:
	// INSERT /*+ */ INTO
	// CHILDREN(CHILD_ID,name,age,birthDay,CH_MOTHER_ID,CH_FATHER_ID,CH_FATHER_TYPE)
	// values (?,?,?,?,?,?,?)
	// 2007-08-29 17:09:50,124 INFO [AndroDAOSupportTest.java:160] : DeleteSQL:
	// DELETE /*+ */ FROM CHILDREN WHERE CHILD_ID = ?
	// 2007-08-29 17:09:50,124 INFO [AndroDAOSupportTest.java:161] : UpdateSQL:
	// UPDATE /*+ */ CHILDREN SET CHILD_ID = ?,name = ?,age = ?,birthDay =
	// ?,CH_MOTHER_ID = ?,CH_FATHER_ID = ?,CH_FATHER_TYPE = ? WHERE CHILD_ID = ?

	private static final String insertSQL = "INSERT /*+ */ INTO CHILDREN_WITH_LOB(CHILD_ID,name,age,birthDay,CH_MOTHER_ID,CH_FATHER_ID,CH_FATHER_TYPE) values (?,?,?,?,?,?,?)";

	private static final String updateSQL = "UPDATE /*+ */ CHILDREN_WITH_LOB SET CHILD_ID = ?,name = ?,age = ?,birthDay = ?,CH_MOTHER_ID = ?,CH_FATHER_ID = ?,CH_FATHER_TYPE = ? WHERE CHILD_ID = ?";

	private static final String deleteSQL = "DELETE /*+ */ FROM CHILDREN_WITH_LOB WHERE CHILD_ID = ?";

	/**
	 * @return the deleteSQL
	 */
	public String getDeleteSQL() {
		return deleteSQL;
	}

	/**
	 * @return the insertSQL
	 */
	public String getInsertSQL() {
		return insertSQL;
	}

	/**
	 * @return the updateSQL
	 */
	public String getUpdateSQL() {
		return updateSQL;
	}

	List<TChildWithLob> children;

	public TChildWithLobMock() {
	}

	public List<TChildWithLob> getChildren() {
		return children;
	}

	public void setChildren(TFatherMock fatherMock, TMotherMock motherMock) {

		Vector<TChildWithLob> ch = new Vector<TChildWithLob>();

		TChildWithLob child;
		TFather father;
		TMother mother;

		List<TFather> fatherList = fatherMock.getFathers();
		List<TMother> motherList = motherMock.getMothers();

		father = fatherList.get(0);
		mother = motherList.get(0);

		Calendar date = Calendar.getInstance();
		date.set(2004, 11, 13);

		child = new TChildWithLob();
		child.setId("0");
		child.setName("Johny");
		child.setAge(3);
		child.setBirthDay(date);
		child.setFather(father);
		child.setMother(mother);
		child.setBiography(FakeObjects.biography);
		child.setPhoto(FakeObjects.photo);
		child.setNameDay(new Date(1234567890));
		ch.add(child);

		date = Calendar.getInstance();
		date.set(2005, 7, 13);
		child = new TChildWithLob();
		child.setId("1");
		child.setName("Angela");
		child.setAge(2);
		child.setBirthDay(date);
		child.setFather(father);
		child.setMother(mother);
		ch.add(child);

		date = Calendar.getInstance();
		date.set(2005, 7, 17);
		father = fatherList.get(2);
		child = new TChildWithLob();
		child.setId("2");
		child.setName("Mike");
		child.setAge(2);
		child.setBirthDay(date);
		child.setFather(father);
		child.setMother(mother);
		ch.add(child);

		father = fatherList.get(1);
		mother = motherList.get(1);

		child = new TChildWithLob();
		child.setId("3");
		child.setName("Julia");
		child.setAge(2);
		child.setBirthDay(date);
		// testing advanced null value
		child.setFather(father);
		child.setMother(mother);
		ch.add(child);

		this.children = ch;
	}
}
