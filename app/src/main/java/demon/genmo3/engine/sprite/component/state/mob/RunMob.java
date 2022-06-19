package demon.genmo3.engine.sprite.component.state.mob;

import demon.genmo3.engine.sprite.component.state.State;
import demon.genmo3.engine.sprite.component.state.StateType;

public class RunMob extends State
{
    public RunMob()
    {
        this.type = StateType.RUN;
        this.level = 0;
    }
}
