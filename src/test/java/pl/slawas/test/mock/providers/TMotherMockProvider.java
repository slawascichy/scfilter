package pl.slawas.test.mock.providers;

import java.util.Hashtable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.slawas.helpers.MockLoader;
import pl.slawas.test.entities.TMother;

public class TMotherMockProvider {

	final protected static Logger log = LoggerFactory
			.getLogger(TMotherMockProvider.class);

	public static Hashtable<String, TMother> rows = null;

	static {

		if (rows == null) {
			rows = new Hashtable<String, TMother>();
			List<String[]> readedRows = MockLoader.loadCSV(
					TMotherMockProvider.class,
					"/pl/wp/andro/test/mock/tMotherData.csv");
			for (String[] row : readedRows) {
				rows.put(row[0], new TMother(row[0], row[1]));
				
					
			}
			log.info("Loaded {} rows", rows.size());
		}
	}

	public static enum Fields {
		key, id, parentName;

		public String getValue(TMother row) {
			switch (this) {
			case key:
				return row.getId();
			case id:
				return row.getId();
			case parentName:
				return row.getParentName();
			default:
				return null;
			}
		}

	}

}
