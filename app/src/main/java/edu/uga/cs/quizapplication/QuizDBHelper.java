package edu.uga.cs.quizapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class QuizDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
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

        //history table
        String CREATE_HISTORY_TABLE = "CREATE TABLE quiz_history (" +
                "history_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "quiz_date TEXT," +
                "questions_correct INTEGER," +
                "questions_wrong INTEGER)";
        db.execSQL(CREATE_HISTORY_TABLE);
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

//insert history method
public void insertHistory(String date, int questionsRight, int questionsWrong) {
    SQLiteDatabase db = getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put("quiz_date", date);
    contentValues.put("questions_correct", questionsRight);
    contentValues.put("questions_wrong", questionsWrong);
    long result = db.insert("quiz_history", null, contentValues);
    if (result == -1) {
        Log.e("QuizDBHelper", "Failed to insert into quiz_history");
    } else {
        Log.d("QuizDBHelper", "Insert into quiz_history successful");
    }
}

//delete above

    public void populateQuestions(Context context) {
        AssetManager asset = context.getAssets();
        SQLiteDatabase db = getWritableDatabase();

        InputStream inStream;
        try {
            inStream = asset.open("StateCapitals.csv");
            BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
            String line = "";
            db.beginTransaction();
            while ((line = buffer.readLine()) != null) {
                String[] columns = line.split(",");
                System.out.println("Inserting: " + Arrays.toString(columns));

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

    public boolean isTableEmpty() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT (*)  FROM quiz_questions",null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count == 0;
    }
    // Method to get random questions for a new quiz
    public List<Map<String, String>> Randomizer(int n) {

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM quiz_questions", null);

        List<Map<String, String>> allQuestions = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Map<String, String> question = new HashMap<>();
                question.put("question_id", String.valueOf(cursor.getInt(0)));
                question.put("state_name", cursor.getString(1));
                question.put("capital_city", cursor.getString(2));
                question.put("additional_city1", cursor.getString(3));
                question.put("additional_city2", cursor.getString(4));
                allQuestions.add(question);
            } while (cursor.moveToNext());
        }
        cursor.close();

        // Randomly select 'n' questions
        Random rand = new Random();
        List<Map<String, String>> selectedQuestions = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int randomIndex = rand.nextInt(allQuestions.size());
            selectedQuestions.add(allQuestions.get(randomIndex));
            allQuestions.remove(randomIndex);  // Remove this question to avoid duplicates
        }
        return selectedQuestions;
    }

    public void clearTable() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("quiz_questions", null, null);
    }


    //fetch history method
    @SuppressLint("Range")
    public List<Map<String, String>> getQuizHistory() {
        List<Map<String, String>> historyList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM quiz_history", null);

        if (cursor.moveToFirst()) {
            do {
                Map<String, String> history = new HashMap<>();
                history.put("quiz_date", cursor.getString(cursor.getColumnIndex("quiz_date"))); // Updated key
                history.put("questions_correct", cursor.getString(cursor.getColumnIndex("questions_correct"))); // Updated key
                history.put("questions_wrong", cursor.getString(cursor.getColumnIndex("questions_wrong"))); // Updated key
                historyList.add(history);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return historyList;
    }




}
