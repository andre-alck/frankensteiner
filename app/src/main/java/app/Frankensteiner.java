package app;

import app.exceptions.InsufficientArgumentsException;

public class Frankensteiner {
	private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

	public static void main(String[] args) {
		if (args.length != Frankensteiner.EXPECTED_NUMBER_OF_ARGUMENTS) {
			throw new InsufficientArgumentsException();
		}

		String path = args[0];
		String extension = args[1];
		SortingWay sortingWay = SortingWayFactory.getSortingWayByArgs(args[2]);

		PathService.checkIfPathIsValid(extension, path);
		ExtensionService.checkIfExtensionIsValid(extension);

		StitchService service = StitchServiceFactory.getImplementation(extension);
		service.stitch(path, sortingWay);
	}

}
