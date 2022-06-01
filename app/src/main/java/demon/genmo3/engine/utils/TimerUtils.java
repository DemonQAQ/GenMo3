package demon.genmo3.engine.utils;

import java.util.Timer;
import java.util.TimerTask;

public class TimerUtils
{
    private final static Timer globalTimer = new Timer();
    private static long time;

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
}
