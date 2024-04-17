package app;

public class Frankensteiner {
	public static void main(String[] args) {
		String extension = "";
		String path = "";

		PathService.checkIfPathIsValid(extension, path);
		ExtensionService.checkIfExtensionIsValid(extension);

		StitchServiceFactory.getImplementation(extension);
	}
}
