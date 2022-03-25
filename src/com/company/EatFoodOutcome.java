package com.company;

public enum EatFoodOutcome {
    ATE_BAD_MAX_FOOD("you ate some very bad food") ,
    ATE_GOOD_MAX_FOOD("you ate some very good food") ,
    ATE_BAD_CURRENT_FOOD("you ate some bad food") ,
    ATE_GOOD_CURRENT_FOOD("you ate some good food") ,

    ;

    private String outcome;
    EatFoodOutcome(String outcome) {
        this.outcome = outcome;
    }

    public String getOutcome() {
        return outcome;
    }
}
