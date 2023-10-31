package edu.uga.cs.quizapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Map;

public class HistoryActivity extends AppCompatActivity {

    private LinearLayout historyLayout;
    private QuizDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_layout);


        dbHelper = new QuizDBHelper(this);


        historyLayout = findViewById(R.id.historyLayout); // Ensure you have this ID in your activity_history.xml

        // Example quiz history items
     //  addQuizHistoryItem("2023-10-01", 8, 2);
       // addQuizHistoryItem("2023-10-05", 9, 1);
        // Fetch data from database
      List<Map<String, String>> quizHistoryItems = dbHelper.getQuizHistory(); // This assumes you have a method like this in your QuizDBHelper class

        for (Map<String, String> item : quizHistoryItems) {
            String date = item.get("quiz_date");
            String correct = item.get("questions_correct");
            String wrong = item.get("questions_wrong");

            if (date != null && correct != null && wrong != null) {
                addQuizHistoryItem(date, Integer.parseInt(correct), Integer.parseInt(wrong));
            } else {
                Log.e("HistoryActivity", "One or more keys are missing or null: "
                        + "Date: " + date + ", Correct: " + correct + ", Wrong: " + wrong);
            }
        }
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

