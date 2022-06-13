package demon.genmo3.engine.utils;

import java.util.Timer;
import java.util.TimerTask;

public class TimerUtils
{
    private final static Timer globalTimer = new Timer();
    private static long time;
    private static long DELTA = 0;

    public static void start()
    {
        globalTimer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                time++;
            }
        },1,1);
    }

    public static void destroy()
    {
        globalTimer.cancel();
    }

    public static long getTime()
    {
        return time;
    }

    public static Timer getTimer()
    {
        return globalTimer;
    }

    public static void setDelta(long delta)
    {
        DELTA = delta;
    }

    public static float getDelta()
    {
        return DELTA / 1000f;
    }
}
