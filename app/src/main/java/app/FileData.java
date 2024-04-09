package app;

public class FileData implements Comparable<FileData> {
	private SortingWay sortingWay;
	private StringBuilder content;
	private String name;

	public FileData() {
		this.sortingWay = new AlphabeticalSorting();
	}

	public FileData(SortingWay sortingWay) {
		this.sortingWay = sortingWay;
	}

	public void setSortingWay(SortingWay sortingWay) {
		this.sortingWay = sortingWay;
	}

	@Override
	public int compareTo(FileData otherFileData) {
		return this.sortingWay.sort(this, otherFileData);
	}

	public StringBuilder getContent() {
		return content;
	}

	public void setContent(StringBuilder content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
