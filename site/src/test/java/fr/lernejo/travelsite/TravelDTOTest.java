package fr.lernejo.travelsite;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class TravelDTOTest {
    @Test
    void constructor_should_succeed() {
        TravelDTO travel = new TravelDTO("France", 22.0);
        Assertions.assertThat(travel.country).isEqualTo("France");
        Assertions.assertThat(travel.temperature).isEqualTo(22.0);
    }
}
