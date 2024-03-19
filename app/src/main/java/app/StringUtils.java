package app;

public class StringUtils {
	private final static String WHITESPACE = "";

	public static boolean isContentEmpty(Object content) {
		if (content == null) {
			return true;
		}

		if (content instanceof String) {
			return ((String) content).trim() == StringUtils.WHITESPACE;
		}

		return false;
	}
}
