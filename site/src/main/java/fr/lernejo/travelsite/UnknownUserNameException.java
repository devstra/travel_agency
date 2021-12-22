package fr.lernejo.travelsite;

public class UnknownUserNameException extends RuntimeException {
    public UnknownUserNameException(String userName) {
        super("Unknown userName: " + userName);
    }
}
