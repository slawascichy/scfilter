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
public class TToyFromManMock implements TableInfoMock {

	private final static String tableName = "MTOYS";

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	public Map<String, String> getColumnInfo() {
		Map<String, String> columns = new HashMap<String, String>();

		columns.put("TOY_ID", "String.getId");

		columns.put("name", "String.getName");
		// 2007-08-29 17:09:50,142 DEBUG [AndroDAOSupportTest.java:125] : ->
		// FATHER_ID: PK.getPk.String.getFatherId.
		columns.put("FATHER_ID", "TFather.getMan.TFatherPK.getId.String.getFatherId");
		// 2007-08-29 17:09:50,143 DEBUG [AndroDAOSupportTest.java:125] : ->
		// FATHER_TYPE: PK.getPk.String.getType.
		columns.put("FATHER_TYPE", "TFather.getMan.TFatherPK.getId.String.getType");

		return columns;
	}

	// InsertSQL: INSERT /*+ */ INTO MTOYS(TOY_ID,name) values (?,?)
	// DeleteSQL: DELETE /*+ */ FROM MTOYS WHERE TOY_ID = ?
	// UpdateSQL: UPDATE /*+ */ MTOYS SET TOY_ID = ?,name = ? WHERE TOY_ID = ?

	private final static String insertSQL = "INSERT /*+ */ INTO MTOYS(TOY_ID,name,FATHER_ID,FATHER_TYPE) values (?,?,?,?)";

	private final static String deleteSQL = "DELETE /*+ */ FROM MTOYS WHERE TOY_ID = ?";

	private final static String updateSQL = "UPDATE /*+ */ MTOYS SET TOY_ID = ?,name = ?,FATHER_ID = ?,FATHER_TYPE = ? WHERE TOY_ID = ?";

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
