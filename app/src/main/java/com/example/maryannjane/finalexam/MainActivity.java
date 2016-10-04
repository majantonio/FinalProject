package com.example.maryannjane.finalexam;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn1, show;
    EditText etxt1,etxt2;
    TextView btnSignup;
    DatabaseAdapter loginDataBaseAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginDataBaseAdapter=new DatabaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        btn1=(Button)findViewById(R.id.login);
        btnSignup=(TextView)findViewById(R.id.signup);
        etxt1=(EditText)findViewById(R.id.email_address);
        etxt2=(EditText)findViewById(R.id.pwd);
        show=(Button)findViewById(R.id.show);
        final Context context = this;



        btnSignup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentSignUP=new Intent(getApplicationContext(),Signup.class);
                startActivity(intentSignUP);
            }
        });

        assert btn1 != null;
        btn1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String email = etxt1.getText().toString();
                String pword = etxt2.getText().toString();
                String uname = etxt1.getText().toString();

                String savePassword = DatabaseAdapter.getSinlgeEntry(email);
                String savePassword1 = DatabaseAdapter.getUsername(uname);


                if(pword.equals(savePassword)|pword.equals(savePassword1))
                {
                    Toast.makeText(MainActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                    Intent intentSignUP=new Intent(getApplicationContext(),MainForm.class);
                    startActivity(intentSignUP);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Usernaame or Password does not match", Toast.LENGTH_SHORT).show();
                }
            }
        });

        show.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        etxt2.setTransformationMethod(null);
                        break;

                    case MotionEvent.ACTION_UP:
                        etxt2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        break;

                }
                return true;}
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
        loginDataBaseAdapter.close();
    }

}
