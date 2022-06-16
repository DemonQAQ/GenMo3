package demon.genmo3.engine.sprite.component.state.local;

import android.util.Log;

import demon.genmo3.engine.control.Keys;
import demon.genmo3.engine.sprite.component.StateMachine;
import demon.genmo3.engine.sprite.component.state.State;
import demon.genmo3.engine.sprite.component.state.StateList;
import demon.genmo3.engine.sprite.component.state.StateType;
import demon.genmo3.engine.utils.TimerUtils;
import demon.genmo3.engine.utils.ValueUtils;

public class Attack3 extends State
{
    private float delta;

    public Attack3()
    {
        this.type = StateType.ATTACK3;
        this.level = 4;
    }

    @Override
    public State tryTranslate(StateMachine stateMachine)
    {
        Log.d("state", this.type +","+delta);
        delta += TimerUtils.getDelta()*1000;
        if (delta >= ValueUtils.ATTACK3_TIME)
        {
            delta = 0;
            stateMachine.setPreState(this.type);
            return StateList.getState(StateType.IDLE,false);
        }
        stateMachine.setPreState(this.type);
        return this;
    }
}
