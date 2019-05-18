package com.gearback.zt.boundedview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class BoundedView extends LinearLayout {
    private int mBoundedWidth;
    private int mBoundedHeight;

    public BoundedView(Context context) {
        super(context);
        mBoundedWidth = 0;
        mBoundedHeight = 0;
    }

    public BoundedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BoundedView);
        mBoundedWidth = array.getDimensionPixelSize(R.styleable.BoundedView_boundedWidth, 0);
        mBoundedHeight = array.getDimensionPixelSize(R.styleable.BoundedView_boundedHeight, 0);
        array.recycle();
    }

    public void setBoundedWidth(int width) {
        mBoundedWidth = width;
        requestLayout();
    }

    public void setBoundedHeight(int height) {
        mBoundedHeight = height;
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measuredWidth = MeasureSpec.getSize(widthMeasureSpec);
        if (mBoundedWidth > 0 && mBoundedWidth < measuredWidth) {
            int measureMode = MeasureSpec.getMode(widthMeasureSpec);
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(mBoundedWidth, measureMode);
        }

        int measuredHeight = MeasureSpec.getSize(heightMeasureSpec);
        if (mBoundedHeight > 0 && mBoundedHeight < measuredHeight) {
            int measureMode = MeasureSpec.getMode(heightMeasureSpec);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(mBoundedHeight, measureMode);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
