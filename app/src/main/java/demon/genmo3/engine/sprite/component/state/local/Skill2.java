package demon.genmo3.engine.sprite.component.state.local;

import android.util.Log;

import demon.genmo3.engine.sprite.component.state.State;
import demon.genmo3.engine.sprite.component.state.StateList;
import demon.genmo3.engine.sprite.component.state.StateMachine;
import demon.genmo3.engine.sprite.component.state.StateType;
import demon.genmo3.engine.utils.TimerUtils;
import demon.genmo3.engine.utils.ValueUtils;

public class Skill2 extends State
{
    private float delta;

    public Skill2()
    {
        this.type = StateType.SKILL2;
        this.level = 4;
    }

    @Override
    public State tryTranslate(StateMachine stateMachine)
    {
        Log.d("state", String.valueOf(this.type));
        delta += TimerUtils.getDelta() * 1000;
        if (delta >= ValueUtils.CAST_TIME)
        {
            delta = 0;
            stateMachine.setPreState(this.type);
            return StateList.getState(StateType.IDLE, 0);
        }
        stateMachine.setPreState(this.type);
        return this;
    }
}
