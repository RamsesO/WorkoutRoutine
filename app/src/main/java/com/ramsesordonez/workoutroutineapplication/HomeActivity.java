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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class HomeActivity extends AppCompatActivity {

    private EditText workoutTitle;
    private Button buttonAddWorkout;
    private RecyclerView workoutRecyclerView;
    private RecyclerView.Adapter wAdapter;
    private RecyclerView.LayoutManager wLayoutManager;

    private DatabaseReference databaseWorkouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        databaseWorkouts = FirebaseDatabase.getInstance().getReference("workouts");

        workoutTitle = findViewById(R.id.text_workout_title);
        buttonAddWorkout = findViewById(R.id.button_add_workout);
        workoutRecyclerView = findViewById(R.id.workout_recycler_view);

        wLayoutManager = new LinearLayoutManager(this);
        workoutRecyclerView.setLayoutManager(wLayoutManager);

//        wAdapter = new WorkoutAdapter(databaseWorkouts);
//        workoutRecyclerView.setAdapter(wAdapter);

        buttonAddWorkout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                addWorkout();
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

