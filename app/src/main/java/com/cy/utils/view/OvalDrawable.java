package com.cy.utils.view;

import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cy.utils.R;


/**
 * 波纹效果
 */
public class OvalDrawable extends Drawable implements ValueAnimator.AnimatorUpdateListener, Animatable {

    /* renamed from: a */
    private ValueAnimator mValueAnimator = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});

    /* renamed from: b */
    private float mFloatValue;

    /* renamed from: c */
    private Drawable mBg;

    /* renamed from: d */
    private float mMaxX;

    /* renamed from: e */
    private float mMaxY;

    /* renamed from: f */
    private float mMinX;

    /* renamed from: g */
    private float mMinY;

    public OvalDrawable(Resources resources) {
        mValueAnimator.addUpdateListener(this);
        mValueAnimator.setDuration(1000);
        mValueAnimator.setRepeatMode(1);
        mValueAnimator.setRepeatCount(-1);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mBg = resources.getDrawable(R.drawable.bg_listening);
        mMaxX = resources.getDimension(R.dimen.x95);
        mMaxY = resources.getDimension(R.dimen.y95);
        mMinX = resources.getDimension(R.dimen.x50);
        mMinY = resources.getDimension(R.dimen.y50);
    }

    /* renamed from: a */
    private void redraw(Canvas canvas, float f) {
        float f2 = (mMinX * f) + mMaxX;
        float f3 = (mMinY * f) + mMaxY;
        int i = (int) ((1.0f - f) * 255.0f);
        mBg.setBounds(new Rect((int) (((float) getBounds().centerX()) - (f2 / 2.0f)), (int) (((float) getBounds().centerY()) - (f3 / 2.0f))
                , (int) ((f2 / 2.0f) + ((float) getBounds().centerX())), (int) ((f3 / 2.0f) + ((float) getBounds().centerY()))));
        mBg.setAlpha(i);
        mBg.draw(canvas);
    }

    public void draw(@NonNull Canvas canvas) {
        redraw(canvas, this.mFloatValue);
        float f = mFloatValue - 0.33f;
        if (f <= 0.0f) {
            f += 1.0f;
        }
        redraw(canvas, f);
        float f2 = this.mFloatValue - 0.66f;
        if (f2 <= 0.0f) {
            f2 += 1.0f;
        }
        redraw(canvas, f2);
    }

    public int getOpacity() {
        return -3;
    }

    public boolean isRunning() {
        return false;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.mFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        invalidateSelf();
    }

    public void setAlpha(int i) {
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }

    public void start() {
        mValueAnimator.start();
    }

    public void stop() {
        mValueAnimator.cancel();
    }
}
