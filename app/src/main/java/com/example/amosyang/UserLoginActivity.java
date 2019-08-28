package com.example.amosyang;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class UserLoginActivity extends AppCompatActivity {
    private Button Login;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_layout);
        Login=findViewById(R.id.UserLoginButton);

        //设置监听
        setListener();
    }

    private void setListener(){
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(UserLoginActivity.this,alarmActivity.class);
                startActivity(i);
            }
        });
    }
}
