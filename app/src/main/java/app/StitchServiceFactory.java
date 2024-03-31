package app;

import java.util.Map;

public class StitchServiceFactory {
	private static Map<Extensions, Class<? extends StitchService>> extensionsImplementations = Map.of(Extensions.SQL,
			StitchServiceSQLImpl.class, Extensions.TXT, StitchServiceTXTImpl.class);

	public static StitchService getImplementation(String validInput) {
		final Extensions extension = Extensions.getElementBasedOnInputText(validInput);
		final Class<? extends StitchService> serviceClass = StitchServiceFactory.extensionsImplementations.get(extension);
		StitchService serviceImpl = null;
		try {
			serviceImpl = serviceClass.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			Logger.log(e);
		}
		return serviceImpl;
	}

}
