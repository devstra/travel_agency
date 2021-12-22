
package fr.lernejo.travelsite;

public class TravelDTO {
    public final String country;
    public final double temperature;

    public TravelDTO() {
        this.country = null;
        this.temperature = 1;
    }
    public TravelDTO(String country, double temperature) {
        this.country = country;
        this.temperature = temperature;
    }
}
