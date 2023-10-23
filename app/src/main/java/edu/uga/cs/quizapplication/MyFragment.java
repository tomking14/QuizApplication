package edu.uga.cs.quizapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MyFragment extends Fragment {

    private Button submitButton;
    private boolean isLastQuestion = false; // Set this to true for the last question

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple_view_pager, container, false);

        // Get references to the text and radio buttons
        TextView textView = view.findViewById(R.id.text_view);
        RadioGroup radioGroup = view.findViewById(R.id.radio_group);
        RadioButton radioButton1 = view.findViewById(R.id.radio_button1);
        RadioButton radioButton2 = view.findViewById(R.id.radio_button2);
        RadioButton radioButton3 = view.findViewById(R.id.radio_button3);

        boolean isLastQuestion = getArguments().getBoolean("isLastQuestion", false);

        // Set the text
        String text = getArguments().getString("text");
        textView.setText(text);

        // Handle radio button selection
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio_button1) {
                    // Handle radio button 1 selection
                } else if (checkedId == R.id.radio_button2) {
                    // Handle radio button 2 selection
                } else if (checkedId == R.id.radio_button3) {
                    // Handle radio button 3 selection
                }
            }
        });

        // Check if this is the last question and show the submit button
        if (isLastQuestion) {
            submitButton = view.findViewById(R.id.submit_button);
            submitButton.setVisibility(View.VISIBLE);
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle quiz submission logic here
                    // For example, store the user's answers, calculate results, and show the ResultFragment
                    showResults();
                }
            });
        }

        return view;
    }

    private void showResults() {
        // Create an Intent to start the ResultActivity
        Intent resultIntent = new Intent(getActivity(), ResultsActivity.class);
        // Add any data you want to pass to the ResultActivity using putExtra
        resultIntent.putExtra("someKey", "someValue");

        // Start the ResultActivity
        startActivity(resultIntent);
    }
}