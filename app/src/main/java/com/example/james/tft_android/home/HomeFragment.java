package com.example.james.tft_android.home;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.example.james.tft_android.R;
import com.example.james.tft_android.base.BaseFragment;

import com.example.james.tft_android.base.network.NetworkManage;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    protected RecyclerView mRecyclerView;
    private GroupRecyAdapter mAdapter;
    private List<String> list;
    private LinkedHashMap<String, ArrayList<HomeListBean.DataBean.ChildListBean>> groupMap =
            new LinkedHashMap<String, ArrayList<HomeListBean.DataBean.ChildListBean>>();
//    protected
    public HomeFragment() {
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void onCreateView() {
        list = new ArrayList<>();
        initView();
    }

    private void initView() {

        final GridLayoutManager manager = new GridLayoutManager(getActivity(), 3, OrientationHelper.VERTICAL, false);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                if(mAdapter.getItemViewType(position) == GroupRecyAdapter.DISCOUNT_ITEM_TYPE){
                    return manager.getSpanCount();
                }

                if(mAdapter.getItemViewType(position) == GroupRecyAdapter.CHILD_ITEM_TYPE){
                    return 1;
                }else {
                    return manager.getSpanCount();
                }
//                return mAdapter.getItemViewT ype(position) == GroupRecyAdapter.GROUP_ITEM_TYPE ? manager.getSpanCount() : 1;
            }
        });
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new GroupRecyAdapter(getActivity());


        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setList(initData());
    }

    private LinkedHashMap<String, ArrayList<HomeListBean.DataBean.ChildListBean>> initData() {
/**
 * json数据
 */
        String data = NetworkManage.homeList(getContext());

        HomeListBean bean = new Gson().fromJson(data, HomeListBean.class);

        for (HomeListBean.DataBean group : bean.getData()) {

            ArrayList<HomeListBean.DataBean.ChildListBean> childList = new ArrayList<>();

            for (HomeListBean.DataBean.ChildListBean child : group.getChildList()) {

                HomeListBean.DataBean.ChildListBean childBean = new HomeListBean.DataBean.ChildListBean(child.getChildName());
                childList.add(childBean);

            }
            groupMap.put(group.getGroupName(), childList);
        }
        return groupMap;
    }
}
