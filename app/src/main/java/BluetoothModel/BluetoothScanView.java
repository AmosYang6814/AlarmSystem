package BluetoothModel;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.SweepGradient;
import android.util.AttributeSet;

import com.example.amosyang.R;

import java.util.ArrayList;

import Util.RadarView;

public class BluetoothScanView extends RadarView {

    private ArrayList<BluetoothDevice> list=new ArrayList<>();
    Context context;


    public BluetoothScanView(Context context){
        super(context);
        this.context=context;
    } public BluetoothScanView(Context context, AttributeSet attributeset){
        super(context,attributeset);
    }

    @Override
    public void initPaint() {
        circleSpace=170; //修改圆间距
        viewSize=2000;

        // TODO Auto-generated method stub
        setBackgroundColor(Color.TRANSPARENT);


        //宽度=5，抗锯齿，描边效果的白色画笔
        mPaintLine = new Paint();
        mPaintLine.setStrokeWidth(5);
        mPaintLine.setAntiAlias(true);
        mPaintLine.setStyle(Paint.Style.STROKE);
        mPaintLine.setColor(getResources().getColor(R.color.ivory));

        mCrossLine=new Paint();
        mCrossLine.setColor(Color.TRANSPARENT);

        //宽度=5，抗锯齿，描边效果的浅绿色画笔
        mPaintCircle = new Paint();
        mPaintCircle.setStrokeWidth(5);
        mPaintCircle.setAntiAlias(true);
        mPaintCircle.setStyle(Paint.Style.FILL);
        mPaintCircle.setColor(getResources().getColor(R.color.lightgray_1));

        //暗绿色的画笔
        mPaintSector = new Paint();
        mPaintSector.setColor(0x9D00ff00);
        mPaintSector.setAntiAlias(true);
        mShader = new SweepGradient(viewSize /2, viewSize /2,
                new int[]{getResources().getColor(R.color.skyblue),getResources().getColor(R.color.whitesmoke),getResources().getColor(R.color.whitesmoke),getResources().getColor(R.color.whitesmoke)},
                new float[]{(float) 0.001,(float) 0.05,(float) 0.2,(float) 0.659});
        mPaintSector.setShader(mShader);

        //白色实心画笔
        mPaintPoint=new Paint();
        mPaintPoint.setColor(Color.WHITE);
        mPaintPoint.setStyle(Paint.Style.FILL);

//        //随机生成的点，模拟雷达扫描结果
//        point_x = utilTools.Getrandomarray(15, 20, 300);
//        point_y = utilTools.Getrandomarray(15,20, 300);
    }

}
