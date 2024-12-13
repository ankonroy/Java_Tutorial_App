package com.example.learnjavafx;

public class CurrentTopic {
    private static String currTopicName = "Nai";
    private static String currContent = "Nai";

    public static String getCurrTopic() {
        return currTopicName;
    }

    public static String getCurrContent() {
        return currContent;
    }

    public static void setCurrTopic(String newTopicName) {
        currTopicName = newTopicName;
        currContent = Utill.giveTopicDetails(CurrentCourse.getCurrCourse(), newTopicName);
    }
}