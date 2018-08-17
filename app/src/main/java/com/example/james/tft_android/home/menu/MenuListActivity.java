package com.example.james.tft_android.home.menu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.james.tft_android.R;

import java.util.ArrayList;
import java.util.List;

public class MenuListActivity extends AppCompatActivity {


    private ViewPager viewPager;  //对应的viewPager

    private List<Fragment> listfragment;//view数组

    private TabLayout tableLayout;

    private String[] tabStrs = {"num1", "asd", "num3", "8", "111", "000", "333"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_scrolling);


            viewPager = (ViewPager) findViewById(R.id.viewpager); //获取ViewPager
            tableLayout = findViewById(R.id.tablayout);

            listfragment = new ArrayList<>(); //new一个List<Fragment>

            for (int i = 0; i < tabStrs.length; i++) {
                tableLayout.addTab(tableLayout.newTab().setText(tabStrs[i]));
                listfragment.add(new MenuSortFragment());

            }

            MenuListAdapter adapter=new MenuListAdapter(getSupportFragmentManager(),listfragment);
            viewPager.setAdapter(adapter);


            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tableLayout));
            tableLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });

        }
}
