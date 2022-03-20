package com.example.projectapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.YuvImage;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.content.Intent;
import android.util.Log;
import android.content.Context;

public class GamePlayActivity extends Activity {

    SpaceGameView spaceGameView;
    Spaceship spaceShip;
//    private Object Spaceship;
//
//    private void initLevel(){
//
//        Spaceship = new Spaceship(Context, screenX, screenY);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Display display = getWindowManager().getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);


        spaceGameView = new SpaceGameView(this, size.x, size.y);
        setContentView(spaceGameView);
        spaceGameView.setPaused(false);
    }

    // This method executes when the player starts the game
    @Override
    protected void onResume() {
        super.onResume();

        // Tell the gameView resume method to execute
        spaceGameView.resume();
    }

//    // This method executes when the player quits the game
    @Override
    protected void onPause() {
        super.onPause();

        // Tell the gameView pause method to execute
        spaceGameView.pause();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {

            // Player has touched the screen
            case MotionEvent.ACTION_DOWN:

                if(motionEvent.getY() > screenY - screenY / 2) {
                    if (motionEvent.getX() > screenX / 2) {
                        Spaceship.setMovementState(Spaceship.RIGHT);
                    } else {
                        Spaceship.setMovementState(Spaceship.LEFT);
                    }

                }

//                if(motionEvent.getY() < screenY - screenY / 8) {
//                    // Shots fired
//                    if(bullet.shoot(Spaceship.getX()+
//                            Spaceship.getLength()/2,screenY,bullet.UP)){
//                    }
//                }
                break;

            // Player has removed finger from screen
            case MotionEvent.ACTION_UP:
                if(motionEvent.getY() > screenY - screenY / 10) {
                    Spaceship.setMovementState(Spaceship.STOPPED);
                }

                break;

        }

        return true;
    }
}