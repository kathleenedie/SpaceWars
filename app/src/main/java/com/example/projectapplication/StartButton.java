package com.example.projectapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;

public class StartButton{

    RectF rect;
    private Bitmap bitmap;
    private Bitmap bitmapstart;

    private float height;
    private float length;
    private float x;
    private float y;

    public StartButton(Context context, int screenX, int screenY){

        rect = new RectF();

        length = screenX/3;
        height = screenY/5;

        x = (screenX/2)-length/2;
        y = (screenY/5)*2;

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.start);

        // stretch the bitmap to a size appropriate for the screen resolution
        bitmap = Bitmap.createScaledBitmap(bitmap,
                (int) (length),
                (int) (height),
                false);

        bitmapstart = BitmapFactory.decodeResource(context.getResources(), R.drawable.start);
        bitmapstart = Bitmap.createScaledBitmap(bitmapstart, (int) (length), (int) (height),false);
    }

    public RectF getRect(){
        return rect;
    }

    public Bitmap getBitmap(){

        return bitmap;
    }

    public float getX(){
        return x;
    }

    public float getY() {
        return y;
    }

    public float getLength(){
        return length;
    }

    public float getHeight() { return height; }

}
