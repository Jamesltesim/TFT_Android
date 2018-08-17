package com.example.james.tft_android.base;

public class TFManage {
    //    1. 创建类中的私有构造
    private TFManage(){

    }
    // 2. 创建类的私有静态实例

    private static TFManage instance = new TFManage();
    // 3 创建共有静态方法 返回静态对象

    public static TFManage getInstance(){
        return instance;
    }
}
