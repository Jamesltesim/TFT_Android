package com.example.james.tft_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private View home,activity,discover,me;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        home = findViewById(R.id.home);
        activity = findViewById(R.id.activity);
        discover = findViewById(R.id.discover);
        me = findViewById(R.id.me);

        home.setOnClickListener(this);
        activity.setOnClickListener(this);
        discover.setOnClickListener(this);
        me.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home:{
                Toast.makeText(MainActivity.this, R.string.home,Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.activity:{
                Toast.makeText(MainActivity.this, R.string.activity,Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.discover:{
                Toast.makeText(MainActivity.this, R.string.discover,Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.me:{
                Toast.makeText(MainActivity.this, R.string.me,Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
}
