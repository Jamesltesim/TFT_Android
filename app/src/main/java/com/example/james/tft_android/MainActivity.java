package com.example.james.tft_android;

import android.view.View;
import android.widget.LinearLayout;

import com.example.james.tft_android.activity.ActivityFragment;
import com.example.james.tft_android.base.BaseActivity;
import com.example.james.tft_android.discover.DiscoverFragment;
import com.example.james.tft_android.home.HomeFragment;
import com.example.james.tft_android.me.MeFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity  {

    HomeFragment homeFragment = new HomeFragment();
    ActivityFragment activityFragment = new ActivityFragment();
    DiscoverFragment discoverFragment = new DiscoverFragment();
    MeFragment meFragment = new MeFragment();

    @BindView(R.id.home)
    LinearLayout home;
    @BindView(R.id.activity)
    LinearLayout activity;
    @BindView(R.id.discover)
    LinearLayout discover;
    @BindView(R.id.me)
    LinearLayout me;

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onCreate() {

        getSupportFragmentManager().
                beginTransaction().
                add(R.id.fragment_content, homeFragment).
                add(R.id.fragment_content, activityFragment).
                add(R.id.fragment_content, discoverFragment).
                add(R.id.fragment_content, meFragment).
                hide(activityFragment).
                hide(discoverFragment).
                hide(meFragment).
                commit();
    }

    @OnClick({R.id.home, R.id.activity, R.id.discover, R.id.me})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home: {

                getSupportFragmentManager().
                        beginTransaction().
                        show(homeFragment).
                        hide(activityFragment).
                        hide(discoverFragment).
                        hide(meFragment).
                        commit();

                break;
            }
            case R.id.activity: {

                getSupportFragmentManager().
                        beginTransaction().
                        show(activityFragment).
                        hide(homeFragment).
                        hide(discoverFragment).
                        hide(meFragment).
                        commit();

                break;
            }
            case R.id.discover: {
                getSupportFragmentManager().
                        beginTransaction().
                        show(discoverFragment).
                        hide(homeFragment).
                        hide(activityFragment).
                        hide(meFragment).
                        commit();

                break;
            }
            case R.id.me: {
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
