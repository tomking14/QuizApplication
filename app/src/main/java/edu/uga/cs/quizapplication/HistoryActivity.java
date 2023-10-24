package edu.uga.cs.quizapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HistoryActivity extends AppCompatActivity {

    private LinearLayout historyLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_layout);

        historyLayout = findViewById(R.id.historyLayout); // Ensure you have this ID in your activity_history.xml

        // Example quiz history items
        addQuizHistoryItem("2023-10-01", 8, 2);
        addQuizHistoryItem("2023-10-05", 9, 1);
    }

    private void addQuizHistoryItem(String date, int rightAnswers, int wrongAnswers) {
        // Inflate the history_layout.xml for each history item
        View historyItemView = getLayoutInflater().inflate(R.layout.history_layout, null);

        // Access the TextView elements using their IDs
        TextView textDate = historyItemView.findViewById(R.id.textDate);
        TextView textRightAnswers = historyItemView.findViewById(R.id.textRightAnswers);
        TextView textWrongAnswers = historyItemView.findViewById(R.id.textWrongAnswers);

        // Set the text values
        textDate.setText("Date: " + date);
        textRightAnswers.setText("Correct: " + rightAnswers);
        textWrongAnswers.setText("Incorrect: " + wrongAnswers);

        // Add the history item view to the parent layout
        historyLayout.addView(historyItemView);
    }

}

