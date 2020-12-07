package com.example.quiz;

import android.content.Intent;
import android.security.keystore.StrongBoxUnavailableException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity<msg> extends AppCompatActivity {
    private TextView textView;
    private Button yesBtn;
    private Button noBtn;
    private Button correct_answer;
    private questions[] questions = new questions[]{
            new questions(R.string.question1, true),
            new questions(R.string.question2, false),
            new questions(R.string.question3, true),
            new questions(R.string.question4, false),
            new questions(R.string.question5, true)};
    private int questionIndex = 0; //изначальный номер вопроса
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("SYSTEM INFO:", "МЕТОД onCreate() ЗАПУЩЕН");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null){
            questionIndex=savedInstanceState.getInt("questionIndex");
        }
        textView = findViewById(R.id.Question);
        textView.setText(questions[questionIndex].getQuestionResID());

        yesBtn = findViewById(R.id.yesBtn);
        noBtn = findViewById(R.id.noBtn);
        correct_answer = findViewById(R.id.correct_answer);

        yesBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(questions[questionIndex].isAnswerTrue())
                Toast.makeText(MainActivity.this, R.string.Correct, Toast.LENGTH_SHORT).show();
                else Toast.makeText(MainActivity.this, R.string.Incorrect, Toast.LENGTH_SHORT).show();
                questionIndex++;
                if (questionIndex>questions.length) questionIndex=0;
            textView.setText(questions[questionIndex].getQuestionResID());
            }
        });

        noBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(questions[questionIndex].isAnswerTrue())
                    Toast.makeText(MainActivity.this, R.string.Incorrect, Toast.LENGTH_SHORT).show();
                else Toast.makeText(MainActivity.this, R.string.Correct, Toast.LENGTH_SHORT).show();
                questionIndex++;
                if (questionIndex>questions.length) questionIndex=0;
                textView.setText(questions[questionIndex].getQuestionResID());
            }
        });
        correct_answer.setOnClickListener(new View.OnClickListener(){ //обработчик нажатия кнопки "Подсказка"
            @Override
            public void onClick(View view){
            Intent intent = new Intent(MainActivity.this, Answer.class); //создаём намерение запустить новую активность Answer
                intent.putExtra("answer", questions[questionIndex].isAnswerTrue()); // передаём в активность информацию
                startActivity(intent); //запускаем активность
            }
        });

    }
    @Override
    public void onStart(){
    super.onStart();
        Log.d("SYSTEM INFO:", "МЕТОД onStart() ЗАПУЩЕН");
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d("SYSTEM INFO:", "МЕТОД onResume() ЗАПУЩЕН");
    }
    @Override
    public void onPause(){
        super.onPause();
        Log.d("SYSTEM INFO:", "МЕТОД onPause() ЗАПУЩЕН");
    }
    @Override
    public void onStop(){
        super.onStop();
        Log.d("SYSTEM INFO:", "МЕТОД onStop() ЗАПУЩЕН");
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.d("SYSTEM INFO:", "МЕТОД onSaveInstanceState() ЗАПУЩЕН");
        savedInstanceState.putInt("questionIndex", questionIndex);
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("SYSTEM INFO:", "МЕТОД onDestroy() ЗАПУЩЕН");
    }
}