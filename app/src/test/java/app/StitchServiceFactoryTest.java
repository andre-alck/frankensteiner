package app;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class StitchServiceFactoryTest {
    
    @ParameterizedTest
    @ValueSource(strings = {"SQL", "TXT"})
    void givenValidInput_whenCallingForInstanceImplementation_shouldReturnStitchServiceImplementation(String validInput) {
        StitchService service = StitchServiceFactory.getImplementation(validInput);
        assertNotNull(service);
    }
}
