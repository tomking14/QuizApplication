package edu.uga.cs.quizapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class MyPagerAdapter extends FragmentStatePagerAdapter {
    private int totalQuestions; // Total number of quiz questions

    public MyPagerAdapter(FragmentManager fm, int totalQuestions) {
        super(fm);
        this.totalQuestions = totalQuestions;
    }

    @Override
    public Fragment getItem(int position) {
        // Create and return the appropriate fragment for the given position
        return createFragment(position);
    }

    @Override
    public int getCount() {
        // Return the total number of questions
        return totalQuestions;
    }

    private Fragment createFragment(int position) {
        // Customize this method to create the individual question fragments
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        args.putString("text", "Question " + (position + 1));
        fragment.setArguments(args);
        return fragment;
    }
}