package com.zofa.mastermindadmin;

public class ModelMind {

    public String name;
    public String score;

    public ModelMind()
    {

    }

    public ModelMind(String name, String score) {
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
