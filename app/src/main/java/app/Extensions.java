package app;

import java.util.List;
import java.util.NoSuchElementException;

import app.exceptions.UnsupportedExtensionException;

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

	public static Extensions getElementBasedOnInputText(String inputText) {
		try {
			return List.of(Extensions.values()).stream().filter(t -> t.name().equalsIgnoreCase(inputText)).findFirst()
					.get();
		} catch (NoSuchElementException e) {
			throw new UnsupportedExtensionException(e.getMessage(), e.getCause());
		}

	}
}
