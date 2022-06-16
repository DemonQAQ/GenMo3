package demon.genmo3.engine.core;

import java.util.ArrayList;

/*
* 负责执行所有实现了Executable接口并加入队列的对象的onUpdate()方法
* */
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
