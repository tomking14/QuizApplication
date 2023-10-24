package edu.uga.cs.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import android.os.Bundle;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {
    private Button homeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_fragment); // Set the layout for the result screen
        homeBtn = findViewById(R.id.backToMainButton);
        homeBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
            goToMainActivity();
        }
    });


        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity();
            }
        });
    }

    public void goToMainActivity() {
        Intent intent = new Intent(ResultsActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}