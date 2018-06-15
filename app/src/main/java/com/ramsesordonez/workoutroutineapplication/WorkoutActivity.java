package com.ramsesordonez.workoutroutineapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

public class WorkoutActivity extends AppCompatActivity {

    private Workout workout;
    private DatabaseReference databaseExercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Gson gson = new Gson();
        Intent intent = getIntent();
        String workoutJson = intent.getStringExtra(HomeActivity.WORKOUT_CHOSEN);
        workout = gson.fromJson(workoutJson, Workout.class);

        databaseExercise = FirebaseDatabase.getInstance().getReference("workouts").child(workout.getWorkoutId());

        TextView title = findViewById(R.id.workout_name);
        title.setText(workout.getName().toUpperCase());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewExercise();
            }
        });
    }

    private void createNewExercise(){
        AlertDialog.Builder builder = new AlertDialog.Builder(WorkoutActivity.this);
        builder.setTitle("Create New Exercise");

        final EditText input = new EditText(WorkoutActivity.this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("We Gucci", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = input.getText().toString().trim();
                if(!name.isEmpty()){
                    String id = databaseExercise.push().getKey();
                    Exercise e = new Exercise(name.toLowerCase(), id);
                    Toast.makeText(WorkoutActivity.this, "exercise added", Toast.LENGTH_LONG).show();
                    databaseExercise.child(id).setValue(e);
                }
                else{
                    Toast.makeText(WorkoutActivity.this, "please enter a name", Toast.LENGTH_LONG).show();
                }

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               dialog.cancel();
           }
        });
        builder.show();
    }

}
