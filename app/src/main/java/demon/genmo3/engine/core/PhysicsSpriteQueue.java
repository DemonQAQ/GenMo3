package demon.genmo3.engine.core;

import java.security.Key;
import java.util.ArrayList;

import demon.genmo3.engine.control.Keys;
import demon.genmo3.engine.physics.Gravity;
import demon.genmo3.engine.physics.Movable;
import demon.genmo3.engine.sprite.EntitySprite;
import demon.genmo3.engine.utils.TimerUtils;

public class PhysicsSpriteQueue
{
    private static final ArrayList<Gravity> GRAVITY_LIST = new ArrayList<>();
    private static final ArrayList<Movable> MOVABLE_LIST = new ArrayList<>();

    public PhysicsSpriteQueue()
    {

    }

    public void onPhysics()
    {
        //处理重力影响
        gravity();
        //处理碰撞事件
        move();
    }

    private void move()
    {
        //刷新地图位置

        //碰撞检测
        Movable[] m = MOVABLE_LIST.toArray(new Movable[0]);
        MOVABLE_LIST.forEach(e->
        {
            for (Movable movable : m)
            {
                if(e.intersect(movable))e.onIntersect(movable);
            }
        });
        //处理坐标变换
        MOVABLE_LIST.forEach(Movable::move);
    }

    private void gravity()
    {
        GRAVITY_LIST.forEach(e ->
        {
            if (!e.isOnGround())
            {
                if (e instanceof EntitySprite)
                {
                    EntitySprite sprite = (EntitySprite) e;
                    if (sprite.getY() + sprite.getHeight() >= 750)
                    {
                        sprite.setOnGround(true);
                        sprite.setY(750 - sprite.getHeight());
                        sprite.setYSpeed(0);
                    }
                    else sprite.setYSpeed(sprite.getYSpeed()-(Gravity.G* (TimerUtils.getDelta()/1000f)));
                }
            }
        });
    }


    public void addG(Gravity e)
    {
        GRAVITY_LIST.add(e);
    }

    public void addM(Movable e)
    {
        MOVABLE_LIST.add(e);
    }

    public <E> void add(E e)
    {
        if (e instanceof Gravity) GRAVITY_LIST.add((Gravity) e);
        if (e instanceof Movable) MOVABLE_LIST.add((Movable) e);
    }

    public void removeG(Gravity e)
    {
        GRAVITY_LIST.remove(e);
    }

    public void removeM(Movable e)
    {
        MOVABLE_LIST.remove(e);
    }

    public <E> void remove(E e)
    {
        if (e instanceof Gravity) GRAVITY_LIST.remove((Gravity) e);
        if (e instanceof Movable) MOVABLE_LIST.remove((Movable) e);
    }
}
