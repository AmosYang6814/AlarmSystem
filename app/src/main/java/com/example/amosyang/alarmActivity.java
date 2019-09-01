package com.example.amosyang;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import BluetoothModel.ScanBluetoothActivity;

public class alarmActivity extends AppCompatActivity {
    private Button AddDivce;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_page_layout);
        initView();
        SetListener();
    }

    private void initView(){
        AddDivce=findViewById(R.id.AddDeviceButton);
    }
    private void SetListener(){
        AddDivce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(alarmActivity.this, ScanBluetoothActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=this.getMenuInflater();
        inflater.inflate(R.menu.user_navigation,menu);
        return true;
    }
}
