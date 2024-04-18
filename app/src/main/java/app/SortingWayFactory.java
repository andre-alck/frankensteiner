package app;

import app.exceptions.UnsupportedSortingWayException;

public class SortingWayFactory {
	private static final String ALPHABETICAL_ARG = "alphabetical";
	private static final String ALPHABETICAL_ABREVIATION_ARG = "a";
	private static final String MODIFICATION_DATE_ARG = "modification";
	private static final String MODIFICATION_DATE_ARG_ABREVIATION_ARG = "m";

	public static SortingWay getSortingWayByArgs(String string) {
		if (string.equalsIgnoreCase(SortingWayFactory.ALPHABETICAL_ARG) || string.equalsIgnoreCase(SortingWayFactory.ALPHABETICAL_ABREVIATION_ARG)) {
			return new AlphabeticalSorting();
		}

		if (string.equalsIgnoreCase(SortingWayFactory.MODIFICATION_DATE_ARG) || string.equalsIgnoreCase(SortingWayFactory.MODIFICATION_DATE_ARG_ABREVIATION_ARG)) {
			return new AlphabeticalSorting();
		}

		throw new UnsupportedSortingWayException();
	}
}
