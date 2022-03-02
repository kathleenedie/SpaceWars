package com.example.projectapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;

public class Alien {

    RectF rect;
    private Bitmap bitmap;
    private Bitmap bitmapalien;

    public Bitmap currentBitmap;
    private float height;
    private float length;
    private float x;
    private float y;

    private float AlienSpeed;
    public final int STOPPED = 0;
    public final int LEFT = 1;
    public final int RIGHT = 2;
    public final int UP = 3;
    public final int DOWN = 4;

    ///maybe more movement than this
    private int AlienMoving = STOPPED;
    private int alienSpeed;

    public Alien(Context context, int screenX, int screenY){

        rect = new RectF();

        length = screenX/15;
        height = screenY/15;

        x = screenX;
        y = screenY;

        alienSpeed = 350;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.alien);

        // stretch the bitmap to a size appropriate for the screen resolution
        bitmap = Bitmap.createScaledBitmap(bitmap,
                (int) (length),
                (int) (height),
                false);

        bitmapalien = BitmapFactory.decodeResource(context.getResources(), R.drawable.spaceshipup);
        bitmapalien = Bitmap.createScaledBitmap(bitmapalien, (int) (length), (int) (height),false);

        // currentBitmap = bitmap;
    }

    public void setMovementState(int state){
        AlienMoving = state;
    }


    public void update(long fps){
        //  if(AlienMoving == LEFT){
        //      x = x - AlienSpeed / fps;
        //       currentBitmap = bitmapalien;
        //   }
        //    if(AlienMoving == RIGHT){
        //        x = x + AlienSpeed / fps;
        //        currentBitmap = bitmapalien;
        //    }
        //    if(AlienMoving == UP){
        //        y = y - AlienSpeed / fps;
        // currentBitmap = bitmapalien;

        //    }

        //    if(AlienMoving == DOWN){
        //        y=y + AlienSpeed / fps;
        //   currentBitmap = bitmapalien;

        //   }

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

    public float getY() { return y; }

    public float getLength(){
        return length;
    }

    public float getHeight() { return height; }
}

