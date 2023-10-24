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
    private TextView rightResults;
    private TextView wrongResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_fragment);
        Intent intent = getIntent();

        // Retrieve the values from the Intent using the keys
        int questionsRight = intent.getIntExtra("questionsRight", 0); // 0 is the default value if the key is not found
        int questionsWrong = intent.getIntExtra("questionsWrong", 0);

        homeBtn = findViewById(R.id.backToMainButton);
        rightResults = findViewById(R.id.text_view_correct);
        wrongResults = findViewById(R.id.text_view_incorrect);
        rightResults.setText( "You got " + String.valueOf(questionsRight) + " questions correct!");
        wrongResults.setText("You got "+ String.valueOf(questionsWrong) + " questions incorrect...");

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