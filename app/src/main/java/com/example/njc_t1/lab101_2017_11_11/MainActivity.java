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

import static com.example.njc_t1.lab101_2017_11_11.R.id.imageView;
import static com.example.njc_t1.lab101_2017_11_11.R.id.imageView1;
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

        ImageView imageView100 = (ImageView) findViewById(R.id.imageView100);
        imageView100.setY(600);

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

        ImageView imageview1 = (ImageView)findViewById(imageView1);
        imageview1.setY(100);

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
        }

        public void onFinish(){
            TextView textViewT = (TextView)findViewById(textView);
            textViewT.setText("終わったよ");
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //参照値の取得
        ImageView imageView100 = (ImageView) findViewById(R.id.imageView100);

        int x = (int) event.getX();                    //タッチしたＸ座標
        int y = (int) event.getY();                //タッチしたＹ座標

        textView2.setText("X座標 "+x+"Y座標 " + y);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                textView2.append("ACTION_DOWN");
                //imageView100.setX(x);
                break;
            case MotionEvent.ACTION_UP:
                textView2.append("ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                textView2.append("ACTION_MOVE");
                imageView100.setX(x);
                break;
            case MotionEvent.ACTION_CANCEL:
                textView2.append("ACTION_CANCEL");
                break;
        }

        return true;

    }


    public class  Enemy1 {
        int dir = +1;

        ImageView imageview1 = (ImageView)findViewById(imageView1);

        public  void  Move(int vlue){

            DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
            int width = dm.widthPixels;
            //int height = dm.heightPixels

            width = width - imageview1.getWidth();

            float x = imageview1.getX();
            x = x + dir * vlue;
            imageview1.setX(x);

            if(x < 0 || x >= width)
                    dir = -dir;
        }
    }

    public class Game{

        //インスタンスの生成
        Enemy1 enemy1 = new Enemy1();


    }
}


