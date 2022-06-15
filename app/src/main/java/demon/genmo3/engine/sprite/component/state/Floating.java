package demon.genmo3.engine.sprite.component.state;

import android.util.Log;

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
        Log.d("state", String.valueOf(this.type));
        if (stateMachine.isOnGround())
        {
            stateMachine.setPreState(this.type);
            return StateList.getState(StateType.IDLE);
        }
        stateMachine.setPreState(this.type);
        return this;
    }
}
