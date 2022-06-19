package demon.genmo3.engine.sprite.component.state.mob;

import demon.genmo3.engine.sprite.component.state.State;
import demon.genmo3.engine.sprite.component.state.StateType;

public class JumpMob extends State
{
    public JumpMob()
    {
        this.type = StateType.JUMP;
        this.level = 2;
    }
}
