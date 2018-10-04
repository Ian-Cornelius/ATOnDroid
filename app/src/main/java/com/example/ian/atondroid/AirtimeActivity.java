package com.example.ian.atondroid;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*
Importations for our UI widgets
 */
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

/*
Importation to help in form validation
 */
import android.text.TextUtils;
import android.widget.Toast;

/*
For using AT SDK Libraries
 */
import com.africastalking.services.AirtimeService;
import com.africastalking.*;
import com.africastalking.models.airtime.AirtimeResponse;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.HashMap;


public class AirtimeActivity extends AppCompatActivity {

    /*
    Private member variables to hold references to our active widgets
     */
    private EditText mEditRecipient, mEditAmount;
    private Button mBtnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airtime);

        /*
        Get our widget references
         */
        mEditRecipient = findViewById(R.id.editRecipient);
        mEditAmount = findViewById(R.id.editAmount1);
        mBtnSend = findViewById(R.id.btnSend);

        /*
        Set up our listener for button click on send button
         */
        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                Ensure our form is valid
                 */
                if (TextUtils.isEmpty(mEditRecipient.getText().toString()) || TextUtils.isEmpty(mEditAmount.getText().toString())){
                    /*
                    Show a Toast with error message
                     */
                    Toast.makeText(getApplicationContext(),"Please leave no field empty",Toast.LENGTH_LONG).show();
                } else {

                    /*
                Else, put our values in a Hashmap, with the key as the phone number. Since we are only sending airtime to one person, go direct to
                call our method to sendAirtime, passing in the Hashmap
                 */
                    HashMap<String,String> recipient = new HashMap<>();
                    recipient.put(mEditRecipient.getText().toString(), "KES " + mEditAmount.getText().toString());
                    sendAirtime(recipient);
                }


            }
        });
    }

    /*
    implementation of sendAirtime()
     */
    private void sendAirtime(final HashMap<String, String> recipient){

        /*
        Run our code in a separate thread from the UI thread, using AsyncTask. Required by Android for all Network calls
         */

        @SuppressLint("StaticFieldLeak") AsyncTask <Void, String, Void> taskSendAirtime = new AsyncTask<Void, String, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {

                /*
                Where we put our code. This is the code that will be executed in the thread
                 */
                try {

                /*
                Log that we are trying to get service
                 */
                    Log.e("AIRTIME NOTICE", "Trying to get airtime service");
                    AirtimeService service = AfricasTalking.getAirtimeService();

                    //Now that we have the service, send the airtime, get the response
                    AirtimeResponse response = service.send(recipient);

                    //Log our success message
                    Log.e("AIRTIME SUCCESS","Sent airtime worth " + response.totalAmount + " to " + mEditRecipient.getText().toString());
                } catch (IOException e){

                    /*
                    Log our failure
                     */
                    Log.e("AIRTIME FAILURE","Failed to send airtime with exception " + e.toString());
                }

                return null;
            }
        };

        //Execute our task
        taskSendAirtime.execute();
    }
}
