package com.example.projectapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;

public class Bullet {

    RectF rect;
    private Bitmap bitmap;
    private Bitmap bitmapbullet;

    public Bitmap currentBitmap;
    private float height;
    private float length;
    private float x;
    private float y;

    private float BulletSpeed;
    public final int STOPPED = 0;
    public final int UP = 1;
    public final int DOWN = 2;

    ///maybe more movement than this
    private int BulletMoving = STOPPED;
    // private int BulletSpeed;
    private boolean isActive;

    public Bullet(Context context, int screenX, int screenY) {

        rect = new RectF();

        length = screenX / 5;
        height = screenY / 6;

        x = screenX;
        y = screenY;

        BulletSpeed = 350;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet);

        // stretch the bitmap to a size appropriate for the screen resolution
        bitmap = Bitmap.createScaledBitmap(bitmap,
                (int) (length),
                (int) (height),
                false);

        isActive = true;

        bitmapbullet = BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet);
        bitmapbullet = Bitmap.createScaledBitmap(bitmapbullet, (int) (length), (int) (height), false);

         currentBitmap = bitmap;
    }

    public void setMovementState(int state) {

        BulletMoving = state;
    }


    public void update(long fps) {
        if (BulletMoving == UP) {
            y = y - BulletSpeed / fps;
            currentBitmap = bitmapbullet;
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

    // Going nowhere
    int heading = -1;
//    float speed =  350;
//
//    private int width = 1;
//    private int height;


//    public void bullet(int screenY) {
//
//        height = screenY / 20;
//        isActive = false;
//
//        rect = new RectF();
//    }

//    public RectF getRect(){
//        return  rect;
//    }

    public boolean getStatus(){
        return isActive;
    }

    public void setInactive(){
        isActive = false;
    }

//    public float getImpactPointY(){
//        if (BulletMoving == DOWN){
//            return y + height;
//        }else{
//            return  y;
//        }


    public boolean shoot(float startX, float startY, int direction) {
        if (!isActive) {
            x = startX;
            y = startY;
            BulletMoving = direction;
            isActive = true;
            return true;
        }

        // Bullet already active
        return false;

//    public void update(long fps) {
//
//        // Just move up or down
//        if (heading == UP) {
//            y = y - speed / fps;
//        } else {
//            y = y + speed / fps;
//        }

        // Update rect
//        rect.left = x;
//        rect.right = x + length;
//        rect.top = y;
//        rect.bottom = y + height;


//        bullet = new Bullet(context, screenX, screenY);
//        // Update the players bullet
//        if (BulletMoving.state) {
//            bitmapbullet.update(fps);
//        }


    }}