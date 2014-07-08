/*
 * Slawas.pl Copyright &copy; 2011-2012 
 * http://slawas.pl 
 * All rights reserved.
 * 
 * THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN
 * NO EVENT SHALL SŁAWOMIR CICHY BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package pl.slawas.filter.impl.lucene;

import java.util.regex.Pattern;

import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;

import pl.slawas.filter.beans.Accuracy;

/**
 * LuceneHelper
 * 
 * @author Sławomir Cichy &lt;slawas@slawas.pl&gt;
 * @version $Revision: 1.1 $
 * 
 */
public class LuceneHelper {

	public static final String LUCENE_ESCAPE_CHARS = ""
			+ "[\\\\+\\-\\!\\(\\)\\:\\^\\]\\{\\}\\~\\*\\?]";

	public static final Pattern LUCENE_PATTERN = Pattern
			.compile(LUCENE_ESCAPE_CHARS);

	public static final String REPLACEMENT_STRING = "\\\\$0";

	/**
	 * Transformacja trafności wyniku zapytania do trafności {@link Occur}
	 * 
	 * @param accuracy
	 *            trafność systemu ARES
	 * @return trafność WPLucene, domyślnie zwraca {@link Occur#MUST}
	 */
	public Occur getOccur(Accuracy accuracy) {
		if (accuracy != null) {
			switch (accuracy) {
			case MUST:
				return Occur.MUST;
			case NOT:
				return Occur.MUST_NOT;
			case SHOULD:
				return Occur.SHOULD;
			default:
				return Occur.MUST;
			}
		} else
			return Occur.MUST;
	}

	/**
	 * Escapuje string który chcemy wyszukać
	 * 
	 * @param input
	 *            string który chcemy wyszukać
	 * @return wyescapowany string
	 */
	public static String escapeChars(String input) {
		if (input != null) {
			return QueryParser.escape(input);
		} else {
			return input;
		}
	}

}
