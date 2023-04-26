package com.example.javalearningtrainticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText userField;
    private EditText mestoField;
    private EditText timeOutField;
    private EditText timeInField;
    private EditText costField;

    private Button button;

    private String user;
    private String mesto;
    private String timeOut;
    private String timeIn;
    private String cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userField = findViewById(R.id.userField);
        mestoField = findViewById(R.id.mestoField);
        timeOutField = findViewById(R.id.timeOutField);
        timeInField = findViewById(R.id.timeInField);
        costField = findViewById(R.id.costField);

        button = findViewById(R.id.button); // Привязка к кнопке
        button.setOnClickListener(listener); //Обработка нажатия кнопки

    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Считывание данных с экрана
            user = userField.getText().toString();
            mesto = mestoField.getText().toString();
            timeOut = timeOutField.getText().toString();
            timeIn = timeInField.getText().toString();
            cost = costField.getText().toString();

            //Создаем объект сущности и через конструктор инициализируем его
            Ticket ticket = new Ticket(user, mesto, timeOut, timeIn, cost);

            Intent intent = new Intent(getApplicationContext(), SecondActivity.class);

            //Запаковка данных в контейнер намерения
            intent.putExtra(Ticket.class.getSimpleName(), ticket); //.class.getSimpleName() является ключом для запаковки в контейнер

            startActivity(intent);

        }
    };

}