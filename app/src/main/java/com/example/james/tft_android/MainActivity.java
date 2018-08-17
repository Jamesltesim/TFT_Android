package com.example.james.tft_android;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.james.tft_android.activity.ActivityFragment;
import com.example.james.tft_android.base.BaseActivity;
import com.example.james.tft_android.discover.DiscoverFragment;
import com.example.james.tft_android.home.HomeFragment;
import com.example.james.tft_android.me.MeFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private View content,home,activity,discover,me;

    HomeFragment homeFragment;
    ActivityFragment activityFragment;
    DiscoverFragment discoverFragment;
    MeFragment meFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        content = findViewById(R.id.fragment_content);

        home = findViewById(R.id.home);
        activity = findViewById(R.id.activity);
        discover = findViewById(R.id.discover);
        me = findViewById(R.id.me);

        home.setOnClickListener(this);
        activity.setOnClickListener(this);
        discover.setOnClickListener(this);
        me.setOnClickListener(this);


        homeFragment = new HomeFragment();
        activityFragment = new ActivityFragment();
        discoverFragment = new DiscoverFragment();
        meFragment = new MeFragment();

        getSupportFragmentManager().
                beginTransaction().
                add(R.id.fragment_content,homeFragment).
                add(R.id.fragment_content,activityFragment ).
                add(R.id.fragment_content,discoverFragment).
                add(R.id.fragment_content,meFragment).
                hide(activityFragment).
                hide(discoverFragment).
                hide(meFragment).
                commit();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home:{

                getSupportFragmentManager().
                        beginTransaction().
                        show(homeFragment).
                        hide(activityFragment).
                        hide(discoverFragment).
                        hide(meFragment).
                        commit();

                break;
            }
            case R.id.activity:{

                getSupportFragmentManager().
                        beginTransaction().
                        show(activityFragment).
                        hide(homeFragment).
                        hide(discoverFragment).
                        hide(meFragment).
                        commit();

                break;
            }
            case R.id.discover:{
                getSupportFragmentManager().
                        beginTransaction().
                        show(discoverFragment).
                        hide(homeFragment).
                        hide(activityFragment).
                        hide(meFragment).
                        commit();

                break;
            }
            case R.id.me:{
                getSupportFragmentManager().
                        beginTransaction().
                        show(meFragment).
                        hide(homeFragment).
                        hide(activityFragment).
                        hide(discoverFragment).
                        commit();

                break;
            }
        }
    }
}
