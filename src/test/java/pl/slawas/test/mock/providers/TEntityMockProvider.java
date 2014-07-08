package pl.slawas.test.mock.providers;

import java.util.Hashtable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.slawas.helpers.MockLoader;
import pl.slawas.helpers.Strings;
import pl.slawas.test.entities.TEntity;
import pl.slawas.test.entities.TIndex;
import pl.slawas.test.entities._ITEntity;

public class TEntityMockProvider {

	final protected static Logger log = LoggerFactory
			.getLogger(TEntityMockProvider.class);

	public static Hashtable<String, _ITEntity> rows = null;

	public static Hashtable<String, TIndex> searchIndex = null;

	static {

		if (rows == null) {
			rows = new Hashtable<String, _ITEntity>();
			searchIndex = new Hashtable<String, TIndex>();
			List<String[]> readedRows = MockLoader.loadCSV(
					TEntityMockProvider.class,
					"/pl/wp/andro/test/mock/tEntityData.csv");
			for (String[] row : readedRows) {
				rows.put(row[0], new TEntity(row[0], row[1], row[2], row[3], row[4]));
				
				String id = 
					Strings.lpad(row[0], "0", 10);
				String date = 
					Strings.replaceAll(row[2], "-", "");
				String price = 
					Strings.lpad(row[3], "0", 10);
				searchIndex.put(
						row[0], new TIndex(row[0], id, row[1], date, price, row[4]));
					
			}
			log.info("Loaded {} rows", rows.size());
		}
	}

	public static enum Fields {
		key, id, name, date, price, user;

		public String getValue(_ITEntity row) {
			switch (this) {
			case key:
				return row.getId();
			case id:
				return row.getId();
			case name:
				return row.getName();
			case date:
				return row.getDate();
			case price:
				return row.getPrice();
			case user:
				return row.getUser();
			default:
				return null;
			}
		}

		public String getValue(TIndex row) {
			switch (this) {
			case key:
				return row.getKey();
			case id:
				return row.getId();
			case name:
				return row.getName();
			case date:
				return row.getDate();
			case price:
				return row.getPrice();
			case user:
				return row.getUser();
			default:
				return null;
			}
		}

	}

}
