package com.mercrura.mountgold;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

import static com.mercrura.mountgold.GameView.dWidth;

public class Heart {
    Bitmap heartoto[] = new Bitmap[9];

    int heartX, heartoY, velocitoto, heratoFrame;
    int width, height;

    Random random;

    public Heart(Context context){

        heartoto[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.hart1);
        heartoto[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.hart2);
        heartoto[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.hart3);
        heartoto[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.hart4);
        heartoto[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.hart5);
        heartoto[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.hart6);
        heartoto[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.hart7);
        heartoto[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.hart8);
        heartoto[8] = BitmapFactory.decodeResource(context.getResources(), R.drawable.hart9);




        width = heartoto[1].getWidth();
        height = heartoto[1].getHeight();

        width /= 4;
        height /= 4;

        heartoto[0] = Bitmap.createScaledBitmap(heartoto[0], width, height, false);
        heartoto[1] = Bitmap.createScaledBitmap(heartoto[1], width, height, false);
        heartoto[2] = Bitmap.createScaledBitmap(heartoto[2], width, height, false);
        heartoto[3] = Bitmap.createScaledBitmap(heartoto[3], width, height, false);
        heartoto[4] = Bitmap.createScaledBitmap(heartoto[4], width, height, false);
        heartoto[5] = Bitmap.createScaledBitmap(heartoto[5], width, height, false);
        heartoto[6] = Bitmap.createScaledBitmap(heartoto[6], width, height, false);
        heartoto[7] = Bitmap.createScaledBitmap(heartoto[7], width, height, false);
        heartoto[8] = Bitmap.createScaledBitmap(heartoto[8], width, height, false);





        random = new Random();
        heratoFrame = 0;
        resetPositionPill();

    }

    public Bitmap getBitmap(){
        return  heartoto[heratoFrame];
    }

    public int getWidth(){
        return  heartoto[0].getWidth();
    }

    public int getHeith(){
        return heartoto[0].getHeight();
    }


    public void resetPositionPill() {
        heartX = dWidth + random.nextInt(1400);
        heartoY = random.nextInt(800);
        velocitoto = 5 + random.nextInt(13);
    }
}

