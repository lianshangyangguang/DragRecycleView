package com.zxy.androiddemo.activity;

import android.app.Service;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.zxy.androiddemo.DragBean;
import com.zxy.androiddemo.R;
import com.zxy.androiddemo.adapter.DragAdapter;
import com.zxy.androiddemo.adapter.DragViewHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author xiyingzhu
 * @date 2019/3/4
 */
public class DragActivity extends AppCompatActivity {

    private static final int DEFAULT_COUNT = 20;
    private RecyclerView mDragRecycleView;
    private DragAdapter mDragAdapter;
    private List<DragBean> mList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_layout);

        mList = new ArrayList<>();
        for (int i = 0; i < DEFAULT_COUNT; i++) {
            DragBean bean = new DragBean();
            bean.setContent("To discuss a play he heard about");
            if (i > 9) {
                bean.setTitle(String.valueOf(i));
            } else {
                bean.setTitle(new StringBuilder("0").append(String.valueOf(i)).toString());
            }
            mList.add(bean);
        }

        mDragRecycleView = findViewById(R.id.drag_recycle_view);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
//        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mDragRecycleView.setLayoutManager(manager);
        mDragRecycleView.setItemAnimator(new DefaultItemAnimator());
        mDragAdapter = new DragAdapter(this, mList);
        mDragRecycleView.setAdapter(mDragAdapter);

        helper.attachToRecyclerView(mDragRecycleView);
    }

    /**
     * 为RecycleView绑定触摸事件
     */
    ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            //首先回调的方法 返回int表示是否监听该方向
            //拖拽
            int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN|ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
//            int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;  // LinearLayoutManager
            //滑动
            int swipeFlags = 0;
//            int swipeFlags = ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;//侧滑删除
            return makeMovementFlags(dragFlags, swipeFlags);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//            //滑动事件
//            Collections.swap(mList, viewHolder.getAdapterPosition(), target.getAdapterPosition());
//            mDragAdapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
//
//            return false;
            //得到当拖拽的viewHolder的Position
            int fromPosition = viewHolder.getAdapterPosition();
            //拿到当前拖拽到的item的viewHolder
            int toPosition = target.getAdapterPosition();
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(mList, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(mList, i, i - 1);
                }
            }
            mDragAdapter.notifyItemMoved(fromPosition, toPosition);
            return true;

        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            //侧滑事件
//            mList.remove(viewHolder.getAdapterPosition());
//            mDragAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
        }

        @Override
        public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
            //当长按选中item的时候（拖拽开始的时候）调用((改变选中状态))
            //获取系统震动服务(震动100毫秒)
            Vibrator vib = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
            vib.vibrate(100);
            if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                if (viewHolder instanceof DragViewHolder) {
                    ((DragViewHolder) viewHolder).titleText.setSelected(true);
                    ((DragViewHolder) viewHolder).contentText.setSelected(true);
                    ((DragViewHolder) viewHolder).layout.setSelected(true);
                }
            }
            super.onSelectedChanged(viewHolder, actionState);
        }

        @Override
        public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            //当手指松开的时候（拖拽完成的时候）调用(改变选中状态)
            viewHolder.itemView.setBackgroundColor(0);
            if (viewHolder instanceof DragViewHolder) {
                ((DragViewHolder) viewHolder).titleText.setSelected(false);
                ((DragViewHolder) viewHolder).contentText.setSelected(false);
                ((DragViewHolder) viewHolder).layout.setSelected(false);
            }
        }

        @Override
        public boolean isLongPressDragEnabled() {
            //是否可拖拽(可以处理某个可滑动或者不能滑动的需求)
            return true;
        }
    });

}
