package demon.genmo3.engine.sprite.component.combat;

import android.util.Log;

import demon.genmo3.engine.sprite.EntitySprite;
import demon.genmo3.engine.sprite.component.CollisionBox;
import demon.genmo3.engine.utils.EngineUtils;
import demon.genmo3.engine.utils.MapUtils;
import demon.genmo3.engine.utils.TimerUtils;

public class SkillEntity implements Combat
{
    //渲染的坐标
    public float x;
    public float y;
    //地图当前选取的坐标左上角
    public float mx;
    public float my;
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

    private void flashLocation()
    {
        this.mx = MapUtils.getMX();
        this.my = MapUtils.getMY();
        this.x = damageSource.getCollisionBox().x;
        this.y = damageSource.getCollisionBox().y;
    }

    public void start(float xSpeed, float ySpeed, float xAccelerate, float yAccelerate)
    {
        flashLocation();
        this.delta = 0;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.xAccelerate = xAccelerate;
        this.yAccelerate = yAccelerate;
        Log.i("玩家坐标", "(" + damageSource.getCollisionBox().x + "," + damageSource.getCollisionBox().y + ")");
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
        //Log.i("castTime", String.valueOf(delta));
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
        if (MapUtils.getMX() != this.mx)
        {
            x = x - (MapUtils.getMX() - this.mx);
            this.mx = MapUtils.getMX();
        }
        if (MapUtils.getMY() != this.my)
        {
            y = y - (MapUtils.getMY() - this.my);
            this.my = MapUtils.getMY();
        }
        if (xAccelerate > 0 && damageSource.getDirection()) xAccelerate = -xAccelerate;
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
