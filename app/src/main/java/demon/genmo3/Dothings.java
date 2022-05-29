package demon.genmo3;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Dothings implements SurfaceHolder.Callback
{
    private SurfaceHolder sh;
    private SurfaceView sv;
    private Bitmap image;
    Resources r2;

    public Dothings(SurfaceHolder sh, SurfaceView sv, Resources r2) {
        super();
        this.sh = sh;
        this.sv = sv;
        this.r2 = r2;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // 启动线程
        MyThread t = new MyThread(sh, sv,r2);
        new Thread(t).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

}
