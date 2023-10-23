package edu.uga.cs.quizapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class QuizDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Quiz.db";

    public QuizDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public void onCreate(SQLiteDatabase db) {
        String CREATE_QUESTIONS_TABLE = "CREATE TABLE quiz_questions (" +
                "question_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "state_name TEXT," +
                "capital_city TEXT," +
                "additional_city1 TEXT," +
                "additional_city2 TEXT)";
        db.execSQL(CREATE_QUESTIONS_TABLE);


        String CREATE_QUIZZES_TABLE = "CREATE TABLE quizzes (" +
                "quiz_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "quiz_date TEXT," +
                "question1 INTEGER," +
                "question2 INTEGER," +
                "question3 INTEGER," +
                "question4 INTEGER," +
                "question5 INTEGER," +
                "question6 INTEGER," +
                "quiz_result INTEGER," +
                "questions_answered INTEGER," +
                "FOREIGN KEY (question1) REFERENCES quiz_questions(question_id)," +
                "FOREIGN KEY (question2) REFERENCES quiz_questions(question_id)," +
                "FOREIGN KEY (question3) REFERENCES quiz_questions(question_id)," +
                "FOREIGN KEY (question4) REFERENCES quiz_questions(question_id)," +
                "FOREIGN KEY (question5) REFERENCES quiz_questions(question_id)," +
                "FOREIGN KEY (question6) REFERENCES quiz_questions(question_id))";
        db.execSQL(CREATE_QUIZZES_TABLE);
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public void populateQuestions(Context context) {
        AssetManager aset = context.getAssets();
        SQLiteDatabase db = getWritableDatabase();

        InputStream inStream;
        try {
            inStream = aset.open("state_capitals.csv");
            BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
            String line = "";
            db.beginTransaction();
            while ((line = buffer.readLine()) != null) {
                String[] columns = line.split(",");
                ContentValues contentValues = new ContentValues();
                contentValues.put("state_name", columns[0]);
                contentValues.put("capital_city", columns[1]);
                contentValues.put("additional_city1", columns[2]);
                contentValues.put("additional_city2", columns[3]);
                db.insert("quiz_questions", null, contentValues);
            }
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (IOException e) {
            e.printStackTrace();
        }







    }
}
