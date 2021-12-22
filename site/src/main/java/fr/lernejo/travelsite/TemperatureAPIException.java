package fr.lernejo.travelsite;

public class TemperatureAPIException extends RuntimeException{
    public TemperatureAPIException(){
        super("A problem occurred with the temperature API");
    }
}
