package com.example.ian.atondroid;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*
Importations for our active UI widgets
 */
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

/*
Importation to help us with simple form validation
 */
import android.text.TextUtils;
import android.widget.Toast;

/*
Importation for the AT SDK libs we'll use]
 */
import com.africastalking.services.AirtimeService;
import com.africastalking.models.sms.Recipient;
import com.africastalking.AfricasTalking;
import com.africastalking.services.SmsService;

import java.io.IOException;
import java.util.List;

public class MessageActivity extends AppCompatActivity {

    /*
    Declare private members that will hold references to our UI widgets
     */

    private EditText mEditNumber, mEditMessage;
    private Button mBtnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        /*
        Get our UI widget references
         */
        mEditNumber = findViewById(R.id.editNumber);
        mEditMessage = findViewById(R.id.editMessage);
        mBtnSend = findViewById(R.id.btnSend);

        /*
        Wire up our click event listener for the send button
         */
        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                First, ensure the form is not empty
                 */
                if (TextUtils.isEmpty(mEditNumber.getText().toString()) || TextUtils.isEmpty(mEditMessage.getText().toString())){

                    /*
                    Show a toast telling the user that the he/she must not leave an empty field. Basic form validation
                     */
                    Toast.makeText(getApplicationContext(),"Please leave no field empty",Toast.LENGTH_LONG).show();
                } else {

                    /*
                Invoke method that will send our message, passing the number and message as string parameters
                 */
                    sendMessage(mEditNumber.getText().toString(),mEditMessage.getText().toString());
                }

            }
        });
    }

    /*
    Implementation of our sendMessage method
     */
    private void sendMessage(final String number, final String message){

        /*
        get our sms service and use it to send the message
         */
        @SuppressLint("StaticFieldLeak")AsyncTask<Void, String, Void> smsTask = new AsyncTask<Void, String, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {

                /*
                put it in try catch block
                 */
                try{

                    //Log this
                    Log.e("SMS INFO", "Attempting to send SMS");

                    //get the sms service
                    SmsService smsService = AfricasTalking.getSmsService();

                    //Send the sms, get the response
                    List<Recipient> recipients = smsService.send(message, new String[] {number});

                    /*
                    Log the response
                     */
                    Log.e("SMS RESPONSE", recipients.get(0).messageId + " " + recipients.get(0).status);
                } catch (IOException e){

                    Log.e("SMS FAILURE", e.toString());
                }
                return null;
            }
        };

        smsTask.execute();
    }
}
