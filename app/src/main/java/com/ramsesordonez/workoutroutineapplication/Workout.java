package com.ramsesordonez.workoutroutineapplication;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Workout {
    private String workoutId;
    private String name;
    private List<Exercise> exerciseList;

    public Workout() {

    }

    public Workout(String workoutId, String name){
        this.workoutId = workoutId;
        this.name = name;
        this.exerciseList = new ArrayList<>();
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

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }
}
