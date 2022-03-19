package com.example.projectapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.YuvImage;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

public class GamePlayActivity extends Activity {

    SpaceGameView spaceGameView;
    Spaceship spaceship;
    private void initLevel(){

        spaceShip = new Spaceship(context, screenX, screenY);
    }




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

    // This method executes when the player quits the game
    @Override
    protected void onPause() {
        super.onPause();

        // Tell the gameView pause method to execute
        spaceGameView.pause();
    }
    public static void setMovementState(int state){
        SpaceShipMoving = state;
    }

    // on touch event to move spaceship left and right
    public boolean onTouchEvent(Context context MotionEvent motionEvent) {
        int eventType = motionEvent.getActionMasked();

        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_DOWN:

                float screenY = getY();
                if (motionEvent.getY() > screenY - screenY / 2) {
                    setMovementState(Spaceship.RIGHT);
                } else {
                    setMovementState(Spaceship.LEFT);
                }

            case MotionEvent.ACTION_UP:

                setMovementState(Spaceship.STOPPED);
                break;
        }
        return true;
    }
}