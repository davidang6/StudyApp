package com.example.davidang.studyapp;

import android.Manifest;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Telephony;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class Timer extends AppCompatActivity{

    TextView countdown;
    TextView textView1;
    BroadcastReceiver receiver;
    IntentFilter filter;
    SmsManager manager;
    Handler handler;
    NotificationManager mNotificationManager;
    static Typeface roboto,ptsans;
    int hr,min,sec = 0;
    int inithr,initmin;
    boolean run = true;

    public Runnable sendMessage(final String numberToSend, final String messageToSend){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                manager.sendTextMessage(numberToSend,null,messageToSend,null,null);
            }
        };
        return runnable;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_timer);

        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (!mNotificationManager.isNotificationPolicyAccessGranted()) {
            Intent intent = new Intent(android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
            startActivity(intent);
        }

        if (ContextCompat.checkSelfPermission(Timer.this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Timer.this, new String[]{Manifest.permission.RECEIVE_SMS}, 0);
        }

        manager = SmsManager.getDefault();
        handler = new Handler();

        filter = new IntentFilter();
        filter.addAction(Telephony.Sms.Intents.SMS_RECEIVED_ACTION);
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle bundle = intent.getExtras();
                if(bundle != null){
                    Object[] pdus = (Object[])bundle.get("pdus");
                    System.out.println(pdus);
                    final SmsMessage[] messages = new SmsMessage[pdus.length];
                    for (int i=0;i<pdus.length;i++){
                        messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    }
                    handler.postDelayed(sendMessage(messages[0].getOriginatingAddress(),"I am currently studying. " +
                            "Please get back to me in "+hr+" hours and "+min+" minutes"),0);
                }
            }
        };

        registerReceiver(receiver, filter);

        roboto = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        ptsans = Typeface.createFromAsset(getAssets(), "fonts/PT_Sans-Web-Regular.ttf");

        countdown = findViewById(R.id.countdown);
        countdown.setTypeface(roboto);

        textView1 = findViewById(R.id.textView1);
        textView1.setTypeface(roboto);

        hr = getIntent().getIntExtra("hours",0);
        min = getIntent().getIntExtra("mins",0);
        inithr = hr;
        initmin = min;


        countdown.setText(String.format("%02d",hr)+":"+String.format("%02d",min)+":"+String.format("%02d",sec));

        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (run){
                    if (mNotificationManager.isNotificationPolicyAccessGranted()) {
                        mNotificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_NONE);
                    }
                    try{
                        Thread.sleep((long)(1000));
                    }catch(Exception e){}
                    if(sec == 0 && (min > 0 || hr > 0)){
                        sec = 59;
                        if(min > 0){
                            min--;
                        }
                        if(min == 0 && hr > 0){
                            min = 59;
                            hr--;
                        }
                    }
                    else if(min == 0 && hr == 0 && sec == 0){
                        run = false;
                        break;
                    }
                    else{
                        sec--;
                        System.out.println(sec);
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            countdown.setText(String.format("%02d",hr)+":"+String.format("%02d",min)+":"+String.format("%02d",sec));
                        }
                    });
                }
            }
        };
        new Thread(runnable).start();

        final Handler handler2 = new Handler();
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                while(true) {
                    if (!run) {
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("hr", inithr);
                        returnIntent.putExtra("min", initmin);
                        setResult(Activity.RESULT_OK, returnIntent);
                        mNotificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_ALL);
                        finish();
                    }
                }
            }
        };
        new Thread(runnable2).start();

    }
}
