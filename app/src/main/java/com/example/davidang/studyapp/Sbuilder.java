package com.example.davidang.studyapp;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Sbuilder extends AppCompatActivity{


    Button back;
    TextView textView;
    ListView listView;
    ArrayList<Map<String, String>> data = new ArrayList<>();
    SimpleAdapter simpleAdapter;
    String date;
    final Context context = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sbuilder);

        if(getIntent().getSerializableExtra("schedule") != null) {
            data = (ArrayList<Map<String, String>>) getIntent().getSerializableExtra("schedule");
        }

        date = getIntent().getStringExtra("date");
        // Sets Header to Current Date
        textView = findViewById(R.id.textView);
        textView.setText(date);
        // Generates Time Table
        listView = findViewById(R.id.listView);
        if(data.size() == 0){
            for(int i = 0; i < 24; i++) {
                Map<String, String> datum = new HashMap<>(3);
                if (i == 0) {
                    datum.put("title", "12:00 AM");
                    datum.put("activity", "");
                    datum.put("activityFlag","false");
                    data.add(datum);
                } else if (i < 12) {
                    datum.put("title", i + ":00 AM");
                    datum.put("activity", "");
                    datum.put("activityFlag","false");
                    data.add(datum);
                } else if (i == 12) {
                    datum.put("title", "12:00 PM");
                    datum.put("activity", "");
                    datum.put("activityFlag","false");
                    data.add(datum);
                } else {
                    datum.put("title", i - 12 + ":00 PM");
                    datum.put("activity", "");
                    datum.put("activityFlag","false");
                    data.add(datum);
                }
            }
        }
        simpleAdapter = new SimpleAdapter(this, data, android.R.layout.simple_list_item_2, new String[] {"title", "activity"}, new int[] {android.R.id.text1, android.R.id.text2});
        listView.setAdapter(simpleAdapter);
        // List onClick Event
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object o = listView.getItemAtPosition(i);
                final Map<String,String> m = (HashMap<String,String>)o;
                //Toast.makeText(getApplicationContext(), ""+o, 0).show();
                // Dialog Box
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Add Activity");
                // Set up the input
                final EditText input = new EditText(context);
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                alertDialog.setView(input);
                alertDialog.setCancelable(true);
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        m.put("activity",input.getText().toString());
                        if(input.getText().toString().length() != 0){
                            m.put("activityFlag", "true");
                        }
                        else{
                            m.put("activityFlag","false");
                        }
                    }
                });
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alertDialog1 = alertDialog.create();
                alertDialog1.show();
            }
        });
        // Back Button
        back = findViewById(R.id.button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent returnIntent = new Intent(Sbuilder.this, Schedule.class);
                returnIntent.putExtra("schedule", data);
                returnIntent.putExtra("sDate", date);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }
}
