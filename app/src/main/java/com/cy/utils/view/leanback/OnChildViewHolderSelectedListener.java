package com.cy.utils.view.leanback;

//**

import androidx.recyclerview.widget.RecyclerView;
/**
 * Interface for receiving notification when a child of this
 * ViewGroup has been selected.
 */
public abstract class OnChildViewHolderSelectedListener {
    /**
     * Callback method to be invoked when a child of this ViewGroup has been
     * selected.
     *
     * @param parent      The RecyclerView where the selection happened.
     * @param child       The ViewHolder within the RecyclerView that is selected, or null if no
     *                    view is selected.
     * @param position    The position of the view in the adapter, or NO_POSITION
     *                    if no view is selected.
     * @param subposition The index of which {@link ItemAlignmentDef} being used,
     *                    0 if there is no ItemAlignmentDef defined for the item.
     */
    public void onChildViewHolderSelected(RecyclerView parent, RecyclerView.ViewHolder child,
                                          int position, int subposition) {
    }
}
