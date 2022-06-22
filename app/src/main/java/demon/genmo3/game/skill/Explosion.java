package demon.genmo3.game.skill;

import demon.genmo3.R;
import demon.genmo3.engine.render.DynamicTexture;
import demon.genmo3.engine.sprite.EntitySprite;
import demon.genmo3.engine.sprite.component.combat.Skill;
import demon.genmo3.engine.sprite.component.combat.SkillFactory;
import demon.genmo3.engine.utils.TextureUtils;

public class Explosion extends SkillFactory
{
    public static float mp = 50;
    public static int width = 384;
    public static int height = 384;
    public static float damage = 20;
    public static float duration = 900;
    public static int times = 1;
    public static float xAccelerate = 0;
    public static float yAccelerate = 0;
    public static float cd = 1500;
    public static int id_up = R.drawable.explosion_up;
    public static int id_down = R.drawable.explosion_down;
    public static float offsetX = 100;
    public static float offsetY = 60;

    public static Skill getSkill(EntitySprite sprite)
    {
        DynamicTexture texture = TextureUtils.getDynamicTexture(R.drawable.explosion, 5, 4, 18, 50, true);
        return new Skill(offsetX, offsetY, mp, width, height, damage, duration, times, texture, sprite, xAccelerate, yAccelerate, cd, id_up, id_down);
    }
}
