package com.dkalsan.speedreader.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomView extends TextView {

    private Paint mBasePaint;

    public CustomView(Context context) {
        super(context);
        init(null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {
        mBasePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float width = (float) this.getWidth();
        float height = (float) this.getHeight();
        float textSize = this.getTextSize();
        mBasePaint.setColor(Color.BLACK);
        mBasePaint.setStrokeWidth(textSize / 10);

        canvas.drawLine(0f, 0f, width, 0f, mBasePaint);
        canvas.drawLine(0f, height, width, height, mBasePaint);
        mBasePaint.setStrokeWidth(textSize / 20);
        canvas.drawLine(width / 2, 0f, width / 2, (float)(0.1 * height), mBasePaint);
        canvas.drawLine(width / 2, height, width / 2, (float)(0.9 * height), mBasePaint);
    }
}