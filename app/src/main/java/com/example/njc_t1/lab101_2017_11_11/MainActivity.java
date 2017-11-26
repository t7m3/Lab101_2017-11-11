package com.example.njc_t1.lab101_2017_11_11;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.njc_t1.lab101_2017_11_11.R.id.imageView;
import static com.example.njc_t1.lab101_2017_11_11.R.id.imageView1;
import static com.example.njc_t1.lab101_2017_11_11.R.id.textView;


public class MainActivity extends AppCompatActivity {

    //グローバル変数の宣言
    MyCountDownTimer myCountDownTimer;
    Game game;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        TextView textView2 = (TextView)findViewById(R.id.textView2);
        textView2.setText("スタート位置は "+ xs + "と" + ys);

        // Ｙ座標を＋50する
        y = y+50;
        imageView.setY(y);

        //インスタンスの生成
        game = new Game();

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
            x = x+5;
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


