package app;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class StitchServiceSQLImpl extends StitchService {

	@Override
	protected Extensions getExtension() {
		return Extensions.SQL;
	}

	@Override
	protected void writeFile(List<FileData> filesData, SortingWay sortingWay) {
		File f = new File(this.getResultFileName());
		try {
			f.createNewFile();
		} catch (IOException e) {
			Logger.log(e);
		}
	}

}
