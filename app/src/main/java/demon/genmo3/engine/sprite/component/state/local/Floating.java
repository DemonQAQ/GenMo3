package demon.genmo3.engine.sprite.component.state.local;

import android.util.Log;

import demon.genmo3.engine.control.Keys;
import demon.genmo3.engine.sprite.component.StateMachine;
import demon.genmo3.engine.sprite.component.state.State;
import demon.genmo3.engine.sprite.component.state.StateList;
import demon.genmo3.engine.sprite.component.state.StateType;

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
        if (Keys.LEFT.use() && !Keys.RIGHT.use())
        {
            stateMachine.setDirection(true);
        }
        if (Keys.RIGHT.use() && !Keys.LEFT.use())
        {
            stateMachine.setDirection(false);
        }
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
