package app;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class StitchServiceFactory {
    private static Map<Extensions, Class<? extends StitchService>> extensionsImplementations;

    static {
        extensionsImplementations.put(Extensions.SQL, StitchServiceSQLImpl.class);
        extensionsImplementations.put(Extensions.TXT, StitchServiceTXTImpl.class);
    }

    public static StitchService getImplementation(String validInput) {
        Extensions extension = Extensions.getElementBasedOnInputText(validInput);
        Class<? extends StitchService> serviceClass = StitchServiceFactory.extensionsImplementations.get(extension);
        StitchService serviceImpl = null;
        try {
            serviceImpl = serviceClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            Logger.log(e);
        }
        return serviceImpl;
    }

}
