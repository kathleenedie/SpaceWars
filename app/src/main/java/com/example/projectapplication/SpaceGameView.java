package com.example.projectapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SpaceGameView extends SurfaceView implements Runnable{

    private Context context;

    // This is our thread
    private Thread gameThread = null;

    // Our SurfaceHolder to lock the surface before we draw our graphics
    private SurfaceHolder ourHolder;

    // A boolean which we will set and unset
    // when the game is running- or not.
    private volatile boolean playing;

    // Game is paused at the start
    private boolean paused = true;

    // A Canvas and a Paint object
    private Canvas canvas;
    private Paint paintScore;
    private Paint paintLives;
    private Paint paintTitle;
    private Paint paint;

    // This variable tracks the game frame rate
    private long fps;

    // This is used to help calculate the fps
    private long timeThisFrame;

    // The size of the screen in pixels
    private int screenX;
    private int screenY;

    // The score
    public int score = 0;

    // Lives
    private int lives = 3;

    // Elements
    Spaceship spaceShip;
    Alien alien;
    private List<Alien> alienSpawn = new ArrayList<Alien>();
    StartButton startButton;
    Bullet bullet;

    // This special constructor method runs
    public SpaceGameView(Context context, int x, int y) {

        // The next line of code asks the
        // SurfaceView class to set up our object.
        // How kind.
        super(context);

        // Make a globally available copy of the context so we can use it in another method
        this.context = context;

        // Initialize ourHolder and paint objects
        ourHolder = getHolder();
        paintScore = new Paint();
        paintLives = new Paint();
        paintTitle = new Paint();
        paint = new Paint();

        screenX = x;
        screenY = y;

        initLevel();
    }

    private void initLevel(){

        spaceShip = new Spaceship(context, screenX, screenY);
        alien = new Alien(context, screenX, screenY);
        startButton = new StartButton(context, screenX, screenY);
        bullet = new Bullet(context, screenX, screenY);

    }


    @Override
    public void run() {
        while (playing) {

            // Capture the current time in milliseconds in startFrameTime
            long startFrameTime = System.currentTimeMillis();

            // Update the frame
            if(!paused){
                update();
            }

            // Draw the frame
            draw();

            // Calculate the fps this frame
            // We can then use the result to
            // time animations and more.
            timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if (timeThisFrame >= 1) {
                fps = 1000 / timeThisFrame;
            }
        }
    }



    private void update() {

        // call spaceShip
        spaceShip.update(fps);

        if (alien.getIsVisible()) {
            alien.update(fps);
        }

        //checkCollisions
    }

    private void draw(){
        // Make sure our drawing surface is valid or we crash
        if (ourHolder.getSurface().isValid()) {
            // Lock the canvas ready to draw
            canvas = ourHolder.lockCanvas();

            // Draw the background color
            //canvas.drawColor(getResources().getColor(R.color.black));
            Bitmap backgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background);
            canvas.drawBitmap(backgroundBitmap, 0, 0, paint);

            // Choose the brush color and alignment for Score and Lives
            paintScore.setColor(getResources().getColor(R.color.lives_score));
            paintScore.setTextAlign(Paint.Align.LEFT);
            paintScore.setTextSize(80);

            paintLives.setColor(getResources().getColor(R.color.lives_score));
            paintLives.setTextAlign(Paint.Align.RIGHT);
            paintLives.setTextSize(80);

            paintTitle.setColor(getResources().getColor(R.color.lives_score));
            paintTitle.setTextAlign(Paint.Align.CENTER);
            paintTitle.setTextSize(120);



            // Change the brush color
            paint.setColor(Color.argb(255,  249, 129, 0));

            // Draw the score and remaining lives
            canvas.drawText("Score: " + score, 30,70, paintScore);
            canvas.drawText("Lives: " + lives, canvas.getWidth()-30, 70, paintLives);

            // splashscreen in main activity or when game is paused.
            if (paused){
            //  draw the defender
            canvas.drawBitmap(spaceShip.getBitmap(), spaceShip.getX(), spaceShip.getY() , paint);
            // draw bullets
            canvas.drawBitmap(bullet.getBitmap(), bullet.getX()/2-bullet.getLength()/2, bullet.getY()/20*14, paint);
            canvas.drawBitmap(bullet.getBitmap(), bullet.getX()/2-bullet.getLength()/2, bullet.getY()/20*13, paint);
            canvas.drawBitmap(bullet.getBitmap(), bullet.getX()/2-bullet.getLength()/2, bullet.getY()/20*12, paint);

            // draw aliens
            canvas.drawBitmap(alien.getBitmap(), alien.getX()/10*1, alien.getY()/10*2, paint);
            canvas.drawBitmap(alien.getBitmap(), alien.getX()/10*3, alien.getY()/10*4, paint);
            canvas.drawBitmap(alien.getBitmap(), alien.getX()/10*8, alien.getY()/10*3, paint);
            canvas.drawBitmap(alien.getBitmap(), alien.getX()/10*7, alien.getY()/20*10, paint);
            canvas.drawBitmap(alien.getBitmap(), alien.getX()/10*2, alien.getY()/20*14, paint);
            canvas.drawBitmap(alien.getBitmap(), alien.getX()/2-alien.getLength()/2, alien.getY()/20*12, paint);

            // Draw start button on the screen
            canvas.drawBitmap(startButton.getBitmap(), startButton.getX(), startButton.getY(), paint);

            // Draw title on the screen
            canvas.drawText("Space Wars", canvas.getWidth()/2, canvas.getHeight()/5, paintTitle);
            }

            // Draw game play characters
            canvas.drawBitmap(spaceShip.getBitmap(), spaceShip.getX(), spaceShip.getY(), paint);
            if(alien.getIsVisible()){
                canvas.drawBitmap(alien.getBitmap(), alien.getX()/10*1, alien.getY()/10*2, paint);
            }


            // Draw everything to the screen
            ourHolder.unlockCanvasAndPost(canvas);


    }}




    // If SpaceGameActivity is paused/stopped
    // shutdown our thread.
    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            Log.e("Error:", "joining thread");
        }

    }



    // If SpaceInvadersActivity is started then
    // start our thread.
    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setPaused(boolean paused){
        this.paused = paused;
    }


}  // end class
