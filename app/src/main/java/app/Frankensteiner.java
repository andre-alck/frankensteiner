package app;

public class Frankensteiner {
	public static void main(String[] args) {
		String extension = "";
		String path = "";
		SortingWay sortingWay = null;

		PathService.checkIfPathIsValid(extension, path);
		ExtensionService.checkIfExtensionIsValid(extension);

		StitchService service = StitchServiceFactory.getImplementation(extension);
		service.stitch(path, sortingWay);
	}
}
