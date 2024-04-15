package app;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class StitchServiceSQLImpl extends StitchService {

	@Override
	protected Extensions getExtension() {
		return Extensions.SQL;
	}

	// TODO: since it's a sql file, there should be a comment indicating the task
	// number etc.
	// also, i'd like to find a way to sort the files before writing to the stitched file.
	// i won't use the StitchService#getConcatenatedStringThroughFileData, it's too generic, can't do much.
	@Override
	protected void writeFile(List<FileData> filesData, SortingWay sortingWay) {
		try (FileWriter stitchedFileWriter = new FileWriter(this.getResultFileName())) {
			stitchedFileWriter.write(this.getConcatenatedStringThroughFileData(filesData));
		} catch (IOException e) {
			Logger.log(e);
		}
	}

}
