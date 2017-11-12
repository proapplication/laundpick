package com.example.hfauzy.bigproject.start;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hfauzy.bigproject.MainActivity;
import com.example.hfauzy.bigproject.R;
import com.example.hfauzy.bigproject.login.ProfileActivity;

public class WelcomeSwipe extends AppCompatActivity {


    private Button next;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_welcome_swipe);

            next = (Button) findViewById(R.id.next);
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent b = new Intent(getApplicationContext(), ProfileActivity.class);
                    startActivity(b);
                }
            });
        }


    }

