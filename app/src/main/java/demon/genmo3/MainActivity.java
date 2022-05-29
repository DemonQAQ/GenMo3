package demon.genmo3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity
{
    private SurfaceView sv;
    private SurfaceHolder sh;
    private Bitmap image;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Resources r2 = this.getResources();
        setContentView(R.layout.activity_main);
        //得到控制器
        sv=(SurfaceView)findViewById(R.id.surfaceview1);
        sh=sv.getHolder();
        //添加回调函数
        sh.addCallback(new Dothings(sh,sv,r2));

    }
}