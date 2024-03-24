package app;

public class StringUtils {
	public static final String DOT = ".";
	public static final int INDEX_NOT_FOUND = -1;
	public final static String WHITESPACE = "";

	public static boolean isContentEmpty(Object content) {
		if (content == null) {
			return true;
		}

		if (content instanceof String) {
			return ((String) content).trim().equals(StringUtils.WHITESPACE);
		}

		return false;
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
