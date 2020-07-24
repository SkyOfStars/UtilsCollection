package com.cy.utils.view.leanback;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;

import com.cy.utils.R;

@RequiresApi(18)
class ShadowHelperJbmr2 {

    static class ShadowImpl {
        View mNormalShadow;
        View mFocusShadow;
    }

    /* prepare parent for allowing shadows of a child */
    public static void prepareParent(ViewGroup parent) {
        parent.setLayoutMode(ViewGroup.LAYOUT_MODE_OPTICAL_BOUNDS);
    }

    /* add shadows and return a implementation detail object */
    public static Object addShadow(ViewGroup shadowContainer) {
        shadowContainer.setLayoutMode(ViewGroup.LAYOUT_MODE_OPTICAL_BOUNDS);
        LayoutInflater inflater = LayoutInflater.from(shadowContainer.getContext());
        inflater.inflate(R.layout.lb_shadow, shadowContainer, true);
        ShadowImpl impl = new ShadowImpl();
        impl.mNormalShadow = shadowContainer.findViewById(R.id.lb_shadow_normal);
        impl.mFocusShadow = shadowContainer.findViewById(R.id.lb_shadow_focused);
        return impl;
    }

    /* set shadow focus level 0 for unfocused 1 for fully focused */
    public static void setShadowFocusLevel(Object impl, float level) {
        ShadowImpl shadowImpl = (ShadowImpl) impl;
        shadowImpl.mNormalShadow.setAlpha(1 - level);
        shadowImpl.mFocusShadow.setAlpha(level);
    }
}
