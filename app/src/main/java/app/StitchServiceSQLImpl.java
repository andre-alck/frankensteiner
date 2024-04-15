package app;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class StitchServiceSQLImpl extends StitchService {

	@Override
	protected Extensions getExtension() {
		return Extensions.SQL;
	}

	@Override
	protected void writeFile(List<FileData> filesData, SortingWay sortingWay) {
		try (FileWriter stitchedFileWriter = new FileWriter(this.getResultFileName())) {
			stitchedFileWriter.write(this.getConcatenatedStringThroughFileData(filesData));
		} catch (IOException e) {
			Logger.log(e);
		}
	}

}
