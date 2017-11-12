package com.example.hfauzy.bigproject.start;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.hfauzy.bigproject.MainActivity;
import com.example.hfauzy.bigproject.R;
import com.example.hfauzy.bigproject.login.ProfileActivity;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_splash);



        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        if (isFirstRun) {
            //show start activity
            Thread myThread =  new Thread(){
                @Override
                public void run() {
                    try {
                        sleep(3000);
                        Intent intent = new Intent(getApplicationContext(), WelcomeSwipe.class);
                        startActivity(intent);
                        finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            myThread.start();


            Toast.makeText(getApplicationContext(), "Hi", Toast.LENGTH_LONG)
                    .show();
        }
        else {

           Thread myThread =  new Thread(){
                    @Override
                    public void run() {
                        try {
                            sleep(3000);
                            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                            startActivity(intent);
                            finish();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                myThread.start();
        }

        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).commit();
    }
}

