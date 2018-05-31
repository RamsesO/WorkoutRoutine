package com.ramsesordonez.workoutroutineapplication;

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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HomeActivity extends AppCompatActivity {

    private EditText workoutTitle;
    private Button buttonAddWorkout;
    private RecyclerView workoutRecyclerView;
    private RecyclerView.Adapter wAdapter;
    private RecyclerView.LayoutManager wLayoutManager;

    private DatabaseReference databaseWorkouts;
    private ArrayList<Exercise> exerciseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        databaseWorkouts = FirebaseDatabase.getInstance().getReference("workouts");

        workoutTitle = findViewById(R.id.text_workout_title);
        buttonAddWorkout = findViewById(R.id.button_add_workout);
        workoutRecyclerView = findViewById(R.id.workout_recycler_view);

        exerciseList = new ArrayList<>();

        wLayoutManager = new LinearLayoutManager(this);
        workoutRecyclerView.setLayoutManager(wLayoutManager);

        wAdapter = new WorkoutAdapter(exerciseList, this);
        workoutRecyclerView.setAdapter(wAdapter);


        buttonAddWorkout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                addWorkout();
            }
        });

        databaseWorkouts.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Exercise a = dataSnapshot.getValue(Exercise.class);
                System.out.println(a.getName() + " " + a.getExerciseId());
                exerciseList.add(a);
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
            Exercise e = new Exercise(id, name);

            databaseWorkouts.child(id).setValue(e);

            Toast.makeText(this, "exercise added", Toast.LENGTH_LONG).show();

        }
        else{
            Toast.makeText(this, "enter a name", Toast.LENGTH_LONG).show();
        }
    }
}

