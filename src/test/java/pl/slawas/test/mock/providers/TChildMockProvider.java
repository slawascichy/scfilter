package pl.slawas.test.mock.providers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.slawas.helpers.MockLoader;
import pl.slawas.test.entities.TChild;
import pl.slawas.test.entities.TFather;
import pl.slawas.test.entities.TFatherPK;
import pl.slawas.test.entities.TMother;

public class TChildMockProvider {

	final protected static Logger log = LoggerFactory
			.getLogger(TChildMockProvider.class);

	public static SortedMap<Integer, TChild> rows = null;

	static {

		Hashtable<TFatherPK, TFather> fathers = TFatherMockProvider.rows;
		Hashtable<String, TMother> mothers = TMotherMockProvider.rows;
		SimpleDateFormat defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		if (rows == null) {
			rows = new TreeMap<Integer, TChild>();

			List<String[]> readedRows = MockLoader.loadCSV(
					TChildMockProvider.class,
					"/pl/wp/andro/test/mock/tChildData.csv");
			for (String[] row : readedRows) {
				TChild child = new TChild();
				/* ID */
				child.setId(row[0]);
				/* mama */
				String motherPk = row[1];
				TMother mother = mothers.get(motherPk);
				child.setMother(mother);
				/* tata */
				TFatherPK fatherPk = new TFatherPK(Integer.parseInt(row[2]), row[3]);
				TFather father = fathers.get(fatherPk);
				child.setFather(father);
				/* imie */
				child.setName(row[4]);
				try {
					Date date = defaultDateFormat.parse(row[5]);
					setBirthDayAndCalculateAge(date, child);
				} catch (ParseException e) {
					log.warn("Nie udalo sie sparsowac daty urodzin: {}", e.getMessage());
				}
				rows.put(Integer.parseInt(child.getId()), child);
			}
			log.info("Loaded {} rows", rows.size());
		}
	}

	/**
	 * Przeliczenie i ustawienie
	 * 
	 * @param defaultDateFormat
	 * @param now
	 * @param row
	 * @param child
	 */
	public static final void setBirthDayAndCalculateAge(
			Date date,
			TChild child) {
		Calendar now = Calendar.getInstance();
		/* data urodzin */
		Calendar birthDate = Calendar.getInstance();
		birthDate.setTime(date);
		child.setBirthDay(birthDate);
		/* wiek */
		int nowYear = now.get(Calendar.YEAR);
		int birthYear = birthDate.get(Calendar.YEAR);
		int age = nowYear - birthYear;
		child.setAge(age);
	}

	public static enum Fields {
		key, 
		id, 
		name, 
		age, 
		birthDay, 
		mother,
		mother_id,
		mother_parentName,
		father,
		father_id,
		father_id_fatherId,
		father_id_type,
		father_parentName;

		public Object getValue(TChild row) {
			switch (this) {
			case key:
				return row.getId();
			case id:
				return row.getId();
			case name:
				return row.getName();
			case age:
				return row.getAge();
			case birthDay:
				return row.getBirthDay();
			case mother:
				return row.getMother();
			case mother_id:
				return row.getMother().getId();
			case mother_parentName:
				return row.getMother().getParentName();
			case father:
				return row.getFather();
			case father_id:
				return row.getFather().getId();
			case father_id_fatherId:
				return row.getFather().getId().getFatherId();
			case father_id_type:
				return row.getFather().getId().getType();
			case father_parentName:
				return row.getFather().getParentName();
			default:
				return null;
			}
		}

	}

}
