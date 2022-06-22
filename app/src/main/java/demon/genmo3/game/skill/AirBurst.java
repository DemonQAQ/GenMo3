package demon.genmo3.game.skill;

import demon.genmo3.R;
import demon.genmo3.engine.render.DynamicTexture;
import demon.genmo3.engine.sprite.EntitySprite;
import demon.genmo3.engine.sprite.component.combat.Skill;
import demon.genmo3.engine.sprite.component.combat.SkillFactory;
import demon.genmo3.engine.utils.TextureUtils;

public class AirBurst extends SkillFactory
{
    public static float mp = 50;
    public static int width = 384;
    public static int height = 384;
    public static float damage = 20;
    public static float duration = 350;
    public static int times = 1;
    public static float xAccelerate = 0;
    public static float yAccelerate = 0;
    public static float cd = 500;
    public static int id_up = R.drawable.airburst_up;
    public static int id_down = R.drawable.airburst_down;
    public static float offsetX = 0;
    public static float offsetY = 20;

    public static Skill getSkill(EntitySprite sprite)
    {
        DynamicTexture texture = TextureUtils.getDynamicTexture(R.drawable.airburst, 3, 3, 7, 50, true);
        return new Skill(offsetX, offsetY, mp, width, height, damage, duration, times, texture, sprite, xAccelerate, yAccelerate, cd, id_up, id_down);
    }
}
