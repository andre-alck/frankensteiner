package app;

public enum Extensions {
	SQL, TXT;

	public static boolean isElementExistent(String element) {
		for (int i = 0; i < Extensions.values().length; i++) {
			if (((String) Extensions.values()[i].name()).equalsIgnoreCase(element)) {
				return true;
			}
		}

		return false;
	}
}
