package com.example.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Answer extends AppCompatActivity {
    private boolean isAnswerTrue;
    private TextView answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        isAnswerTrue = getIntent().getBooleanExtra("answer", false); //загружаем в переменную подсказку
        answer = findViewById(R.id.answer);
        answer.setText(isAnswerTrue?R.string.yes:R.string.no); //тернарный оператор
    }
}