package demon.genmo3.engine.sprite.component.state;

import android.util.Log;

import demon.genmo3.engine.control.Keys;
import demon.genmo3.engine.sprite.component.StateMachine;
import demon.genmo3.engine.utils.TimerUtils;
import demon.genmo3.engine.utils.ValueUtils;

public class Attack2 extends State
{
    private float delta;

    public Attack2()
    {
        this.type = StateType.ATTACK2;
        this.level = 4;
    }

    @Override
    public State tryTranslate(StateMachine stateMachine)
    {
        Log.d("state", this.type +","+delta);
        delta += TimerUtils.getDelta()*1000;
        if (delta >= ValueUtils.ATTACK2_TIME)
        {
            delta = 0;
            if (Keys.ATTACK.use())
            {
                stateMachine.setPreState(this.type);
                return StateList.getState(StateType.ATTACK3);
            }
            stateMachine.setPreState(this.type);
            return StateList.getState(StateType.IDLE);
        }
        stateMachine.setPreState(this.type);
        return this;
    }
}
