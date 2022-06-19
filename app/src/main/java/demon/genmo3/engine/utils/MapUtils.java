package demon.genmo3.engine.utils;

import demon.genmo3.engine.sprite.EntitySprite;
import demon.genmo3.engine.sprite.component.map.MapSprite;

public class MapUtils
{
    private static MapSprite currentMap;

    public static void changeMap(MapSprite map)
    {
        currentMap = map;
    }

    public static boolean canMoveX()
    {
        if (currentMap == null) return true;
        else return currentMap.canMoveX();
    }

    public static boolean canMoveY()
    {
        if (currentMap == null) return true;
        else return currentMap.canMoveY();
    }

    public static EntitySprite getPlayer()
    {
        if (currentMap == null) return null;
        else return currentMap.getLockOnSprite();
    }

    //玩家的渲染坐标
    public static float getX()
    {
        if (currentMap == null) return 0;
        else return currentMap.getlX();
    }

    public static float getY()
    {
        if (currentMap == null) return 0;
        else return currentMap.getlY();
    }

    //玩家地图内的坐标
    public static float getPX()
    {
        if (currentMap == null) return 0;
        else return currentMap.getlX() + currentMap.getX();
    }

    public static float getPY()
    {
        if (currentMap == null) return 0;
        else return currentMap.getlY() + currentMap.getY();
    }


    public static float getMX()
    {
        if (currentMap == null) return 0;
        else return currentMap.getX();
    }

    public static float getMY()
    {
        if (currentMap == null) return 0;
        else return currentMap.getY();
    }
}
