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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ResultsActivity extends AppCompatActivity {
    private Button homeBtn;
    private Button historyBtn;
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
        historyBtn = findViewById(R.id.backToHistoryButton);
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
        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHistoryActivity();
            }
        });

//date method  call
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

       // Save the quiz results to the database
        QuizDBHelper dbHelper = new QuizDBHelper(this);
      dbHelper.insertHistory(date, questionsRight, questionsWrong);

   }

    public void goToMainActivity() {
        Intent intent = new Intent(ResultsActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
    public void goToHistoryActivity() {
        Intent intent = new Intent(ResultsActivity.this, HistoryActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}