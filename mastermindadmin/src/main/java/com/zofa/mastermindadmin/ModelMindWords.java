package com.zofa.mastermindadmin;

public class ModelMindWords {

    public String name;
    public String score;

    public ModelMindWords()
    {

    }

    public ModelMindWords(String name, String score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public String getScore() {
        return score;
    }
}
