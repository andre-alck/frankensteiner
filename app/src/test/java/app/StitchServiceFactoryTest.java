package app;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class StitchServiceFactoryTest {

	@ParameterizedTest
	@ValueSource(strings = { "SQL", "TXT" })
	public void givenValidInput_whenCallingForInstanceImplementation_shouldReturnStitchServiceImplementation(String validInput) {
		StitchService service = StitchServiceFactory.getImplementation(validInput);
		assertNotNull(service);
	}
}
