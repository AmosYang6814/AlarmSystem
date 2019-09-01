package BluetoothModel;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;


public class BluetoohStateThread extends Thread {
    Activity startactivity;
    Context context;
    public static final String[] permissions = {
            "android.permission.BLUETOOTH",
            "android.permission.BLUETOOTH_ADMIN",
            "android.permission.ACCESS_COARSE_LOCATION",
//            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.BLUETOOTH_PRIVILEGED"

    };

    /**
     * 权限检查
     */


    public static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    BluetoohStateThread(Context context) {

    }

    @Override
    public void run() {
        super.run();
    }
}
