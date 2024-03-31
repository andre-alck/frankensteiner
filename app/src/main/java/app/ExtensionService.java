package app;

import java.util.List;
import java.util.NoSuchElementException;

import app.exceptions.EmptyExtensionException;
import app.exceptions.UnsupportedExtensionException;

public class ExtensionService {
	public static void checkIfExtensionIsValid(String extension) {
		String extensionWithNoDot = StringUtils.removeDotIfNeeded(extension);

		if (StringUtils.isContentEmpty(extensionWithNoDot)) {
			throw new EmptyExtensionException();
		}

		if (!ExtensionService.isElementExistent(extensionWithNoDot)) {
			throw new UnsupportedExtensionException();
		}
	}

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
