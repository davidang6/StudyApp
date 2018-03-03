package com.example.davidang.studyapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Schedule extends AppCompatActivity{

    CalendarView calendarView;
    Button back;
    Map<String, ArrayList<Map<String, String>>> allSchedules = new HashMap<>();
    final Context context = this;
    Calendar calendar = Calendar.getInstance();
    int currentDay = calendar.get(Calendar.DAY_OF_WEEK);
    int currentYear = calendar.get(Calendar.YEAR);
    int currentMonth = calendar.get(Calendar.MONTH);
    int dayOfMonth = calendar.get(Calendar.DATE);

    private int getWeekHours(){
        //1-7 days, 0-11 months
        int ret = 0;
        //String currentDate = getMonth(currentMonth)+ " " + dayOfMonth + ", " + currentYear;
        ArrayList[] ary = new ArrayList[7];
        if(currentDay == 1) {
            for (int i = 0; i < 7; i++) {
                ary[i] = allSchedules.get(getMonth(currentMonth) + " " + (dayOfMonth + i) + ", " + currentYear);
            }
        }
        if(currentDay == 2) {
            for (int i = -1; i < 6; i++) {
                ary[i + 1] = allSchedules.get(getMonth(currentMonth) + " " + (dayOfMonth + i) + ", " + currentYear);
            }
        }
        if(currentDay == 3) {
            for (int i = -2; i < 5; i++) {
                ary[i + 2] = allSchedules.get(getMonth(currentMonth) + " " + (dayOfMonth + i) + ", " + currentYear);
            }
        }
        if(currentDay == 4) {
            for (int i = -3; i < 4; i++) {
                ary[i + 3] = allSchedules.get(getMonth(currentMonth) + " " + (dayOfMonth + i) + ", " + currentYear);
            }
        }
        if(currentDay == 5) {
            for (int i = -4; i < 3; i++) {
                ary[i + 4] = allSchedules.get(getMonth(currentMonth) + " " + (dayOfMonth + i) + ", " + currentYear);
            }
        }
        if(currentDay == 6) {
            for (int i = -5; i < 2; i++) {
                ary[i + 5] = allSchedules.get(getMonth(currentMonth) + " " + (dayOfMonth + i) + ", " + currentYear);
            }
        }
        if(currentDay == 7) {
            for (int i = -6; i < 1; i++) {
                ary[i + 6] = allSchedules.get(getMonth(currentMonth) + " " + (dayOfMonth + i) + ", " + currentYear);
            }
        }
        int counter = 0;
        for(int i = 0; i < 7; i++){
            for(int n = 0; n < 24; n++) {
                if(ary[i]!=null){
                    Map<String, String> datum = (Map<String,String>) ary[i].get(n);
                    if(datum.get("activityFlag").equals("true")){
                        counter++;
                    }
                }
            }
        }
        return counter;
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 5){
            if(resultCode == Activity.RESULT_OK){
                String date = data.getStringExtra("sDate");
                ArrayList<Map<String, String>> dailySchedule = (ArrayList<Map<String, String>>) data.getSerializableExtra("schedule");
                allSchedules.put(date,dailySchedule);
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_schedule);
        //System.out.println(getMonth(currentMonth)+dayOfMonth+currentYear);
        if(allSchedules.size() == 0){
            ArrayList<Map<String, ArrayList<Map<String, String>>>> datum = (ArrayList<Map<String, ArrayList<Map<String, String>>>>) getIntent().getSerializableExtra("allSchedules");
            allSchedules = datum.get(0);
        }


        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent(Schedule.this,Menu.class);
                ArrayList<Map<String, ArrayList<Map<String, String>>>> datum = new ArrayList<>();
                datum.add(allSchedules);
                int temp = getWeekHours();
                returnIntent.putExtra("allSchedules",datum);
                returnIntent.putExtra("activityHoursWeek",temp);
                //Toast.makeText(context, ""+temp, 0).show();
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

        calendarView = findViewById(R.id.calendarView);
        calendarView.setDate(Calendar.getInstance().getTimeInMillis());
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                //open Schedule
                Intent intent = new Intent(Schedule.this, Sbuilder.class);
                String date = getMonth(month)+ " " + day + ", " + year;
                //input any existing data
                ArrayList<Map<String, String>> data = allSchedules.get(date);
                intent.putExtra("date", date);
                intent.putExtra("schedule",data);
                startActivityForResult(intent, 5);
            }
        });


    }

    private String getMonth(int month){
        if(month == 0){
            return "January";
        }
        if(month == 1){
            return "February";
        }
        else if(month == 2){
            return "March";
        }
        else if(month == 3){
            return "April";
        }
        else if(month == 4){
            return "May";
        }
        else if(month == 5){
            return "June";
        }
        else if(month == 6){
            return "July";
        }
        else if(month == 7){
            return "August";
        }
        else if(month == 8){
            return "September";
        }
        else if(month == 9){
            return "October";
        }
        else if(month == 10){
            return "November";
        }
        else{
            return "December";
        }
    }
}
