package com.cy.utils.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.SystemClock;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.cy.utils.common.GraphicUtil;
import com.cy.utils.common.ViewUtil;

/**
 * created by cy on 2020/1/16 0016.
 */
public class MonitorView extends View {

    /** The m start time. */
    private long mStartTime = -1;

    /** The m counter. */
    private int mCounter;

    /** The m fps. */
    private int mFps;

    /** The m paint. */
    private final Paint mPaint;

    /**
     * 构造阻塞测试View.
     *
     * @param context the context
     */
    public MonitorView(Context context) {
        this(context, null);
    }

    /**
     * 构造阻塞测试View.
     *
     * @param context the context
     * @param attributeset the attributeset
     */
    public MonitorView(Context context, AttributeSet attributeset) {
        super(context);
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);
        mPaint.setTypeface(Typeface.DEFAULT);
        mPaint.setTextSize(16);
    }

    /**
     * 绘制
     */
    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawColor(Color.argb(80, 0, 0, 0));
        if (mStartTime == -1) {
            mStartTime = SystemClock.elapsedRealtime();
            mCounter = 0;
        }

        long now = SystemClock.elapsedRealtime();
        long delay = now - mStartTime;

        if(delay!=0){
            // 计算帧速率
            mFps = (int)(mCounter * 1000 / delay);
        }

        String text = mFps + " fps";
        //获取值的文本的高度
        TextPaint mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTypeface(Typeface.DEFAULT);
        ViewUtil.setTextSize(this.getContext(),mTextPaint, 30);
        ViewUtil.setTextSize(this.getContext(), mPaint, 30);
        Paint.FontMetrics fm  = mTextPaint.getFontMetrics();
        //得到行高
        int textHeight = (int)Math.ceil(fm.descent - fm.ascent)+2;
        int textWidth = (int) GraphicUtil.getStringWidth(text,mTextPaint);

        canvas.drawText(text,(this.getWidth()-textWidth)/2, textHeight, mPaint);
        if (delay > 1000L) {
            mStartTime = now;
            mFps = mCounter;
            mCounter = 0;
        }
        mCounter++;
        super.onDraw(canvas);
    }

}

