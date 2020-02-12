package com.johnmelodyme.speechtotextengine;
/*
 * This is  a simple application inspired
 * by My Girlfriend a SpeechToText
 * engine for android for device
 * Api 21 and above.
 */

import androidx.appcompat.app.AppCompatActivity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Locale;

/**
 * @Author : John Melody Melissa
 * @Inspired_by : {GF} Sin Dee 😍🥰🥰
 * @Project_name: Speech To Text
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private final int REQ_CODE = 0x64;
    private Button VOICE;
    private TextView OUTPUT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VOICE = findViewById(R.id.voice);
        OUTPUT = findViewById(R.id.output);

        VOICE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w(TAG,  "Voice button clicked.");
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say Something \n and Will appear to the screen");
                Log.w(TAG,  "\"Need to speak\"");
                try {
                    startActivityForResult(intent, REQ_CODE);
                } catch (ActivityNotFoundException a) {
                    Toaster("Sorry your device not supported");
                    Log.w(TAG,"Sorry your device not supported" + a);
                }
            }
        });
    }

    public void Toaster(String msg) {
        Toast.makeText(getApplicationContext(),
                msg,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    OUTPUT.setText((CharSequence) result.get(0x0)); // (!= int)**
                    Log.w(TAG,  "Voice ====> Text" + "\t {OK}");
                }
            }
        }
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}