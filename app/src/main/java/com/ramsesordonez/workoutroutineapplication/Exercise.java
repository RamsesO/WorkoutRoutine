package com.ramsesordonez.workoutroutineapplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Exercise {

    private String name;
    private String id;
    private Map<String, ExerciseSet> setList;

    private class ExerciseSet {
        int repsDone;
        int weight;
        Date date;

        public ExerciseSet() {}

        public ExerciseSet(int w) {
            this.weight = w;
            date = new Date();
        }

        public int getRepsDone() {
            return repsDone;
        }

        public void setRepsDone(int repsDone) {
            this.repsDone = repsDone;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }

    public Exercise() {}

    public Exercise(String name, String id) {
        this.name = name;
        this.id = id;
        setList = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, ExerciseSet> getSetList() {
        return setList;
    }

    public void setSetList(Map<String, ExerciseSet> setList) {
        this.setList = setList;
    }
}
