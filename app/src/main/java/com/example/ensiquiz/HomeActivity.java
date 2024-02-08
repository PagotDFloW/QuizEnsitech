package com.example.ensiquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.util.Arrays;

public class HomeActivity extends AppCompatActivity {

    private EditText editText;

    private ImageView redCross, greyCross;

    private Button startButtonEnabled, startButtonDisabled;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        editText = findViewById(R.id.editTextName);
        redCross = findViewById(R.id.imageViewRed);
        greyCross = findViewById(R.id.imageViewGrey);
        startButtonEnabled = findViewById(R.id.buttonEnabled);
        startButtonDisabled = findViewById(R.id.buttonDisabled);

        sharedPreferences = getSharedPreferences("ensiquestions", MODE_PRIVATE);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Nothing to do here
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                startButtonDisabled.setVisibility(s.length() > 0 ? Button.GONE : Button.VISIBLE);
                startButtonEnabled.setVisibility(s.length() > 0 ? Button.VISIBLE : Button.GONE);
                redCross.setVisibility(s.length() > 0 ? ImageView.VISIBLE : ImageView.GONE);
                greyCross.setVisibility(s.length() > 0 ? ImageView.GONE : ImageView.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Nothing to do here
            }
        });

        fillQuestions();
    }

    public void clearEditText(View view) {
        editText.setText("");
    }

    public void startQuiz(View view) {
        // Save the name
        sharedPreferences.edit().putString("name", editText.getText().toString()).apply();
        // Start the quiz
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }

    public void fillQuestions() {

        Gson gson = new Gson();
        // Get the questions
        Question questionOne = new Question("Le premier président de la 4eme république ?", Arrays.asList("Vinecent AURIOL", "René COTY", "Albert LEBRUN", "Paul DOUMER"),
                0);
        sharedPreferences.edit().putString("1", gson.toJson(questionOne)).apply();

        Question questionTwo = new Question("Le nombre de pays dans l'union européenne ?", Arrays.asList("15", "24", "27", "32"), 2);
        sharedPreferences.edit().putString("2", gson.toJson(questionTwo)).apply();


        Question questionThree = new Question("La langue la moins parlé au monde ?", Arrays.asList("L'artchi", "Le Siibo", "Le Rotokas", "Le Piraha"), 0);
        sharedPreferences.edit().putString("3", gson.toJson(questionThree)).apply();


        Question questionFour = new Question("Le pays le plus peuplé au monde ?", Arrays.asList("USA", "Chine", "Inde", "Indonesie"), 2);
        sharedPreferences.edit().putString("4", gson.toJson(questionFour)).apply();

    }
}