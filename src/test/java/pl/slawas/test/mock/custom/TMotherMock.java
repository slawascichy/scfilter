package pl.slawas.test.mock.custom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import pl.slawas.test.entities.TMother;
import pl.slawas.test.mock.TableInfoMock;

/**
 * Testowe dane dla obiektow:
 * 
 * @see pl.wp.ares.test.core.ejb3.data.TMother
 * 
 * @author slawas
 * 
 */
public class TMotherMock implements TableInfoMock {

	private static final String tableName = "MOTHERS";

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	public Map<String, String> getColumnInfo() {
		Map<String, String> columns = new HashMap<String, String>();

		// 2007-08-29 17:09:50,158 DEBUG [AndroDAOSupportTest.java:125] : ->
		// MOTHER_ID: String.getId.
		columns.put("MOTHER_ID", "String.getId");
		// 2007-08-29 17:09:50,158 DEBUG [AndroDAOSupportTest.java:125] : ->
		// PARENT_NAME: String.getParentName.
		columns.put("PARENT_NAME", "String.getParentName");

		return columns;
	}

	// 2007-08-29 17:09:50,158 INFO [AndroDAOSupportTest.java:128] : InsertSQL:
	// INSERT /*+ */ INTO MOTHERS(MOTHER_ID,PARENT_NAME) values (?,?)
	// 2007-08-29 17:09:50,159 INFO [AndroDAOSupportTest.java:129] : DeleteSQL:
	// DELETE /*+ */ FROM MOTHERS WHERE MOTHER_ID = ?
	// 2007-08-29 17:09:50,159 INFO [AndroDAOSupportTest.java:130] : UpdateSQL:
	// UPDATE /*+ */ MOTHERS SET MOTHER_ID = ?,PARENT_NAME = ? WHERE MOTHER_ID =
	// ?

	private static final String insertSQL = "INSERT /*+ */ INTO MOTHERS(MOTHER_ID,PARENT_NAME) values (?,?)";

	private static final String updateSQL = "UPDATE /*+ */ MOTHERS SET MOTHER_ID = ?,PARENT_NAME = ? WHERE MOTHER_ID = ?";

	private static final String deleteSQL = "DELETE /*+ */ FROM MOTHERS WHERE MOTHER_ID = ?";

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

	private List<TMother> mothers;

	public TMotherMock() {
		setMothers();
	}

	public List<TMother> getMothers() {
		return mothers;
	}

	public void setMothers() {

		Vector<TMother> m = new Vector<TMother>();
		TMother mother;

		mother = new TMother();
		mother.setParentName("July");
		m.add(mother);

		mother = new TMother();
		mother.setParentName("Marry");
		m.add(mother);

		this.mothers = m;

	}

}
