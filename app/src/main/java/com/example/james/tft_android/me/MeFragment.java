package com.example.james.tft_android.me;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.example.james.tft_android.R;
import com.example.james.tft_android.base.BaseFragment;
import com.example.james.tft_android.tools.DataUtils;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;

public class MeFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    protected RecyclerView mRecyclerView;

    private GroupRecyAdapter mAdapter;
    private List<String> list;
    private LinkedHashMap<String, ArrayList<GroupChildBean.ResultMsgBean.GroupListBean.ChildListBean>> groupMap =
            new LinkedHashMap<String, ArrayList<GroupChildBean.ResultMsgBean.GroupListBean.ChildListBean>>();


    @Override
    public int getLayout() {
        return R.layout.fragment_me;
    }

    @Override
    public void onCreateView() {
        list = new ArrayList<>();
//        mRecyclerView = rootView.findViewById(R.id.recyclerView);
        initView();
    }

    private void initView() {

        final GridLayoutManager manager = new GridLayoutManager(getActivity(), 3, OrientationHelper.VERTICAL, false);
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
        mAdapter = new GroupRecyAdapter(getActivity());


        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setList(initData());
    }

    private LinkedHashMap<String, ArrayList<GroupChildBean.ResultMsgBean.GroupListBean.ChildListBean>> initData() {
/**
 * json数据
 */
        String data = DataUtils.getJson("me.json",getActivity());

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


}
