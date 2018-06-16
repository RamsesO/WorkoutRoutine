package com.ramsesordonez.workoutroutineapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.util.ArrayList;

public class WorkoutActivity extends AppCompatActivity implements ExerciseAdapter.OnItemClicked {
    private Workout workout;
    private DatabaseReference databaseExerciseList;

    private RecyclerView exerciseRecyclerView;
    private ExerciseAdapter eAdapter;
    private RecyclerView.LayoutManager eLayoutManager;

    private ArrayList<Exercise> exerciseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // getting intent from home activity and initializing current workout
        Gson gson = new Gson();
        Intent intent = getIntent();
        String workoutJson = intent.getStringExtra(HomeActivity.WORKOUT_CHOSEN);
        workout = gson.fromJson(workoutJson, Workout.class);

        TextView title = findViewById(R.id.workout_name);
        title.setText(workout.getName().toUpperCase());

        // initializing firebase reference
        databaseExerciseList = FirebaseDatabase.getInstance().getReference("workouts").child(workout.getWorkoutId()).child("exerciseList");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewExercise();
            }
        });

        exerciseRecyclerView = findViewById(R.id.exercise_recycler_view);
        exerciseList = new ArrayList<>();
        eLayoutManager = new LinearLayoutManager(this);
        exerciseRecyclerView.setLayoutManager(eLayoutManager);

        eAdapter = new ExerciseAdapter(exerciseList, this);
        exerciseRecyclerView.setAdapter(eAdapter);

        eAdapter.setOnClick(this);

        databaseExerciseList.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Exercise e = dataSnapshot.getValue(Exercise.class);
                exerciseList.add(e);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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
                    String id = databaseExerciseList.push().getKey();
                    Exercise e = new Exercise(name.toLowerCase(), id);
                    Toast.makeText(WorkoutActivity.this, "exercise added", Toast.LENGTH_LONG).show();
                    databaseExerciseList.child(id).setValue(e);
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

    @Override
    public void onItemClick(int position){
        Toast.makeText(this, "exercise clicked", Toast.LENGTH_LONG).show();
    }
}
