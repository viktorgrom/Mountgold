package com.mercrura.mountgold;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

import static com.mercrura.mountgold.GameView.dWidth;

public class Cvadrokopter extends Kvadrocopter {

    Bitmap[] Cvadro = new Bitmap[11];

    int cvadroX,cvadroY, velocito, cvadroFrame;
    int width, height;
    Random random;

    public Cvadrokopter(Context context) {
        super(context);

        Cvadro[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.kopter1);
        Cvadro[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.kopter2);
        Cvadro[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.kopter3);
        Cvadro[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.kopter4);
        Cvadro[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.kopter5);
        Cvadro[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.kopter6);
        Cvadro[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.kopter7);
        Cvadro[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.kopter8);
        Cvadro[8] = BitmapFactory.decodeResource(context.getResources(), R.drawable.kopter9);



        width = Cvadro[1].getWidth();
        height = Cvadro[1].getHeight();

        width /= 4;
        height /= 4;

        Cvadro[0] = Bitmap.createScaledBitmap(Cvadro[0], width, height, false);
        Cvadro[1] = Bitmap.createScaledBitmap(Cvadro[1], width, height, false);
        Cvadro[2] = Bitmap.createScaledBitmap(Cvadro[2], width, height, false);
        Cvadro[3] = Bitmap.createScaledBitmap(Cvadro[3], width, height, false);
        Cvadro[4] = Bitmap.createScaledBitmap(Cvadro[4], width, height, false);
        Cvadro[5] = Bitmap.createScaledBitmap(Cvadro[5], width, height, false);
        Cvadro[6] = Bitmap.createScaledBitmap(Cvadro[6], width, height, false);
        Cvadro[7] = Bitmap.createScaledBitmap(Cvadro[7], width, height, false);
        Cvadro[8] = Bitmap.createScaledBitmap(Cvadro[8], width, height, false);




        random = new Random();

        cvadroFrame = 0;
        resetPosition2();

    }

    public Bitmap getBitmap() {
        return Cvadro[cvadroFrame];
    }

    public int getWidth() {
        return Cvadro[0].getWidth();
    }

    public int getHeith() {
        return Cvadro[0].getHeight();
    }


    public void resetPosition2() {
        cvadroX = dWidth + random.nextInt(1200);
         cvadroY = random.nextInt(750);
        velocito = 2 + random.nextInt(7);
    }
}
