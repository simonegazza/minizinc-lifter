package anonymous.code.lift.utils;

/**
 * String utility class. It has some helpful methods to manipulate strings.
 */
public class StringUtils {

	/**
	 * Removes a portion of a string starting from the first occurrence of a
	 * given start substring up to the first occurrence of a given end substring
	 * that appears after it.
	 * <p>
	 * The method reconstructs the original string by concatenating the part
	 * before the start substring and the part after the end substring
	 * (including the end delimiter).
	 * </p>
	 *
	 * @param s        the original input string
	 * @param startSub the substring marking the beginning of the section to
	 *                     remove (inclusive)
	 * @param endSub   the substring marking the end of the section to remove
	 *                     (inclusive)
	 *
	 * @return a new string with the specified section removed; if either
	 *             substring is not found, the original string is returned
	 *             unchanged
	 */
	public static String delete(String s, String startSub, String endSub) {
		int start = s.indexOf(startSub);
		if (start == -1)
			return s;

		int end = s.indexOf(endSub, start);
		if (end == -1)
			return s;

		return s.substring(0, start) + s.substring(end + endSub.length());
	}
}
