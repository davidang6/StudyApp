package com.example.davidang.studyapp;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Menu extends AppCompatActivity {

    TextView hoursView,levelView,progressText;
    TextView textView1,textView2,textView3;
    Spinner hrSpinner,minSpinner;
    ProgressBar progressBar;
    ArrayList<String> hrSpinnerArray,minSpinnerArray;
    ArrayAdapter<String> hrAdapter,minAdapter;
    Button startSession, achievements, statistics, schedule;
    public static final String SHARED_PREFS_FILE = "pref";
    int level,exp;
    int req;
    int activityHoursWeek;
    static double mostHoursWeek;
    static double studyHours;
    static double studyHoursTotal;

    static Typeface roboto, ptsans, comic;
    boolean levelup = false;

    Map<String, ArrayList<Map<String, String>>> scheduleData = new HashMap<>();
    static File file;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Context context = this;
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS_FILE, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        if(requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                DecimalFormat df = new DecimalFormat("##.###");
                studyHours = data.getIntExtra("hr",0)+((double)data.getIntExtra("min",0))/60;
                editor.putFloat("studyHours",(float)studyHours);
                studyHoursTotal+=studyHours;
                editor.putFloat("studyHoursTotal",(float) studyHoursTotal);
                exp+=(int)((studyHours*100));
                editor.putInt("exp", exp);
                System.out.println(exp);
                System.out.println(((((double)exp)/req))+"");
                if(exp>=req){
                    exp = exp-req;
                    editor.putInt("exp", exp);
                    req = (int)(req*1.2);
                    editor.putInt("req", req);
                    level++;
                    editor.putInt("level", level);
                    progressBar.setProgress(((exp/req)*100));
                    levelView.setText(level+"");
                    progressText.setText(exp+"/"+req+" EXP");
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                    alertDialog.setTitle("LEVEL UP");
                    alertDialog.setCancelable(true);
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {}
                    });

                    AlertDialog alertDialog1 = alertDialog.create();
                    alertDialog1.show();
                }
                System.out.println(studyHoursTotal);
                if(studyHoursTotal>mostHoursWeek){
                    mostHoursWeek = studyHoursTotal;
                    editor.putFloat("mostHoursWeek",(float)mostHoursWeek);
                }
                if(studyHours>1){
                    Achievements.achieved1 = true;
                    editor.putBoolean("achieved1",true);
                }
                if(studyHours>5){
                    Achievements.achieved2 = true;
                    editor.putBoolean("achieved2",true);
                }
                if(studyHours>10){
                    Achievements.achieved3 = true;
                    editor.putBoolean("achieved3",true);
                }
                if(studyHours>20){
                    Achievements.achieved4 = true;
                    editor.putBoolean("achieved4",true);
                }
                if(studyHours>30){
                    Achievements.achieved5 = true;
                    editor.putBoolean("achieved5",true);
                }
                if(studyHours>40){
                    Achievements.achieved6 = true;
                    editor.putBoolean("achieved6",true);
                }
                if(studyHoursTotal>=100){
                    Achievements.achieved7 = true;
                    editor.putBoolean("achieved7",true);
                }
                if(studyHoursTotal>=200){
                    Achievements.achieved8 = true;
                    editor.putBoolean("achieved8",true);
                }
                if(studyHoursTotal>=300){
                    Achievements.achieved9 = true;
                    editor.putBoolean("achieved9",true);
                }
                if(studyHoursTotal>=400){
                    Achievements.achieved10 = true;
                    editor.putBoolean("achieved10",true);
                }
                if(studyHoursTotal>=500){
                    Achievements.achieved11 = true;
                    editor.putBoolean("achieved11",true);
                }
                if(studyHoursTotal>=1000){
                    Achievements.achieved12 = true;
                    editor.putBoolean("achieved12",true);
                }
                progressBar.setProgress((int)(((((double)exp)/req))*100));
                progressText.setText(exp+"/"+req+" EXP");
                hoursView.setText(df.format(studyHoursTotal));
            }
        }
        if (requestCode == 4){
            ArrayList<Map<String, ArrayList<Map<String, String>>>> temp = (ArrayList<Map<String, ArrayList<Map<String, String>>>>) data.getSerializableExtra("allSchedules");
            activityHoursWeek = data.getIntExtra("activityHoursWeek",0);
            Toast.makeText(context, ""+activityHoursWeek, Toast.LENGTH_SHORT).show();
            //System.out.println(activityHoursWeek);
            editor.putInt("activityHoursWeek",activityHoursWeek);

            scheduleData = temp.get(0);
            //save schedule
            try{
                ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
                outputStream.writeObject(scheduleData);
                outputStream.flush();
                outputStream.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        editor.apply();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_menu);
        file = new File(getDir("data", MODE_PRIVATE), "map");
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS_FILE, MODE_PRIVATE);
        //load schedule
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
            scheduleData = (Map<String, ArrayList<Map<String, String>>>) inputStream.readObject();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        //load literally everything
        level = prefs.getInt("level", 0);
        exp = prefs.getInt("exp", 0);
        //Toast.makeText(this, ""+prefs.getInt("exp",0), Toast.LENGTH_SHORT).show();
        req = prefs.getInt("req",100);
        activityHoursWeek = prefs.getInt("activityHoursWeek",0);
        Toast.makeText(this, ""+activityHoursWeek, Toast.LENGTH_SHORT).show();
        mostHoursWeek = (double)prefs.getFloat("mostHoursWeek",0);;
        studyHours = (double)prefs.getFloat("studyHours",0);;
        studyHoursTotal = (double)prefs.getFloat("studyHoursTotal",0);
        Achievements.achieved1 = prefs.getBoolean("achieved1",false);
        Achievements.achieved2 = prefs.getBoolean("achieved2",false);
        Achievements.achieved3 = prefs.getBoolean("achieved3",false);
        Achievements.achieved4 = prefs.getBoolean("achieved4",false);
        Achievements.achieved5 = prefs.getBoolean("achieved5",false);
        Achievements.achieved6 = prefs.getBoolean("achieved6",false);
        Achievements.achieved7 = prefs.getBoolean("achieved7",false);
        Achievements.achieved8 = prefs.getBoolean("achieved8",false);
        Achievements.achieved9 = prefs.getBoolean("achieved9",false);
        Achievements.achieved10 = prefs.getBoolean("achieved10",false);
        Achievements.achieved11 = prefs.getBoolean("achieved11",false);
        Achievements.achieved12 = prefs.getBoolean("achieved12",false);


        roboto = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        ptsans = Typeface.createFromAsset(getAssets(), "fonts/PT_Sans-Web-Regular.ttf");
        comic = Typeface.createFromAsset(getAssets(), "fonts/comic.ttf");

        progressBar = findViewById(R.id.progressBar);
        progressBar.setScaleY(5f);
        progressBar.setProgress((int)(((((double)exp)/req))*100));

        progressText = findViewById(R.id.progressText);
        progressText.setTypeface(roboto);
        progressText.setText(exp+"/"+req+" EXP");

        hoursView = findViewById(R.id.hoursView);
        levelView = findViewById(R.id.levelView);
        levelView.setText(level+"");

        startSession = findViewById(R.id.startSession);
        startSession.setTextColor(Color.parseColor("#820000"));

        achievements = findViewById(R.id.achievements);
        achievements.setTextColor(Color.parseColor("#820000"));

        statistics = findViewById(R.id.statistics);
        statistics.setTextColor(Color.parseColor("#820000"));

        schedule = findViewById(R.id.schedule);
        schedule.setTextColor(Color.parseColor("#820000"));

        hrSpinner = findViewById(R.id.hrSpinner);
        hrSpinnerArray = new ArrayList<>();
        for (int i=0;i<10;i++)
            hrSpinnerArray.add(i+"");
        hrAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,hrSpinnerArray);
        hrAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        hrSpinner.setAdapter(hrAdapter);

        minSpinner = findViewById(R.id.minSpinner);
        minSpinnerArray = new ArrayList<>();
        for (int i=0;i<60;i++)
            minSpinnerArray.add(i+"");
        minAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,minSpinnerArray);
        minAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        minSpinner.setAdapter(minAdapter);

        textView1 = findViewById(R.id.textView1);
        textView1.setTypeface(roboto);

        textView2 = findViewById(R.id.textView2);
        textView2.setTypeface(roboto);

        textView3 = findViewById(R.id.textView3);
        textView3.setTypeface(roboto);

        hoursView.setTypeface(roboto);
        DecimalFormat df = new DecimalFormat("##.###");
        hoursView.setText(df.format(studyHoursTotal));
        //run timer
        startSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this,Timer.class);
                intent.putExtra("hours",Integer.parseInt(hrSpinner.getSelectedItem()+""));
                intent.putExtra("mins",Integer.parseInt(minSpinner.getSelectedItem()+""));
                setResult(RESULT_OK,intent);
                startActivityForResult(intent,1);
            }
        });
        //Open Achievements
        achievements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this,Achievements.class);
                setResult(RESULT_OK,intent);
                startActivityForResult(intent, 2);
            }
        });
        //Open Statistics
        statistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this,Statistics.class);

                setResult(RESULT_OK,intent);
                startActivityForResult(intent,3);
            }
        });
        //Open Calendar
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this,Schedule.class);
                ArrayList<Map<String, ArrayList<Map<String, String>>>> datum = new ArrayList<>();
                datum.add(scheduleData);
                intent.putExtra("allSchedules",datum);
                //Toast.makeText(Menu.this, ""+datum, 0).show();
                setResult(RESULT_OK,intent);
                startActivityForResult(intent, 4);
            }
        });


        // Save Pref
        /*
        editor.putInt("level", level);
        editor.putInt("exp", exp);
        editor.putInt("req", req);
        editor.putFloat("mostHoursWeek",(float)mostHoursWeek);
        editor.putFloat("studyHours",(float)studyHours);
        editor.putFloat("studyHoursTotal",(float) studyHoursTotal);
        editor.apply();
        //String restoredText = prefs.getString("text", null);
        // Load Pref
        */
        //Toast.makeText(getApplicationContext(), , 0).show();
    }


}
