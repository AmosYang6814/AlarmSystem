package BluetoothModel;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import Util.BluetoothUtils;

import com.example.amosyang.R;

import java.util.ArrayList;
import java.util.List;

import Util.BluetoothScanView;
import Util.ProgressUtils;
import Util.RadarView;

public class ScanBluetoothActivity extends AppCompatActivity  {
    public static int ActivityCode=10;
    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
            }
        }
    };

    private ArrayList<BluetoothDevice> list=new ArrayList<>();

    private BluetoothScanView bluetoothScanView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_bluetooth_layout);

        initView();
        setListener();

        //启动雷达扫面动图
        bluetoothScanView.setDirection(RadarView.ANTI_CLOCK_WISE);
        bluetoothScanView.start();
        initReceiver();
        BluetoothUtils.getInstance(this).scanDevices();

        if(Build.VERSION.SDK_INT>=23){
            requestPermissions(BluetoohStateThread.permissions,1);
        }
        //检查权限
        checkPermissions();

        //初始化过滤器
    }

    private void checkPermissions() {
        List<String> permissionDeniedList = new ArrayList<>();
        for (String permission : BluetoohStateThread.permissions) {
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


    private void initReceiver() {

        //初始化意图过滤器
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);


        filter.addAction(BluetoothConstant.ACTION_INIT_COMPLETE);
        filter.addAction(BluetoothConstant.ACTION_CONNECTED_SERVER);
        filter.addAction(BluetoothConstant.ACTION_CONNECT_ERROR);
        registerReceiver(receiver, filter);
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()){
                case BluetoothDevice.ACTION_FOUND:
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    for(int i=0;i<list.size();i++){
                        if(device.getAddress()==null || device.getAddress().equals(list.get(i).getAddress())){
                            return;
                        }
                    }
                    list.add(device);
                    showToast("list中共有"+list.size()+"个装备");
                    break;
                case BluetoothAdapter.ACTION_DISCOVERY_STARTED:
//                    showToast("开始扫描");
                    break;
                case BluetoothAdapter.ACTION_DISCOVERY_FINISHED:
//                    showToast("扫描完成");
                    break;
                case BluetoothConstant.ACTION_INIT_COMPLETE:
                    ProgressUtils.dismissProgress();
                    break;
                case BluetoothConstant.ACTION_CONNECTED_SERVER:
                    ProgressUtils.dismissProgress();
                    String remoteAddress = intent.getStringExtra(BluetoothUtils.EXTRA_REMOTE_ADDRESS);
                    break;
                case BluetoothConstant.ACTION_CONNECT_ERROR:
                    ProgressUtils.dismissProgress();
                    showToast(intent.getStringExtra(BluetoothUtils.EXTRA_ERROR_MSG));
                    break;
            }
        }
    };

    private void initView(){
        bluetoothScanView=findViewById(R.id.FindBluetooth);
    }

    private void setListener(){

    }

    public void showToast(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

}
