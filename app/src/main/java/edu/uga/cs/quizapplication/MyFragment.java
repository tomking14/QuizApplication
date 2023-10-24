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
import android.widget.Toast;
import android.content.Context;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import androidx.lifecycle.ViewModelProvider;

public class MyFragment extends Fragment {

    private Button submitButton;
    private int questionsRight;
    private int questionsWrong;
    private QuizViewModel quizViewModel;
    private String capitalCity; // Store the correct capital city for this question
    private String selectedChoice;
    // Set this to true for the last question



    private boolean Answer = false;
    private boolean history = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple_view_pager, container, false);
        quizViewModel = new ViewModelProvider(requireActivity()).get(QuizViewModel.class);


        Answer = false;
        history = false;


        // Get references to the text and radio buttons
        TextView textView = view.findViewById(R.id.text_view);
        RadioGroup radioGroup = view.findViewById(R.id.radio_group);
        RadioButton radioButton1 = view.findViewById(R.id.radio_button1);
        RadioButton radioButton2 = view.findViewById(R.id.radio_button2);
        RadioButton radioButton3 = view.findViewById(R.id.radio_button3);

        // delete below
        // Fetch the question data from arguments
        String stateName = getArguments().getString("stateName");
        String capitalCity = getArguments().getString("capitalCity");
        String additionalCity1 = getArguments().getString("additionalCity1");
        String additionalCity2 = getArguments().getString("additionalCity2");

        textView.setText("What is the capital of " + stateName + "?");

        // Randomly set the choices
        List<String> choices = Arrays.asList(capitalCity, additionalCity1, additionalCity2);
        Collections.shuffle(choices);
        radioButton1.setText(choices.get(0));
        radioButton2.setText(choices.get(1));
        radioButton3.setText(choices.get(2));


        //delete above


        boolean isLastQuestion = getArguments().getBoolean("isLastQuestion", false);

        // Set the text
//        String text = getArguments().getString("text");
//        textView.setText(text);

        // Handle radio button selection
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selectedRadioButton = group.findViewById(checkedId);
                String selectedValue = selectedRadioButton.getText().toString();
                boolean Correct = selectedValue.equals(capitalCity);



                    if(Correct && !history){
                        quizViewModel.incrementRight();
                        quizViewModel.decrementWrong();
                        history =true;
                    } else if(!Correct && history){
                        quizViewModel.incrementWrong();
                        quizViewModel.decrementRight();
                        history=false;
                    }

                    Correct = history;
                }


});
            // Check if this is the last question and show the submit button
        if(isLastQuestion)

            {
                submitButton = view.findViewById(R.id.submit_button);
                submitButton.setVisibility(View.VISIBLE);
                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        showResults();
                    }
                });
            }

        return view;
        }

    private void showResults() {
        Intent resultIntent = new Intent(getActivity(), ResultsActivity.class);
        questionsRight = quizViewModel.getQuestionsRight();
        questionsWrong = quizViewModel.getQuestionsWrong();
        // Add any data you want to pass to the ResultActivity using putExtra
        resultIntent.putExtra("questionsRight", questionsRight);
        resultIntent.putExtra("questionsWrong", (questionsWrong + 6));

        // Start the ResultActivity
        startActivity(resultIntent);
    }
    public String getSelectedAnswer() {
        return selectedChoice;
    }
}