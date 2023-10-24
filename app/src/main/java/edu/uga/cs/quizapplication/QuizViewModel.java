package edu.uga.cs.quizapplication;

import androidx.lifecycle.ViewModel;

public class QuizViewModel extends ViewModel {
    private int questionsRight = 0;
    private int questionsWrong = 0;

    public int getQuestionsRight() {
        return questionsRight;
    }

    public int getQuestionsWrong() {
        return questionsWrong;
    }

    public void incrementRight() {
        questionsRight++;
    }

    public void incrementWrong() {
        questionsWrong++;
    }

    public void decrementWrong() {questionsWrong--;}

    public void decrementRight(){questionsRight--;}
}