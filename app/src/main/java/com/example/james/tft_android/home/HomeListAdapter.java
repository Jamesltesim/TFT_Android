package com.example.james.tft_android.home;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.james.tft_android.R;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by caobin on 2017/9/14.
 */

public class HomeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int DISCOUNT_HEADER__TYPE = 4;
    public static final int DISCOUNT_ITEM_TYPE = 5;
    public static final int DISCOUNT_FOOTER__TYPE = 6;

    public static final int GENERAL_HEADER__TYPE = 7;
    public static final int GENERAL_ITEM__TYPE = 8;
    public static final int GENERAL_FOOTER__TYPE = 9;
    private List<HomeListBean.ChildListBean> mList = new ArrayList<>();
    private List<String> imageList = new ArrayList<>();

    private LayoutInflater inflater;
    private Context mContext;

    public void setBanner(Banner banner) {
        this.banner = banner;
    }

    public Banner getBanner() {
        return banner;
    }

    private Banner banner;


    protected HomeListItemOnClickListener onClickListener;
    public void setOnClickListener(HomeListItemOnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public HomeListAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(context);

    }

    /**
     * @param children
     */
    public void setList(List<HomeListBean.ChildListBean> children) {
        mList = children;
        notifyDataSetChanged();
    }


    public void setImageList(List<String> list){
        imageList = list;
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;

        if(viewType == DISCOUNT_ITEM_TYPE){
            view = inflater.inflate(R.layout.item_discount_home, parent, false);
            holder = new ChildViewHolder1(view);
        }else if (viewType == GENERAL_ITEM__TYPE){
            view = inflater.inflate(R.layout.item_general_home, parent, false);
            holder = new ChildViewHolder(view);
        }
        else if (viewType == DISCOUNT_HEADER__TYPE){
            view = inflater.inflate(R.layout.item_banner_home, parent, false);
//            System.err.println("onCreateViewHolder");
            holder = new BannerViewHolder(view);

        }
        else {
            view = inflater.inflate(R.layout.item_list_group, parent, false);
            System.err.println("onCreateViewHolder");
            holder = new GroupViewHolder(view);
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final HomeListBean.ChildListBean bean = mList.get(position);
        int type = holder.getItemViewType();

        if(type == DISCOUNT_ITEM_TYPE){
            ChildViewHolder1 holder1 = (ChildViewHolder1) holder;
//            holder1.textView.setText(bean.getChildName());
//            holder1.cardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(mContext, bean.getChildName(), Toast.LENGTH_SHORT).show();
//                }
//            })
            holder1.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null){
                        onClickListener.homeListItemOnClick(bean.section,bean.index);
                    }
                }
            });
        }else if(type == GENERAL_ITEM__TYPE){
            ChildViewHolder holder1 = (ChildViewHolder) holder;
            holder1.textView.setText(bean.getChildName());
            holder1.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null){
                        onClickListener.homeListItemOnClick(bean.section,bean.index);
                    }
                }
            });
        }
        else if(type == DISCOUNT_HEADER__TYPE){
            BannerViewHolder viewHolder = (BannerViewHolder) holder;
            viewHolder.banner.setImageLoader(new GlideImageLoader());
            //设置图片集合

            viewHolder.banner.setImages(imageList);
//            viewHolder.banner.setBannerStyle(Banner.)
//            banner设置方法全部调用完毕时最后调用
            viewHolder.banner.start();
//            holder1.setIsRecyclable(false);

        }
        else {
            GroupViewHolder holder1 = (GroupViewHolder) holder;
            holder1.textView.setText(bean.getChildName());
            holder1.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, bean.getChildName(), Toast.LENGTH_SHORT).show();
                }
            });
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
        else if(mList.get(position).isGeneralItem()){
            return GENERAL_ITEM__TYPE;
        }
        else if(mList.get(position).isDiscountHeader()){
            return DISCOUNT_HEADER__TYPE;
        }
        else if(mList.get(position).isDiscountFooter()){
            return DISCOUNT_FOOTER__TYPE;
        }
        else if(mList.get(position).isGeneralHeader()){
            return GENERAL_HEADER__TYPE;
        }
        else {
            return GENERAL_FOOTER__TYPE;
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
        ImageView cardView;

        public ChildViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv);
            cardView = (ImageView) itemView.findViewById(R.id.iv);
        }
    }

    /**
     * 优惠特价商品
     */
    class ChildViewHolder1 extends RecyclerView.ViewHolder {
//        TextView textView;
//        CardView cardView;

          View view;
        public ChildViewHolder1(View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.discount_item);
//            textView = (TextView) itemView.findViewById(R.id.tv);
//            cardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }

//    public void setHomeBanner(){
//
//    }

    /**
     * banner
     */
    class BannerViewHolder extends RecyclerView.ViewHolder {
        Banner banner;

        public BannerViewHolder(View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);

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

    protected interface HomeListItemOnClickListener{

        void homeListItemOnClick(int section,int index);
    }
}
