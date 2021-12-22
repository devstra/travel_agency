package fr.lernejo.prediction;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class TemperatureControllerTest {
    @Test
    void getTemperature_valid_country(@Autowired MockMvc mockMvc) throws Exception {
        Pattern datePattern = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}"
            , Pattern.CASE_INSENSITIVE);
        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/temperature?country=France"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.country"
                , is("France")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.temperatures"
                , isA(ArrayList.class)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.temperatures"
                , hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.temperatures[0].date"
                , isA(String.class)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.temperatures[0].date"
                , matchesRegex(datePattern)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.temperatures[0].temperature"
                , isA(double.class)));
    }

    @Test
    void getTemperature_invalid_country(@Autowired MockMvc mockMvc) throws Exception {
        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/temperature?country=XXXX"))
            .andExpect(MockMvcResultMatchers.status().isExpectationFailed())
            .andExpect(MockMvcResultMatchers.content().string("Unknown country: XXXX"));
    }
}
