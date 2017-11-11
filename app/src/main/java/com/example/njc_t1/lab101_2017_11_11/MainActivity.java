package com.example.njc_t1.lab101_2017_11_11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        float x = 0;
        float y = 0;
        float xs = 0;
        float ys = 0;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        x = imageView.getX();
        xs = x;
        y = imageView.getY();
        ys = y;

        TextView textView = (TextView)findViewById(R.id.textView);
        textView.setText("スタート位置は "+ x + "と" + y);

//        x = x+300;
//        y = y+300;
//        imageView.setX(x);
//        imageView.setY(y);

        while(y < ys+600){
            while(x < xs+300){
                x = x+1;
                imageView.setX(x);
            }

            y = y+10;
            imageView.setY(y);
        }
    }

    public void  onClickButton(View view){

        TextView textView = (TextView)findViewById(R.id.textView);
        textView.setText("ボタンがタップされました。");
    }
}
