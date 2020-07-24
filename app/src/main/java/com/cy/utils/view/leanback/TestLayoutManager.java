package com.cy.utils.view.leanback;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cy.utils.view.leanback.ItemAlignmentFacet;
import com.cy.utils.view.recycleview.ScaleRecyclerView;

import static androidx.recyclerview.widget.RecyclerView.NO_POSITION;

/**
 * created by cy on 2020/6/3 0003.
 */
public class TestLayoutManager extends LinearLayoutManager {
    RecyclerView recyclerView;
    public TestLayoutManager(Context context,RecyclerView recyclerView) {
        super(context);
        this.recyclerView= recyclerView;
    }

    public TestLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public TestLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    final static class LayoutParams extends RecyclerView.LayoutParams {

        // For placement
        private int mLeftInset;
        private int mTopInset;
        private int mRightInset;
        private int mBottomInset;

        // For alignment
        private int mAlignX;
        private int mAlignY;
        private int[] mAlignMultiple;
        private ItemAlignmentFacet mAlignmentFacet;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(RecyclerView.LayoutParams source) {
            super(source);
        }

        public LayoutParams(LayoutParams source) {
            super(source);
        }

        int getAlignX() {
            return mAlignX;
        }

        int getAlignY() {
            return mAlignY;
        }

        int getOpticalLeft(View view) {
            return view.getLeft() + mLeftInset;
        }

        int getOpticalTop(View view) {
            return view.getTop() + mTopInset;
        }

        int getOpticalRight(View view) {
            return view.getRight() - mRightInset;
        }

        int getOpticalBottom(View view) {
            return view.getBottom() - mBottomInset;
        }

        int getOpticalWidth(View view) {
            return view.getWidth() - mLeftInset - mRightInset;
        }

        int getOpticalHeight(View view) {
            return view.getHeight() - mTopInset - mBottomInset;
        }

        int getDecoratedOpticalLeftWithMargin(RecyclerView.LayoutManager lm, View view) {
            return lm.getDecoratedLeft(view) + mLeftInset - leftMargin;
        }

        int getDecoratedOpticalTopWithMargin(RecyclerView.LayoutManager lm, View view) {
            return lm.getDecoratedTop(view) + mTopInset - topMargin;
        }

        int getDecoratedOpticalRightWithMargin(RecyclerView.LayoutManager lm, View view) {
            return lm.getDecoratedRight(view) - mRightInset + rightMargin;
        }

        int getDecoratedOpticalBottomWithMargin(RecyclerView.LayoutManager lm, View view) {
            return lm.getDecoratedBottom(view) - mBottomInset + bottomMargin;
        }

        int getDecoratedOpticalWidthWithMargin(RecyclerView.LayoutManager lm, View view) {
            return lm.getDecoratedRight(view) - lm.getDecoratedLeft(view)
                    - mLeftInset - mRightInset + leftMargin + rightMargin;
        }

        int getDecoratedOpticalHeightWithMargin(RecyclerView.LayoutManager lm, View view) {
            return lm.getDecoratedBottom(view) - lm.getDecoratedTop(view)
                    - mTopInset - mBottomInset + topMargin + bottomMargin;
        }

        int getOpticalLeftInset() {
            return mLeftInset;
        }

        int getOpticalRightInset() {
            return mRightInset;
        }

        int getOpticalTopInset() {
            return mTopInset;
        }

        int getOpticalBottomInset() {
            return mBottomInset;
        }

        void setAlignX(int alignX) {
            mAlignX = alignX;
        }

        void setAlignY(int alignY) {
            mAlignY = alignY;
        }

        void setItemAlignmentFacet(ItemAlignmentFacet facet) {
            mAlignmentFacet = facet;
        }

        ItemAlignmentFacet getItemAlignmentFacet() {
            return mAlignmentFacet;
        }

        void calculateItemAlignments(int orientation, View view) {
            ItemAlignmentFacet.ItemAlignmentDef[] defs = mAlignmentFacet.getAlignmentDefs();
            if (mAlignMultiple == null || mAlignMultiple.length != defs.length) {
                mAlignMultiple = new int[defs.length];
            }
            for (int i = 0; i < defs.length; i++) {
                mAlignMultiple[i] = ItemAlignmentFacetHelper
                        .getAlignmentPosition(view, defs[i], orientation);
            }
            if (orientation == HORIZONTAL) {
                mAlignX = mAlignMultiple[0];
            } else {
                mAlignY = mAlignMultiple[0];
            }
        }

        int[] getAlignMultiple() {
            return mAlignMultiple;
        }

        void setOpticalInsets(int leftInset, int topInset, int rightInset, int bottomInset) {
            mLeftInset = leftInset;
            mTopInset = topInset;
            mRightInset = rightInset;
            mBottomInset = bottomInset;
        }

    }
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        int itemCount = state.getItemCount();
        int targetScrollPosition = state.getTargetScrollPosition();
//        recyclerView.smoothScrollToPosition(targetScrollPosition);
    }

    /**
     * True if focus search is disabled.
     */
    private boolean mFocusSearchDisabled;
    private static final String TAG = "TestLayoutManager";
    private boolean mInLayout;
    private boolean mInScroll;
    private boolean mInSelection = false;
    @Override
    public boolean onRequestChildFocus(@NonNull RecyclerView parent, @NonNull View child, @Nullable View focused) {
        Log.i(TAG, "onRequestChildFocus: ");
        if (mFocusSearchDisabled) {
            return true;
        }
        if (getPositionByView(child) == NO_POSITION) {
            // This shouldn't happen, but in case it does be sure not to attempt a
            // scroll to a view whose item has been removed.
            return true;
        }
        if (!mInLayout && !mInSelection && !mInScroll) {
//            scrollToView(child, focused, true);
        }
        return true;
    }
    private int getPositionByView(View view) {
        if (view == null) {
            return NO_POSITION;
        }
        LayoutParams params = (LayoutParams) view.getLayoutParams();
        if (params == null || params.isItemRemoved()) {
            // when item is removed, the position value can be any value.
            return NO_POSITION;
        }
        return params.getViewPosition();
    }

    private int getSubPositionByView(View view, View childView) {
        if (view == null || childView == null) {
            return 0;
        }
        final LayoutParams lp = (LayoutParams) view.getLayoutParams();
        final ItemAlignmentFacet facet = lp.getItemAlignmentFacet();
        if (facet != null) {
            final ItemAlignmentFacet.ItemAlignmentDef[] defs = facet.getAlignmentDefs();
            if (defs.length > 1) {
                while (childView != view) {
                    int id = childView.getId();
                    if (id != View.NO_ID) {
                        for (int i = 1; i < defs.length; i++) {
                            if (defs[i].getItemAlignmentFocusViewId() == id) {
                                return i;
                            }
                        }
                    }
                    childView = (View) childView.getParent();
                }
            }
        }
        return 0;
    }
    public static final long NO_ID = -1L;
    private void dispatchChildSelected() {
        if (mChildSelectedListener == null && mChildViewHolderSelectedListener == null) {
            return;
        }

        View view = mFocusPosition == NO_POSITION ? null : findViewByPosition(mFocusPosition);
        if (view != null) {
            RecyclerView.ViewHolder vh = mBaseGridView.getChildViewHolder(view);
            if (mChildSelectedListener != null) {
                mChildSelectedListener.onChildSelected(mBaseGridView, view, mFocusPosition,
                        vh == null? NO_ID: vh.getItemId());
            }
            if (mChildViewHolderSelectedListener != null) {
                mChildViewHolderSelectedListener.onChildViewHolderSelected(mBaseGridView, vh,
                        mFocusPosition, mSubFocusPosition);
            }
        } else {
            if (mChildSelectedListener != null) {
                mChildSelectedListener.onChildSelected(mBaseGridView, null, NO_POSITION, NO_ID);
            }
            if (mChildViewHolderSelectedListener != null) {
                mChildViewHolderSelectedListener.onChildViewHolderSelected(mBaseGridView, null,
                        NO_POSITION, 0);
            }
        }

        // Children may request layout when a child selection event occurs (such as a change of
        // padding on the current and previously selected rows).
        // If in layout, a child requesting layout may have been laid out before the selection
        // callback.
        // If it was not, the child will be laid out after the selection callback.
        // If so, the layout request will be honoured though the view system will emit a double-
        // layout warning.
        // If not in layout, we may be scrolling in which case the child layout request will be
        // eaten by recyclerview.  Post a requestLayout.
        if (!mInLayout && !mBaseGridView.isLayoutRequested()) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (getChildAt(i).isLayoutRequested()) {
                    forceRequestLayout();
                    break;
                }
            }
        }
    }
    private void forceRequestLayout() {
        // RecyclerView prevents us from requesting layout in many cases
        // (during layout, during scroll, etc.)
        // For secondary row size wrap_content support we currently need a
        // second layout pass to update the measured size after having measured
        // and added child views in layoutChildren.
        // Force the second layout by posting a delayed runnable.
        // TODO: investigate allowing a second layout pass,
        // or move child add/measure logic to the measure phase.
        ViewCompat.postOnAnimation(mBaseGridView, mRequestLayoutRunnable);
    }
    private static final boolean DEBUG = true;
    private final Runnable mRequestLayoutRunnable = new Runnable() {
        @Override
        public void run() {
            if (DEBUG) Log.v(TAG, "request Layout from runnable");
            requestLayout();
        }
    };

    /**
     * Focus Scroll strategy.
     */
    private int mFocusScrollStrategy = ScaleRecyclerView.FOCUS_SCROLL_ALIGNED;

    private ScaleRecyclerView mBaseGridView;
    private int mOrientation = HORIZONTAL;
    private int mFocusPosition = NO_POSITION;
    /**
     * A view can have mutliple alignment position,  this is the index of which
     * alignment is used,  by default is 0.
     */
    private int mSubFocusPosition = 0;
    private int mFocusPositionOffset = 0;
    private OnChildSelectedListener mChildSelectedListener = null;
    /**
     * Temporary variable: an int array of length=2.
     */
    private static int[] sTwoInts = new int[2];
    /**
     * True if scroll content,  might be disabled during transition.
     */
    private boolean mScrollEnabled = true;
    private OnChildViewHolderSelectedListener mChildViewHolderSelectedListener = null;
    private void scrollGrid(int scrollPrimary, int scrollSecondary, boolean smooth) {
        Log.i(TAG, "scrollGrid: --scrollPrimary="+scrollPrimary+"--scrollSecondary="
                +scrollSecondary+"--smooth="+smooth+"--mInLayout="+mInLayout);
        if (mInLayout) {//垂直RowList滚动
//            scrollDirectionPrimary(scrollPrimary);
//            scrollDirectionSecondary(scrollSecondary);
        } else {//水平滚动
            int scrollX;
            int scrollY;
            if (mOrientation == HORIZONTAL) {
                scrollX = scrollPrimary;
                scrollY = scrollSecondary;
            } else {
                scrollX = scrollSecondary;
                scrollY = scrollPrimary;
            }
            if (smooth) {
                mBaseGridView.smoothScrollBy(scrollX, scrollY);
            } else {
                mBaseGridView.scrollBy(scrollX, scrollY);
            }
        }
    }
}
