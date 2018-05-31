package com.ramsesordonez.workoutroutineapplication;

import java.util.List;

public class Workout {
    private String workoutId;
    private String name;

    public Workout() {

    }

    public Workout(String workoutId, String name){
        this.workoutId = workoutId;
        this.name = name;
    }

    public String getWorkoutId() {
        return workoutId;
    }

    public String getName() {
        return name;
    }

    public void setWorkoutId(String workoutId) {
        this.workoutId = workoutId;
    }

    public void setName(String name) {
        this.name = name;
    }
}
