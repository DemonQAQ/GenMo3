package demon.genmo3.game.entity;

import android.util.Log;

import java.util.Random;

import demon.genmo3.R;
import demon.genmo3.engine.control.KeyEvent;
import demon.genmo3.engine.physics.Movable;
import demon.genmo3.engine.render.DynamicTexture;
import demon.genmo3.engine.render.Texture;
import demon.genmo3.engine.sprite.EntitySprite;
import demon.genmo3.engine.sprite.component.CollisionBox;
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
    private int keyValue;

    public MobEntity(float x, float y, Texture texture, float width, float height)
    {
        super(x, y, texture, width, height, 2);
        this.range = 500;
        this.keyValue = KeyEvent.NONE;
        setxRunAccelerate(500);
        flashMapLocation();
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        flashMapLocation();
//        Log.i("mobHp", String.valueOf(getAttribute().getHp()));
//        Log.i("death", String.valueOf(isDeath()));
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
        if (keyValue == KeyEvent.LEFT)
            setXAccelerate(getxAccelerate() - getxRunAccelerate() * TimerUtils.getDelta());
        if (keyValue == KeyEvent.RIGHT)
            setXAccelerate(getxAccelerate() + getxRunAccelerate() * TimerUtils.getDelta());
        if (keyValue == KeyEvent.NONE)
        {
            setXAccelerate(0);
            setXSpeed(0);
        }
        setXSpeed(getXSpeed() + getXAccelerate() * TimerUtils.getDelta());
        setYSpeed(getYSpeed() + getYAccelerate() * TimerUtils.getDelta());
        setX(getX() + getXSpeed() * TimerUtils.getDelta());
        setY(getY() + getYSpeed() * TimerUtils.getDelta());
        moveCollisionBox();
    }

    public void ai()
    {
        delta += TimerUtils.getDelta() * 1000;
        if (delta > 500)
        {
            delta = 0;
            Random random = new Random();
            int x = random.nextInt(3);
            if (x == 1)
            {
                keyValue = KeyEvent.LEFT;
                getStateMachine().setDirection(true);
            }
            if (x == 2)
            {
                keyValue = KeyEvent.RIGHT;
                getStateMachine().setDirection(false);
            } else
            {
                keyValue = KeyEvent.NONE;
            }
        }
    }

    public int getKeyValue()
    {
        return this.keyValue;
    }

    public void setKeyValue(int keyValue)
    {
        this.keyValue = keyValue;
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
    public boolean intersect(Movable e)
    {
        return this.getCollisionBox().checkIntersect(e.getCollisionBox());
    }

    //todo 怪物碰撞
    @Override
    public void onIntersect(Movable e)
    {
        if (e instanceof LocalPlayer)
        {
            LocalPlayer player = (LocalPlayer) e;
            if (this.getCollisionBox().checkSideIntersect(player.getCollisionBox()))
            {
                if (this.getCollisionBox().x + this.getCollisionBox().width * 0.5f > player.getCollisionBox().x)
                {
                    if (player.getXSpeed() >= 0)
                    {
                        player.setXSpeed(-50);
                        player.setxAccelerate(-player.getxRunAccelerate() * 0.1f);
                    }
                } else
                {
                    if (player.getXSpeed() <= 0)
                    {
                        player.setXSpeed(50);
                        player.setxAccelerate(player.getxRunAccelerate() * 0.1f);
                    }
                }
            }
            if (this.getCollisionBox().checkAboveIntersect(player.getCollisionBox()))
            {
                if (player.getXSpeed() >= 0)
                {
                    player.setYSpeed(-player.getYSpeed() * 0.5f);
                }
            }
        }
    }

    @Override
    public void changeAnimation()
    {
        if (isDynamic())
        {
            DynamicTexture texture = AnimationsUtils.getAnimationMob(getStateMachine().getState(), 0);
            if (texture != null)
            {
                texture.start();
                setTexture(texture);
            }
        }
    }
}
