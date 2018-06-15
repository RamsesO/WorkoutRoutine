package com.ramsesordonez.workoutroutineapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder> {
    private Context eContext;

    private ArrayList<Exercise> eDataSet;

    // interface for clicks
    private ExerciseAdapter.OnItemClicked onClick;

    public interface OnItemClicked {
        void onItemClick(int position);
    }

    public ExerciseAdapter(ArrayList<Exercise> dataSet, Context context) {
        eDataSet = dataSet;
        eContext = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView exercise_name;

        public ViewHolder(View v) {
            super(v);
            exercise_name = v.findViewById(R.id.exercise_name);
        }

    }

    @Override
    public ExerciseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(eContext);
        View view = layoutInflater.inflate(R.layout.exercise_item, parent, false);

        ExerciseAdapter.ViewHolder viewHolder = new ExerciseAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ExerciseAdapter.ViewHolder holder, final int position) {
        Exercise exercise = eDataSet.get(position);
        TextView name = holder.exercise_name;
        name.setText(exercise.getName());

        holder.exercise_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eDataSet.size();
    }

}
