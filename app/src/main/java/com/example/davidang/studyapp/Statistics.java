package com.example.davidang.studyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import java.text.DecimalFormat;

public class Statistics extends AppCompatActivity{

    Button back;
    TextView mostHoursWeekView,lifetimeHoursView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_statistics);

        back = findViewById(R.id.back);

        mostHoursWeekView = findViewById(R.id.mostHoursWeekView);
        lifetimeHoursView = findViewById(R.id.lifetimeHoursView);

        DecimalFormat df = new DecimalFormat("##.###");
        mostHoursWeekView.setText("Most Hours in a Week: "+df.format(Menu.mostHoursWeek));
        //System.out.println(""+getIntent().getIntExtra("mostHoursWeek",0));
        //System.out.println(""+getIntent().getIntExtra("totalStudyHours",0));
        lifetimeHoursView.setText("Lifetime Hours: "+df.format(Menu.studyHoursTotal));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent(Statistics.this,Menu.class);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

    }
}
