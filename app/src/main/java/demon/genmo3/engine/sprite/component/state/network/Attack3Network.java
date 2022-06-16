package demon.genmo3.engine.sprite.component.state.network;

import demon.genmo3.engine.sprite.component.StateMachine;
import demon.genmo3.engine.sprite.component.state.State;
import demon.genmo3.engine.sprite.component.state.local.Attack3;

public class Attack3Network extends Attack3
{
    @Override
    public State tryTranslate(StateMachine stateMachine)
    {
        return this;
    }
}
