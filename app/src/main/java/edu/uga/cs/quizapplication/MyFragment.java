package edu.uga.cs.quizapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import android.widget.TextView;

public class MyFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple_view_pager, container, false);
        TextView textView = view.findViewById(R.id.text_view);
        String text = getArguments().getString("text");
        textView.setText(text);
        return view;
    }
}