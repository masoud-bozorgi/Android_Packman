package com.example.masoud.a2017_05_24_canvas.Model;

import android.graphics.Bitmap;

/**
 * Created by masoud on 2017-05-24.
 */

public class Square {

    private float xPosition;
    private float yPosition;
    private Bitmap bitmapImage;

    private int xDirection = 1;
    private int yDirection = 1;

    private int speed = 1;

    public Square(float xPosition, float yPosition, Bitmap bitmapImage, int xDirection, int yDirection, int speed) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.bitmapImage = bitmapImage;
        this.xDirection = xDirection;
        this.yDirection = yDirection;
        this.speed = speed;
    }

    public Square(float xPosition, float yPosition, Bitmap bitmapImage, int speed) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.bitmapImage = bitmapImage;
        this.speed = speed;
    }

    public Square(float xPosition, float yPosition, Bitmap bitmapImage) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.bitmapImage = bitmapImage;
    }


    public float getxPosition() {
        return xPosition;
    }

    public void setxPosition(float xPosition) {
        this.xPosition = xPosition;
    }

    public float getyPosition() {
        return yPosition;
    }

    public void setyPosition(float yPosition) {
        this.yPosition = yPosition;
    }

    public int getxDirection() {
        return xDirection;
    }

    public void setxDirection(int xDirection) {
        this.xDirection = xDirection;
    }

    public int getyDirection() {
        return yDirection;
    }

    public void setyDirection(int yDirection) {
        this.yDirection = yDirection;
    }

    public Bitmap getBitmapImage() {
        return bitmapImage;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setBitmapImage(Bitmap bitmapImage) {
        this.bitmapImage = bitmapImage;
    }

    @Override
    public String toString() {
        return "X Position=" + xPosition + ", Y Position=" + yPosition;
    }
}
