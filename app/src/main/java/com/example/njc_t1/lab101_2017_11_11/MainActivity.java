package com.example.njc_t1.lab101_2017_11_11;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static android.R.attr.width;
import static android.R.attr.x;
import static com.example.njc_t1.lab101_2017_11_11.R.id.imageView;
import static com.example.njc_t1.lab101_2017_11_11.R.id.textView;


public class MainActivity extends AppCompatActivity {

    //グローバル変数の宣言
    MyCountDownTimer myCountDownTimer;
    TextView textView2;
    Game game;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imagePlayer = (ImageView) findViewById(R.id.imagePlayer);
        imagePlayer.setY(600);

        float x = 0;
        float y = 0;
        float xs = 0;
        float ys = 0;

        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        x = imageView.getX();
        xs = x;
        y = imageView.getY();
        ys = y;

        TextView textView = (TextView)findViewById(R.id.textView);

        textView2 = (TextView)findViewById(R.id.textView2);
        textView2.setText("スタート位置は "+ xs + "と" + ys);

        // Ｙ座標を＋50する
        y = y+50;
        imageView.setY(y);

        //インスタンスの生成
        game = new Game();

        ImageView imageEnemy1 = (ImageView)findViewById(R.id.imageEnemy1);
        imageEnemy1.setY(100);

        //タイマーのインスタンスの生成
        myCountDownTimer = new MyCountDownTimer(2*60*1000, 50);

        //タイマーをスタートする
        myCountDownTimer.start();

        textView2.setText("ここまで来たよ");

    }

    public void  onClickButton(View view){

        TextView textView = (TextView)findViewById(R.id.textView);
        textView.setText("ボタンがタップされました。");
    }

    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInertval){
            super(millisInFuture, countDownInertval);
        }

        //タイマの処理
        public void onTick(long millisUntilFinished){
            ImageView imageViewT = (ImageView)findViewById(imageView);
            float x = imageViewT.getX();
            x = x + 5;
            imageViewT.setX(x);
            TextView textViewT = (TextView)findViewById(textView);
            textViewT.setText("タイマが経過しました。");

            //game.enemy.Move();
            game.enemy1.Move(5);
            game.bullet.Move(10);
        }

        public void onFinish(){
            TextView textViewT = (TextView)findViewById(textView);
            textViewT.setText("終わったよ");
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //参照値の取得
        ImageView imagePlayer = (ImageView) findViewById(R.id.imagePlayer);

        int x = (int) event.getX();                //タッチした場所のＸ座標
        int y = (int) event.getY();                //タッチした場所のＹ座標

        textView2.setText("X座標 "+x+"Y座標 " + y);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                textView2.append("ACTION_DOWN");
                //imagePlayer.setX(x);
                break;
            case MotionEvent.ACTION_UP:
                textView2.append("ACTION_UP");
                game.bullet.set((int) imagePlayer.getX(), (int) imagePlayer.getY());
                game.bullet.setShoot(true);
                break;
            case MotionEvent.ACTION_MOVE:
                textView2.append("ACTION_MOVE");
                imagePlayer.setX(x);
                break;
            case MotionEvent.ACTION_CANCEL:
                textView2.append("ACTION_CANCEL");
                break;
        }

        return true;

    }


    public class  Enemy1 {

        private int dir = +1;

        ImageView imageEnemy1 = (ImageView)findViewById(R.id.imageEnemy1);

        public  void  Move(int vlue){

            DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
            int width = dm.widthPixels;
            //int height = dm.heightPixels

            width = width - imageEnemy1.getWidth();

            float x = imageEnemy1.getX();
            x = x + dir * vlue;
            imageEnemy1.setX(x);

            if(x < 0 || x >= width)
                    dir = -dir;
        }
    }


    public  class Bullet {

        private int dir = +1;
        private boolean shoot = false;

        ImageView bullet = (ImageView)findViewById(R.id.imageBullet);

        public Bullet() {

            DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
            int x = dm.widthPixels;
            int y = dm.heightPixels;
            x = x * 10/100;
            y = y * 70/100;
            bullet.setX(x);
            bullet.setY(y);

        }

        public void setShoot(boolean s){
            shoot = s;
        }

        public boolean getShoot(){
            return shoot;
        }

        public void  set(int x, int y){
            bullet.setX(x);
            bullet.setY(y);
        }

        public  void  Move(int vlue){

            DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
            int height = dm.heightPixels;
            //int height = dm.heightPixels

            if(shoot == true) {
                height = width - bullet.getHeight();

                float y = bullet.getY();
                y = y - dir * vlue;
                bullet.setY(y);

                if (x < 0 || x >= width)
                    dir = -dir;
            }
        }

    }


    public class Game{

        //インスタンスの生成
        Enemy1 enemy1 = new Enemy1();
        Bullet bullet = new Bullet();

    }
}


