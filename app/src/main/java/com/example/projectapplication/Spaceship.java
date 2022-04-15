package com.example.projectapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;

public class Spaceship {

    RectF rect;
    private Bitmap bitmap;
    private Bitmap bitmapleft;
    private Bitmap bitmapright;
    private Bitmap bitmapdown;
    private Bitmap bitmapup;
    public Bitmap currentBitmap;
    private float height;
    private float length;
    private float x;
    private float y;

    private float SpaceShipSpeed;
    public final int STOPPED = 0;
    public final int LEFT = 1;
    public final int RIGHT = 2;

    private int SpaceShipMoving = STOPPED;

    public Spaceship(Context context, int screenX, int screenY){

        rect = new RectF();

        length = screenX/8;
        height = screenY/10;

        x = (screenX/2)-length/2;
        y = (screenY/5)*4;

        SpaceShipSpeed = 350;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.spaceshipup);

        // stretch the bitmap to a size appropriate for the screen resolution
        bitmap = Bitmap.createScaledBitmap(bitmap,
                (int) (length),
                (int) (height),
                false);

        bitmapup = BitmapFactory.decodeResource(context.getResources(), R.drawable.spaceshipup);
        bitmapup = Bitmap.createScaledBitmap(bitmapup, (int) (length), (int) (height),false);

        currentBitmap = bitmap;
    }

    public void setMovementState(int state){
        SpaceShipMoving = state;
    }

    public void update(long fps){
          if(SpaceShipMoving == LEFT){
              x = x - SpaceShipSpeed / fps;
              currentBitmap = bitmap;
           }
            if(SpaceShipMoving == RIGHT){
                x = x + SpaceShipSpeed / fps;
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

    public float getY() { return y; }

    public float getLength(){
        return length;
    }

    public float getHeight() { return height; }
}