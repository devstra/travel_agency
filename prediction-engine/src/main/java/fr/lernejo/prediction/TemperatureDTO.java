package fr.lernejo.prediction;

public class TemperatureDTO {
    public final String date;
    public final double temperature;

    public TemperatureDTO() {
        this.date = null;
        this.temperature = 1;
    }

    public TemperatureDTO(String date, double temperature) {
        this.date = date;
        this.temperature = temperature;
    }
}
