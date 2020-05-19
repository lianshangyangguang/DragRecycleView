package com.zxy.androiddemo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zxy.androiddemo.R;

/**
 * @author xiyingzhu
 * @date 2019/3/4
 */
public class DragViewHolder extends RecyclerView.ViewHolder {
    public TextView titleText, contentText;
    public RelativeLayout layout;

    public DragViewHolder(@NonNull View itemView) {
        super(itemView);
        titleText = itemView.findViewById(R.id.drag_item_title);
        contentText = itemView.findViewById(R.id.drag_item_content);
        layout = itemView.findViewById(R.id.drag_layout);
    }
}
