package com.ramsesordonez.workoutroutineapplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Exercise {

    private String mName;
    private String mId;
    private List<ExerciseSet> setList;

    private class ExerciseSet {
        int repsDone;
        int weight;
        Date date;

        public ExerciseSet(int w) {
            this.weight = w;
            date = new Date();
        }

        public void setRepsDone(int s) {
            this.repsDone = s;
        }

    }

    public Exercise(String name, String id) {
        this.mName = name;
        this.mId = id;
        setList = new ArrayList<>();
    }

    public String getName() {
        return mName;
    }

    public String getId() {
        return mId;
    }

    public List<ExerciseSet> getWorkoutList() {
        return setList;
    }
}
