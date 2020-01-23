package ru.syomka.canvasbuttons;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import java.text.AttributedCharacterIterator;


public class Ring2 extends View {
    private Bitmap mBack;
    private Paint mPaint;
    private RectF mOval;
    private Paint mTextPaint;

    private float newAngle=0;

    public Ring2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Resources res = getResources();
        mBack = BitmapFactory.decodeResource(res, R.drawable.icon_back);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Bitmap ring = BitmapFactory.decodeResource(res, R.drawable.icon_round);
        mPaint.setShader(new BitmapShader(ring, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        mOval = new RectF(0, 0, mBack.getWidth(), mBack.getHeight());
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(24);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.translate((getWidth() - mBack.getWidth()) / 2, (getHeight() - mBack.getHeight()) / 2);
        canvas.drawBitmap(mBack, 0, 0, null);
        float angle = this.newAngle; //220;


        canvas.drawArc(mOval, -90, angle, true, mPaint);
        canvas.drawText("Text",
            mBack.getWidth() / 2,
            (mBack.getHeight() - mTextPaint.ascent()) / 2,
            mTextPaint);
    }

public void setNewAngle(float newAngle){
        //это то что выше    /а это то что прилетело при вызове функции
        this.newAngle = newAngle;
    }

//вызыватся снаружи, из другом класса
    public float getNewAngle(){
        return newAngle;
    }



}

/*
public class Ring2 extends View {
    private Bitmap back;
    private Bitmap ring;
    private RectF oval;
    private Paint arcPaint;

    public Ring2(Context context) {
        super(context);
        Resources res = getResources();
        back = BitmapFactory.decodeResource(res, R.drawable.back);
        ring = BitmapFactory.decodeResource(res, R.drawable.ring);
        arcPaint = new Paint();
        arcPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        oval = new RectF(-1, -1, ring.getWidth()+1, ring.getHeight()+1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawARGB(0xaa, 0, 255, 0);
        canvas.drawBitmap(back, 0, 0, null);
        //canvas.saveLayer(oval, null, Canvas.HAS_ALPHA_LAYER_SAVE_FLAG);
        canvas.drawBitmap(ring, 0, 0, null);
        float angle = 180;
        canvas.drawArc(oval, angle-90, 360-angle, true, arcPaint);
        canvas.restore();
    }
}*/
