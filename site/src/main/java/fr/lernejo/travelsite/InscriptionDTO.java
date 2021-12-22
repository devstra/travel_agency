package fr.lernejo.travelsite;

public class InscriptionDTO {
    public final String userEmail;
    public final String userName;
    public final String userCountry;
    public final String weatherExpectation;
    public final int minimumTemperatureDistance;

    public InscriptionDTO() {
        this.userEmail = null;
        this.userName = null;
        this.userCountry = null;
        this.weatherExpectation = null;
        this.minimumTemperatureDistance = 1;
    }
    public InscriptionDTO(String userEmail, String userName, String userCountry,
                          String weatherExpectation, int minimumTemperatureDistance) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userCountry = userCountry;
        this.weatherExpectation = weatherExpectation;
        this.minimumTemperatureDistance = minimumTemperatureDistance;
    }
}
