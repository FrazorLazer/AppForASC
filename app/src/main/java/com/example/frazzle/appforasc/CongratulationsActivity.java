package com.example.frazzle.appforasc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CongratulationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulations);
    }


    public void continueStory(View view){
        this.finish();
    }
}
