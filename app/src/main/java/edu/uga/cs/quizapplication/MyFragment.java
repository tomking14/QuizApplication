package edu.uga.cs.quizapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MyFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple_view_pager, container, false);

        // Get references to the text and radio buttons
        TextView textView = view.findViewById(R.id.text_view);
        RadioGroup radioGroup = view.findViewById(R.id.radio_group);
        RadioButton radioButton1 = view.findViewById(R.id.radio_button1);
        RadioButton radioButton2 = view.findViewById(R.id.radio_button2);
        RadioButton radioButton3 = view.findViewById(R.id.radio_button3);

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

        return view;
    }
}