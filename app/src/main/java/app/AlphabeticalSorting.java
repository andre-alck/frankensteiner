package app;

public class AlphabeticalSorting implements SortingWay {

	@Override
	public int sort(FileData fileData, FileData otherFileData) {
		return fileData.getName().compareToIgnoreCase(otherFileData.getName());
	}

}
