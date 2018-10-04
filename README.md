# ATOnDroid
Full ATOnDroid Code

Hey guys. So a quick update on the readme file.

Please make sure you go to https://account.africastalking.com/auth/login, click on register and create an account.Once logged in, 
select the sandbox app. In the new page, go to settings, on the bottom left, select API key, and click on generate.

## NOW THIS IS SUPER IMPORTANT.

Copy the generated API key, and go to the project, server module in ghe project window, open the MyServer java class and in the variable
APIKey, paste your APIKey there. Without this, your server and entire app won't run successfully. 

Lastly, before you run the app, make sure the "host" variable in MainActivity is correct. How would you know this?

There are two ways to it.

First, if you are running the app on the android studio emulator, this value should be set to <b>10.0.2.2</b> This will connect the emulator to the 
local host (your computer), which is running the server. If you are running the app in your real android phone, first, make sure your phone and your laptop/computer
are connected to the same network (you can set up a hotspot, with active internet, that your laptop/computer and phone will use)

Now, get the ip address of your computer on the network. For Windows users, run power shell or command prompt as admin and type ipconfig . 
Copy the <b>ipv4 address</b> under the WiFi adapter result. Linux users can use <b>ifconfig</b> to get the same data.

The address you've just copied above is your host value. Place it in the <b>"host"</b> variable in MainActivity.

## How do I run my server?

Quite simple. Next to the green run button, to the left, there's a dropdown. Select it, choose server, and then click the run button.
Your server will go live.

Now, the last step. Select app in the dropdown you just used. Click run. 
Go to your Africa's Talking account, at the bottom left, click launch simulator. Key in the phone number you'll be using, and you're all set!
Now send airtime, make a payment, and send a message.

Don't forget to look at the logcat for output from the app. Select filters, show application only, to just focus on the log cat output. Make sure 
you have selected error in the drop down. This is where I've placed all logs (even if they are not errors)

<b>HAPPY TESTING NERD ONES!</b>

How to go prod (production) with the SDK will be updated soon, for those of you who want to go a step further!


