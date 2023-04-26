package com.example.javalearningtrainticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private TextView dataMainActivity;
    private Button button;

    private Ticket ticket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        dataMainActivity = findViewById(R.id.dataMainActivity);

        //Считывание данных из главной активности
        Bundle bundleIntent = getIntent().getExtras();

        if (bundleIntent != null){
            //Инициализируем поле сущности пользователя
            ticket = (Ticket) bundleIntent.getSerializable(Ticket.class.getSimpleName());

            //Вывод на экран данных из главной активности
            dataMainActivity.setText(
                    "Имя: " + ticket.getUser() + "\n" +
                    "Место: " + ticket.getMesto() + "\n" +
                    "Время отправления: " + ticket.getTimeOut() + "\n" +
                    "Время прибытия: " + ticket.getTimeIn() + "\n" +
                    "Стоимость: " + ticket.getCost() + "\n"
            );
        }

        button = findViewById(R.id.button); // Привязка к кнопке
        button.setOnClickListener(listener); //Обработка нажатия кнопки

    }

    //Создаем слушателя кнопки
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Переключение на другой экран
            Intent intent = new Intent(getApplicationContext(), MainActivity.class); //Создание намерения переключения на другой экран
            //FLAG_ACTIVITY_REORDER_TO_FRONT - флаг позволяет переместить вызываемую активность на вершину стека
            //FLAG_ACTIVITY_CLEAR_TOP - флаг очищает все активити кроме той которая запускается
            //FLAG_ACTIVITY_SINGLE_TOP - флаг позволяет намерению действовать аналогично режиму по умолчанию, за исключением ситуации вызова
                //активности находящейся на вершине стека, в данном случае новая активность не создаётся
            //FLAG_ACTIVITY_NO_HISTORY - флаг позволяет не сохранять в стеке активность к которой переводит намерение

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //очищает все активити кроме той которая запускается
            startActivity(intent); //Запуск дополнительной активности

        }
    };

}