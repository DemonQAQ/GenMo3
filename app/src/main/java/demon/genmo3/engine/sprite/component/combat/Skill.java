package demon.genmo3.engine.sprite.component.combat;

import demon.genmo3.engine.render.DynamicTexture;
import demon.genmo3.engine.render.Texture;
import demon.genmo3.engine.sprite.EntitySprite;
import demon.genmo3.engine.utils.TimerUtils;

public class Skill
{
    public final int icon_up;
    public final int icon_down;
    private final float mp;
    //DamageArea参数
    private final int width;
    private final int height;
    private final float damage;
    private final float duration;
    private final int times;
    //效果动画
    private Texture effectTexture;
    private DynamicTexture effectTexture1;
    private boolean dynamic;
    public float xSpeed;
    public float ySpeed;
    public float xAccelerate;
    public float yAccelerate;
    //控制技能释放
    private final float cd;
    private float castTime;
    private final EntitySprite damageSource;

    //伤害区域的初始化参数(碰撞箱(x,y,width,height),damage,duration,times),
    //特效的初始化参数(texture)
    //伤害源(sprite)
    public Skill(float mp, int width, int height, float damage, float duration, int times, Texture texture, EntitySprite sprite, float xAccelerate, float yAccelerate, float cd,int id_up,int id_down)
    {
        this.icon_up = id_up;
        this.icon_down = id_down;
        this.mp = mp;
        this.width = width;
        this.height = height;
        this.damage = damage;
        this.duration = duration;
        this.times = times;
        if (texture instanceof DynamicTexture)
        {
            dynamic = true;
            effectTexture1 = (DynamicTexture) texture;
        } else effectTexture = texture;
        this.damageSource = sprite;
        this.xAccelerate = xAccelerate;
        this.yAccelerate = yAccelerate;
        this.cd = cd;
    }

    //执行技能时传入技能的初始坐标
    public void cast()
    {
        if (canCast())
        {
            castTime = 0;
            damageSource.getAttribute().setMp(damageSource.getAttribute().getMp() - mp);
            EffectTexture effect = new EffectTexture(dynamic ? effectTexture1 : effectTexture);
            effect.init(damageSource.getDirection());
            SkillEntity entity = new SkillEntity(new DamageArea(0, 0, this.width, this.height, damage, duration, times,damageSource), effect, damageSource);
            entity.start(xSpeed, ySpeed, xAccelerate, yAccelerate);
        }
    }

    private boolean canCast()
    {
        boolean enoughCd;
        if (castTime == 0)enoughCd = true;
        else
        {
            castTime += TimerUtils.getDelta() * 1000f;
            enoughCd = castTime > cd;
        }
        boolean enoughMp = damageSource.getAttribute().getMp() > this.mp;
        return enoughCd & enoughMp;
    }
}
