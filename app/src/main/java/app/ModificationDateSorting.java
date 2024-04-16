package app;

public class ModificationDateSorting implements SortingWay {

	@Override
	public int sort(FileData fileData, FileData otherFileData) {
		return fileData.getModificationDate().compareTo(otherFileData.getModificationDate());
	}

}
