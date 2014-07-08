package pl.slawas.filter;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

import pl.slawas.helpers.Strings;

/**
 * The user enters text into a search box. This class is used to parse that text
 * into specific search terms (or tokens). It eliminates common words, and
 * allows for the quoting of text, using double quotes.
 */
public final class SearchBoxParser {

	/**
	 * oparetor logiczny "musi" dla klauzuli występowania słowa w wynikiku
	 * filtrowania
	 */
	public static String AND_OPERATOR = "+";

	/**
	 * oparetor logiczny "może" dla klauzuli występowania słowa w wynikiku
	 * filtrowania
	 */
	public static String OR_OPERATOR = "|";

	/**
	 * oparetor logiczny "nie występuje" dla klauzuli występowania słowa w
	 * wynikiku filtrowania
	 */
	public static String MINUS_OPERATOR = "-";

	/**
	 * @param aSearchText
	 *            is non-null, but may have no content, and represents what the
	 *            user has input in a search box.
	 */
	public SearchBoxParser(String aSearchText) {
		if (aSearchText == null) {
			throw new IllegalArgumentException("Search Text cannot be null.");
		}
		aSearchText = Strings.replaceAll(aSearchText, AND_OPERATOR + "\"", "\""
				+ AND_OPERATOR);
		aSearchText = Strings.replaceAll(aSearchText, OR_OPERATOR + "\"", "\""
				+ OR_OPERATOR);
		aSearchText = Strings.replaceAll(aSearchText, MINUS_OPERATOR + "\"",
				"\"" + MINUS_OPERATOR);
		fSearchText = aSearchText;
	}

	/**
	 * Parse the user's search box input into a Set of String tokens.
	 * 
	 * @return Set of Strings, one for each word in fSearchText; here "word" is
	 *         defined as either a lone word surrounded by whitespace, or as a
	 *         series of words surrounded by double quotes, "like this"; also,
	 *         very common words (and, the, etc.) do not qualify as possible
	 *         search targets.
	 */
	public Vector<String> parseSearchText() {
		Vector<String> result = new Vector<String>();

		boolean returnTokens = true;
		String currentDelims = fWHITESPACE_AND_QUOTES;
		StringTokenizer parser = new StringTokenizer(fSearchText,
				currentDelims, returnTokens);

		String token = null;
		while (parser.hasMoreTokens()) {
			token = parser.nextToken(currentDelims);
			if (!isDoubleQuote(token)) {
				addNonTrivialWordToResult(token, result);
			} else {
				currentDelims = flipDelimiters(currentDelims);
			}
		}
		return result;
	}

	// PRIVATE //
	private String fSearchText;

	private static final Set<String> fCOMMON_WORDS = new HashSet<String>();

	private static final String fDOUBLE_QUOTE = "\"";

	// the parser flips between these two sets of delimiters
	private static final String fWHITESPACE_AND_QUOTES = " \t\r\n\"";

	private static final String fQUOTES_ONLY = "\"";

	/**
	 * Very common words against which searches will not be performed.
	 */
	static {
		fCOMMON_WORDS.add(AND_OPERATOR);
		fCOMMON_WORDS.add(OR_OPERATOR);
		fCOMMON_WORDS.add(MINUS_OPERATOR);
		fCOMMON_WORDS.add("*");
		fCOMMON_WORDS.add("?");
	}

	/**
	 * Use to determine if a particular word entered in the search box should be
	 * discarded from the search.
	 */
	private boolean isCommonWord(String aSearchTokenCandidate) {
		return fCOMMON_WORDS.contains(aSearchTokenCandidate);
	}

	private boolean textHasContent(String aText) {
		return (aText != null) && (!aText.trim().equals(""));
	}

	private void addNonTrivialWordToResult(String aToken, Vector<String> aResult) {
		if (textHasContent(aToken) && !isCommonWord(aToken.trim())) {
			aResult.add(aToken.trim());
		}
	}

	private boolean isDoubleQuote(String aToken) {
		return aToken.equals(fDOUBLE_QUOTE);
	}

	private String flipDelimiters(String aCurrentDelims) {
		String result = null;
		if (aCurrentDelims.equals(fWHITESPACE_AND_QUOTES)) {
			result = fQUOTES_ONLY;
		} else {
			result = fWHITESPACE_AND_QUOTES;
		}
		return result;
	}
}
