package com.mercrura.mountgold;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

import static com.mercrura.mountgold.GameView.dWidth;

public class Kvadrocopter {

    Bitmap kvadrocops[] = new Bitmap[9];

    int kvadroX, kvadroY,  kvadroFrame;
    public int velocitoto;
    int width, height;

    Random random;

    public Kvadrocopter(Context context){

        kvadrocops[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.cvadro1);
        kvadrocops[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.cvadro2);
        kvadrocops[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.cvadro3);
        kvadrocops[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.cvadro4);
        kvadrocops[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.cvadro5);
        kvadrocops[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.cvadro6);
        kvadrocops[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.cvadro7);
        kvadrocops[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.cvadro8);
        kvadrocops[8] = BitmapFactory.decodeResource(context.getResources(), R.drawable.cvadro9);



        width = kvadrocops[1].getWidth();
        height = kvadrocops[1].getHeight();

        width /= 4;
        height /= 4;

        kvadrocops[0] = Bitmap.createScaledBitmap(kvadrocops[0], width, height, false);
        kvadrocops[1] = Bitmap.createScaledBitmap(kvadrocops[1], width, height, false);
        kvadrocops[2] = Bitmap.createScaledBitmap(kvadrocops[2], width, height, false);
        kvadrocops[3] = Bitmap.createScaledBitmap(kvadrocops[3], width, height, false);
        kvadrocops[4] = Bitmap.createScaledBitmap(kvadrocops[4], width, height, false);
        kvadrocops[5] = Bitmap.createScaledBitmap(kvadrocops[5], width, height, false);
        kvadrocops[6] = Bitmap.createScaledBitmap(kvadrocops[6], width, height, false);
        kvadrocops[7] = Bitmap.createScaledBitmap(kvadrocops[7], width, height, false);
        kvadrocops[8] = Bitmap.createScaledBitmap(kvadrocops[8], width, height, false);




        random = new Random();
        kvadroFrame = 0;
        resetPosition();

    }

    public Bitmap getBitmap(){
        return  kvadrocops[kvadroFrame];
    }

    public int getWidth(){
        return  kvadrocops[0].getWidth();
    }

    public int getHeith(){
        return kvadrocops[0].getHeight();
    }


    public void resetPosition() {
        kvadroX = dWidth + random.nextInt(1270);
        kvadroY = random.nextInt(700);
        velocitoto = 4 + random.nextInt(6);
    }
}
