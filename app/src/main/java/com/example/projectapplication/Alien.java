package com.example.projectapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;

import java.util.Random;

public class Alien {

    RectF rect;
    private Bitmap bitmap;
    public Bitmap currentBitmap;

    private float height;
    private float length;
    private float x;
    private float y;

    private float alienSpeed;
    public final int STOPPED = 0;
    public final int DOWN = 4;

    // Our Alien is only moving down
    private int alienMoving = DOWN;

    private boolean isVisible;

    Random spawner = new Random();

    public Alien(Context context, int row, int column, int screenX, int screenY){

        rect = new RectF();

        length = screenX/15;
        height = screenY/15;

        isVisible = true;

        x = spawner.nextInt(1000);
        y = spawner.nextInt(300);

        alienSpeed = 40;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.alien);

        // stretch the bitmap to a size appropriate for the screen resolution
        bitmap = Bitmap.createScaledBitmap(bitmap,
                (int) (length),
                (int) (height),
                false);
    }

    public void setMovementState(int state){
          alienMoving = state;
       }

    public void update(long fps){
        if(alienMoving == DOWN){
            y = y + alienSpeed / fps;
            currentBitmap = bitmap;
       }

        rect.top = y;
        rect.bottom = y + height;
        rect.left = x;
        rect.right = x + length;
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

    public float getHeight() {
        return height;
    }

    public boolean getIsVisible(){
        return isVisible;
    }

    public void setInvisible(){
        isVisible = false;
    }
}

