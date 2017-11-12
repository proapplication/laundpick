package com.example.hfauzy.bigproject.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hfauzy.bigproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    //firebase auth object
    private FirebaseAuth firebaseAuth;

    //view objects
    private TextView textViewUserEmail;
    private Button buttonLogout;
    private int q=0;
    private Button tambahGalon, kurangiGalon;
    private TextView jumlahGalon, textViewGalon, textViewWash;
    private LinearLayout setUpGalon;
    private Button orderGalon, cancelWash, orderWash;
    private LinearLayout sendLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        //if the user is not logged in
        //that means current user will return null
        if(firebaseAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }

        //getting current user
        FirebaseUser user = firebaseAuth.getCurrentUser();

        //INITIALIZING VIEWs
        //view user login
        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        //view galon
        tambahGalon = (Button) findViewById(R.id.addGalon);
        kurangiGalon = (Button) findViewById(R.id.subsGalon);
        jumlahGalon = (TextView) findViewById(R.id.jumlGalon);
        textViewGalon = (TextView) findViewById(R.id.textviewGalon);
        //view wash
        cancelWash = (Button) findViewById(R.id.cancelWash);
        textViewWash =(TextView) findViewById(R.id.textviewWash);
        orderWash = (Button) findViewById(R.id.orderWash);
        //view send button and order information
        sendLayout = (LinearLayout) findViewById(R.id.sendLayout) ;

        //displaying logged in user name
        textViewUserEmail.setText("Welcome "+user.getEmail());

        //adding listener to button
        buttonLogout.setOnClickListener(this);

        orderWash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendLayout.setVisibility(View.VISIBLE);
                textViewWash.setVisibility(View.VISIBLE);
                textViewWash.setText("Jemput Laundry Saya");
                cancelWash.setVisibility(View.VISIBLE);
                orderWash.setVisibility(View.INVISIBLE);
            }
        });
        cancelWash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewWash.setText("1");
                cancelWash.setVisibility(View.INVISIBLE);
                orderWash.setVisibility(View.VISIBLE);
                textViewWash.setVisibility(View.INVISIBLE);
                if(textViewGalon.getText().toString()=="0"){
                    sendLayout.setVisibility(View.INVISIBLE);}
            }
        });

        tambahGalon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q++;
                jumlahGalon.setText("" + q );
                kurangiGalon.setVisibility(View.VISIBLE);
                sendLayout.setVisibility(View.VISIBLE);
                textViewGalon.setVisibility(View.VISIBLE);
                textViewGalon.setText("Order " + q + " Galoon");
            }
        });
        kurangiGalon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q==1){
                    q=0;
                    kurangiGalon.setVisibility(View.INVISIBLE);
                    textViewGalon.setText("0");
                    if(textViewWash.getText().toString()=="1"){
                        sendLayout.setVisibility(View.INVISIBLE);}
                    textViewGalon.setVisibility(View.GONE);
                }
                else {q--;}
                jumlahGalon.setText("" + q );
            }
        });

    }

    @Override
    public void onClick(View view) {
        //if logout is pressed
        if(view == buttonLogout){
            //logging out the user
            firebaseAuth.signOut();
            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}