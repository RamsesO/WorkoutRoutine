package com.ramsesordonez.workoutroutineapplication;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Workout {
    private String workoutId;
    private String name;
    private Map<String, Exercise> exerciseList;

    public Workout() {

    }

    public Workout(String workoutId, String name){
        this.workoutId = workoutId;
        this.name = name;
        this.exerciseList = new HashMap<>();
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

    public Map<String, Exercise> getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(Map<String, Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }
}
