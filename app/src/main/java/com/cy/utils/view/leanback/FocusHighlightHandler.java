package com.cy.utils.view.leanback;

import android.view.View;

/**
 * created by cy on 2020/6/2 0002.
 */
public interface FocusHighlightHandler {
    /**
     * Called when an item gains or loses focus.
     * @hide
     *
     * @param view The view whose focus is changing.
     * @param hasFocus True if focus is gained; false otherwise.
     */

    void onItemFocused(View view, boolean hasFocus);

    /**
     * Called when the view is being created.
     */
    void onInitializeView(View view);
}
