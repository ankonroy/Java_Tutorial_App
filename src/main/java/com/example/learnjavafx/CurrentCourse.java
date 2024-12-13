package com.example.learnjavafx;

public class CurrentCourse {
    private static String currCourse = "Nai";

    public static String getCurrCourse() {
        return currCourse;
    }

    public static void setCurrCourse(String newCourse) {
        currCourse = newCourse;
    }
}