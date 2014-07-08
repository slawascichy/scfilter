package pl.slawas.test.mock;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Clob;
import java.sql.SQLException;

public class TClob implements Clob {

	private final String value;

	/**
	 * @param value
	 */
	public TClob(String value) {
		super();
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Clob#free()
	 */
	public void free() throws SQLException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Clob#getAsciiStream()
	 */
	public InputStream getAsciiStream() throws SQLException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Clob#getCharacterStream()
	 */
	public Reader getCharacterStream() throws SQLException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Clob#getCharacterStream(long, long)
	 */
	public Reader getCharacterStream(long pos, long length) throws SQLException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Clob#getSubString(long, int)
	 */
	public String getSubString(long pos, int length) throws SQLException {

		return this.value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Clob#length()
	 */
	public long length() throws SQLException {
		return this.value.length();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Clob#position(java.sql.Clob, long)
	 */
	public long position(Clob searchstr, long start) throws SQLException {

		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Clob#position(java.lang.String, long)
	 */
	public long position(String searchstr, long start) throws SQLException {

		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Clob#setAsciiStream(long)
	 */
	public OutputStream setAsciiStream(long pos) throws SQLException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Clob#setCharacterStream(long)
	 */
	public Writer setCharacterStream(long pos) throws SQLException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Clob#setString(long, java.lang.String, int, int)
	 */
	public int setString(long pos, String str, int offset, int len) throws SQLException {

		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Clob#setString(long, java.lang.String)
	 */
	public int setString(long pos, String str) throws SQLException {

		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Clob#truncate(long)
	 */
	public void truncate(long len) throws SQLException {

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TClob [value=" + value + "]";
	}

}
