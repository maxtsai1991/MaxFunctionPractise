package com.example.demo.jsonpersoncat;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 定義元件類別
 */
public class MyView extends View {  // 繼承View類別
    private Resources resources;
    private Paint paint;
    private float offset;

    /**
     * 定義建構子
     */
    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        resources = getResources();
        paint = new Paint();
        offset = 300;
    }

    public float getOffset() {
        return offset;
    }

    public void setOffset(float offset) {
        this.offset = offset;
    }

    /**
     * 覆寫onDraw()，來繪製元件外觀
     */
    @Override
    protected void onDraw(Canvas canvas) {
        // 使用Paint物件來繪製

        paint.setColor(resources.getColor(R.color.purple_200));
        canvas.drawRect(offset + 0, 0, offset + 250, 130, paint);

        paint.setColor(resources.getColor(R.color.teal_200));
        canvas.drawCircle(offset + 300, 50, 50, paint);
    }
}
