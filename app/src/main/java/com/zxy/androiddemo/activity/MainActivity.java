package com.zxy.androiddemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.zxy.androiddemo.R;

/**
 * @author xiyingzhu
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mDragButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDragButton = findViewById(R.id.drag_btn);
        mDragButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.drag_btn:
                startActivity(new Intent(this, DragActivity.class));
                break;
        }
    }
}
