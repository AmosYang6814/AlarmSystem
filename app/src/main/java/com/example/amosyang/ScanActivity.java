package com.example.amosyang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;

import BluetoothModel.ScanBluetoothActivity;
import Util.MessageScanView;
import Util.RadarView;

public class ScanActivity extends AppCompatActivity {
    private MapView mapView;
    private MessageScanView messagescanview;
    private Button AddDevice;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_layout);

        initView();
        initMap(savedInstanceState);
        setListener();
        //设置雷达扫描方向
        messagescanview.setDirection(RadarView.ANTI_CLOCK_WISE);
        messagescanview.start();
    }

    /**
     * 控件初始化函数
     */
    private void initView(){
        mapView = (MapView) findViewById(R.id.Map);
        messagescanview = findViewById(R.id.radar);
        AddDevice=findViewById(R.id.AddPoliceDeviceButton);
    }

    /**
     * 地图初始化函数
     * @param savedInstanceState
     */
    private void initMap(Bundle savedInstanceState){
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        AMap aMap = mapView.getMap();

        aMap.setTrafficEnabled(true);// 显示实时交通状况
        //地图模式可选类型：MAP_TYPE_NORMAL,MAP_TYPE_SATELLITE,MAP_TYPE_NIGHT
        aMap.setMapType(AMap.MAP_TYPE_NORMAL);// 卫星地图模式
    }

    /**
     * 监听函数
     */
    private void setListener(){
        AddDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ScanActivity.this, ScanBluetoothActivity.class);
                startActivity(i);
            }
        });
    }
}
