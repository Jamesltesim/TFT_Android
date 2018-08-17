package com.example.james.tft_android.me;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.example.james.tft_android.R;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class TestActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private GroupRecyAdapter mAdapter;
    private List<String> list;
    private LinkedHashMap<String, ArrayList<GroupChildBean.ResultMsgBean.GroupListBean.ChildListBean>> groupMap =
            new LinkedHashMap<String, ArrayList<GroupChildBean.ResultMsgBean.GroupListBean.ChildListBean>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        list = new ArrayList<>();
        mRecyclerView = findViewById(R.id.recyclerView);
//设置RecyclerView管理器
        initView();
        //通过findViewById拿到RecyclerView实例

//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
////初始化适配器
////设置添加或删除item时的动画，这里使用默认动画
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
////设置适配器
//        mRecyclerView.setAdapter(mAdapter);


    }

    private void initView() {

        final GridLayoutManager manager = new GridLayoutManager(this, 3, OrientationHelper.VERTICAL, false);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(mAdapter.getItemViewType(position) == GroupRecyAdapter.CHILD_ITEM_TYPE){
                    return 1;
                }else {
                    return manager.getSpanCount();
                }
//                return mAdapter.getItemViewType(position) == GroupRecyAdapter.GROUP_ITEM_TYPE ? manager.getSpanCount() : 1;
            }
        });
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new GroupRecyAdapter(this);


        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setList(initData());
    }

    private LinkedHashMap<String, ArrayList<GroupChildBean.ResultMsgBean.GroupListBean.ChildListBean>> initData() {
/**
 * json数据
 */
        String data = getJson("me.json",this);

        GroupChildBean bean = new Gson().fromJson(data, GroupChildBean.class);

        for (GroupChildBean.ResultMsgBean.GroupListBean group : bean.getResultMsg().getGroupList()) {

            ArrayList<GroupChildBean.ResultMsgBean.GroupListBean.ChildListBean> childList = new ArrayList<>();

            for (GroupChildBean.ResultMsgBean.GroupListBean.ChildListBean child : group.getChildList()) {

                GroupChildBean.ResultMsgBean.GroupListBean.ChildListBean childBean = new GroupChildBean.ResultMsgBean.GroupListBean.ChildListBean(child.getChildName());
                childList.add(childBean);

            }
            groupMap.put(group.getGroupName(), childList);
        }
        return groupMap;
    }

    //    json --> jsonString
    public String getJson(String fileName, Context context){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStream is = context.getAssets().open(fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line=bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }
}
