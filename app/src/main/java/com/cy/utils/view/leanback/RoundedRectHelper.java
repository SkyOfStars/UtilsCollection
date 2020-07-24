package com.cy.utils.view.leanback;

import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.cy.utils.R;

/**
 * Helper for setting rounded rectangle backgrounds on a view.
 */
final class RoundedRectHelper {

    private final static RoundedRectHelper sInstance = new RoundedRectHelper();
    private final Impl mImpl;

    /**
     * Returns an instance of the helper.
     */
    public static RoundedRectHelper getInstance() {
        return sInstance;
    }

    public static boolean supportsRoundedCorner() {
        return Build.VERSION.SDK_INT >= 21;
    }

    /**
     * Sets or removes a rounded rectangle outline on the given view.
     */
    public void setClipToRoundedOutline(View view, boolean clip, int radius) {
        mImpl.setClipToRoundedOutline(view, clip, radius);
    }

    /**
     * Sets or removes a rounded rectangle outline on the given view.
     */
    public void setClipToRoundedOutline(View view, boolean clip) {
        mImpl.setClipToRoundedOutline(view, clip, view.getResources().getDimensionPixelSize(
                R.dimen.lb_rounded_rect_corner_radius));
    }

    static interface Impl {
        public void setClipToRoundedOutline(View view, boolean clip, int radius);
    }

    /**
     * Implementation used prior to L.
     */
    private static final class StubImpl implements Impl {
        StubImpl() {
        }

        @Override
        public void setClipToRoundedOutline(View view, boolean clip, int radius) {
            // Not supported
        }
    }

    /**
     * Implementation used on api 21 (and above).
     */
    @RequiresApi(21)
    private static final class Api21Impl implements Impl {
        Api21Impl() {
        }

        @Override
        public void setClipToRoundedOutline(View view, boolean clip, int radius) {
            RoundedRectHelperApi21.setClipToRoundedOutline(view, clip, radius);
        }
    }

    private RoundedRectHelper() {
        if (supportsRoundedCorner()) {
            mImpl = new Api21Impl();
        } else {
            mImpl = new StubImpl();
        }
    }
}
