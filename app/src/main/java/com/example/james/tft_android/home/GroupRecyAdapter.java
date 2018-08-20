package com.example.james.tft_android.home;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.james.tft_android.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by caobin on 2017/9/14.
 */

public class GroupRecyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int GROUP_ITEM_TYPE = 1;
    public static final int CHILD_ITEM_TYPE = 2;
    public static final int FOOTER_ITEM_TYPE = 3;


    public static final int DISCOUNT_HEADER__TYPE = 4;
    public static final int DISCOUNT_ITEM_TYPE = 5;
    public static final int DISCOUNT_FOOTER__TYPE = 6;

    public static final int GENERAL_HEADER__TYPE = 7;
    public static final int GENERAL_ITEM__TYPE = 8;
    public static final int GENERAL_FOOTER__TYPE = 9;
    private List<HomeListBean.ChildListBean> mList = new ArrayList<>();
    private LayoutInflater inflater;
    private Context mContext;

    public GroupRecyAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(context);
    }

    /**
     * @param map
     */
    public void setList(LinkedHashMap<String, ArrayList<HomeListBean.ChildListBean>> map) {

        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next().toString();

            if (map.get(key).size() > 0) {
                mList.add(new HomeListBean.ChildListBean(key, true));
//                Log.i("tag",""+key);
            }
            mList.addAll(map.get(key));

        }

        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        if (viewType == GROUP_ITEM_TYPE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_group, parent, false);
            System.err.println("onCreateViewHolder");
            holder = new GroupViewHolder(view);
        }
        else if (viewType == CHILD_ITEM_TYPE){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_child, parent, false);
            holder = new ChildViewHolder(view);
        }

        else if (viewType == FOOTER_ITEM_TYPE){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_group, parent, false);
            System.err.println("onCreateViewHolder");
            holder = new GroupViewHolder(view);
        }



        if(viewType == DISCOUNT_ITEM_TYPE){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_discount_home, parent, false);
            holder = new ChildViewHolder1(view);
        }

        return holder;


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final HomeListBean.ChildListBean bean = mList.get(position);
        int type = holder.getItemViewType();
        if (type == GROUP_ITEM_TYPE) {
            GroupViewHolder holder1 = (GroupViewHolder) holder;
            holder1.textView.setText(bean.getChildName());
            holder1.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, bean.getChildName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if(type == CHILD_ITEM_TYPE){
            ChildViewHolder holder1 = (ChildViewHolder) holder;
            holder1.textView.setText(bean.getChildName());
            holder1.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, bean.getChildName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if (type == FOOTER_ITEM_TYPE){
            GroupViewHolder holder1 = (GroupViewHolder) holder;
            holder1.textView.setText(bean.getChildName());
            holder1.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, bean.getChildName(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        if(type == DISCOUNT_ITEM_TYPE){
            ChildViewHolder1 holder1 = (ChildViewHolder1) holder;
//            holder1.textView.setText(bean.getChildName());
//            holder1.cardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(mContext, bean.getChildName(), Toast.LENGTH_SHORT).show();
//                }
//            });
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        System.err.println("getItemViewType");

        if (mList.get(position).isDiscountItem()) {
            return DISCOUNT_ITEM_TYPE;
        }



        if (mList.get(position).isFooter()) {
            return FOOTER_ITEM_TYPE;
        }

        if (mList.get(position).isGroup()) {
            return GROUP_ITEM_TYPE;
        } else {
            return CHILD_ITEM_TYPE;
        }


    }


    /**
     * 分组
     */
    class GroupViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        LinearLayout cardView;

        public GroupViewHolder(View itemView) {
            super(itemView);
            System.err.println("GroupViewHolder");
            textView = (TextView) itemView.findViewById(R.id.header);
            cardView = (LinearLayout) itemView.findViewById(R.id.card_view);
        }
    }

    /**
     * 成员
     */
    class ChildViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CardView cardView;

        public ChildViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }

    /**
     * 打折商品
     */
    class ChildViewHolder1 extends RecyclerView.ViewHolder {
//        TextView textView;
//        CardView cardView;

        public ChildViewHolder1(View itemView) {
            super(itemView);
//            textView = (TextView) itemView.findViewById(R.id.tv);
//            cardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }

    /**
     * footer
     */
    class FooterViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        LinearLayout cardView;

        public FooterViewHolder(View itemView) {
            super(itemView);
            System.err.println("GroupViewHolder");
            textView = (TextView) itemView.findViewById(R.id.header);
            cardView = (LinearLayout) itemView.findViewById(R.id.card_view);
        }
    }
}
