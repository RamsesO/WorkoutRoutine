package com.ramsesordonez.workoutroutineapplication;

import java.util.Date;

public class Exercise {
    private String exerciseId;
    private String name;



    public Exercise(String exerciseId, String name){
        this.exerciseId = exerciseId;
        this.name = name;
    }

    public String getExerciseId() {
        return exerciseId;
    }

    public String getName() {
        return name;
    }
}
