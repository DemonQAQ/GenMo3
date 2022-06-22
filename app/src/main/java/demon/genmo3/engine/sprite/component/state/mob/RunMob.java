package demon.genmo3.engine.sprite.component.state.mob;

import android.util.Log;

import demon.genmo3.engine.control.KeyEvent;
import demon.genmo3.engine.sprite.component.state.State;
import demon.genmo3.engine.sprite.component.state.StateList;
import demon.genmo3.engine.sprite.component.state.StateMachine;
import demon.genmo3.engine.sprite.component.state.StateType;
import demon.genmo3.engine.sprite.entity.MobEntity;

public class RunMob extends State
{
    public RunMob()
    {
        this.type = StateType.RUN;
        this.level = 0;
    }

    @Override
    public State tryTranslate(StateMachine stateMachine)
    {
        Log.d("Mobstate", String.valueOf(this.type));
        MobEntity mob = (MobEntity) stateMachine.getSprite();
        if (mob.getAttribute().getHp()<=0)
        {
            stateMachine.setPreState(this.type);
            return StateList.getState(StateType.DEATH,2);
        }
        if (mob.getKeyValue() == KeyEvent.HURT)
        {
            stateMachine.setPreState(this.type);
            return StateList.getState(StateType.HURT,2);
        }
        if (mob.getKeyValue() == KeyEvent.NONE)
        {
            stateMachine.setPreState(this.type);
            return StateList.getState(StateType.IDLE,2);
        }
        stateMachine.setPreState(this.type);
        return this;
    }
}
