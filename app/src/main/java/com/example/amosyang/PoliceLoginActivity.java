package com.example.amosyang;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class PoliceLoginActivity extends AppCompatActivity {
    private Button Login;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.police_info);
        Login=findViewById(R.id.PoliceLoginButton);

        //设置点击事件
        setListener();
    }

    private void setListener(){
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PoliceLoginActivity.this,ScanActivity.class);
                startActivity(i);
            }
        });
    }


}
