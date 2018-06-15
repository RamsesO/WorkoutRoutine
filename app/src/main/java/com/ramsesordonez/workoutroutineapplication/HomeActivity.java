package com.ramsesordonez.workoutroutineapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.util.ArrayList;


public class HomeActivity extends AppCompatActivity implements WorkoutAdapter.OnItemClicked{

    public static final String WORKOUT_CHOSEN = "com.ramsesordonez.workoutroutineapplication.WORKOUT";

    private EditText workoutTitle;
    private Button buttonAddWorkout;
    private RecyclerView workoutRecyclerView;
    private WorkoutAdapter wAdapter;
    private RecyclerView.LayoutManager wLayoutManager;

    private DatabaseReference databaseWorkouts;
    private ArrayList<Workout> workoutList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        databaseWorkouts = FirebaseDatabase.getInstance().getReference("workouts");

        workoutTitle = findViewById(R.id.text_workout_title);
        buttonAddWorkout = findViewById(R.id.button_add_workout);
        workoutRecyclerView = findViewById(R.id.workout_recycler_view);

        workoutList = new ArrayList<>();

        wLayoutManager = new LinearLayoutManager(this);
        workoutRecyclerView.setLayoutManager(wLayoutManager);

        wAdapter = new WorkoutAdapter(workoutList, this);
        workoutRecyclerView.setAdapter(wAdapter);

        wAdapter.setOnClick(this);

        buttonAddWorkout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                addWorkout();
            }
        });

        databaseWorkouts.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Workout a = dataSnapshot.getValue(Workout.class);
                workoutList.add(a);
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

    private void addWorkout(){
        String name = workoutTitle.getText().toString().trim();

        if(!TextUtils.isEmpty(name)){
            String id = databaseWorkouts.push().getKey();
            Workout w = new Workout(id, name.toLowerCase());

            databaseWorkouts.child(id).setValue(w);
            workoutList.add(w);

            Toast.makeText(this, "workout added", Toast.LENGTH_LONG).show();

        }
        else{
            Toast.makeText(this, "enter a name", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemClick(int position){
        Intent intent = new Intent(this, WorkoutActivity.class);
        Workout w = workoutList.get(position);
        Gson gson = new Gson();
        intent.putExtra(WORKOUT_CHOSEN, gson.toJson(w));
        startActivity(intent);
    }
}

