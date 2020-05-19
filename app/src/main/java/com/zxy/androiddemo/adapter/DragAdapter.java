package com.zxy.androiddemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zxy.androiddemo.DragBean;
import com.zxy.androiddemo.R;

import java.util.List;

/**
 * @author xiyingzhu
 * @date 2019/3/4
 */
public class DragAdapter extends RecyclerView.Adapter<DragViewHolder> {

    private List<DragBean> mList;
    private Context mContext;

    public DragAdapter(Context context, List<DragBean> mList) {
        this.mContext = context;
        this.mList = mList;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    @NonNull
    @Override
    public DragViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_drag_item_layout, viewGroup, false);
        return new DragViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DragViewHolder viewHolder, int i) {
        DragBean bean = mList.get(i);
        viewHolder.titleText.setText(bean.getTitle());
        viewHolder.contentText.setText(bean.getContent());
    }

}
