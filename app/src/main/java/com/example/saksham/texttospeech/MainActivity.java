package com.example.saksham.texttospeech;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextToSpeech toSpeech;
    EditText editText;
    Button btnspeak,btnstop;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnspeak=(Button)findViewById(R.id.btnspeak);
        btnstop=(Button)findViewById(R.id.btnstop);
        editText=(EditText)findViewById(R.id.editText);
        toSpeech=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener(){

            @Override
            public void onInit(int status) {
                if(status!=TextToSpeech.ERROR){
                    toSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });
        btnspeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tospeak=editText.getText().toString();
                Toast.makeText(MainActivity.this, tospeak, Toast.LENGTH_SHORT).show();
                toSpeech.speak(tospeak,TextToSpeech.QUEUE_FLUSH,null);

            }
        });
        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toSpeech!=null){
                    toSpeech.stop();
                }
            }
        });

    }
    public void onPause() {
        if(toSpeech!=null){
            toSpeech.stop();
            toSpeech.shutdown();
        }

        super.onPause();
    }
}
