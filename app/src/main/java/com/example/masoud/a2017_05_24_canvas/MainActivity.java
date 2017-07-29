package com.example.masoud.a2017_05_24_canvas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.masoud.a2017_05_24_canvas.Model.Animation;
import com.example.masoud.a2017_05_24_canvas.Model.CustomView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout linMid;
    Button btnStart, btnStop;
    CustomView customView;

    Animation animation;

    TextView textViewCatch, textViewMissed, textViewScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize() {


        textViewCatch = (TextView)findViewById(R.id.textViewCatch);
        textViewMissed = (TextView)findViewById(R.id.textViewMissed);
        textViewScore = (TextView)findViewById(R.id.textViewScore);


        btnStart = (Button)findViewById(R.id.btnStart);
        btnStart.setOnClickListener(this);
        btnStop = (Button)findViewById(R.id.btnStop);
        btnStop.setOnClickListener(this);


        linMid = (LinearLayout)findViewById(R.id.linMid);
        customView = new CustomView(this);
        linMid.addView(customView);
        animation = new Animation(customView);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnStart:
                animation.start();
                btnStart.setEnabled(false);
                btnStop.setEnabled(true);
                break;
            case R.id.btnStop:
                animation.stop();
                btnStop.setEnabled(false);
                btnStart.setEnabled(true);
                calculateScore();
                break;
        }
    }

    int score = 0;

    private void calculateScore() {

        if((customView.getCatchSquare() + customView.getMissedSquare()) > 0){
            score = customView.getCatchSquare() * 100 / (customView.getCatchSquare() + customView.getMissedSquare());
            setTextViews();
        }
    }

    private void setTextViews() {
        textViewCatch.setText(String.valueOf(customView.getCatchSquare()));
        textViewScore.setText(String.valueOf(score + " %"));
        textViewMissed.setText(String.valueOf(customView.getMissedSquare()));
    }
}
