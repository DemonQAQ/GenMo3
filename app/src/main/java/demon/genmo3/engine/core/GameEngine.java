package demon.genmo3.engine.core;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import demon.genmo3.engine.core.GameThread;
import demon.genmo3.engine.utils.TimerUtils;

/*
*
* */
public abstract class GameEngine implements SurfaceHolder.Callback,Engine
{
    private SurfaceHolder holder;
    private SurfaceView surfaceView;
    private GameThread mainThread;
    private Canvas canvas;
    public RenderSpriteQueue renderSpriteQueue;
    public PhysicsSpriteQueue physicsSpriteQueue;

    public GameEngine(SurfaceView surfaceView, SurfaceHolder holder)
    {
        this.surfaceView = surfaceView;
        this.holder = holder;
        init();
    }

    /*
    * 开启游戏主线程
    * update在主线程内
    * */
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder)
    {
        mainThread = new GameThread(this);
        new Thread(mainThread).start();
        TimerUtils.start();
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder)
    {
        TimerUtils.destroy();
    }

    @Override
    public void update()
    {

    }

    @Override
    public void draw()
    {
        canvas = holder.lockCanvas(new Rect(0, 0, surfaceView.getWidth(), surfaceView.getHeight()));
        renderSpriteQueue.onDraw(canvas);
        holder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void physics()
    {
        this.physicsSpriteQueue.onPhysics();
    }

    @Override
    public void init()
    {
        this.renderSpriteQueue = new RenderSpriteQueue();
        this.physicsSpriteQueue = new PhysicsSpriteQueue();
    }
}
