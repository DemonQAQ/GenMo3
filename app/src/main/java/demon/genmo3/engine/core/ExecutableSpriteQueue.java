package demon.genmo3.engine.core;

import java.util.ArrayList;

import demon.genmo3.engine.physics.Gravity;

public class ExecutableSpriteQueue
{
    private static final ArrayList<Executable> EXE_LIST = new ArrayList<>();

    public void onUpdate()
    {
        EXE_LIST.forEach(Executable::onUpdate);
    }

    public void add(Executable e)
    {
        EXE_LIST.add(e);
    }

    public void remove(Executable e)
    {
        EXE_LIST.remove(e);
    }
}
