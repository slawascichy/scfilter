package pl.slawas.test.mock;

import java.util.Map;

public interface TableInfoMock {
	
	public String getTableName();

	public Map<String,String> getColumnInfo();
	
	public String getInsertSQL();
	
	public String getUpdateSQL();

	public String getDeleteSQL();

}
