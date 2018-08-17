package com.example.james.tft_android.me;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.james.tft_android.R;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<String> list;
    private OnItemClickListener onItemClickListener;

    public MyRecyclerViewAdapter(List<String> list) {
        this.list = list;

    }

    /**
     * 插入一条数据
     *
     * @param index 下标
     * @param s     数据
     */
    public void addItem(int index, String s) {
        list.add(index, s);
        notifyItemInserted(index);
    }

    /**
     * 删除一条数据
     *
     * @param index 下标
     */
    public void deleteItem(int index) {
        list.remove(index);
        notifyItemRemoved(index);
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_base_use, parent, false);
        MyRecyclerViewAdapter.ViewHolder viewHolder = new MyRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override

    public void onBindViewHolder(MyRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.mText.setText(list.get(position));
        if (onItemClickListener != null){
            holder.itemView.setOnClickListener(new MyOnClickListener(position,list.get(position)));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mText;

        ViewHolder(View itemView) {
            super(itemView);
            mText = itemView.findViewById(R.id.item_tx);
        }

    }





    private class MyOnClickListener implements View.OnClickListener{
        private int position;
        private String data;

        public MyOnClickListener(int position, String data) {
            this.position = position;
            this.data = data;
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v,position,data);
        }
    }
//    监听器
    public interface OnItemClickListener {
        void onItemClick(View view, int position, String data);
    }

}
