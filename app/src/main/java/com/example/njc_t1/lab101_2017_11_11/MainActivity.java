package com.example.njc_t1.lab101_2017_11_11;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.njc_t1.lab101_2017_11_11.R.id.imageView;
import static com.example.njc_t1.lab101_2017_11_11.R.id.textView;


public class MainActivity extends AppCompatActivity {

    MyCountDownTimer myCountDownTimer;

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
        textView.setText("スタート位置は "+ x + "と" + y);

        // Ｙ座標を＋50する
        y = y+50;
        imageView.setY(y);

        //タイマーのインスタンスの生成
        myCountDownTimer = new MyCountDownTimer(2*60*1000, 50);

        //タイマーをスタートする
        myCountDownTimer.start();

        textView.setText("ここまで来たよ");

    }

    public void  onClickButton(View view){

        TextView textView = (TextView)findViewById(R.id.textView);
        textView.setText("ボタンがタップされました。");
    }

    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInertval){
            super(millisInFuture, countDownInertval);
        }

        public void onTick(long millisUntilFinished){
            ImageView imageViewT = (ImageView)findViewById(imageView);
            float x = imageViewT.getX();
            x = x+5;
            imageViewT.setX(x);
            TextView textViewT = (TextView)findViewById(textView);
            textViewT.setText("タイマが経過しました。");
        }

        public void onFinish(){
            TextView textViewT = (TextView)findViewById(textView);
            textViewT.setText("終わったよ");
        }

    }
}


