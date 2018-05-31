package com.ramsesordonez.workoutroutineapplication;

public class Exercise {
    private String exerciseId;
    private String name;

    public Exercise() {

    }

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

    public void setExerciseId(String exerciseId) {
        this.exerciseId = exerciseId;
    }

    public void setName(String name) {
        this.name = name;
    }
}
