package com.example.ian.atondroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*
Importation for our button widget
 */
import android.util.Log;
import android.view.View;
import android.widget.Button;

/*
Importation to allow us to open a new activity (basically a new UI screen)
 */
import android.content.Intent;

/*
Importations that will give us access to africa's talking libraries
 */
import com.africastalking.*;
import com.africastalking.utils.Logger;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    /*
    private members to hold values of our server ip address and port
     */
//    private final String host = "192.168.0.169";

    //For the emulator, connecting to local host
    private final String host = "172.20.10.11";
    private final int port = 8088;

    /*
    Private data members to hold our widget references
     */
    private Button mBtnSendMessage, mBtnSendAirtime, mBtnMakePayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Get our widget references and wire them up
         */
        mBtnSendMessage = findViewById(R.id.btn_send_message);
        mBtnSendAirtime = findViewById(R.id.btn_send_airtime);
        mBtnMakePayment = findViewById(R.id.btn_make_payment);


        /*
        Wire up the listeners to our buttons, to listen to click events and act appropriately
         */

        //Send messsage button
        mBtnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                Launch the MessageActivity
                 */
                Intent intent = new Intent(MainActivity.this,MessageActivity.class);
                startActivity(intent);

            }
        });

        //send airtime button
        mBtnSendAirtime.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick (View v){

                /*
                Launch the send airtime activity
                 */
                Intent intent = new Intent(MainActivity.this,AirtimeActivity.class);
                startActivity(intent);
            }
        });

        //Make payment button
        mBtnMakePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                Launch the Payment activity
                 */
                Intent intent = new Intent(MainActivity.this,PaymentActivity.class);
                startActivity(intent);
            }
        });

        /*
        Invoke the method that will connect us to our server
         */
        connectToServer();

    }

    /*
    implementation of connectToServer()
     */
    private void connectToServer(){

        //Initialize te sdk, and connect to our server. Do this in a try catch block
        try{

            /*
            Put a notice in our log that we are attempting to initialize
             */
            Log.e("NOTICE","Attempting to intialize server");
            AfricasTalking.initialize(host, port,true);

            //Use AT's Logger to get any message
            AfricasTalking.setLogger(new Logger() {
                @Override
                public void log(String message, Object... args) {

                    /*
                    Log this too
                     */
                    Log.e("FROM AT LOGGER",message + " " + args.toString());
                }
            });

            /*
            Final log to tell us if successful
             */
            Log.e("SERVER SUCCESS","Managed to connect to server");
        } catch (IOException e){

            /*
            Log our failure to connect
             */
            Log.e("SERVER ERROR", "Failed to connect to server");
        }
    }
}
