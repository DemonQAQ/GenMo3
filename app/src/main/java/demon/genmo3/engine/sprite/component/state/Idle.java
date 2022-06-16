package demon.genmo3.engine.sprite.component.state;

import android.util.Log;

import demon.genmo3.engine.control.Keys;
import demon.genmo3.engine.sprite.component.StateMachine;

public class Idle extends State
{

    public Idle()
    {
        this.type = StateType.IDLE;
        this.level = 0;
    }

    @Override
    public State tryTranslate(StateMachine stateMachine)
    {
        Log.d("state", String.valueOf(this.type));
        //切换方向
        if (Keys.LEFT.use() && !Keys.RIGHT.use())
        {
            stateMachine.setDirection(true);
        }
        if (Keys.RIGHT.use() && !Keys.LEFT.use())
        {
            stateMachine.setDirection(false);
        }
        if (Keys.JUMP.use() && stateMachine.isOnGround())
        {
            stateMachine.setPreState(this.type);
            return StateList.getState(StateType.JUMP);
        }
        if (!stateMachine.isOnGround())
        {
            stateMachine.setPreState(this.type);
            return StateList.getState(StateType.FLOATING);
        }
        if (Keys.ITEM.use())
        {
            stateMachine.setPreState(this.type);
        }
        if (Keys.LEFT.use()||Keys.RIGHT.use())
        {
            stateMachine.setPreState(this.type);
            return StateList.getState(StateType.RUN);
        }
        stateMachine.setPreState(this.type);
        return this;
    }
}
