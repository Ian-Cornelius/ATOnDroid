package com.example.ian.server;

import com.africastalking.*;

import java.io.IOException;

public class MyServer {

    /*
    First, save our username and API KEY in final, private, static strings. Also, the port (int) that our server will be using on its host machine

    [This is for testing. Port number can be anything not greater than 65536]
     */
    private static final String userName = "sandbox"; //Sandbox is the testing app
    private static final String APIKey = ""; //Put your API Key here

    //for our server's port. Will use 8080
    private static final int port = 8088;

    /*
    Now, to our main method
     */
    public static void main (String[] args){

        //Initialize the SDK, passing in our username and APIKey
        AfricasTalking.initialize(userName,APIKey);

        //Now, initialize our server
        Server server = new Server();

        //Start our server. Do this in a try..catch block to aid in handling exceptions
        try{

            server.startInsecure(port);

            //A loop to help us keep our server online until we terminate it
            System.out.println("Press ENTER to exit");
            System.in.read();
        } catch (IOException e){

            //Log our error
            System.out.println("Failed to start server at port " + String.valueOf(port) + " with exception " + e.toString());
        }
    }
}
