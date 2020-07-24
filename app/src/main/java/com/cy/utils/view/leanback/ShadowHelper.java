package com.cy.utils.view.leanback;

import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

/**
 * created by cy on 2020/6/2 0002.
 */
final class ShadowHelper {

    final static ShadowHelper sInstance = new ShadowHelper();
    boolean mSupportsDynamicShadow;
    ShadowHelperVersionImpl mImpl;

    /**
     * Interface implemented by classes that support Shadow.
     */
    static interface ShadowHelperVersionImpl {
        public Object addDynamicShadow(
                View shadowContainer, float unfocusedZ, float focusedZ, int roundedCornerRadius);
        public void setZ(View view, float z);
        public void setShadowFocusLevel(Object impl, float level);
    }

    /**
     * Interface used when we do not support Shadow animations.
     */
    private static final class ShadowHelperStubImpl implements ShadowHelperVersionImpl {
        ShadowHelperStubImpl() {
        }

        @Override
        public Object addDynamicShadow(
                View shadowContainer, float focusedZ, float unfocusedZ, int roundedCornerRadius) {
            // do nothing
            return null;
        }

        @Override
        public void setShadowFocusLevel(Object impl, float level) {
            // do nothing
        }

        @Override
        public void setZ(View view, float z) {
            // do nothing
        }
    }

    /**
     * Implementation used on api 21 (and above).
     */
    @RequiresApi(21)
    private static final class ShadowHelperApi21Impl implements ShadowHelperVersionImpl {
        ShadowHelperApi21Impl() {
        }

        @Override
        public Object addDynamicShadow(
                View shadowContainer, float unfocusedZ, float focusedZ, int roundedCornerRadius) {
            return ShadowHelperApi21.addDynamicShadow(
                    shadowContainer, unfocusedZ, focusedZ, roundedCornerRadius);
        }

        @Override
        public void setShadowFocusLevel(Object impl, float level) {
            ShadowHelperApi21.setShadowFocusLevel(impl, level);
        }

        @Override
        public void setZ(View view, float z) {
            ShadowHelperApi21.setZ(view, z);
        }
    }

    /**
     * Returns the ShadowHelper.
     */
    private ShadowHelper() {
        if (Build.VERSION.SDK_INT >= 21) {
            mSupportsDynamicShadow = true;
            mImpl = new ShadowHelperApi21Impl();
        } else {
            mImpl = new ShadowHelperStubImpl();
        }
    }

    public static ShadowHelper getInstance() {
        return sInstance;
    }

    public boolean supportsDynamicShadow() {
        return mSupportsDynamicShadow;
    }

    public Object addDynamicShadow(
            View shadowContainer, float unfocusedZ, float focusedZ, int roundedCornerRadius) {
        return mImpl.addDynamicShadow(shadowContainer, unfocusedZ, focusedZ, roundedCornerRadius);
    }

    public void setShadowFocusLevel(Object impl, float level) {
        mImpl.setShadowFocusLevel(impl, level);
    }

    /**
     * Set the view z coordinate.
     */
    public void setZ(View view, float z) {
        mImpl.setZ(view, z);
    }

}

