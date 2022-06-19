package demon.genmo3.engine.core;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import demon.genmo3.engine.utils.TimerUtils;

/*
 * 游戏引擎部分
 * 执行顺序:
 * 1.MapCheck() ··· 地图碰撞判定
 * 2.onUpdate() ··· 处理游戏逻辑
 * 3.onPhysics() ··· 处理物理系统
 * 4.onCombat() ··· 处理战斗系统
 * 5.onDraw() ··· 处理渲染任务
 * */
public abstract class GameEngine extends SurfaceView implements SurfaceHolder.Callback, Engine
{
    private SurfaceHolder holder;
    private GameThread mainThread;
    private Canvas canvas;
    private Paint cleaner;
    public RenderSpriteQueue renderSpriteQueue;
    public PhysicsSpriteQueue physicsSpriteQueue;
    public ExecutableSpriteQueue executableSpriteQueue;
    public CombatSpriteQueue combatSpriteQueue;

    public GameEngine(Context context)
    {
        super(context);
        init();
    }

    public GameEngine(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public GameEngine(Context context, AttributeSet attrs, int defStyle)
    {

        super(context, attrs, defStyle);
        init();
    }

    public GameEngine(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
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
        TimerUtils.start();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(mainThread, 0, 16, TimeUnit.MILLISECONDS);
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder)
    {
        TimerUtils.destroy();
    }

    @Override
    public void update()
    {
        this.physicsSpriteQueue.onMapCheck();
        this.executableSpriteQueue.onUpdate();
    }

    @Override
    public void draw()
    {
        canvas = holder.lockCanvas(new Rect(0, 0, this.getWidth(), this.getHeight()));
        //清除屏幕
        canvas.drawPaint(cleaner);
        renderSpriteQueue.onDraw(canvas);
        holder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void physics()
    {
        this.physicsSpriteQueue.onPhysics();
        this.combatSpriteQueue.onCombat();
    }

    @Override
    public void init()
    {
        holder = getHolder();
        holder.addCallback(this);
        cleaner = new Paint();
        cleaner.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.CLEAR));
        this.renderSpriteQueue = new RenderSpriteQueue();
        this.physicsSpriteQueue = new PhysicsSpriteQueue();
        this.executableSpriteQueue = new ExecutableSpriteQueue();
        this.combatSpriteQueue = new CombatSpriteQueue();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2)
    {

    }

}
