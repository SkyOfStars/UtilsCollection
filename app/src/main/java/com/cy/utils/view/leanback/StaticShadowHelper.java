package com.cy.utils.view.leanback;

import android.os.Build;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
/**
 * Helper for static (nine patch) shadows.
 */

final class StaticShadowHelper {

    final static StaticShadowHelper sInstance = new StaticShadowHelper();
    boolean mSupportsShadow;
    ShadowHelperVersionImpl mImpl;

    /**
     * Interface implemented by classes that support Shadow.
     */
    static interface ShadowHelperVersionImpl {
        public void prepareParent(ViewGroup parent);

        public Object addStaticShadow(ViewGroup shadowContainer);

        public void setShadowFocusLevel(Object impl, float level);
    }

    /**
     * Interface used when we do not support Shadow animations.
     */
    private static final class ShadowHelperStubImpl implements ShadowHelperVersionImpl {
        ShadowHelperStubImpl() {
        }

        @Override
        public void prepareParent(ViewGroup parent) {
            // do nothing
        }

        @Override
        public Object addStaticShadow(ViewGroup shadowContainer) {
            // do nothing
            return null;
        }

        @Override
        public void setShadowFocusLevel(Object impl, float level) {
            // do nothing
        }
    }

    /**
     * Implementation used on JBMR2 (and above).
     */
    @RequiresApi(19)
    private static final class ShadowHelperJbmr2Impl implements ShadowHelperVersionImpl {
        ShadowHelperJbmr2Impl() {
        }

        @Override
        public void prepareParent(ViewGroup parent) {
            ShadowHelperJbmr2.prepareParent(parent);
        }

        @Override
        public Object addStaticShadow(ViewGroup shadowContainer) {
            return ShadowHelperJbmr2.addShadow(shadowContainer);
        }

        @Override
        public void setShadowFocusLevel(Object impl, float level) {
            ShadowHelperJbmr2.setShadowFocusLevel(impl, level);
        }
    }

    /**
     * Returns the StaticShadowHelper.
     */
    private StaticShadowHelper() {
        if (Build.VERSION.SDK_INT >= 21) {
            mSupportsShadow = true;
            mImpl = new ShadowHelperJbmr2Impl();
        } else {
            mSupportsShadow = false;
            mImpl = new ShadowHelperStubImpl();
        }
    }

    public static StaticShadowHelper getInstance() {
        return sInstance;
    }

    public boolean supportsShadow() {
        return mSupportsShadow;
    }

    public void prepareParent(ViewGroup parent) {
        mImpl.prepareParent(parent);
    }

    public Object addStaticShadow(ViewGroup shadowContainer) {
        return mImpl.addStaticShadow(shadowContainer);
    }

    public void setShadowFocusLevel(Object impl, float level) {
        mImpl.setShadowFocusLevel(impl, level);
    }
}