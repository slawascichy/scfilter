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
package pl.slawas.filter.pagination;

/**
 * SortOrder
 * 
 * @author Sławomir Cichy &lt;slawas@slawas.pl&gt;
 * @version $Revision: 1.1 $
 * 
 */
public enum SortOrderEnum {

	DESCENDING(1, "descending"), ASCENDING(2, "ascending");

	private final int code;
	private final String name;

	private SortOrderEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * @return the {@link #code}
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @return the {@link #name}
	 */
	public String getName() {
		return name;
	}

	public static SortOrderEnum fromCode(int key) {
		for (SortOrderEnum so : SortOrderEnum.values()) {
			if (key == so.getCode()) {
				return so;
			}
		}
		return null;
	}

	public static SortOrderEnum fromCode(Integer key) {
		if (key == null) {
			return null;
		}
		return fromCode(key.intValue());
	}

	public static SortOrderEnum fromName(String code) {
		for (SortOrderEnum so : SortOrderEnum.values()) {
			if (so.getName().equalsIgnoreCase(code)) {
				return so;
			}
		}
		return null;
	}

}
