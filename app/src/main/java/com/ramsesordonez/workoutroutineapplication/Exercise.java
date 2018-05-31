package com.ramsesordonez.workoutroutineapplication;

import java.util.Date;
import java.util.List;


public class Exercise {

    private String mName;
    private String mId;
    private List<WorkoutSet> setList;

    private class WorkoutSet{
        int repsDone;
        int weight;
        Date date;

        public WorkoutSet(int w) {
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
    }

    public String getName() {
        return mName;
    }

    public String getId() {
        return mId;
    }

    public List<WorkoutSet> getWorkoutList() {
        return setList;
    }
}
