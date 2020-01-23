package ru.syomka.canvasbuttons;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


    public class MyButton extends View {

        Paint mPaint;

        public MyButton(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            mPaint = new Paint();
        }

        @Override
        public void onDraw(Canvas canvas) {
            canvas.drawCircle(50.0f, 50.0f, 50.0f, this.mPaint);
        }
}