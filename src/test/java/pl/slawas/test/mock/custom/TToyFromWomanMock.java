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
public class TToyFromWomanMock implements TableInfoMock {

	private static final String tableName = "WTOYS";

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	public Map<String, String> getColumnInfo() {
		Map<String, String> columns = new HashMap<String, String>();

		// 2007-08-29 17:09:50,151 DEBUG [AndroDAOSupportTest.java:125] : ->
		// TOY_ID: String.getId.
		columns.put("TOY_ID", "String.getId");
		// 2007-08-29 17:09:50,151 DEBUG [AndroDAOSupportTest.java:125] : ->
		// name: String.getName.
		columns.put("name", "String.getName");
		// 2007-08-29 17:09:50,151 DEBUG [AndroDAOSupportTest.java:125] : ->
		// CHILD_ID: TChild.getChildren.String.getId.
		columns.put("CHILD_ID", "TChild.getChildren.String.getId");
		// 2007-08-29 17:09:50,152 DEBUG [AndroDAOSupportTest.java:125] : ->
		// MOTHER_ID: TMother.getWoman.String.getId.
		columns.put("MOTHER_ID", "TMother.getWoman.String.getId");

		return columns;
	}

	// 2007-08-29 17:09:50,152 INFO [AndroDAOSupportTest.java:129] : InsertSQL:
	// INSERT /*+ */ INTO WTOYS(TOY_ID,name,CHILD_ID,MOTHER_ID) values (?,?,?,?)
	// 2007-08-29 17:09:50,152 INFO [AndroDAOSupportTest.java:129] : DeleteSQL:
	// DELETE /*+ */ FROM WTOYS WHERE TOY_ID = ?
	// 2007-08-29 17:09:50,152 INFO [AndroDAOSupportTest.java:130] : UpdateSQL:
	// UPDATE /*+ */ WTOYS SET TOY_ID = ?,name = ?,CHILD_ID = ?,MOTHER_ID = ?
	// WHERE TOY_ID = ?

	private static final String insertSQL = "INSERT /*+ */ INTO WTOYS(TOY_ID,name,CHILD_ID,MOTHER_ID) values (?,?,?,?)";

	private static final String updateSQL = "UPDATE /*+ */ WTOYS SET TOY_ID = ?,name = ?,CHILD_ID = ?,MOTHER_ID = ? WHERE TOY_ID = ?";

	private static final String deleteSQL = "DELETE /*+ */ FROM WTOYS WHERE TOY_ID = ?";

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
