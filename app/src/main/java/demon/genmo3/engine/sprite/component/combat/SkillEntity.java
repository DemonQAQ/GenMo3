package demon.genmo3.engine.sprite.component.combat;

import demon.genmo3.engine.sprite.EntitySprite;
import demon.genmo3.engine.sprite.component.CollisionBox;
import demon.genmo3.engine.utils.EngineUtils;
import demon.genmo3.engine.utils.TimerUtils;

public class SkillEntity implements Combat
{
    public float x;
    public float y;
    public float xSpeed;
    public float ySpeed;
    public float xAccelerate;
    public float yAccelerate;
    //用于控制技能什么时候结束
    public float delta;
    private final DamageArea damageArea;
    private final EffectTexture effectTexture;
    private final EntitySprite damageSource;

    public SkillEntity(DamageArea damageArea, EffectTexture effectTexture, EntitySprite damageSource)
    {
        this.damageArea = damageArea;
        this.effectTexture = effectTexture;
        this.damageSource = damageSource;
    }

    public void start(float x, float y, float xSpeed, float ySpeed, float xAccelerate, float yAccelerate)
    {
        delta = 0;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.xAccelerate = xAccelerate;
        this.yAccelerate = yAccelerate;
        damageArea.move(x, y);
        effectTexture.move(x, y);
        effectTexture.init(damageSource.getDirection());
        EngineUtils.getCombat().add(this);
        EngineUtils.getRender().addEnd(this.effectTexture);
    }

    private void end()
    {
        EngineUtils.getCombat().remove(this);
        EngineUtils.getRender().removeEnd(this.effectTexture);
    }

    private boolean checkEnd()
    {
        delta += TimerUtils.getDelta() * 1000f;
        return !(delta < damageArea.duration);
    }

    @Override
    public boolean intersect(CollisionBox e)
    {
        return this.damageArea.area.checkIntersect(e);
    }

    @Override
    public void damage(Combat e)
    {
        if (checkEnd()) end();
        else
        {
            onDamage();
        }
    }

    //制作技能时覆盖此方法实现技能逻辑
    private void onDamage()
    {

    }

    @Override
    public void move()
    {
        this.damageArea.move(this.x, this.y);
        this.effectTexture.move(this.x, this.y);
    }

    @Override
    public DamageArea getDamageArea()
    {
        return damageArea;
    }

    @Override
    public CollisionBox getCollisionBox()
    {
        return damageArea.area;
    }

    @Override
    public Attributes getAttribute()
    {
        return damageSource.getAttribute();
    }

    @Override
    public boolean isNumbness()
    {
        return false;
    }

    @Override
    public void setNumbness(boolean flag)
    {

    }
}
