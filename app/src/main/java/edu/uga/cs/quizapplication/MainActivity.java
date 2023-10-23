package edu.uga.cs.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = findViewById(R.id.startBtn);
        startButton.setOnClickListener( new overviewButtonClickListener());

    }

    private class overviewButtonClickListener implements View.OnClickListener
    {
        @Override
        public void onClick( View view ) {
            try {
                // Gain access to the app's resources
                Intent intent = new Intent( view.getContext(), ChildActivity.class );
                startActivity( intent );
            } catch (Exception e) {
                // e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Uh oh, something went wrong...", Toast.LENGTH_SHORT).show();

            }

        }
    }
}