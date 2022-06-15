package demon.genmo3.engine.core;

import java.security.Key;
import java.util.ArrayList;

import demon.genmo3.engine.control.Keys;
import demon.genmo3.engine.physics.Gravity;
import demon.genmo3.engine.physics.Movable;
import demon.genmo3.engine.sprite.EntitySprite;
import demon.genmo3.engine.sprite.component.map.Building;
import demon.genmo3.engine.sprite.component.map.MapSprite;
import demon.genmo3.engine.utils.TimerUtils;

public class PhysicsSpriteQueue
{
    private static final ArrayList<Gravity> GRAVITY_LIST = new ArrayList<>();
    private static final ArrayList<Movable> MOVABLE_LIST = new ArrayList<>();
    private static final ArrayList<Movable> MOVABLE_LIST_DONT_INTERSECT = new ArrayList<>();
    private static MapSprite Map;

    public PhysicsSpriteQueue()
    {

    }

    public void setMap(MapSprite map)
    {
        Map = map;
    }

    public void onPhysics()
    {
        //处理重力影响
        gravity();
        //处理碰撞事件
        move();
    }

    public void onMapCheck()
    {
        //刷新地图位置
        if (Map!=null)
        {
            Map.onUpdate(MOVABLE_LIST.toArray(new Movable[0]));
        }
    }

    private void move()
    {
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
        MOVABLE_LIST_DONT_INTERSECT.forEach(Movable::move);
        MOVABLE_LIST.forEach(Movable::move);

    }

    private void gravity()
    {
        GRAVITY_LIST.forEach(e ->
        {
            if (!e.isOnGround())
            {
                e.setYAccelerate(Gravity.G);
            }
            else
            {
                e.setYAccelerate(0);
                e.setYSpeed(0);
            }
        });
    }


    public void addG(Gravity e)
    {
        if (!GRAVITY_LIST.contains(e))GRAVITY_LIST.add(e);
    }

    public void addM(Movable e)
    {
        if (!MOVABLE_LIST.contains(e))MOVABLE_LIST.add(e);
    }

    public void addMDI(Movable e)
    {
        if (!MOVABLE_LIST_DONT_INTERSECT.contains(e))MOVABLE_LIST_DONT_INTERSECT.add(e);
    }

    public void removeMDI(Movable e)
    {
        MOVABLE_LIST_DONT_INTERSECT.remove(e);
    }

    public <E> void add(E e)
    {
        if (!GRAVITY_LIST.contains(e) && e instanceof Gravity) GRAVITY_LIST.add((Gravity) e);
        if (!MOVABLE_LIST.contains(e) &&e instanceof Movable) MOVABLE_LIST.add((Movable) e);
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
