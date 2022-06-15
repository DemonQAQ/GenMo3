package demon.genmo3.engine.utils;

import demon.genmo3.engine.core.ExecutableSpriteQueue;
import demon.genmo3.engine.core.GameEngine;
import demon.genmo3.engine.core.PhysicsSpriteQueue;
import demon.genmo3.engine.core.RenderSpriteQueue;

public class QueueUtils
{
    private static GameEngine engine;

    public static void init(GameEngine e)
    {
        engine = e;
    }

    public static ExecutableSpriteQueue getExecute()
    {
        return engine.executableSpriteQueue;
    }

    public static PhysicsSpriteQueue getPhysics()
    {
        return engine.physicsSpriteQueue;
    }

    public static RenderSpriteQueue getRender()
    {
        return engine.renderSpriteQueue;
    }
}
