package demon.genmo3.engine.sprite.component.state.network;

import demon.genmo3.engine.sprite.component.state.StateMachine;
import demon.genmo3.engine.sprite.component.state.State;
import demon.genmo3.engine.sprite.component.state.local.Attack1;

public class Attack1Network extends Attack1
{

    @Override
    public State tryTranslate(StateMachine stateMachine)
    {
        return this;
    }
}
