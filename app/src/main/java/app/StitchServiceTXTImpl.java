package app;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class StitchServiceTXTImpl extends StitchService {

	@Override
	protected Extensions getExtension() {
		return Extensions.TXT;
	}

	@Override
	protected void writeFile(List<FileData> filesData, SortingWay sortingWay) {
		this.sort(filesData, sortingWay);
		try (FileWriter stitchedFileWriter = new FileWriter(this.getResultFileName())) {
			stitchedFileWriter.write(this.getConcatenatedStringThroughFileData(filesData));
		} catch (IOException e) {
			Logger.log(e);
		}
	}

}
