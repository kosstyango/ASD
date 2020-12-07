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
    private int res = 0; //кол-во правильных ответов
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        yesBtn.setOnClickListener(new View.OnClickListener(){ //обработчик нажатия кнопки "ДА"
            @Override
            public void onClick(View view){
                if(questions[questionIndex].isAnswerTrue()){ //проверяем правильность ответа
                Toast.makeText(MainActivity.this, R.string.Correct, Toast.LENGTH_SHORT).show();
                res++; //увеличиваем res на 1
                }
                else Toast.makeText(MainActivity.this, R.string.Incorrect, Toast.LENGTH_SHORT).show();
                questionIndex++;
                if (questionIndex>questions.length) { //проверяем, не закончились ли вопросы
                    Intent intent = new Intent(MainActivity.this, Result.class); //создаём намерение запустить новую активность Result
                    intent.putExtra("res", res); // передаём в активность Result информацию о кол-ве правильных ответов
                    startActivity(intent); //запускаем активность Result
                }
            textView.setText(questions[questionIndex].getQuestionResID());
            }
        });

        noBtn.setOnClickListener(new View.OnClickListener(){ //Обработчик нажатия кнопки "НЕТ"
            @Override
            public void onClick(View view){
                if(questions[questionIndex].isAnswerTrue())
                    Toast.makeText(MainActivity.this, R.string.Incorrect, Toast.LENGTH_SHORT).show();
                else Toast.makeText(MainActivity.this, R.string.Correct, Toast.LENGTH_SHORT).show();
                questionIndex++;
                if (questionIndex>questions.length) { //проверяем, не закончились ли вопросы
                    Intent intent = new Intent(MainActivity.this, Result.class); //создаём намерение запустить новую активность Result
                    intent.putExtra("res", res); // передаём в активность Result информацию о кол-ве правильных ответов
                    startActivity(intent); //запускаем активность Result
                }
                textView.setText(questions[questionIndex].getQuestionResID());
            }
                });

        correct_answer.setOnClickListener(new View.OnClickListener(){ //Обработчик нажатия кнопки "Подсказка"
            @Override
            public void onClick(View view){
            Intent intent = new Intent(MainActivity.this, Answer.class); //создаём намерение запустить новую активность Answer
                intent.putExtra("answer", questions[questionIndex].isAnswerTrue()); // передаём в активность информацию
                startActivity(intent); //запускаем активность
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.d("SYSTEM INFO:", "МЕТОД onSaveInstanceState() ЗАПУЩЕН");
        savedInstanceState.putInt("questionIndex", questionIndex);
    }
}