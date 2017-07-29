package com.example.masoud.a2017_05_24_canvas.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.example.masoud.a2017_05_24_canvas.R;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by masoud on 2017-05-24.
 */

public class CustomView extends View implements View.OnTouchListener{

    //Declare the object we want to animate
    private Square square1, square2, square3, square4, square5;
    Random r;
    float canvasWidth, canvasHeight;

    ArrayList<Square> squareArrayList;

    int missedSquare = 0;
    int catchSquare = 0;



    public int getMissedSquare() {
        return missedSquare;
    }

    public int getCatchSquare() {
        return catchSquare;
    }

    //------------------------------------- Constructor
    public CustomView(Context context) {
        super(context);

        initializeGame();
    }

    private void initializeGame() {

        //initialize square images
        Bitmap army_pacman_h = BitmapFactory.decodeResource(getResources(), R.drawable.army_packman);
        Bitmap army_pacman = Bitmap.createScaledBitmap(army_pacman_h,130,130,true);

        Bitmap lady_pacman_h = BitmapFactory.decodeResource(getResources(), R.drawable.lady_packman_m);
        Bitmap lady_pacman = Bitmap.createScaledBitmap(lady_pacman_h,130,130,true);

        Bitmap one_eye_pacman_h = BitmapFactory.decodeResource(getResources(), R.drawable.one_eye_packman);
        Bitmap one_eye_pacman = Bitmap.createScaledBitmap(one_eye_pacman_h,130,130,true);

        Bitmap color_pacman_h = BitmapFactory.decodeResource(getResources(), R.drawable.color_packman);
        Bitmap color_pacman = Bitmap.createScaledBitmap(color_pacman_h,130,130,true);

        Bitmap sleep_h = BitmapFactory.decodeResource(getResources(), R.drawable.sleep);
        Bitmap sleep = Bitmap.createScaledBitmap(sleep_h,350,300,true);


        //Create Square objects
        square1 =  new Square(10f, -200f,army_pacman,1);

        square2 =  new Square(200f,-700f,lady_pacman,2);
        square1.setxDirection(-1);

        square3 =  new Square(400f,-300f,one_eye_pacman,1);
        square1.setxDirection(0);

        square4 =  new Square(600f,-500f,color_pacman,1);
        square1.setxDirection(-1);

        //Mr.Simpson
        square5 =  new Square(250f,950f,sleep);


        //Add square objects to an ArrayList
        squareArrayList = new ArrayList<>();
        squareArrayList.add(square1);
        squareArrayList.add(square2);
        squareArrayList.add(square3);
        squareArrayList.add(square4);

        setOnTouchListener(this);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();


        for (Square currentSquare : squareArrayList){

            float xPos = currentSquare.getxPosition();
            float yPos = currentSquare.getyPosition();

            canvas.drawBitmap(currentSquare.getBitmapImage(),xPos,yPos,null);
        }


        float xPos = square5.getxPosition();
        float yPos = square5.getyPosition();

        canvas.drawBitmap(square5.getBitmapImage(),xPos,yPos,null);

    }


    float nextXPos = canvasWidth - 150;
    float nextYPos = 0;


    public void move()
    {
        for(Square square : squareArrayList){
            defineDirection(square);
        }
    }



    //=================================================================== New Move Mechanism

    private void defineDirection(Square square){


        //If collision with LEFT or RIGHT wall reverse X direction
        if( (square.getxPosition() <= 0) || (square.getxPosition() >= canvasWidth  - square.getBitmapImage().getWidth())){  //
            square.setxDirection(square.getxDirection()*(-1));
        }

        //If there is a collision with Simpson change Y direction
        if(collisionDetection(square)){
            square.setyDirection(square.getyDirection()*(-1));
        }

        //If square move upward, in collision with top border change Y direction
        if( (square.getyDirection() == -1) && (square.getyPosition() <= 0) ){
            square.setyDirection(square.getyDirection()*(-1));
        }

        //If square passed canvasHeight and went out of screen start over again from top with same X position
        if((square.getyPosition() >= canvasHeight)){
            missedSquare ++;
            square.setyPosition(-200);
        }


        calculateAndSetNextPosition(square);
    }


    private void calculateAndSetNextPosition(Square square){
        nextXPos = square.getxPosition() + (square.getxDirection() * 7 * (square.getSpeed()));
        nextYPos = square.getyPosition() + (square.getyDirection() * 2 * (square.getSpeed()));

        square.setxPosition(nextXPos);
        square.setyPosition(nextYPos);
    }



    //==============================================================================================



    float canvasTouchedXpos;
    float canvasTouchedYpos;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //--------------------------------------- Display position by touching canvas
        //Log.d("TOUCH", square1.toString());
        canvasTouchedYpos = event.getY();
        if(canvasTouchedYpos >= (canvasHeight - 100)){
            canvasTouchedXpos = event.getX() - (square5.getBitmapImage().getWidth()/2);
        }
        //Log.d("TOUCH", " Touched X: " + String.valueOf(canvasTouchedXpos) + ", Touched Y:" + String.valueOf(canvasTouchedYpos));
        moveSimpson();
        return true;
    }


    float nextSleepXpos;

    private void moveSimpson() {


//        if(   canvasTouchedXpos <= (square5.getxPosition() + square5.getBitmapImage().getWidth()/2)   ){
//            if(  (canvasTouchedXpos + square5.getBitmapImage().getWidth()) >= canvasWidth ) {
//                nextSleepXpos = canvasWidth - square5.getBitmapImage().getWidth(); }
//            else{
//                nextSleepXpos = canvasTouchedXpos;
//            }
//        }else if( (  canvasTouchedXpos > (square5.getxPosition() + square5.getBitmapImage().getWidth()/2 ) && ( canvasTouchedXpos - square5.getBitmapImage().getWidth() <= 0 ))){
//            nextSleepXpos = 0;
//        }
//        else {
//            nextSleepXpos = canvasTouchedXpos - (square5.getxPosition() + square5.getBitmapImage().getWidth()) + square5.getxPosition();
//        }


        //=========================================================================== New Version

        if(   canvasTouchedXpos <= (square5.getxPosition() + square5.getBitmapImage().getWidth()/2)   ){
            nextSleepXpos = canvasTouchedXpos;
        }else {
            nextSleepXpos = canvasTouchedXpos - (square5.getxPosition() + square5.getBitmapImage().getWidth()) + square5.getxPosition();
        }

        //===========================================================================


        square5.setxPosition(nextSleepXpos);
    }


    private boolean collisionDetection(Square square){

        if(
                ( square.getxPosition()  >= square5.getxPosition() - square.getxPosition() ) && (  square.getxPosition() <= square5.getxPosition() + square5.getBitmapImage().getWidth()  )
                        &&
                ( square.getyPosition() + square.getBitmapImage().getHeight() == square5.getyPosition() + 100  )
          )
        {
            Log.d("COLLISION", "-----------------> Object collision");
            catchSquare ++;
            return true;
        }else {
            return false;
        }
    }
}
