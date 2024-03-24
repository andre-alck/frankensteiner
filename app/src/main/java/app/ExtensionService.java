package app;

import app.exceptions.EmptyExtensionException;
import app.exceptions.UnsupportedExtensionException;

public class ExtensionService {
    protected static void checkIfExtensionIsValid(String extension) {
        String extensionWithNoDot = StringUtils.removeDotIfNeeded(extension);

		if (StringUtils.isContentEmpty(extensionWithNoDot)) {
			throw new EmptyExtensionException();
		}

		if (!Extensions.isElementExistent(extensionWithNoDot)) {
			throw new UnsupportedExtensionException();
		}
	}
}
