package com.cy.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.cy.utils.R;


/**
 * created by cy on 2020/7/30 0030.
 */
public class MyDialog extends Dialog implements View.OnClickListener {
    private static final String TAG = "SceneExecuteDialog";
    public final static int TYPE_SHOW_SCENE = 0;
    public final static int TYPE_SHOW_DEVICE = 1;
    private int mTypeShow = 0;

    private TextView mTvTitle;

    public MyDialog(@NonNull Context context) {
        super(context);
    }

    public MyDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_scenes_execution);
        mTvTitle = findViewById(R.id.tv_title);
        TextView btnOk = findViewById(R.id.btn_confirm);
        TextView btnCancel = findViewById(R.id.btn_cancel);
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);


    }

    public MyDialog setTypeShow(int type) {
        this.mTypeShow = type;
        return this;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm:
                if (mIClickCallback != null) {
                    mIClickCallback.confirm();
                }
                dismiss();
                break;
            case R.id.btn_cancel:
                if (mIClickCallback != null) {
                    mIClickCallback.cancel();
                }
                dismiss();
                break;
            default:
                break;
        }
    }

    public static interface IClickCallback {
        void confirm();//

        void cancel();
    }

    private IClickCallback mIClickCallback;

    public MyDialog setClickCallback(IClickCallback mIClickCallback) {
        this.mIClickCallback = mIClickCallback;
        return this;
    }

}
