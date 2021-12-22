package fr.lernejo.travelsite;

public class InvalidInscriptionException extends RuntimeException {
    public InvalidInscriptionException() {
        super("Unknown InscriptionDTO object");
    }
}
