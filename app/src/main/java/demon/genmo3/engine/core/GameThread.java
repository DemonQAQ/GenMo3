package demon.genmo3.engine.core;

import demon.genmo3.engine.utils.TimerUtils;

public class GameThread implements Runnable
{
    private long current;
    private long delta;
    private Engine g;


    public GameThread(Engine g)
    {
        this.g = g;
    }

    @Override
    public void run()
    {
        current = TimerUtils.getTime();
        update();
        draw();
        delta = TimerUtils.getTime() - current;
    }

    //执行所以的游戏逻辑
    private void update()
    {
        g.update();
        g.physics();
    }

    //执行渲染
    private void draw()
    {
        g.draw();
    }

    protected long getDelta()
    {
        return this.delta;
    }
}
