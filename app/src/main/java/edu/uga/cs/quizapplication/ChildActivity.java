package edu.uga.cs.quizapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ChildActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_activity); // Use the correct layout here

        viewPager = findViewById(R.id.viewPager);
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
    }

    private class MyPagerAdapter extends FragmentStatePagerAdapter {

        private final int NUM_PAGES = 6;

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
            args.putString("text", "What is the capital of Page " + (position + 1));
            fragment.setArguments(args);
            return fragment;
        }
    }
}