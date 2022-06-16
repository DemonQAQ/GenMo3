package demon.genmo3.engine.sprite.component.state.network;

import demon.genmo3.engine.sprite.component.state.StateMachine;
import demon.genmo3.engine.sprite.component.state.State;
import demon.genmo3.engine.sprite.component.state.local.Attack2;

public class Attack2Network extends Attack2
{
    @Override
    public State tryTranslate(StateMachine stateMachine)
    {
        return this;
    }
}
