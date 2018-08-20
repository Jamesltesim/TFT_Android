package com.example.james.tft_android.base.network;

import android.app.Activity;
import android.content.Context;

import com.example.james.tft_android.App;
import com.example.james.tft_android.tools.DataUtils;

public class NetworkManage {

    public static String  homeList(){
       return DataUtils.getJson("homelist.json", App.getApplication());
    }
}
