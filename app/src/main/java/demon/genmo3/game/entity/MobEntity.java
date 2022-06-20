package demon.genmo3.game.entity;

import android.util.Log;

import java.util.Random;

import demon.genmo3.R;
import demon.genmo3.engine.render.DynamicTexture;
import demon.genmo3.engine.render.Texture;
import demon.genmo3.engine.sprite.EntitySprite;
import demon.genmo3.engine.utils.AnimationsUtils;
import demon.genmo3.engine.utils.MapUtils;
import demon.genmo3.engine.utils.TimerUtils;

//todo complete mob ai
public class MobEntity extends EntitySprite
{
    private EntitySprite target;
    private final float range;
    public float mx;
    public float my;
    private float delta;

    public MobEntity(float x, float y, Texture texture, float width, float height)
    {
        super(x, y, texture, width, height, 2);
        this.range = 500;
        flashMapLocation();
        Log.i("mobInit", getX()+","+getY());
        Log.i("mobInit", this.mx+","+this.my);
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        flashMapLocation();
        changeAnimation();
    }

    @Override
    public void speedEvent()
    {
        ai();
    }

    @Override
    public void move()
    {
        if (MapUtils.getMX() != this.mx)
        {
            setX(getX() - (MapUtils.getMX() - this.mx));
            this.mx = MapUtils.getMX();
        }
        if (MapUtils.getMY() != this.my)
        {
            setY(getY() - (MapUtils.getMY() - this.my));
            this.my = MapUtils.getMY();
        }
        setXSpeed(getXSpeed() + getXAccelerate() * TimerUtils.getDelta());
        setYSpeed(getYSpeed() + getYAccelerate() * TimerUtils.getDelta());
        Log.i("mobSpeed", getXSpeed()+","+getYSpeed());
        setX(getX() + getXSpeed() * TimerUtils.getDelta());
        setY(getY() + getYSpeed() * TimerUtils.getDelta());
        moveCollisionBox();
    }

    public void ai()
    {
        delta += TimerUtils.getDelta() *1000;
        if (delta > 3000)
        {
            delta = 0;
            Random random = new Random();
            int x = random.nextInt(2);
            if (x == 1)
            {
                getStateMachine().setDirection(!getStateMachine().getDirection());
            }
        }
    }

    private void flashMapLocation()
    {
        this.mx = MapUtils.getMX();
        this.my = MapUtils.getMY();
    }

    public void changeTarget(EntitySprite target)
    {
        this.target = target;
    }



    @Override
    public void changeAnimation()
    {
        if (isDynamic())
        {
            DynamicTexture texture = AnimationsUtils.getAnimationMob(getStateMachine().getState(),0);
            if (texture != null)
            {
                texture.start();
                setTexture(texture);
            }
        }
    }
}
