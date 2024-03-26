package app;

import java.util.List;

public class Logger {
	public static void log(Object content) {
		Logger.logToOutput(content);
	}

	public static void log(Object[] content) {
		List.of(content).forEach(t -> {
			Logger.logToOutput(content);
		});
	}

	private static void logToOutput(Object content) {
		System.out.println(content.toString());
	}
}
