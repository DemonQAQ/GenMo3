package demon.genmo3.engine.core;

import java.util.ArrayList;

import demon.genmo3.engine.physics.Gravity;
import demon.genmo3.engine.physics.Movable;

public class PhysicsSpriteQueue
{
    private static final ArrayList<Gravity> GRAVITY_LIST = new ArrayList<>();
    private static final ArrayList<Movable> MOVABLE_LIST = new ArrayList<>();

    public PhysicsSpriteQueue()
    {

    }

    public void onPhysics()
    {
        GRAVITY_LIST.forEach(Gravity::gravity);
        MOVABLE_LIST.forEach(Movable::move);
    }

    public void add(Gravity e)
    {
        GRAVITY_LIST.add(e);
    }

    public void add(Movable e)
    {
        MOVABLE_LIST.add(e);
    }

    public <E> void add(E e)
    {
        if (e instanceof Gravity && e instanceof Movable) {
            GRAVITY_LIST.add((Gravity) e);
            MOVABLE_LIST.add((Movable) e);
        }
    }

    public void remove(Gravity e)
    {
        GRAVITY_LIST.remove(e);
    }

    public void remove(Movable e)
    {
        MOVABLE_LIST.remove(e);
    }

    public <E> void remove(E e)
    {
        if (e instanceof Gravity && e instanceof Movable) {
            GRAVITY_LIST.remove((Gravity) e);
            MOVABLE_LIST.remove((Movable) e);
        }
    }
}
