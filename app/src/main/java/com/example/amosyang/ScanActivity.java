package com.example.amosyang;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;

import Util.RadarView;

public class ScanActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_layout);

        MapView mapView = (MapView) findViewById(R.id.Map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        AMap aMap = mapView.getMap();

        aMap.setTrafficEnabled(true);// 显示实时交通状况
        //地图模式可选类型：MAP_TYPE_NORMAL,MAP_TYPE_SATELLITE,MAP_TYPE_NIGHT
        aMap.setMapType(AMap.MAP_TYPE_NORMAL);// 卫星地图模式

        RadarView radarView = (RadarView) findViewById(R.id.radar);
        //设置雷达扫描方向
        radarView.setDirection(RadarView.ANTI_CLOCK_WISE);
        radarView.start();
    }
}
