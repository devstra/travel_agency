package fr.lernejo.travelsite;

import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class TravelSiteService {
    final PredictionEngineClient predictionEngineClient;
    final Map<String, InscriptionDTO> inscriptions = new HashMap<>();
    final List<String> countries = new ArrayList<>();

    public TravelSiteService(PredictionEngineClient predictionEngineClient) throws IOException {
        this.predictionEngineClient = predictionEngineClient;
        InputStream inputStream = this.getClass().getClassLoader()
            .getResourceAsStream("countries.txt");
        String content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        countries.addAll(content.lines().toList());
    }

    public List<TravelDTO> getTravelsByUsername(String userName) {
        if (userName == null || !inscriptions.containsKey(userName)) return new ArrayList<>();
        List<TemperatureResponseDTO> temperatureResponses;
        try {
            temperatureResponses = fetchTemperaturesFromApi();
        } catch (TemperatureAPIException ex) {
            System.err.println(ex.getMessage());
            return new ArrayList<>();
        }
        List<TravelDTO> temperatures = computeAverageTemperature(temperatureResponses);
        InscriptionDTO insc = inscriptions.get(userName);
        TravelDTO userTravel = findUserCountryAvgTemperature(temperatures,
            insc.userCountry);
        return getPossibleDestinations(temperatures, userTravel.temperature,
            insc.minimumTemperatureDistance, insc.weatherExpectation);
    }

    public void registerUser(InscriptionDTO inscriptionObj) {
        inscriptions.put(inscriptionObj.userName, inscriptionObj);
    }

    private List<TravelDTO> computeAverageTemperature(List<TemperatureResponseDTO> temperatureResponses) {
        return temperatureResponses.stream()
            .map(TemperatureResponseDTO::getAverageTemperatureAsTravel).toList();
    }

    private List<TemperatureResponseDTO> fetchTemperaturesFromApi() throws TemperatureAPIException {
        return countries.stream()
            .map(predictionEngineClient::getTemperaturesByCountry)
            .map(call -> {
                try {
                    return call.execute();
                } catch (IOException e) {
                    throw new TemperatureAPIException();
                }
            })
            .map(Response::body).toList();
    }

    private TravelDTO findUserCountryAvgTemperature(List<TravelDTO> travels, String userCountry) {
        return travels.stream()
            .filter(t -> t.country.equalsIgnoreCase(userCountry))
            .findFirst().orElseThrow(RuntimeException::new);
    }

    private List<TravelDTO> getPossibleDestinations(List<TravelDTO> travels,
                                                    double userCountryTemperature,
                                                    double minTempDistance,
                                                    String weatherExpectation) {
        return switch (weatherExpectation) {
            case "WARMER" -> travels.stream()
                .filter(t -> t.temperature >= userCountryTemperature +
                    minTempDistance).toList();
            case "COLDER" -> travels.stream()
                .filter(t -> t.temperature <= userCountryTemperature +
                    minTempDistance).toList();
            default -> throw new RuntimeException();
        };
    }
}
