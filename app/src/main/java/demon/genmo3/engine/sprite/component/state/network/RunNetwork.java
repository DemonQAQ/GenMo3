package demon.genmo3.engine.sprite.component.state.network;

import demon.genmo3.engine.sprite.component.StateMachine;
import demon.genmo3.engine.sprite.component.state.State;
import demon.genmo3.engine.sprite.component.state.local.Run;

public class RunNetwork extends Run
{
    @Override
    public State tryTranslate(StateMachine stateMachine)
    {
        return this;
    }
}
