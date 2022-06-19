package demon.genmo3.engine.sprite.component.state.mob;

import demon.genmo3.engine.sprite.component.state.State;
import demon.genmo3.engine.sprite.component.state.StateType;

public class FloatingMob extends State
{
    public FloatingMob()
    {
        this.type = StateType.FLOATING;
        this.level = 3;
    }
}
