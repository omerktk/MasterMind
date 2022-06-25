package com.zofa.mastermindadmin;

public class Mind {

    public String name;
    public String score;

    public Mind()
    {

    }

    public Mind(String name, String score) {
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
