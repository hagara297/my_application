package com.example.app;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    // declaring required variables
    private Socket client;
    private EditText textField;
    private TextView txv;
    private Button button;
    private String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // reference to the text field
        textField = (EditText) findViewById(R.id.editText1);
        txv=(TextView ) findViewById(R.id.textView);
        // reference to the send button
        button = (Button) findViewById(R.id.button1);
        // Button press event listener
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the text message on the text field
                message = textField.getText().toString();
                txv.setText(message);
                // start the Thread to connect to server
                new Thread(new ClientThread(message)).start();
            }
});
        }

    // the networking operations
    class ClientThread implements Runnable {
        private final String message;

        ClientThread(String message) {
            this.message = message;
        }

        @Override
        public void run() {
            try {
                client = new Socket("192.168.6.107", 5555); // connect to server
                PrintWriter printwriter = new PrintWriter(client.getOutputStream(), true);
                printwriter.write(message); // write the message to output
                printwriter.flush();
                printwriter.close();
                client.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            }
        }
        public void toastMsg(String msg){
            Toast toast=Toast.makeText(this,msg,Toast.LENGTH_LONG);
            toast.show();
            toastMsg("welcome to my app");
    }
        public void displayToastMsg(View v){
        toastMsg("welcome to my app");


    }}







            // updating the UI








