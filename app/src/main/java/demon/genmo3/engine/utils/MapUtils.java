package demon.genmo3.engine.utils;

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
        if (currentMap == null)return true;
        else return currentMap.canMoveX();
    }

    public static boolean canMoveY()
    {
        if(currentMap == null)return true;
        else return currentMap.canMoveY();
    }
}
