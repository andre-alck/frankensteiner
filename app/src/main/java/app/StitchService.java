package app;

public abstract class StitchService {
    protected String extension;
    protected String path;

    private final static String WHITESPACE = "";
    private final static String DOT = ".";
    private static final int INDEX_NOT_FOUND = -1;

    public StitchService(String extension, String path) {
        this.setExtension(extension);
        this.setPath(path);

        this.checkIfExtensionIsValid();
    }

    private void checkIfExtensionIsValid() {
        this.removeDotIfNeeded();

        if(this.isContentEmpty(this.getExtension())) {
            throw new RuntimeException("fixme - add custom exception for dealing with missing extension");
        }

        if(this.isExtensionUnsupported()) {
            throw new RuntimeException("fixme - add custom exception for unsupported extension");
        }
    }

    private void removeDotIfNeeded() {
        int indexOfDot = this.getExtension().indexOf(StitchService.DOT);
        if(indexOfDot == StitchService.INDEX_NOT_FOUND) {
            return;
        }

        StringBuilder extension = new StringBuilder();
        extension.append(this.getExtension());
        extension.deleteCharAt(indexOfDot);
        this.setExtension(extension.toString());
    }

    private boolean isContentEmpty(String content) {
        return content == null || content.trim() == StitchService.WHITESPACE;
    }

    private boolean isExtensionUnsupported() {
        // use Extensions enum
        throw new RuntimeException("missing impl!");
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
