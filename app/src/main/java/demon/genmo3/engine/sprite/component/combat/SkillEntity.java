package demon.genmo3.engine.sprite.component.combat;

import android.util.Log;

import demon.genmo3.engine.control.KeyEvent;
import demon.genmo3.engine.sprite.EntitySprite;
import demon.genmo3.engine.sprite.component.CollisionBox;
import demon.genmo3.engine.utils.EngineUtils;
import demon.genmo3.engine.utils.MapUtils;
import demon.genmo3.engine.utils.TimerUtils;
import demon.genmo3.engine.sprite.entity.MobEntity;

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

    private void flashLocation(float ox, float oy)
    {
        this.mx = MapUtils.getMX();
        this.my = MapUtils.getMY();
        this.x = damageSource.getCollisionBox().x + damageSource.getCollisionBox().width * 0.5f;
        this.y = damageSource.getCollisionBox().y;
        if (damageSource.getDirection())
        {
            this.x = this.x - ox - damageSource.getCollisionBox().width * 0.5f - damageArea.area.width;
        } else
        {
            this.x = this.x + ox + damageSource.getCollisionBox().width * 0.5f;
        }
        this.y -= oy;
    }

    public void start(float ox, float oy, float xSpeed, float ySpeed, float xAccelerate, float yAccelerate)
    {
        flashLocation(ox,oy);
        this.delta = 0;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.xAccelerate = xAccelerate;
        this.yAccelerate = yAccelerate;
        damageArea.move(this.x, this.y);
        if (effectTexture != null)
        {
            effectTexture.move(this.x, this.y);
            effectTexture.init(damageSource.getDirection());
            EngineUtils.getRender().addEnd(this.effectTexture);
        }
        EngineUtils.getCombat().add(this);
        EngineUtils.getRender().addEnd(this.damageArea.area);
    }

    //todo clear debug
    private void end()
    {
        Log.i("end", "结束开始");
        EngineUtils.getCombat().remove(this);
        if (effectTexture != null) EngineUtils.getRender().removeEnd(this.effectTexture);
        EngineUtils.getRender().removeEnd(this.damageArea.area);
        Log.i("end", "结束完成");
    }

    @Override
    public void checkEnd()
    {
        delta += TimerUtils.getDelta() * 1000f;
        if (delta > damageArea.duration) end();
    }

    @Override
    public boolean isDeath()
    {
        return false;
    }

    @Override
    public void death()
    {

    }

    @Override
    public boolean intersect(CollisionBox e)
    {
        return this.damageArea.area.checkIntersect(e);
    }

    @Override
    public void damage(Combat e)
    {
        if (e instanceof SkillEntity || e == this.damageSource || e.isNumbness() || e.isDeath()) return;
        else
        {
            Attributes attributes = e.getAttribute();
            attributes.setHp(attributes.getHp() - this.damageArea.damage);
            if (e instanceof MobEntity) ((MobEntity) e).setKeyValue(KeyEvent.HURT);
        }
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
        if (effectTexture != null)this.effectTexture.move(this.x, this.y);
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
