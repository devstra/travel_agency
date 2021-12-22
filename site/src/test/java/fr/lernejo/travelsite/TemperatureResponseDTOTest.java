package fr.lernejo.travelsite;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class TemperatureResponseDTOTest {
    @Test
    void constructor_should_succeed() {
        TemperatureResponseDTO tempResponse = new TemperatureResponseDTO("France",
            new ArrayList<>());
        Assertions.assertThat(tempResponse.country).isEqualTo("France");
        Assertions.assertThat(tempResponse.temperatures).hasSize(0);
    }
}
