package fr.lernejo.travelsite;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class TemperatureResponseDTOTest {
    @Test
    void constructor_should_succeed() {
        TemperatureResponseDTO tempResponse = new TemperatureResponseDTO("France",
            new ArrayList<>());
        Assertions.assertThat(tempResponse.country).isEqualTo("France");
        Assertions.assertThat(tempResponse.temperatures).hasSize(0);
    }

    @Test
    void getting_average_temperature_should_succeed() {
        TemperatureResponseDTO tempResponse = new TemperatureResponseDTO("France",
            List.of(new TemperatureDTO("2021-12-19", 20.0),
                new TemperatureDTO("2021-12-18", 20.0)));

        TravelDTO travel = tempResponse.getAverageTemperatureAsTravel();
        Assertions.assertThat(travel.country).isEqualTo("France");
        Assertions.assertThat(travel.temperature).isEqualTo(20.0);
    }
}
