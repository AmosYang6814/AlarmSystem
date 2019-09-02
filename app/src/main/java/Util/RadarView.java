package Util;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.example.amosyang.R;

public abstract class RadarView extends FrameLayout {

    private Context mContext;
    protected int viewSize = 800;
    protected Paint mPaintLine;
    protected Paint mPaintCircle;
    protected Paint mPaintSector;
    protected boolean isstart = false;
    protected ScanThread mThread;
    protected Paint mPaintPoint;
    protected int circleSpace=111;
    protected Paint mCrossLine;
    //旋转效果起始角度
    protected int start = 0;

//    private int[] point_x;
//    private int[] point_y;

    protected Shader mShader;

    protected Matrix matrix;


    public final static int CLOCK_WISE=1;
    public final static int ANTI_CLOCK_WISE=-1;

    public @interface RADAR_DIRECTION {

    }
    //默认为顺时针呢
    protected final static int DEFAULT_DIERCTION=CLOCK_WISE;



    //设定雷达扫描方向
    protected int direction=DEFAULT_DIERCTION;

    protected boolean threadRunning;

    {
        threadRunning = true;
    }

    public RadarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        mContext = context;
        initPaint();
    }

    public RadarView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        mContext = context;
        initPaint();

    }

    public abstract  void initPaint();

    public void setViewSize(int size) {
        this.viewSize = size;
        setMeasuredDimension(viewSize, viewSize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        setMeasuredDimension(viewSize, viewSize);
    }

    public void start() {
        mThread = new ScanThread(this);
        mThread.setName("radar");
        mThread.start();
        threadRunning = true;
        isstart = true;
    }

    public void stop() {
        if (isstart) {
            threadRunning = false;
            isstart = false;
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {




        // TODO Auto-generated method stub
        canvas.drawCircle(viewSize / 2, viewSize / 2, viewSize, mPaintCircle);

        for(int i=125;i<viewSize;i=i+circleSpace){
            canvas.drawCircle(viewSize / 2, viewSize / 2, i, mPaintLine);
        }
        //绘制两条十字线
        if(mCrossLine==null)mCrossLine=mPaintLine;
        canvas.drawLine(viewSize / 2, 0, viewSize / 2, viewSize, mCrossLine);
        canvas.drawLine(0, viewSize / 2, viewSize, viewSize / 2, mCrossLine);




        //这里在雷达扫描过制定圆周度数后，将随机绘制一些白点，模拟搜索结果
//        if (start > 100) {
//            for (int i = 0; i < 2; i++) {
//                canvas.drawCircle(viewSize / 2 + point_x[i], viewSize / 2 + point_y[i], 10, mPaintPoint);
//            }
//        }
//        if (start > 200) {
//            for (int i = 2; i < 5; i++) {
//                canvas.drawCircle(viewSize / 2 + point_x[i], viewSize / 2 + point_y[i], 10, mPaintPoint);
//            }
//        }
//        if (start > 300) {
//            for (int i = 5; i < 9; i++) {
//                canvas.drawCircle(viewSize / 2 + point_x[i], viewSize / 2 + point_y[i], 10, mPaintPoint);
//            }
//        }
//        if (start > 500) {
//            for (int i = 9; i < 11; i++) {
//                canvas.drawCircle(viewSize / 2 + point_x[i], viewSize / 2 + point_y[i], 10, mPaintPoint);
//            }
//        }
//        if (start > 800) {
//            for (int i = 11; i < point_x.length; i++) {
//                canvas.drawCircle(viewSize / 2 + point_x[i], viewSize / 2 + point_y[i], 10, mPaintPoint);
//            }
//        }

        DrawExtralImage(canvas);


        //根据matrix中设定角度，不断绘制shader,呈现出一种扇形扫描效果
        canvas.concat(matrix);

        canvas.drawCircle(viewSize / 2, viewSize / 2, viewSize, mPaintSector);

        super.onDraw(canvas);
    }


    public void DrawExtralImage(Canvas canvas){

    }


    public void setDirection(@RADAR_DIRECTION int direction) {
        if (direction != CLOCK_WISE && direction != ANTI_CLOCK_WISE) {
            throw new IllegalArgumentException("Use @RADAR_DIRECTION constants only!");
        }
        this.direction = direction;
    }





    protected class ScanThread extends Thread {

        private RadarView view;

        public ScanThread(RadarView view) {
            // TODO Auto-generated constructor stub
            this.view = view;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            while (threadRunning) {
                if (isstart) {
                    view.post(new Runnable() {
                        public void run() {
                            start = start + 1;
                            matrix = new Matrix();
                            //设定旋转角度,制定进行转转操作的圆心
//                          matrix.postRotate(start, viewSize / 2, viewSize / 2);
//                          matrix.setRotate(start,viewSize/2,viewSize/2);
                            matrix.preRotate(direction*start,viewSize/2,viewSize/2);
                            view.invalidate();


                        }
                    });
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}