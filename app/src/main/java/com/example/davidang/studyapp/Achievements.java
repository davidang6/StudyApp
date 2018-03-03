package com.example.davidang.studyapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Achievements extends AppCompatActivity{

    ImageView image1;
    ImageView image2;
    ImageView image3;
    ImageView image4;
    ImageView image5;
    ImageView image6;
    TextView t1;
    TextView t2;
    TextView t3;
    TextView t4;
    TextView t5;
    TextView t6;
    ImageView image7;
    ImageView image8;
    ImageView image9;
    ImageView image10;
    ImageView image11;
    ImageView image12;
    TextView t7;
    TextView t8;
    TextView t9;
    TextView t10;
    TextView t11;
    TextView t12;

    static boolean achieved1;
    static boolean achieved2;
    static boolean achieved3;
    static boolean achieved4;
    static boolean achieved5;
    static boolean achieved6;
    static boolean achieved7;
    static boolean achieved8;
    static boolean achieved9;
    static boolean achieved10;
    static boolean achieved11;
    static boolean achieved12;

    Button back;

    final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_achievements);

        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent(Achievements.this,Menu.class);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

        t1 = findViewById(R.id.text1);
        image1 = (ImageView) findViewById(R.id.achievement1);
        if (!achieved1) {
            t1.setText("Incomplete");
            image1.setColorFilter(filter);
        }
        else{
            t1.setText("Complete");
            image1.clearColorFilter();
        }
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Studying 1");
                alertDialog.setMessage("Study at least 1 hour in a week");
                alertDialog.setCancelable(true);

                AlertDialog alertDialog1 = alertDialog.create();
                alertDialog1.show();
            }
        });


        t2 = (TextView) findViewById(R.id.text2);
        image2 = (ImageView) findViewById(R.id.achievement2);
        if (!achieved2) {
            t2.setText("Incomplete");
            image2.setColorFilter(filter);
        }
        else{
            t2.setText("Complete");
            image2.clearColorFilter();
        }
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Studying 5");
                alertDialog.setMessage("Study at least 5 hours in a week");
                alertDialog.setCancelable(true);

                AlertDialog alertDialog1 = alertDialog.create();
                alertDialog1.show();
            }
        });


        t3 = (TextView) findViewById(R.id.text3);
        image3 = (ImageView) findViewById(R.id.achievement3);
        if (!achieved3) {
            t3.setText("Incomplete");
            image3.setColorFilter(filter);
        }
        else{
            t3.setText("Complete");
            image3.clearColorFilter();
        }
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Studying 10");
                alertDialog.setMessage("Study at least 10 hours in a week");
                alertDialog.setCancelable(true);

                AlertDialog alertDialog1 = alertDialog.create();
                alertDialog1.show();
            }
        });


        t4 = (TextView) findViewById(R.id.text4);
        image4 = (ImageView) findViewById(R.id.achievement4);
        if(!achieved4)
        {
            t4.setText("Incomplete");
            image4.setColorFilter(filter);
        }
        else{
            t4.setText("Complete");
            image4.clearColorFilter();
        }
        image4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Studying 20");
                alertDialog.setMessage("Study at least 20 hours in a week");
                alertDialog.setCancelable(true);

                AlertDialog alertDialog1 = alertDialog.create();
                alertDialog1.show();
            }
        });


        t5 = (TextView) findViewById(R.id.text5);
        image5 = (ImageView)findViewById(R.id.achievement5);
        if(!achieved5)
        {
            t5.setText("Incomplete");
            image5.setColorFilter(filter);
        }
        else{
            t5.setText("Complete");
            image5.clearColorFilter();
        }
        image5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Studying 30");
                alertDialog.setMessage("Study at least 30 hours in a week");
                alertDialog.setCancelable(true);

                AlertDialog alertDialog1 = alertDialog.create();
                alertDialog1.show();
            }
        });


        t6 = (TextView) findViewById(R.id.text6);
        image6 = (ImageView)findViewById(R.id.achievement6);
        if(!achieved6)
        {
            t6.setText("Incomplete");
            image6.setColorFilter(filter);
        }
        else{
            t6.setText("Complete");
            image6.clearColorFilter();
        }
        image6.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Studying 40");
                alertDialog.setMessage("Study at least 40 hours in a week");
                alertDialog.setCancelable(true);

                AlertDialog alertDialog1 = alertDialog.create();
                alertDialog1.show();
            }
        });


        t7 = (TextView) findViewById(R.id.text7);
        image7 = (ImageView)findViewById(R.id.achievement7);
        if(!achieved7)
        {
            t7.setText("Incomplete");
            image7.setColorFilter(filter);
        }
        else{
            t7.setText("Complete");
            image7.clearColorFilter();
        }
        image7.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Noob Student");
                alertDialog.setMessage("Study for 100 hours lifetime");
                alertDialog.setCancelable(true);

                AlertDialog alertDialog1 = alertDialog.create();
                alertDialog1.show();
            }
        });


        t8 = (TextView) findViewById(R.id.text8);
        image8 = (ImageView)findViewById(R.id.achievement8);
        if(!achieved8)
        {
            t8.setText("Incomplete");
            image8.setColorFilter(filter);
        }
        else{
            t8.setText("Complete");
            image8.clearColorFilter();
        }
        image8.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Decent Student");
                alertDialog.setMessage("Study for 200 hours lifetime");
                alertDialog.setCancelable(true);

                AlertDialog alertDialog1 = alertDialog.create();
                alertDialog1.show();
            }
        });


        t9 = (TextView) findViewById(R.id.text9);
        image9 = (ImageView)findViewById(R.id.achievement9);
        if(!achieved9)
        {
            t9.setText("Incomplete");
            image9.setColorFilter(filter);
        }
        else{
            t9.setText("Complete");
            image9.clearColorFilter();
        }
        image9.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Strong Student");
                alertDialog.setMessage("Study for 300 hours lifetime");
                alertDialog.setCancelable(true);

                AlertDialog alertDialog1 = alertDialog.create();
                alertDialog1.show();
            }
        });


        t10 = (TextView) findViewById(R.id.text10);
        image10 = (ImageView)findViewById(R.id.achievement10);
        if(!achieved10)
        {
            t10.setText("Incomplete");
            image10.setColorFilter(filter);
        }
        else{
            t10.setText("Complete");
            image10.clearColorFilter();
        }
        image10.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Amazing Student");
                alertDialog.setMessage("Study for 400 hours lifetime");
                alertDialog.setCancelable(true);

                AlertDialog alertDialog1 = alertDialog.create();
                alertDialog1.show();
            }
        });


        t11 = (TextView) findViewById(R.id.text11);
        image11 = (ImageView)findViewById(R.id.achievement11);
        if(!achieved11)
        {
            t11.setText("Incomplete");
            image11.setColorFilter(filter);
        }
        else{
            t11.setText("Complete");
            image11.clearColorFilter();
        }
        image11.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Valedictorian");
                alertDialog.setMessage("Study for 500 hours lifetime");
                alertDialog.setCancelable(true);

                AlertDialog alertDialog1 = alertDialog.create();
                alertDialog1.show();
            }
        });


        t12 = (TextView) findViewById(R.id.text12);
        image12 = (ImageView)findViewById(R.id.achievement12);
        if(!achieved12)
        {
            t12.setText("Incomplete");
            image12.setColorFilter(filter);
        }
        else{
            t12.setText("Complete");
            image12.clearColorFilter();
        }
        image12.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("God of all Students");
                alertDialog.setMessage("Study for 1000 hours lifetime");
                alertDialog.setCancelable(true);

                AlertDialog alertDialog1 = alertDialog.create();
                alertDialog1.show();
            }
        });
    }
}
