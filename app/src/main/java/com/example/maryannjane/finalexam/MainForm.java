package com.example.maryannjane.finalexam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by MaryAnnJane on 10/2/2016.
 */
public class MainForm extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainform);

        Button btn1 = (Button)findViewById(R.id.ontch);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainForm.this,Ontouch.class );
                startActivity(intent);
            }
        });

    }
}
