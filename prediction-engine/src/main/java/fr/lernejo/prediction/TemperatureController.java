package fr.lernejo.prediction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class TemperatureController {
    private final TemperatureService temperatureService;

    public TemperatureController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @GetMapping("/api/temperature")
    ResponseEntity<?> getTemperature(@RequestParam("country") String country) {
        try {
            final double temp1 = temperatureService.getTemperature(country);
            final double temp2 = temperatureService.getTemperature(country);
            final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            final LocalDate oneDayBefore = LocalDate.now().minusDays(1);
            return new ResponseEntity<>(new TemperatureResponseDTO(country, List.of(
                new TemperatureDTO(dtf.format(oneDayBefore), temp1),
                new TemperatureDTO(dtf.format(oneDayBefore.minusDays(2)), temp2)))
                , HttpStatus.OK);
        } catch (UnknownCountryException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
