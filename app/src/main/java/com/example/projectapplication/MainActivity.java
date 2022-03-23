package com.example.projectapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends Activity {


    SpaceGameView spaceGameView;
    Intent gamePlayIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Display display = getWindowManager().getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);

        spaceGameView = new SpaceGameView(this, size.x, size.y);
        setContentView(spaceGameView);

        // set a listener to detect when an on touch event occurs, which is the trigger for starting the second activity.
//        spaceGameView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                int eventType = motionEvent.getActionMasked();
//
//                if (eventType == MotionEvent.ACTION_DOWN){
//                    onResume();
//                    spaceGameView.setPaused(false);
//                    return true;}
//                return false;
//            }
////        });

    };

    // This method executes when the player starts the game
    @Override
    protected void onResume(){
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

//    public void launchSecondActivity(){
//        gamePlayIntent = new Intent(this, GamePlayActivity.class);
//        startActivity(gamePlayIntent);
//        Log.i("activity", "Second activity started");
//    }
};