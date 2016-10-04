package com.example.maryannjane.finalexam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by MaryAnnJane on 10/2/2016.
 */
public class Ontouch extends AppCompatActivity {
    String msg1 = "", msg2="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ontouch);

        final EditText t1 = (EditText) findViewById(R.id.xy1);
        final EditText t2 = (EditText) findViewById(R.id.xy2);
        final EditText t3 = (EditText) findViewById(R.id.diff);
        final EditText t4 = (EditText) findViewById(R.id.quadrant);
        final EditText t5 = (EditText) findViewById(R.id.motion);
        ImageView image1 = (ImageView)findViewById(R.id.imageView);


        image1.setOnTouchListener(new View.OnTouchListener() {
            float x1, y1, x2, y2, a, b;

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                String direct = "";
                switch (event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        y1 = event.getY();
                        t1.setText("X1: " + x1 + "  & Y1 :" +y1);
                        return true;

                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        y2 = event.getY();
                        t2.setText("X2: "+x2 +"  & Y2: "+y2);

                        a=x1-x2;
                        b=y1-y2;
                        t3.setText("Diff X: " + Math.abs(a) + " & Diff Y: "+Math.abs(b));

                        if (a>0 & b>0 ){
                            msg2="2nd Quadrant";
                        }
                        if (a>0 & b<0){
                            msg2="3rd Quadrant";
                        }
                        if (a<0 & b<0){
                            msg2="4th Quadrant";
                        }
                        if (a<0 & b>0){
                            msg2="1st Quadrant";
                        }

                        if (y1 < y2){
                            msg1 +=" Swiped Bottom";
                        }
                        if (y1 > y2){
                            msg1 +=" Swiped Up";
                        }
                        if (x1 > x2){
                            msg1 +=" Swiped Left";
                        }
                        if (x1 < x2){
                            msg1 +=" Swiped Right";
                        }
                        t4.setText(msg1);
                        msg1="";
                        t5.setText(msg2);
                        msg2="";
                        break;
                }
                return false;
            }

        });

    }
}
