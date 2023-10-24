package edu.uga.cs.quizapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;
import java.util.Map;

public class ChildActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private int totalQuestions = 6;
    private boolean isLastQuestion = false;// Replace with the total number of questions in your quiz
    private List<Map<String, String>> questions; // Store the questions here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_activity);


        // Initialize QuizDBHelper and fetch random questions
        QuizDBHelper dbHelper = new QuizDBHelper(this);
        questions = dbHelper.Randomizer(totalQuestions); // Calls the Randomizer method to get questions

        viewPager = findViewById(R.id.viewPager);
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

    }

    private class MyPagerAdapter extends FragmentStatePagerAdapter {
        private final int NUM_PAGES = totalQuestions;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return createFragment(position);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        private Fragment createFragment(int position) {
            MyFragment fragment = new MyFragment();
            Bundle args = new Bundle();
//            args.putString("text", "What is the capital of Page " + (position + 1));
//            args.putBoolean("isLastQuestion", position == (NUM_PAGES - 1));

            Map<String, String> currentQuestion = questions.get(position);

            args.putString("stateName", currentQuestion.get("state_name"));
            args.putString("capitalCity", currentQuestion.get("capital_city"));
            args.putString("additionalCity1", currentQuestion.get("additional_city1"));
            args.putString("additionalCity2", currentQuestion.get("additional_city2"));
            args.putBoolean("isLastQuestion", position == (NUM_PAGES - 1));
            // delete above
            fragment.setArguments(args);
            return fragment;
        }
    }


}