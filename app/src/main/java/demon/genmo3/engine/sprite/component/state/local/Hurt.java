package demon.genmo3.engine.sprite.component.state.local;

import demon.genmo3.engine.sprite.component.state.State;
import demon.genmo3.engine.sprite.component.state.StateType;

public class Hurt extends State
{
    public Hurt()
    {
        this.type = StateType.HURT;
        this.level = 4;
    }
}
