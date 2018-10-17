package pl.slawas.test.mock.custom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import pl.slawas.test.entities.TFather;
import pl.slawas.test.entities.TFatherPK;
import pl.slawas.test.mock.TableInfoMock;

/**
 * Testowe dane dla obiektow:
 * 
 * @see pl.wp.ares.test.core.ejb3.data.TFather
 * 
 * @author slawas
 * 
 */
public class TFatherMock implements TableInfoMock {

	private static final String tableName = "FATHERS";

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	public Map<String, String> getColumnInfo() {
		Map<String, String> columns = new HashMap<String, String>();

		// 2007-08-29 17:09:50,142 DEBUG [AndroDAOSupportTest.java:125] : ->
		// FATHER_ID: PK.getPk.String.getFatherId.
		columns.put("FATHER_ID", "TFatherPK.getId.String.getFatherId");
		// 2007-08-29 17:09:50,143 DEBUG [AndroDAOSupportTest.java:125] : ->
		// FATHER_TYPE: PK.getPk.String.getType.
		columns.put("FATHER_TYPE", "TFatherPK.getId.String.getType");
		// 2007-08-29 17:09:50,143 DEBUG [AndroDAOSupportTest.java:125] : ->
		// PARENT_NAME: String.getParentName.
		columns.put("PARENT_NAME", "String.getParentName");

		return columns;
	}

	// 2007-08-29 17:09:50,143 INFO [AndroDAOSupportTest.java:128] : InsertSQL:
	// INSERT /*+ */ INTO FATHERS(FATHER_ID,FATHER_TYPE,PARENT_NAME) values
	// (?,?,?)
	// 2007-08-29 17:09:50,143 INFO [AndroDAOSupportTest.java:129] : DeleteSQL:
	// DELETE /*+ */ FROM FATHERS WHERE FATHER_ID = ? AND FATHER_TYPE = ?
	// 2007-08-29 17:09:50,143 INFO [AndroDAOSupportTest.java:130] : UpdateSQL:
	// UPDATE /*+ */ FATHERS SET FATHER_ID = ?,FATHER_TYPE = ?,PARENT_NAME = ?
	// WHERE FATHER_ID = ? AND FATHER_TYPE = ?

	private static final String insertSQL = "INSERT /*+ */ INTO FATHERS(FATHER_ID,FATHER_TYPE,PARENT_NAME) values (?,?,?)";

	private static final String updateSQL = "UPDATE /*+ */ FATHERS SET FATHER_ID = ?,FATHER_TYPE = ?,PARENT_NAME = ? WHERE FATHER_ID = ? AND FATHER_TYPE = ?";

	private static final String deleteSQL = "DELETE /*+ */ FROM FATHERS WHERE FATHER_ID = ? AND FATHER_TYPE = ?";

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

	private List<TFather> fathers;

	public TFatherMock() {
		setFathers();
	}

	public List<TFather> getFathers() {
		return fathers;
	}

	public void setFathers() {
		Vector<TFather> f = new Vector<TFather>();
		TFather father;
		TFatherPK pk;

		father = new TFather();
		father.setParentName("John");
		pk = new TFatherPK();
		pk.setFatherId(0);
		pk.setType("Natural");
		father.setId(pk);
		f.add(father);

		father = new TFather();
		father.setParentName("Robert");
		pk = new TFatherPK();
		pk.setFatherId(1);
		pk.setType("Natural");
		father.setId(pk);
		f.add(father);

		father = new TFather();
		father.setParentName("Scott");
		pk = new TFatherPK();
		pk.setFatherId(0);
		pk.setType("Surprise");
		father.setId(pk);
		f.add(father);

		this.fathers = f;

	}

}
