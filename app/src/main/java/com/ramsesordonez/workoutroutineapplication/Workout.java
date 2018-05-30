package com.ramsesordonez.workoutroutineapplication;

import java.util.List;

public class Workout {
    private String mName;
    private String mId;
    private List<Exercise> mExerciseList;

    public Workout(String name, String id) {
        this.mName = name;
        this.mId = id;
    }


    public String getName() {
        return mName;
    }

    public String getId() {
        return mId;
    }

    public List<Exercise> getExerciseList() {
        return mExerciseList;
    }
}
