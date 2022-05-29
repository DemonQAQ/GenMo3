package demon.genmo3;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.InputStream;

public class MyThread implements Runnable {
    private SurfaceHolder sh;
    private SurfaceView sv;
    private Canvas c;
    private Paint p;
    private Bitmap image1;
    private Bitmap image2;
    int i = 0;
    Resources r2;
    // 小球的初始坐标和移动速度以及半径
    private int x = 70, y = 70, vx = 3, vy = 3, r = 70;

    public MyThread(SurfaceHolder sh, SurfaceView sv,Resources r2) {
        super();
        this.sh = sh;
        this.sv = sv;
        this.r2 = r2;
        image1 = BitmapFactory.decodeResource(r2, R.drawable.test1);
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // 得到画布
            c = sh.lockCanvas(new Rect(0, 0, sv.getWidth(), sv.getHeight()));
            // 设置背景颜色
            c.drawColor(Color.WHITE);
            p = new Paint();
            // 先画白球
            p.setColor(Color.RED);
            c.drawCircle(x, y, r, p);
            // 变化坐标
            x += vx;
            y += vy;
            // 画黑球
            p.setColor(Color.BLACK);
            c.drawCircle(x+50, y+50, r, p);
            c.drawBitmap(image1,50,50,p);

            image2 = Bitmap.createBitmap(image1,16,0,16,16);
            c.drawBitmap(image2,200,200,p);
            // 解锁画布，更新提交屏幕显示内容
            sh.unlockCanvasAndPost(c);
            if (x == 70 || x + r >= sv.getWidth()) {
                vx = -vx;
            }
            if (y == 70 || y + r >= sv.getHeight()) {
                vy = -vy;
            }
        }
    }
}
