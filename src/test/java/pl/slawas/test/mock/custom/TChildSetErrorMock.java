package pl.slawas.test.mock.custom;

import java.util.HashMap;
import java.util.Map;

import pl.slawas.test.mock.TableInfoMock;


/**
 * Testowe dane dla obiektow:
 * 
 * @see pl.wp.ares.test.core.ejb3.data.TChild
 * 
 * @author slawas
 * 
 */
public class TChildSetErrorMock implements TableInfoMock {

	private static final String tableName = "TEST_ERROR";

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	public Map<String, String> getColumnInfo() {
		Map<String, String> columns = new HashMap<String, String>();

		// 2007-08-29 17:09:50,135 DEBUG [AndroDAOSupportTest.java:125] : ->
		// CHILD_ID: String.getId.
		columns.put("CHILD_ID", "String.getId");
		// 2007-08-29 17:09:50,136 DEBUG [AndroDAOSupportTest.java:125] : ->
		// name: String.getName.
		columns.put("name", "String.getName");
		// 2007-08-29 17:09:50,136 DEBUG [AndroDAOSupportTest.java:125] : ->
		// age: Integer.getAge.
		columns.put("age", "Integer.getAge");
		// 2007-08-29 17:09:50,136 DEBUG [AndroDAOSupportTest.java:125] : ->
		// birthDay: Calendar.getBirthDay.Date.getTime.
		columns.put("birthDay", "Calendar.getBirthDay.Date.getTime");
		// 2007-08-29 17:09:50,136 DEBUG [AndroDAOSupportTest.java:125] : ->
		// CH_MOTHER_ID: TMother.getMother.String.getId.
		columns.put("CH_MOTHER_ID", "TMother.getMother.String.getId");
		//CH_FATHER_ID:TFather.getFather.TFatherPK.getId.String.getFatherId.
		columns.put("CH_FATHER_ID", "TFather.getFather.TFatherPK.getId.String.getFatherId");

		columns.put("CH_FATHER_TYPE", "TFather.getFather.TFatherPK.getId.String.getType");
		return columns;
	}

	// 2007-08-29 17:09:50,137 INFO [AndroDAOSupportTest.java:128] : InsertSQL:
	// INSERT /*+ */ INTO TEST_ERROR(CHILD_ID,name,age,birthDay,CH_MOTHER_ID)
	// values (?,?,?,?,?)
	// 2007-08-29 17:09:50,137 INFO [AndroDAOSupportTest.java:129] : DeleteSQL:
	// DELETE /*+ */ FROM TEST_ERROR WHERE CHILD_ID = ?
	// 2007-08-29 17:09:50,137 INFO [AndroDAOSupportTest.java:130] : UpdateSQL:
	// UPDATE /*+ */ TEST_ERROR SET CHILD_ID = ?,name = ?,age = ?,birthDay =
	// ?,CH_MOTHER_ID = ? WHERE CHILD_ID = ?

	private static final String insertSQL = "INSERT /*+ */ INTO TEST_ERROR(CHILD_ID,name,age,birthDay,CH_MOTHER_ID,CH_FATHER_ID,CH_FATHER_TYPE) values (?,?,?,?,?,?,?)";

	private static final String updateSQL = "UPDATE /*+ */ TEST_ERROR SET CHILD_ID = ?,name = ?,age = ?,birthDay = ?,CH_MOTHER_ID = ?,CH_FATHER_ID = ?,CH_FATHER_TYPE = ? WHERE CHILD_ID = ?";

	private static final String deleteSQL = "DELETE /*+ */ FROM TEST_ERROR WHERE CHILD_ID = ?";

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

}
