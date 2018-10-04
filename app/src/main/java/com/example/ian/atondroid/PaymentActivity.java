package com.example.ian.atondroid;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*
importation for our UI widgets (COPY CODE IN MESSAGE ACTIVITY. SHOULD HAVE DONE AND WILL DO THE SAME FOR AIRTIME ACTIVITY, TO SAVE TIME.

COPY ALL CODE, JUST MAKE EDITS TO NAMES/ID REFERENCES
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
Importations for us to use AT SDK Libs
 */
import com.africastalking.services.PaymentService;
import com.africastalking.models.payment.checkout.MobileCheckoutRequest;
import com.africastalking.models.payment.checkout.CheckoutResponse;
import com.africastalking.AfricasTalking;

import java.io.IOException;

public class PaymentActivity extends AppCompatActivity {

    /*
    Private member variables to hold references to our active widgets
     */
    private EditText mEditNumber, mEditAmount;
    private Button mBtnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

         /*
        Get our widget references
         */
        mEditNumber = findViewById(R.id.editNumber1);
        mEditAmount = findViewById(R.id.editAmount);
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
                if (TextUtils.isEmpty(mEditNumber.getText().toString()) || TextUtils.isEmpty(mEditAmount.getText().toString())){
                    /*
                    Show a Toast with error message
                     */
                    Toast.makeText(getApplicationContext(),"Please leave no field empty",Toast.LENGTH_LONG).show();
                } else{

                    /*
                Else, call our method to sendAirtime, passing in string values of phone and amount
                 */
                    sendPayment(mEditNumber.getText().toString(),mEditAmount.getText().toString());
                }

            }
        });
    }

    /*
    Implementation of sendPayment()
     */
    private void sendPayment(final String number, final String amount){

        /*
        implement a demo checkout to this app
         */
        @SuppressLint("StaticFieldLeak") AsyncTask<Void, String, Void> paymentTask = new AsyncTask<Void, String, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {

                /*
                same try catch block
                 */
                try{

                    //Log it
                    Log.e("PAYMENT ALERT","Trying to checkout");

                    //get our payment service
                    PaymentService paymentService = AfricasTalking.getPaymentService();

                    //Create a checkout request
                    MobileCheckoutRequest checkoutRequest = new MobileCheckoutRequest("AT On Droid","KES " + amount, number);

                    //Initiate the checkout, using the checkoutrequest
                    CheckoutResponse response = paymentService.checkout(checkoutRequest);

                    //Log the response
                    Log.e("PAYMENT RESPONSE", response.transactionId + " " + response.status + " " + response.description);

                } catch (IOException e){

                    Log.e("PAYMENT FAILURE","Failed to check out with exception " + e.toString());
                }
                return null;
            }
        };

        //Execute our task
        paymentTask.execute();
    }
}
