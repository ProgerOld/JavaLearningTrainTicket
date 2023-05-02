package com.example.javalearningtrainticket;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class NewFormActivity extends AppCompatActivity {
    private EditText userField;
    private EditText mestoField;
    private EditText dateOutField;
    private EditText timeInField;
    private EditText costField;

    private Button button;

    private String user;
    private String mesto;
    private String timeOut;
    private String timeIn;
    private String cost;

    private int mYear, mMonth, mDay, mHour, mMinute;

    Calendar dateAndTimeOut = Calendar.getInstance();
    Calendar dateAndTimeIn = Calendar.getInstance();

    private final String TAG = this.getClass().getSimpleName();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_form);

        userField = findViewById(R.id.userField);
        mestoField = findViewById(R.id.mestoField);
        dateOutField = findViewById(R.id.dateOutField);
        timeInField = findViewById(R.id.timeInField);
        costField = findViewById(R.id.costField);

        button = findViewById(R.id.button); // Привязка к кнопке
        button.setOnClickListener(listener); //Обработка нажатия кнопки

        setInitialDateTimeOut(); //Устанавливаем начальное значение
        //setInitialDateTimeIn(); //Устанавливаем начальное значение

        dateOutField.setOnClickListener(dateOutOutlistener);// Слушатель нажатия на поле
        timeInField.setOnClickListener(timeInOutlistener);// Слушатель нажатия на поле

    }

    // установка начальных даты и времени Отправления
    private void setInitialDateTimeOut() {

        dateOutField.setText(DateUtils.formatDateTime(this,
                dateAndTimeOut.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
                        | DateUtils.FORMAT_SHOW_TIME));
    }

    // установка начальных даты и времени Прибытия
    private void setInitialDateTimeIn() {

        timeInField.setText(DateUtils.formatDateTime(this,
                dateAndTimeIn.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
                        | DateUtils.FORMAT_SHOW_TIME));
    }

    // установка обработчика выбора времени
    TimePickerDialog.OnTimeSetListener obTimeIn = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            //Устанавливаем дату
            dateAndTimeIn.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTimeIn.set(Calendar.MINUTE, minute);
            setInitialDateTimeIn(); //Обновляем дату и время в поле
        }
    };

    // установка обработчика выбора даты
    DatePickerDialog.OnDateSetListener obDateIn = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            //Устанавливаем дату
            dateAndTimeIn.set(Calendar.YEAR, year);
            dateAndTimeIn.set(Calendar.MONTH, monthOfYear);
            dateAndTimeIn.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            //создаем диалоговое окна выбора времени.
            new TimePickerDialog(
                    NewFormActivity.this,
                    obTimeIn,
                    dateAndTimeOut.get(Calendar.HOUR_OF_DAY),
                    dateAndTimeOut.get(Calendar.MINUTE), true
            ).show();

        }
    };


    // установка обработчика выбора времени Отправления
    TimePickerDialog.OnTimeSetListener obTimeOut = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            //Устанавливаем дату
            dateAndTimeOut.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTimeOut.set(Calendar.MINUTE, minute);

            setInitialDateTimeOut(); //Обновляем дату и время в поле
        }
    };

    // установка обработчика выбора даты Отправления
    DatePickerDialog.OnDateSetListener obDateOut = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            //Устанавливаем дату
            dateAndTimeOut.set(Calendar.YEAR, year);
            dateAndTimeOut.set(Calendar.MONTH, monthOfYear);
            dateAndTimeOut.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            Log.v(TAG, view.toString()); //В лог

            //создаем диалоговое окна выбора времени.
            new TimePickerDialog(
                    NewFormActivity.this,
                    obTimeOut,
                    dateAndTimeOut.get(Calendar.HOUR_OF_DAY),
                    dateAndTimeOut.get(Calendar.MINUTE), true
            ).show();

        }
    };


    //Нажатие на поле Дата и время оправления
    private View.OnClickListener dateOutOutlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //создаем диалоговое окна выбора даты.
            new DatePickerDialog(
                    NewFormActivity.this,
                    obDateOut,
                    dateAndTimeOut.get(Calendar.YEAR),
                    dateAndTimeOut.get(Calendar.MONTH),
                    dateAndTimeOut.get(Calendar.DAY_OF_MONTH)
            ).show();

            /* Вариант 1
            // Get Current Date
            // в строке ниже мы получаем наш день, месяц и год.
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            //создаем переменную для диалогового окна выбора даты.
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    // в строке ниже мы передаем контекст.
                    NewFormActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            //устанавливаем дату для edittext.
                            //dateOutField.setText(dayOfMonth + "." + (monthOfYear + 1) + "." + year);

                            //Добавить вызов времени

                            //создаем переменную для диалогового окна выбора времени.
                            TimePickerDialog timePickerDialog = new TimePickerDialog(
                                    // в строке ниже мы передаем контекст.
                                    NewFormActivity.this,
                                    new TimePickerDialog.OnTimeSetListener() {
                                        @Override
                                        public void onTimeSet(TimePicker view, int hourOfDay,
                                                              int minute) {
                                            //устанавливаем время для edittext.
                                            dateOutField.setText(dayOfMonth + "." + (monthOfYear + 1) + "." + year + " " + hourOfDay + ":" + minute);
                                        }
                                    }, mHour, mMinute, false);
                            timePickerDialog.show(); //вызываем show, чтобы отобразить наше диалоговое окно выбора времени.

                        }
                    },
                    // в строке ниже мы вводим год, месяц и день для выбранной даты в нашем окне выбора даты.
                    mYear, mMonth, mDay);
            // наконец-то мы вызываем show, чтобы отобразить наше диалоговое окно выбора даты.
            datePickerDialog.show();
            */

        }
    };

    //Нажатие на поле Дата и время прибытия
    private View.OnClickListener timeInOutlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //создаем диалоговое окна выбора даты.
            new DatePickerDialog(
                    NewFormActivity.this,
                    obDateIn,
                    dateAndTimeOut.get(Calendar.YEAR),
                    dateAndTimeOut.get(Calendar.MONTH),
                    dateAndTimeOut.get(Calendar.DAY_OF_MONTH)
            ).show();

        }
    };

    //Нажатие кнопки оформить билет
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Считывание данных с экрана
            user = userField.getText().toString();
            mesto = mestoField.getText().toString();
            timeOut = dateOutField.getText().toString();
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