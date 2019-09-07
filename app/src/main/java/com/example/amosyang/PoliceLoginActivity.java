package com.example.amosyang;

import Util.LocationUtil;
import Util.Net;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import bean.PoliceInfo;
import com.amap.api.maps.AMap;
import com.google.gson.Gson;
import database.StorageInSharePreference;

import java.io.UnsupportedEncodingException;

public class PoliceLoginActivity extends AppCompatActivity {
    private EditText Number;
    private Button Login;
    private EditText Password;
    private EditText Address;
    private AppCompatSpinner servicetype;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.police_info);


        initView();
        //设置点击事件
        setListener();
    }

    /**
     * 初始化view
     */
    private void initView(){
        Login=findViewById(R.id.PoliceLoginButton);
        Number=findViewById(R.id.registerNumberInput);
        Password=findViewById(R.id.PasswordInput);
        Address=findViewById(R.id.policeAddressInput1);
        servicetype=findViewById(R.id.policeSelectInput);
    }


    /**
     * 将填写的各项信息存储
     */
    private PoliceInfo save(PoliceInfo policeInfo){
        policeInfo.setPoliceNumber(Number.getText().toString())
                .setPassword(Password.getText().toString())
                .setAddress(Address.getText().toString())
                .setServiceType(servicetype.getSelectedItemPosition());
        StorageInSharePreference.StoragePoliceData(PoliceLoginActivity.this,policeInfo);
        return policeInfo;
    }




    private void setListener(){

        //验证登录之后跳转
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PoliceInfo policeInfo=new PoliceInfo();

               //if(Number.getText().length()!=0) {
                   Log.i("Police登录跟踪","dasf");
                   policeInfo = save(policeInfo);

                   try {
                       Net net=new Net("Http://192.168.0.102:8080/police/login",policeInfo,1) {
                           @Override
                           public void OnResponse() {
                               Log.i("Police_login","success");
                           }

                           @Override
                           public void OnFailure() {
                               Log.i("Police_login","Faild");
                               //Log.i("Police_login",getrespose().code()+"");
                           }
                       };

                       net.execute("");
                   } catch (UnsupportedEncodingException e) {
                       e.printStackTrace();
                   }


                   Intent i = new Intent(PoliceLoginActivity.this, ScanActivity.class);
                   i.putExtra("Data", new Gson().toJson(policeInfo));
                   startActivity(i);
               }
            //}
        });
    }


}
