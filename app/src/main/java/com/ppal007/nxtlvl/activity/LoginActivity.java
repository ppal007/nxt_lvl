package com.ppal007.nxtlvl.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ppal007.nxtlvl.R;
import com.ppal007.nxtlvl.myDb.UserDb;
import com.ppal007.nxtlvl.myDb.UserEntity;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    private TextView textView_signup;
    private ImageView imageView_go;
    private EditText userName,userPass;
    boolean drawableETPassCheck = false;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide title bar
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
        setContentView(R.layout.activity_login);

        //find xml
        textView_signup = findViewById(R.id.tv_signUp);
        imageView_go = findViewById(R.id.img_view_login_button);
        userName = findViewById(R.id.et_login_user_name);
        userPass = findViewById(R.id.et_login_user_pass);

        //show / hide password
        userPass.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_off_24, 0);
        userPass.setOnTouchListener((v, event) -> {
            final int DRAWABLE_LEFT = 0;
            final int DRAWABLE_TOP = 1;
            final int DRAWABLE_RIGHT = 2;
            final int DRAWABLE_BOTTOM = 3;

            if(event.getAction() == MotionEvent.ACTION_UP) {
                if(event.getRawX() >= (userPass.getRight() - userPass.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    // your action here
                    if (!drawableETPassCheck){
                        userPass.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_24, 0);
                        userPass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        drawableETPassCheck=true;

                    }else {
                        userPass.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_off_24, 0);
                        userPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        drawableETPassCheck=false;

                    }

                    return true;
                }
            }
            return false;
        });

        //login button click listener
        imageView_go.setOnClickListener(v -> {
            String _userName = userName.getText().toString().trim();
            String _userPass = userPass.getText().toString().trim();

            if (_userName.isEmpty()){
                userName.setError("Please Enter User Name");
                userName.requestFocus();
            }else if (_userPass.isEmpty()){
                userPass.setError("please Enter Password");
                userPass.requestFocus();
            }else {
                //login check
                UserEntity userEntity = UserDb.getUserDbInstance(getApplicationContext()).getDaoInstance().login_user(_userName,_userPass);
                if(userEntity==null){

                    Toast.makeText(this, "Invalid User!", Toast.LENGTH_SHORT).show();

                } else {

                    Intent intent = new Intent(getApplicationContext(),BioActivity.class);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    finish();

                }
            }

        });

        //text view sign up click listener
        textView_signup.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> finish())
                .setNegativeButton("No", (dialog, id) -> dialog.cancel());
        AlertDialog alert = builder.create();
        alert.show();
    }
}