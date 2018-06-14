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

    private ArrayList<Workout> wDataSet;

    // interface for clicks
    private OnItemClicked onClick;

    public interface OnItemClicked {
        void onItemClick(int position);
    }

    public WorkoutAdapter(ArrayList<Workout> dataSet, Context context) {
        wDataSet = dataSet;
        wContext = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView workout_name;

        public ViewHolder(View v) {
            super(v);
            workout_name = v.findViewById(R.id.workout_name);
        }

    }

    @Override
    public WorkoutAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(wContext);
        View view = layoutInflater.inflate(R.layout.workout_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Workout workout = wDataSet.get(position);
        TextView name = holder.workout_name;
        name.setText(workout.getName());

        holder.workout_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return wDataSet.size();
    }

    public void setOnClick(OnItemClicked onClick) {
        this.onClick = onClick;
    }
}
