package com.example.maryannjane.finalexam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by MaryAnnJane on 10/2/2016.
 */
public class Signup extends AppCompatActivity {

    DatabaseAdapter loginDataBaseAdapter;
    private Toast popToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        loginDataBaseAdapter=new DatabaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        final EditText fname = (EditText) findViewById(R.id.fname);
        final EditText Lname = (EditText) findViewById(R.id.Lname);
        final EditText uname = (EditText) findViewById(R.id.Username);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.password);
        final EditText confirmpass = (EditText) findViewById(R.id.confpassword);
        final Button validate = (Button) findViewById(R.id.okbtn);

        assert validate != null;
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emails = email.getText().toString();
                String username = uname.getText().toString();
                String savePassword = DatabaseAdapter.getEmailforsignup(emails);
                String savePassword1 = DatabaseAdapter.getUsernameforsignup(username);


                if(!validateEmail(email.getText().toString())) {
                    Toast.makeText(Signup.this,"Invalid Email",Toast.LENGTH_LONG).show();

                } else if(!validatePassword(password.getText().toString())) {
                    Toast.makeText(Signup.this, "Password Length needs to be at least 8 characters", Toast.LENGTH_LONG).show();
                }
                else if (!password.getText().toString().equals(confirmpass.getText().toString())) {
                    Toast.makeText(Signup.this, "Password does not match", Toast.LENGTH_LONG).show();
                }
                else if(!validateFname(fname.getText().toString())) {
                    Toast.makeText(Signup.this,"Invalid Firstname",Toast.LENGTH_LONG).show();
                }
                else if(!validateLname(Lname.getText().toString())) {
                    Toast.makeText(Signup.this,"Invalid Lastname",Toast.LENGTH_LONG).show();
                }
                else if(username.equals(savePassword1)|emails.equals(savePassword)){
                    Toast.makeText(Signup.this,"Username or Email already exists",Toast.LENGTH_LONG).show();
                }

                else {
                    DatabaseAdapter.insertEntry(fname.getText().toString(),Lname.getText().toString(),uname.getText().toString(),email.getText().toString(),password.getText().toString());
                    popToast = Toast.makeText(getApplicationContext(), null, Toast.LENGTH_SHORT);
                    popToast.setText("Account Successfully Created ");
                    popToast.show();

                    Intent intent = new Intent(Signup.this,MainActivity.class );
                    startActivity(intent);
                }
            }
        });
    }



    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        loginDataBaseAdapter.close();
    }

    private boolean validateEmail(String email) {
        String emailRegex;
        Pattern pattern;

        emailRegex = "^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,4}$";
        pattern = Pattern.compile(emailRegex);

        Matcher matcher = pattern.matcher(email);
        if (!matcher.find()) {
            return false;
        }
        return true;
    }

    private boolean validatePassword(String password){
        if(password!=null && password.length()>7){
            return true;
        }
        else {
            return false;
        }
    }

    private boolean validateFname(String fname){

        String FNAME_PATTERN = "^([A-Za-z] *)+$";
        Pattern pattern = Pattern.compile(FNAME_PATTERN);
        Matcher matcher = pattern.matcher(fname);
        return matcher.matches();
    }

    private boolean validateLname(String Lname){

        String LNAME_PATTERN = "^([A-Za-z] *)+$";
        Pattern pattern = Pattern.compile(LNAME_PATTERN);
        Matcher matcher = pattern.matcher(Lname);
        return matcher.matches();
    }


}