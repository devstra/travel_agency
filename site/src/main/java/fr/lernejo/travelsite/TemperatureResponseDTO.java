package fr.lernejo.travelsite;

import java.util.List;

public class TemperatureResponseDTO {
    public final String country;
    public final List<TemperatureDTO> temperatures;

    public TemperatureResponseDTO() {
        this.country = null;
        this.temperatures = null;
    }

    public TemperatureResponseDTO(String country, List<TemperatureDTO> temperatures) {
        this.country = country;
        this.temperatures = temperatures;
    }

    public TravelDTO getAverageTemperatureAsTravel() {
        final double sumOfTemperatures = temperatures.stream()
            .map(temp -> temp.temperature)
            .reduce(0.0, Double::sum);
        return new TravelDTO(country,
            sumOfTemperatures / temperatures.size());
    }
}
