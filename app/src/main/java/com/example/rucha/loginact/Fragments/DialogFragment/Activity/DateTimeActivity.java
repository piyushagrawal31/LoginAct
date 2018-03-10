package com.example.rucha.loginact.Fragments.DialogFragment.Activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.rucha.loginact.R;
import com.example.rucha.loginact.Fragments.DialogFragment.Fragments.DatePickerFragment;
import com.example.rucha.loginact.Fragments.DialogFragment.Fragments.TimePickerFragment;

public class DateTimeActivity extends AppCompatActivity implements DatePickerFragment.OnDatePicked,
             TimePickerFragment.OnTimePicked, View.OnClickListener {

    private TextView txtCurrTime;
    private TextView txtCurrDate;
    private Button btnTimePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtCurrTime = findViewById(R.id.txtCurrTime);
        txtCurrDate = findViewById(R.id.txtCurrDate);

        btnTimePicker = findViewById(R.id.btnTime);
        btnTimePicker.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnTimePicker){
            TimePickerFragment fragment = TimePickerFragment.newInstance();
            fragment.show(getSupportFragmentManager(), "Choose Time");
        }
        else {}

    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//        Set result appropriately
        txtCurrDate.setText(String.valueOf(datePicker.getMonth()));
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            txtCurrTime.setText(String.valueOf(timePicker.getHour()));
        }
    }
}
