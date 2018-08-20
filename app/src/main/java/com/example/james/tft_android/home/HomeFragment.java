package com.example.james.tft_android.home;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.example.james.tft_android.R;
import com.example.james.tft_android.base.BaseBean;
import com.example.james.tft_android.base.BaseFragment;

import com.example.james.tft_android.base.network.NetworkManage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    protected RecyclerView mRecyclerView;
    private HomeListAdapter mAdapter;

//    protected
    public HomeFragment() {
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void onCreateView() {
        final GridLayoutManager manager = new GridLayoutManager(getActivity(), 3, OrientationHelper.VERTICAL, false);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                if(mAdapter.getItemViewType(position) == HomeListAdapter.DISCOUNT_ITEM_TYPE){
                    return manager.getSpanCount();
                }

                if(mAdapter.getItemViewType(position) == HomeListAdapter.CHILD_ITEM_TYPE){
                    return 1;
                }else {
                    return manager.getSpanCount();
                }
//                return mAdapter.getItemViewT ype(position) == GroupRecyAdapter.GROUP_ITEM_TYPE ? manager.getSpanCount() : 1;
            }
        });
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new HomeListAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setList(initData());
    }

    private List<HomeListBean.ChildListBean> initData() {
        //网络请求的数据
        String data = NetworkManage.homeList();

        Type type = new TypeToken<BaseBean<HomeListBean>>(){}.getType();

        //解析成model
        BaseBean<HomeListBean> bean = new Gson().fromJson(data, type);

        List<HomeListBean.ChildListBean> list = new ArrayList<>();

        //得到每一组数据
        for (int i=0 ;i<bean.getData().size();i++){

            HomeListBean group = bean.getData().get(i);

            ArrayList<HomeListBean.ChildListBean> childList = new ArrayList<>();

            //添加 当前组 header 数据
            childList.add(new HomeListBean.ChildListBean(group.getGroupName(), true));

            for (int j=0 ;j<group.getChildList().size();j++){
                HomeListBean.ChildListBean child = group.getChildList().get(j);
                HomeListBean.ChildListBean childBean = new HomeListBean.ChildListBean(child.getChildName());
                childList.add(childBean);
            }

            //添加 当前组 footer 数据
            childList.add(new HomeListBean.ChildListBean("footer",false,true));

            list.addAll(childList);
        }

        return list;
    }
}
