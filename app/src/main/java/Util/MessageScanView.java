package Util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.util.AttributeSet;

public class MessageScanView extends RadarView {

    public MessageScanView(Context context){
        super(context);
    }
    public MessageScanView(Context context, AttributeSet attributeset){
        super(context,attributeset);
    }

    @Override

    //初始化雷达扫描图
    public void initPaint() {

        // TODO Auto-generated method stub
        setBackgroundColor(Color.TRANSPARENT);


        //宽度=5，抗锯齿，描边效果的白色画笔
        mPaintLine = new Paint();
        mPaintLine.setStrokeWidth(5);
        mPaintLine.setAntiAlias(true);
        mPaintLine.setStyle(Paint.Style.STROKE);
        mPaintLine.setColor(Color.WHITE);

        //宽度=5，抗锯齿，描边效果的浅绿色画笔
        mPaintCircle = new Paint();
        mPaintCircle.setStrokeWidth(5);
        mPaintCircle.setAntiAlias(true);
        mPaintCircle.setStyle(Paint.Style.FILL);
        mPaintCircle.setColor(0x99000000);

        //暗绿色的画笔
        mPaintSector = new Paint();
        mPaintSector.setColor(0x9D00ff00);
        mPaintSector.setAntiAlias(true);
        mShader = new SweepGradient(viewSize / 2, viewSize / 2, Color.TRANSPARENT, Color.GREEN);
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
