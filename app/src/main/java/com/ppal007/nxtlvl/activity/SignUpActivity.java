package com.ppal007.nxtlvl.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ppal007.nxtlvl.R;
import com.ppal007.nxtlvl.myDb.UserDb;
import com.ppal007.nxtlvl.myDb.UserEntity;

public class SignUpActivity extends AppCompatActivity {

    private EditText fullName,userName,pass;
    private ImageView imageView_go;
    boolean drawableETPassCheck = false;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide title bar
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
        setContentView(R.layout.activity_sign_up);

        //find xml
        fullName=findViewById(R.id.et_signup_full_name);
        userName=findViewById(R.id.et_signup_user_name);
        pass=findViewById(R.id.et_signup_user_pass);
        imageView_go=findViewById(R.id.img_view_signup_go);

        //show / hide password
        pass.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_off_24, 0);
        pass.setOnTouchListener((v, event) -> {
            final int DRAWABLE_LEFT = 0;
            final int DRAWABLE_TOP = 1;
            final int DRAWABLE_RIGHT = 2;
            final int DRAWABLE_BOTTOM = 3;

            if(event.getAction() == MotionEvent.ACTION_UP) {
                if(event.getRawX() >= (pass.getRight() - pass.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    // your action here
                    if (!drawableETPassCheck){
                        pass.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_24, 0);
                        pass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        drawableETPassCheck=true;

                    }else {
                        pass.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_off_24, 0);
                        pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        drawableETPassCheck=false;

                    }

                    return true;
                }
            }
            return false;
        });

        //go button click listener
        imageView_go.setOnClickListener(v -> init_signUp());
    }

    private void init_signUp() {
        String _fullName = fullName.getText().toString();
        String _userName = userName.getText().toString().trim();
        String _pass = pass.getText().toString().trim();

        if (_fullName.isEmpty()){
            fullName.setError("Please enter Full Name");
            fullName.requestFocus();
        }else if (_userName.isEmpty()){
            userName.setError("Please Enter User Name");
            userName.requestFocus();
        }else if (_pass.isEmpty()){
            pass.setError("Please Enter Password");
            pass.requestFocus();
        }else {
            //insert user
            UserEntity userEntity = new UserEntity(_fullName,_userName,_pass);
            long insertId = UserDb.getUserDbInstance(getApplicationContext()).getDaoInstance().user_insertion(userEntity);
            if(insertId > 0){
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(this, "Can't insert!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}