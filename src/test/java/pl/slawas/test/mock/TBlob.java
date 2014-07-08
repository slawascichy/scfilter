package pl.slawas.test.mock;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Arrays;

public class TBlob implements Blob {

	private final byte[] value;
	
	/**
	 * @param value
	 */
	public TBlob(byte[] value) {
		super();
		this.value = value;
	}

	public void free() throws SQLException {

	}

	public InputStream getBinaryStream() throws SQLException {

		return null;
	}

	public InputStream getBinaryStream(long pos, long length) throws SQLException {

		return null;
	}

	public byte[] getBytes(long pos, int length) throws SQLException {

		return value;
	}

	public long length() throws SQLException {

		return value.length;
	}

	public long position(byte[] pattern, long start) throws SQLException {

		return 0;
	}

	public long position(Blob pattern, long start) throws SQLException {

		return 0;
	}

	public OutputStream setBinaryStream(long pos) throws SQLException {

		return null;
	}

	public int setBytes(long pos, byte[] bytes) throws SQLException {

		return 0;
	}

	public int setBytes(long pos, byte[] bytes, int offset, int len) throws SQLException {

		return 0;
	}

	public void truncate(long len) throws SQLException {

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TBlob [value=" + Arrays.toString(value) + "]";
	}

}
