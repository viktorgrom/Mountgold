package com.mercrura.mountgold;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class GameView extends View {

    Bitmap mountain_bg;
    Rect rectangle;

    static int dWidth, dHeight;
    int width_earth, height_earth, width_targeto, height_targeto, width_lifesko, height_lifesko;

    Handler handler;
    Runnable runnable;
    final long UPDATE_MILLIS = 26;

    ArrayList<Kvadrocopter> kvadrocopters;
    ArrayList<Cvadrokopter> cvadrokopters;
    ArrayList<Heart> hearts;

    Bitmap eartho, targetosto;
    private Bitmap life[] = new Bitmap[9];

    float ballX, ballY;
    float sX, sY;
    float fX, fY;
    float dX, dY;
    float tempX, tempY;

    Paint borderPaint, scorePaint, lifePaint;

    int score = 0;
    int lifeto_counter = 10;
    Context context;

    Random random;

    boolean gameState = true;


    public GameView(Context context) {
        super(context);
        mountain_bg = BitmapFactory.decodeResource(getResources(), R.drawable.bg_img);

        Display display = ((Activity)getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        dWidth = size.x;
        dHeight = size.y;

        //dWidth = dWidth/ 6;
        //dHeight = dHeight / 6;
        rectangle = new Rect(0, 0 , dWidth, dHeight);


        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();

            }
        };

        random = new Random();



        kvadrocopters = new ArrayList<>();
        cvadrokopters = new ArrayList<>();
        hearts = new ArrayList<>();

        for (int i = 0; i<2; i++){
            Kvadrocopter money_1 = new Kvadrocopter(context);
            kvadrocopters.add(money_1);
            Cvadrokopter money_2 = new Cvadrokopter(context);
            cvadrokopters.add(money_2);
            Heart pillom = new Heart(context);
            hearts.add(pillom);

        }


        Heart pillom = new Heart(context);
        hearts.add(pillom);




        eartho = BitmapFactory.decodeResource(getResources(), R.drawable.earth);
        targetosto = BitmapFactory.decodeResource(getResources(), R.drawable.ime_target);

        width_earth = eartho.getWidth();
        height_earth = eartho.getHeight();
        width_targeto = targetosto.getWidth();
        height_targeto = targetosto.getHeight();

        width_earth /= 5;
        height_earth /=5;
        width_targeto /= 5;
        height_targeto /= 5;

        eartho = Bitmap.createScaledBitmap(eartho, width_earth, height_earth, false);
        targetosto = Bitmap.createScaledBitmap(targetosto, width_earth, height_earth, false);

        life[0] = BitmapFactory.decodeResource(getResources(), R.drawable.on_heart);
        life[1] = BitmapFactory.decodeResource(getResources(), R.drawable.blach_off);

        width_lifesko = life[0].getWidth();
        height_lifesko = life[1].getHeight();

        width_lifesko /= 8;
        height_lifesko /= 8;

        life[0] = Bitmap.createScaledBitmap(life[0], width_lifesko, height_lifesko, false);
        life[1] = Bitmap.createScaledBitmap(life[1], width_lifesko, height_lifesko, false);

        ballX = ballY = 0;
        sX = sY = fX= fY = 0;
        dX = dY = 0;
        tempX = tempY = 0;

        scorePaint = new Paint();
        scorePaint.setTextSize(50);
        scorePaint.setColor(Color.WHITE);



        borderPaint = new Paint();
        borderPaint.setColor(Color.WHITE);
        borderPaint.setStrokeWidth(5);



        this.context = context;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (lifeto_counter < 1){
            gameState = false;
            Intent intent = new Intent(context, GameOvers.class);
            intent.putExtra("score", score);
            context.startActivity(intent);
            ((Activity)context).finish();
        }
        canvas.drawBitmap(mountain_bg,null, rectangle, null);

        canvas.drawText("SCORE: " + score, 50, 100, scorePaint);


        for (int i=0; i<10; i++){
            int x = (int) (270 + life[0].getWidth()  *i);
            int y = 30;

            if (i < lifeto_counter){
                canvas.drawBitmap(life[0], x, y, null);
            }
            else {
                canvas.drawBitmap(life[1], x, y, null);
            }
        }

        for (int i = 0; i< kvadrocopters.size(); i++){
            canvas.drawBitmap(kvadrocopters.get(i).getBitmap(), kvadrocopters.get(i).kvadroX, kvadrocopters.get(i).kvadroY, null);

            kvadrocopters.get(i).kvadroFrame++;
            if (kvadrocopters.get(i).kvadroFrame >4){
                kvadrocopters.get(i).kvadroFrame = 0;
            }
            kvadrocopters.get(i).kvadroX -= kvadrocopters.get(i).velocitoto;
            if (kvadrocopters.get(i).kvadroX < -kvadrocopters.get(i).getWidth()){
                kvadrocopters.get(i).resetPosition();
                lifeto_counter--;
            }

            canvas.drawBitmap(cvadrokopters.get(i).getBitmap(), cvadrokopters.get(i).cvadroX, cvadrokopters.get(i).cvadroY, null);

            cvadrokopters.get(i).cvadroFrame++;
            if (cvadrokopters.get(i).cvadroFrame >4){
                cvadrokopters.get(i).cvadroFrame = 0;
            }
            cvadrokopters.get(i).cvadroX -= cvadrokopters.get(i).velocito;
            if (cvadrokopters.get(i).cvadroX < -cvadrokopters.get(i).getWidth()){
                cvadrokopters.get(i).resetPosition2();
                lifeto_counter--;
            }

            canvas.drawBitmap(hearts.get(i).getBitmap(), hearts.get(i).heartX, hearts.get(i).heartoY, null);

            hearts.get(i).heratoFrame++;
            if (hearts.get(i).heratoFrame >8){
                hearts.get(i).heratoFrame = 0;
            }
            hearts.get(i).heartX -= hearts.get(i).velocitoto;

            if (score > 30){
                hearts.get(i).heartX -= hearts.get(i).velocitoto +2;
                cvadrokopters.get(i).cvadroX -= cvadrokopters.get(i).velocito +2;
                kvadrocopters.get(i).kvadroX -= kvadrocopters.get(i).velocitoto +2;
            }
            if( score > 80){
                hearts.get(i).heartX -= hearts.get(i).velocitoto +4;
                cvadrokopters.get(i).cvadroX -= cvadrokopters.get(i).velocito +4;
                kvadrocopters.get(i).kvadroX -= kvadrocopters.get(i).velocitoto +4;
            }
            if( score > 120){
                hearts.get(i).heartX -= hearts.get(i).velocitoto +6;
                cvadrokopters.get(i).cvadroX -= cvadrokopters.get(i).velocito +6;
                kvadrocopters.get(i).kvadroX -= kvadrocopters.get(i).velocitoto +6;
            }
            if( score > 150){
                hearts.get(i).heartX -= hearts.get(i).velocitoto +10;
                cvadrokopters.get(i).cvadroX -= cvadrokopters.get(i).velocito +10;
                kvadrocopters.get(i).kvadroX -= kvadrocopters.get(i).velocitoto +10;
            }


            if (hearts.get(i).heartX < -hearts.get(i).getWidth()){
                hearts.get(i).resetPositionPill();

            }

            if (lifeto_counter >10){
                lifeto_counter = 10;
            }

            if (ballX <= (kvadrocopters.get(i).kvadroX + kvadrocopters.get(i).getWidth())
                    && ballX + eartho.getWidth() >= kvadrocopters.get(i).kvadroX
                    && ballY <= (kvadrocopters.get(i).kvadroY + kvadrocopters.get(i).getHeith())
                    && ballY >= kvadrocopters.get(i).kvadroY){
                kvadrocopters.get(i).resetPosition();
                score++;
            }

            if (ballX <= (cvadrokopters.get(i).cvadroX + cvadrokopters.get(i).getWidth())
                    && ballX + eartho.getWidth() >= cvadrokopters.get(i).cvadroX
                    && ballY <= (cvadrokopters.get(i).cvadroY + cvadrokopters.get(i).getHeith())
                    && ballY >= cvadrokopters.get(i).cvadroY){
                cvadrokopters.get(i).resetPosition2();
                score++;
            }

            if (ballX <= (hearts.get(i).heartX + hearts.get(i).getWidth())
                    && ballX + eartho.getWidth() >= hearts.get(i).heartX
                    && ballY <= (hearts.get(i).heartoY + hearts.get(i).getHeith())
                    && ballY >= hearts.get(i).heartoY){
                hearts.get(i).resetPositionPill();
                lifeto_counter++;

            }

        }
        if (sX > 0 && sY > dHeight * .75f){
            canvas.drawBitmap(targetosto, sX - targetosto.getWidth()/2, sY - targetosto.getHeight()/2, null);
        }
        if ((Math.abs(fX - sX) > 0 || Math.abs(fY - sY) > 0) && fY > 0 && fY > dHeight *.75f){
            canvas.drawBitmap(targetosto, fX - targetosto.getWidth()/2, fY - targetosto.getHeight()/2, null);
        }
        if ((Math.abs(dX) > 10 || Math.abs(dY) > 10) && sY > dHeight * .75f && fY > dHeight * .75f){
            ballX = fX - eartho.getWidth()/2 - tempX;
            ballY = fY - eartho.getHeight()/2 - tempY;
            canvas.drawBitmap(eartho, ballX, ballY, null);
            tempX += dX;
            tempY += dY;

        }

        canvas.drawLine(0, dHeight * .75f, dWidth, dHeight * .75f, borderPaint);


        if (gameState)
            handler.postDelayed(runnable, UPDATE_MILLIS);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                dX = dY = fX = fY = tempX = tempY = 0;
                sX = event.getX();
                sY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                fX = event.getX();
                fY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                dX = event.getX();
                dY = event.getY();
                ballX = event.getX();
                ballY = event.getX();
                dX = fX - sX;
                dY = fY - sY;
                break;
        }
        return true;
    }
}
