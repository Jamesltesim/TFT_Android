package com.example.james.tft_android.home;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

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

                if(mAdapter.getItemViewType(position) == HomeListAdapter.GENERAL_ITEM__TYPE){
                    return 1;
                } else {
                    return manager.getSpanCount();
                }

            }
        });
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new HomeListAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setList(initData());
        mAdapter.setOnClickListener(new HomeListAdapter.HomeListItemOnClickListener() {
            @Override
            public void homeListItemOnClick(int section, int index) {
                Toast.makeText(getActivity(),"当前点击了第"+section + "组,第" +index +"行",Toast.LENGTH_SHORT).show();
            }
        });
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
            HomeListBean.ChildListBean header = new HomeListBean.ChildListBean(group.getGroupName());
            header.setDiscountHeader(true);
            childList.add(header);

            for (int j=0 ;j<group.getChildList().size();j++){
                HomeListBean.ChildListBean child = group.getChildList().get(j);
                HomeListBean.ChildListBean childBean = new HomeListBean.ChildListBean(child.getChildName());

                childBean.section = i;
                childBean.index = j;
                if(i==0){
                    childBean.setDiscountItem(true);
                }else if (i==1){
                    childBean.setGeneralItem(true);
                }

                childList.add(childBean);
            }

            //添加 当前组 footer 数据
            HomeListBean.ChildListBean footer = new HomeListBean.ChildListBean("footer");
            footer.setDiscountFooter(true);
            childList.add(footer);


            list.addAll(childList);
        }

        return list;
    }
}
