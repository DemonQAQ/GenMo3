package demon.genmo3.engine.sprite.component.combat;

import android.util.Log;

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
        this.x = x;
        this.y = y;
        this.delta = 0;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.xAccelerate = xAccelerate;
        this.yAccelerate = yAccelerate;
        Log.i("玩家坐标", "(" + damageSource.getX() + "," + damageSource.getY() + ")");
        damageArea.move(x, y);
        effectTexture.move(x, y);
        effectTexture.init(damageSource.getDirection());
        EngineUtils.getCombat().add(this);
        EngineUtils.getRender().addEnd(this.effectTexture);
        EngineUtils.getRender().addEnd(this.damageArea.area);
    }

    //todo clear debug
    private void end()
    {
        Log.i("skillInfo", "end");
        EngineUtils.getCombat().remove(this);
        EngineUtils.getRender().removeEnd(this.effectTexture);
        EngineUtils.getRender().removeEnd(this.damageArea.area);
    }

    @Override
    public void checkEnd()
    {
        Log.i("castTime", String.valueOf(delta));
        delta += TimerUtils.getDelta() * 1000f;
        if (delta > damageArea.duration) end();
    }

    @Override
    public boolean intersect(CollisionBox e)
    {
        return this.damageArea.area.checkIntersect(e);
    }

    @Override
    public void damage(Combat e)
    {

    }

    @Override
    public void move()
    {
        if (xAccelerate>0&&damageSource.getDirection())xAccelerate = -xAccelerate;
        xSpeed += xAccelerate * TimerUtils.getDelta();
        ySpeed += yAccelerate * TimerUtils.getDelta();
        x += xSpeed * TimerUtils.getDelta();
        y += ySpeed * TimerUtils.getDelta();
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
