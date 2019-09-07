package com.example.amosyang;

import BluetoothModel.BluetoohStateThread;
import Util.LocationUtil;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import BluetoothModel.ScanBluetoothActivity;
import Util.Net;
import bean.User;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import database.StorageInSharePreference;

import java.util.ArrayList;
import java.util.List;

public class alarmActivity extends AppCompatActivity {
    private Button AddDivce;
    private ImageButton alarm110;
    private ImageButton alarm119;
    private ImageButton alarm120;




    String[] LocationPemission={Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_page_layout);
        initView();
        SetListener();
        checkPermissions();
        LocationUtil.setLocationService(getApplicationContext(),mLocationListener);//定位
    }

    /**
     * 初始化控件
     */
    private void initView(){
        AddDivce=findViewById(R.id.AddDeviceButton);
        alarm110=findViewById(R.id.police_110);

    }

    /**
     * 设置事件监听
     */
    private void SetListener(){
        AddDivce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(alarmActivity.this, ScanBluetoothActivity.class);
                startActivity(i);
            }
        });

        alarm110.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user=StorageInSharePreference.getUserInformation(alarmActivity.this);
                Log.i("Net_link","dianji");

                try{
                final Net net=new Net("http://106.14.12.46:8080/test",user,1) {
                    @Override
                    public void OnResponse() {
                        Log.i("Net_link", "response "+getrespose().code());
                    }

                    @Override
                    public void OnFailure() {
                        Log.i("Net_link", "response faild");
                    }
                };
                net.execute("");

                }
                catch (Exception e){

                }
            }
        });
    }

    /**
     * 定位回调事件
     */
    public AMapLocationListener mLocationListener=new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            Log.i("alarmActivity","定位更换");
            Log.i("Location","定位为"+aMapLocation.getLongitude()+"");

        }
    };


    /**
     * 定位权限请求检查
     */
    private void checkPermissions() {
        List<String> permissionDeniedList = new ArrayList<>();
        for (String permission : LocationPemission) {
            int permissionCheck = ContextCompat.checkSelfPermission(this, permission);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                permissionDeniedList.add(permission);
            }
        }
        if (!permissionDeniedList.isEmpty()) {
            String[] deniedPermissions = permissionDeniedList.toArray(new String[permissionDeniedList.size()]);
            //申请权限
            if(Build.VERSION.SDK_INT>=23){
                requestPermissions(deniedPermissions,1);
            }
        }
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=this.getMenuInflater();
        inflater.inflate(R.menu.user_navigation,menu);
        return true;
    }

    /**
     * 权限授予请求回调
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);



    }
}
