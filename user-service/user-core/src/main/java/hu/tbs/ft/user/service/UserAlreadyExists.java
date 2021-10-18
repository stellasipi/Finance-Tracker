package hu.tbs.ft.user.service;

public class UserAlreadyExists extends Exception {
    public UserAlreadyExists(String errorMessage) {
        super(errorMessage);
    }
}
