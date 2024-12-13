package com.example.learnjavafx;

public class CurrentUser {
    private static String currUsername = "Nai";

    public static String getCurrUsername() {
        return currUsername;
    }

    public static void setCurrUsername(String newUsername) {
        currUsername = newUsername;
    }
}
