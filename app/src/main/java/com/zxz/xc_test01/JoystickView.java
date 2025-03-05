package com.zxz.xc_test01;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class JoystickView extends View {
    private int outerColor;
    private int innerColor;

    public JoystickView(Context context) {
        super(context);
        initAttributes(context, null);
    }

    public JoystickView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttributes(context, attrs);
    }

    public JoystickView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributes(context, attrs);
    }

    private void initAttributes(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.JoystickView);
            outerColor = a.getColor(R.styleable.JoystickView_outerCircleColor, Color.GRAY);
            innerColor = a.getColor(R.styleable.JoystickView_innerCircleColor, Color.BLUE);
            a.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        float centerX = width / 2f;
        float centerY = height / 2f;

        // 绘制外圆
        Paint outerPaint = new Paint();
        outerPaint.setColor(outerColor);
        outerPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(centerX, centerY, Math.min(width, height)/2f, outerPaint);

        // 绘制内圆
        Paint innerPaint = new Paint();
        innerPaint.setColor(innerColor);
        innerPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(centerX, centerY, Math.min(width, height)/4f, innerPaint);
    }
}