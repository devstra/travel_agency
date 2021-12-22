package fr.lernejo.travelsite;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class SiteControllerTest {

    @Test
    void postInscription_valid(@Autowired MockMvc mockMvc) throws Exception {
        String payload = new Gson().toJson(new InscriptionDTO("machin@truc.com",
            "machin", "France", "WARMER", 2));
        mockMvc
            .perform(MockMvcRequestBuilders.post("/api/inscription")
                .contentType("application/json").content(payload))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void postInscription_invalid(@Autowired MockMvc mockMvc) throws Exception {
        String payload = new Gson().toJson(new InscriptionDTO("machin@truc.com",
            "machin", "France", "XXXX", 2));
        mockMvc
            .perform(MockMvcRequestBuilders.post("/api/inscription")
                .contentType("application/json").content(payload))
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void getUserTravels_invalid(@Autowired MockMvc mockMvc) throws Exception {
        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/travels?userName=test"))
            .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
