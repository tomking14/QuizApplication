package edu.uga.cs.quizapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ResultFragment extends Fragment {

    private TextView textViewCorrect;
    private TextView textViewIncorrect;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.results_fragment, container, false);

        // Get references to the views
        textViewCorrect = view.findViewById(R.id.text_view_correct);
        textViewIncorrect = view.findViewById(R.id.text_view_incorrect);

        // Replace these values with the actual quiz results
        int correctAnswers = getCorrectAnswers(); // Get the correct answers count from your data source
        int incorrectAnswers = getIncorrectAnswers(); // Get the incorrect answers count from your data source

        // Update the TextViews with results
        textViewCorrect.setText("Correct Answers: " + correctAnswers);
        textViewIncorrect.setText("Incorrect Answers: " + incorrectAnswers);

        return view;
    }

    // Add methods to retrieve actual quiz results from your data source
    private int getCorrectAnswers() {
        // Implement the logic to fetch the correct answers count
        return 3; // Replace with the actual value
    }

    private int getIncorrectAnswers() {
        // Implement the logic to fetch the incorrect answers count
        return 1; // Replace with the actual value
    }
}