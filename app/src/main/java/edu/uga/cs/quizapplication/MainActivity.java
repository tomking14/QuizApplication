package edu.uga.cs.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //delete this below
    private QuizDBHelper dbHelper;

    private Button startButton;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new QuizDBHelper(this);

        dbHelper.clearTable();


        // Check if the database is empty and populate it asynchronously if needed
        if (dbHelper.isTableEmpty()) {
            populateDatabaseAsync();
        }


        startButton = findViewById(R.id.startBtn);
        startButton.setOnClickListener( new overviewButtonClickListener());

    }


    // delete
    @SuppressLint("StaticFieldLeak")
    private void populateDatabaseAsync() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                dbHelper.populateQuestions(getApplicationContext());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Database populated!", Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }


    private class overviewButtonClickListener implements View.OnClickListener
    {
        @Override
        public void onClick( View view ) {
            try {
                // Gain access to the app's resources
                Intent intent = new Intent( view.getContext(), ChildActivity.class );
                startActivity( intent );
            } catch (Exception e) {
                // e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Uh oh, something went wrong...", Toast.LENGTH_SHORT).show();

            }

        }
    }
}