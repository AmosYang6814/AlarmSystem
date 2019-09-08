package com.example.amosyang;

import Util.LocationUtil;
import Util.Net;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import bean.PoliceInfo;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;

import BluetoothModel.ScanBluetoothActivity;
import JPush.LocalBroadcastManager;
import Util.MessageScanView;
import Util.RadarView;
import cn.jpush.android.api.JPushInterface;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;


//监听JPUSH的推送，并且在服务端注册
public class ScanActivity extends AppCompatActivity {
    public static  final String RECIEVED_MESSAGE_SCANACTIVITY="com.example.amosyang.MESSAGE_RECEIVED";
    private MapView mapView;
    private MessageScanView messagescanview;
    private Button AddDevice;
    private ConstraintLayout constraintLayout;
    private float longtitude=0.0f;
    private float lngtitude=0.0f;
    private PoliceInfo policeInfo=null;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_layout);
         policeInfo=new Gson().fromJson(getIntent().getStringExtra("Data"),PoliceInfo.class);

        initView();
        initMap(savedInstanceState);
        setListener();


        String JpushID=JPushInterface.getRegistrationID(this);
        Log.i("REID:",JpushID);
        Toast.makeText(this,JpushID,Toast.LENGTH_LONG).show();
        policeInfo.setJpushID(JpushID);

        //设置广播接收器
        setReceiver();

        //开启推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        //设置雷达动画扫描方向
        messagescanview.setDirection(RadarView.ANTI_CLOCK_WISE);
        messagescanview.start();


        //定位
        LocationUtil.setLocationService(getApplicationContext(),mLocationListener);
    }



    /**
     * 定位回调事件,当位置发生变动时，向服务器发送最新的信息
     */
    public AMapLocationListener mLocationListener=new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            Log.i("alarmActivity","定位更换");
            Log.i("Location","ScanActivity定位为"+aMapLocation.getLongitude()+"");

            //位置大致未变，不上传
            if(longtitude==(float) aMapLocation.getLongitude() &&lngtitude==(float)aMapLocation.getLatitude())
                return;

            longtitude=(float) aMapLocation.getLongitude();
            lngtitude=(float)aMapLocation.getLatitude();

            policeInfo.setLng((float) aMapLocation.getLatitude());
            policeInfo.setLong((float)aMapLocation.getLongitude());

            try {
                Net net=new Net("Http://192.168.0.102:8080/police",policeInfo) {
                    @Override
                    public void OnResponse() {
                     Log.i("SCaActivity网络追踪","success");
                    }

                    @Override
                    public void OnFailure() {
                        Log.i("SCaActivity网络追踪","faild");
                    }
                };
                net.execute("");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }


        }
    };


    /**
     * 控件初始化函数
     */
    private void initView(){
        constraintLayout=findViewById(R.id.Scan_annotion);
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


    private void setReceiver(){
        ScanReceiver scanReceiver=new ScanReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(RECIEVED_MESSAGE_SCANACTIVITY);
        LocalBroadcastManager.getInstance(this).registerReceiver(scanReceiver,filter);
    }

    /**
     * 向服务器端注册该警方客户端
     */
    private void sendToNet(PoliceInfo policeInfo){


//        Net net=new Net() {
//            @Override
//            public void OnResponse() {
//
//            }
//
//            @Override
//            public void OnFailure() {
//
//            }
//        };
    }


    private class ScanReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            if(RECIEVED_MESSAGE_SCANACTIVITY.equals(intent.getAction())){
                Toast.makeText(ScanActivity.this,"接受到新的消息",Toast.LENGTH_LONG).show();
                constraintLayout.setVisibility(View.GONE);
            }
        }
    }

}
