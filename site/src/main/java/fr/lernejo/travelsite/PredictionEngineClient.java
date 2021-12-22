package fr.lernejo.travelsite;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PredictionEngineClient {
    @GET("/api/temperature")
    Call<TemperatureResponseDTO> getTemperaturesByCountry(@Query("country") String country);
}
