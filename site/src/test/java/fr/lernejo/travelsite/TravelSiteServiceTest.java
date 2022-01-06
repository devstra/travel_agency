package fr.lernejo.travelsite;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import java.io.IOException;

public class TravelSiteServiceTest {
    private final TravelSiteService service = new TravelSiteService(Mockito.mock(PredictionEngineClient.class));

    public TravelSiteServiceTest() throws IOException {
    }

    @Test
    void registering_valid_user_should_succeed() {
        InscriptionDTO inscription = new InscriptionDTO("machin@truc.com", "machin",
            "France", "WARMER", 2);
        service.registerUser(inscription);
        Assertions.assertThat(service.inscriptions.size()).isEqualTo(1);
    }

    @Test
    void getting_travels_for_invalid_user_should_fail() {
        Assertions.assertThat(service.getTravelsByUsername("XXXX").size()).isEqualTo(0);
    }
}
