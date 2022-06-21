package demon.genmo3.game.skill;

import demon.genmo3.R;
import demon.genmo3.engine.render.DynamicTexture;
import demon.genmo3.engine.sprite.EntitySprite;
import demon.genmo3.engine.sprite.component.combat.Skill;
import demon.genmo3.engine.utils.TextureUtils;

public class Attack
{
    public static float mp = 0;
    public static int width = 264;
    public static int height = 308;
    public static float damage = 20;
    public static float duration = 250;
    public static int times = 1;
    public static float xAccelerate = 0;
    public static float yAccelerate = 0;
    public static float cd = 0;
    public static int id_up = R.drawable.item_null;
    public static int id_down = R.drawable.item_null;
    public static float offsetX = -50;
    public static float offsetY = 0;

    public static Skill getSkill(EntitySprite sprite)
    {
        return new Skill(offsetX, offsetY, mp, width, height, damage, duration, times, null, sprite, xAccelerate, yAccelerate, cd, id_up, id_down);
    }
}
