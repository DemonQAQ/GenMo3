package demon.genmo3.engine.sprite.component.state.mob;

import android.util.Log;

import demon.genmo3.engine.sprite.component.state.State;
import demon.genmo3.engine.sprite.component.state.StateList;
import demon.genmo3.engine.sprite.component.state.StateMachine;
import demon.genmo3.engine.sprite.component.state.StateType;
import demon.genmo3.engine.utils.TimerUtils;

public class DeathMob extends State
{
    private float delta;

    public DeathMob()
    {
        this.type = StateType.DEATH;
        this.level = 5;
    }

    @Override
    public State tryTranslate(StateMachine stateMachine)
    {
        Log.d("Mobstate", String.valueOf(this.type));
        stateMachine.setDeath(true);
        delta += TimerUtils.getDelta()*1000;
        if (delta>=800)
        {
            delta = 0;
            stateMachine.setPreState(this.type);
            return StateList.getState(StateType.IDLE,2);
        }
        stateMachine.setPreState(this.type);
        return this;
    }
}
