package demon.genmo3.game.skill;

import demon.genmo3.R;
import demon.genmo3.engine.render.DynamicTexture;
import demon.genmo3.engine.render.Texture;
import demon.genmo3.engine.sprite.EntitySprite;
import demon.genmo3.engine.sprite.component.combat.Skill;
import demon.genmo3.engine.utils.TextureUtils;

/*
 * 技能工厂类，返回具体的skill实例
 * */
public abstract class WaterSplash
{
    private static float mp;
    private static int width;
    private static int height;
    private static float damage;
    private static float duration;
    private static int times;
    private static float xAccelerate;
    private static float yAccelerate;
    private static float cd;
    private static int id_up;
    private static int id_down;

    public static void init()
    {
        mp = 50;
        width = 264;
        height = 308;
        damage = 100;
        duration = 5000;
        times = 1;
        xAccelerate = 0;
        yAccelerate = 0;
        cd = 500;
        id_up = R.drawable.waterspalsh_up;
        id_down = R.drawable.waterspalsh_up;
    }

    public static Skill getSkill(EntitySprite sprite)
    {
        DynamicTexture texture = TextureUtils.getDynamicTexture(R.drawable.watersplash, 5, 4, 20, 50, true);
        return new Skill(mp, width, height, damage, duration, times, texture, sprite, xAccelerate, yAccelerate, cd, id_up, id_down);
    }
}
