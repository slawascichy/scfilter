package pl.slawas.test.mock.providers;

import java.util.Hashtable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.slawas.helpers.MockLoader;
import pl.slawas.test.entities.TFather;
import pl.slawas.test.entities.TFatherPK;

public class TFatherMockProvider {

	final protected static Logger log = LoggerFactory
			.getLogger(TFatherMockProvider.class);

	public static Hashtable<TFatherPK, TFather> rows = null;

	static {

		if (rows == null) {
			rows = new Hashtable<TFatherPK, TFather>();
			List<String[]> readedRows = MockLoader.loadCSV(
					TFatherMockProvider.class,
					"/pl/wp/andro/test/mock/tFatherData.csv");
			for (String[] row : readedRows) {
				TFatherPK pk = new TFatherPK(Integer.parseInt(row[0]), row[1]);
				rows.put(pk, new TFather(pk, row[2]));
			}
			log.info("Loaded {} rows", rows.size());
		}
	}

	public static enum Fields {
		key, 
		id,
		id_fatherId,
		id_type, 
		parentName;

		public Object getValue(TFather row) {
			switch (this) {
			case key:
				return row.getId();
			case id:
				return row.getId();
			case id_fatherId:
				return row.getId().getFatherId();
			case id_type:
				return row.getId().getType();
			case parentName:
				return row.getParentName();
			default:
				return null;
			}
		}

	}

}
