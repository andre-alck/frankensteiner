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
		this.sort(filesData, sortingWay);
		try (OutputStreamWriter stitchedFileWriter = new OutputStreamWriter(new FileOutputStream(this.getResultFileName()), Charset.forName("ISO-8859-1").newEncoder())) {
			stitchedFileWriter.write(this.getConcatenatedStringThroughFileData(filesData));
		} catch (IOException e) {
			Logger.log(e);
		}
	}

}
