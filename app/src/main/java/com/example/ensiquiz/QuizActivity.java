package com.example.ensiquiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class QuizActivity extends AppCompatActivity {

    private TextView indicatorQuestion, questionTitle;

    private SharedPreferences sharedPreferences;

    private Button ButtonRep1, ButtonRep2, ButtonRep3, ButtonRep4;

    private int questionIndex = 1;

    private Question question;

    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        indicatorQuestion = findViewById(R.id.textQuestionNumber);
        questionTitle = findViewById(R.id.textQuestionTitle);
        ButtonRep1 = findViewById(R.id.buttonRep1);
        ButtonRep2 = findViewById(R.id.buttonRep2);
        ButtonRep3 = findViewById(R.id.buttonRep3);
        ButtonRep4 = findViewById(R.id.buttonRep4);

        sharedPreferences = getSharedPreferences("ensiquestions", MODE_PRIVATE);

        initDisplayQuiz();



    }

    public void verifyResponse(View view){
        Button butoon = (Button) view;
        String response = butoon.getText().toString();
        question.isCorrect(response);
        if(question.isCorrect(response)){
            score++;
            Toast.makeText(this, "Bonne réponse", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Mauvaise réponse", Toast.LENGTH_SHORT).show();
        }
        questionIndex++;
        if(questionIndex <= 4) {
            initDisplayQuiz();
        }
        else {
            //get name
            String name = sharedPreferences.getString("name", "Joueur");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Score");
            builder.setMessage("Bien joué  "+name +" ! votre score est de " + score+ "/4");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(QuizActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            });
            builder.create();
            builder.show();
        }

    }

    @SuppressLint("SetTextI18n")
    public void initDisplayQuiz(){
        Gson gson = new Gson();

        String json = sharedPreferences.getString(Integer.toString(questionIndex), null);
        question = gson.fromJson(json, Question.class);

        indicatorQuestion.setText("Question " + questionIndex);
        questionTitle.setText(question.getQuestion());
        ButtonRep1.setText(question.getPropositions().get(0));
        ButtonRep2.setText(question.getPropositions().get(1));
        ButtonRep3.setText(question.getPropositions().get(2));
        ButtonRep4.setText(question.getPropositions().get(3));
    }
}