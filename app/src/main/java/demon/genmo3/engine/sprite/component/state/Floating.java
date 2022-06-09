package demon.genmo3.engine.sprite.component.state;

import demon.genmo3.engine.sprite.component.StateMachine;

public class Floating extends State
{
    public Floating()
    {
        this.type = StateType.FLOATING;
        this.level = 3;
    }

    @Override
    public State tryTranslate(StateMachine stateMachine)
    {

        return super.tryTranslate(stateMachine);
    }
}
