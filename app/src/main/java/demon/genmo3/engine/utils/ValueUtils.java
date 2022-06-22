package demon.genmo3.engine.utils;

public class ValueUtils
{
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;
    public static float ATTACK1_TIME;
    public static float ATTACK2_TIME;
    public static float ATTACK3_TIME;
    public static float CAST_TIME;

    public static void init(int screenWidth,int screenHeight)
    {
        SCREEN_WIDTH = screenWidth;
        SCREEN_HEIGHT = screenHeight;
    }

    public static void setAttack1Time(float attack1Time)
    {
        ATTACK1_TIME = attack1Time;
    }

    public static void setAttack2Time(float attack2Time)
    {
        ATTACK2_TIME = attack2Time;
    }

    public static void setAttack3Time(float attack3Time)
    {
        ATTACK3_TIME = attack3Time;
    }

    public static void setCastTime(float castTime)
    {
        CAST_TIME = castTime;
    }
}
