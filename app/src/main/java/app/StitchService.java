package app;

public abstract class StitchService {
	private String extension;
	private String path;

	private static final String DOT = ".";
	private static final int INDEX_NOT_FOUND = -1;

	public StitchService(String extension, String path) {
		this.setExtension(extension);
		this.setPath(path);
	}

	protected void checkIfExtensionIsValid() {
		this.removeDotIfNeeded();

		if (StringUtils.isContentEmpty(this.getExtension())) {
			throw new EmptyExtensionException();
		}

		if (this.isExtensionUnsupported()) {
			throw new UnsupportedExtensionException();
		}
	}

	protected void removeDotIfNeeded() {
		int indexOfDot = this.getExtension().indexOf(StitchService.DOT);
		if (indexOfDot == StitchService.INDEX_NOT_FOUND) {
			return;
		}

		StringBuilder extension = new StringBuilder();
		extension.append(this.getExtension());
		extension.deleteCharAt(indexOfDot);
		this.setExtension(extension.toString());
	}

	protected boolean isExtensionUnsupported() {
		return !Extensions.isElementExistent(this.getExtension());
	}

	protected String getExtension() {
		return extension;
	}

	protected void setExtension(StringBuilder extension) {
		this.setExtension(extension.toString());
	}

	protected void setExtension(String extension) {
		this.extension = extension;
	}

	protected String getPath() {
		return path;
	}

	protected void setPath(String path) {
		this.path = path;
	}
}
