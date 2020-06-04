package com.deepbay.cameraapp;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;

public class AutoFitSurfaceView extends SurfaceView {
    public static final String TAG = "AutoFitSurfaceView";
    private float aspectRatio;

    public AutoFitSurfaceView(Context context) {
        this(context, null);
    }

    public AutoFitSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoFitSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public final void setAspectRatio(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Size cannot be negative");
        }
        this.aspectRatio = (float) width / (float) height;
        this.getHolder().setFixedSize(width, height);
        this.requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (this.aspectRatio == 0.0F) {
            this.setMeasuredDimension(width, height);
        } else {
            int newWidth;
            int newHeight;
            float actualRatio = width > height ? this.aspectRatio : 1.0F / this.aspectRatio;
            if ((float) width < (float) height * actualRatio) {
                newHeight = height;
                newWidth = Math.round((float) height * actualRatio);
            } else {
                newWidth = width;
                newHeight = Math.round((float) width / actualRatio);
            }

            Log.d(TAG, "Measured dimensions set: " + newWidth + " x " + newHeight);
            this.setMeasuredDimension(newWidth, newHeight);
        }
    }
}
