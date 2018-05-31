package com.ramsesordonez.workoutroutineapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ViewHolder> {

    private Context wContext;

    private ArrayList<Exercise> wDataSet;


    public WorkoutAdapter(ArrayList<Exercise> dataSet, Context context) {
        wDataSet = dataSet;
        wContext = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView exercise_name;

        public ViewHolder(View v) {
            super(v);
            exercise_name = v.findViewById(R.id.exercise_name);
        }

    }

    @Override
    public WorkoutAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(wContext);
        View view = layoutInflater.inflate(R.layout.exercise_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Exercise exercise = wDataSet.get(position);
        TextView name = holder.exercise_name;
        name.setText(exercise.getName());
    }

    @Override
    public int getItemCount() {
        return wDataSet.size();
    }
}
