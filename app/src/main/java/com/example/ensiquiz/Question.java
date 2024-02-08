package com.example.ensiquiz;

import java.util.List;
import java.util.Objects;

public class Question {

    private String question;

    private List<String> propositions;

    private int answerIndex;

    public Question(String question, List<String> propositions, int answerIndex) {
        this.question = question;
        this.propositions = propositions;
        this.answerIndex = answerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getPropositions() {
        return propositions;
    }

    public int getAnswerIndex() {
        return answerIndex;
    }

    public String getAnswer() {
        return propositions.get(answerIndex);
    }

    public boolean isCorrect(String  answer) {
        return Objects.equals(this.getAnswer(), answer);
    }


}
