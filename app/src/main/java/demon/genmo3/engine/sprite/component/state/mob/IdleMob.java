package demon.genmo3.engine.sprite.component.state.mob;

import demon.genmo3.engine.sprite.component.state.State;
import demon.genmo3.engine.sprite.component.state.StateType;

public class IdleMob extends State
{
    public IdleMob()
    {
        this.type = StateType.IDLE;
        this.level = 0;
    }
}
