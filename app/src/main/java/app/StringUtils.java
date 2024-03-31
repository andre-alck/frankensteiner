package app;

public class StringUtils {
	public static final String DOT = ".";
	public static final int INDEX_NOT_FOUND = -1;
	public final static String WHITESPACE = "";

	public static boolean isContentEmpty(String content) {
		if (content == null) {
			return true;
		}

		return content.trim().equals(StringUtils.WHITESPACE);
	}

	public static String removeDotIfNeeded(String content) {
		int indexOfDot = content.indexOf(StringUtils.DOT);
		if (indexOfDot == StringUtils.INDEX_NOT_FOUND) {
			return content;
		}

		StringBuilder noDotContent = new StringBuilder();
		noDotContent.append(content);
		noDotContent.deleteCharAt(indexOfDot);
		return noDotContent.toString();
	}
}
