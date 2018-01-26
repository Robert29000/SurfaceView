package study.vv.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by student2 on 26.01.18.
 */

public class MySurefaceView extends SurfaceView implements SurfaceHolder.Callback
{
    SurfaceHolder holder;
    public MySurefaceView(Context context) {
        super(context);
        holder=getHolder();
        holder.addCallback(this);
        p.setColor(Color.WHITE);
    }
    Paint p=new Paint();

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        new MyThread().start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(!changed) {
            p.setColor(Color.BLACK);
            color = Color.WHITE;
            changed=true;
        }
        else{
            p.setColor(Color.WHITE);
            color = Color.BLACK;
            changed=false;
        }
        return super.onTouchEvent(event);
    }
    int color=Color.BLACK;
    boolean changed=false;
    class MyThread extends Thread{
        @Override
        public void run() {
            int cx=0;
            int diffx=-10;
            while(true){
                Canvas canvas=holder.lockCanvas();
                canvas.drawColor(color);
                if(cx==canvas.getWidth() || cx==0) {
                    diffx=-diffx;
                }
                cx+=diffx;
                canvas.drawCircle(cx,100,50,p);
                holder.unlockCanvasAndPost(canvas);
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
