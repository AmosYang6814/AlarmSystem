package com.example.amosyang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout UserLogin,PoliceLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserLogin=findViewById(R.id.user_login);
        PoliceLogin=findViewById(R.id.police_login);

        //设置事件监听
        setListener();
    }

    private void setListener(){
        UserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,UserLoginActivity.class);
                startActivity(i);
            }
        });

        PoliceLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,PoliceLoginActivity.class);
                startActivity(i);
            }
        });
    }
}
