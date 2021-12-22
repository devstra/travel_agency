package fr.lernejo.travelsite;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class TemperatureDTOTest {
    @Test
    void constructor_should_succeed() {
        TemperatureDTO temp = new TemperatureDTO("2021-12-19", 23.54);
        Assertions.assertThat(temp.date).isEqualTo("2021-12-19");
        Assertions.assertThat(temp.temperature).isEqualTo(23.54);
    }
}
