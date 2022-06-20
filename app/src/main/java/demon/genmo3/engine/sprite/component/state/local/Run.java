package demon.genmo3.engine.sprite.component.state.local;

import android.util.Log;

import demon.genmo3.engine.control.Keys;
import demon.genmo3.engine.sprite.component.state.StateMachine;
import demon.genmo3.engine.sprite.component.state.State;
import demon.genmo3.engine.sprite.component.state.StateList;
import demon.genmo3.engine.sprite.component.state.StateType;

public class Run extends State
{
    public Run()
    {
        this.type = StateType.RUN;
        this.level = 0;
    }

    @Override
    public State tryTranslate(StateMachine stateMachine)
    {
        Log.d("state", String.valueOf(this.type));
        if (Keys.LEFT.use() && !Keys.RIGHT.use())
        {
            stateMachine.setDirection(true);
        }
        if (Keys.RIGHT.use() && !Keys.LEFT.use())
        {
            stateMachine.setDirection(false);
        }
        if (Keys.ATTACK.use())
        {
            stateMachine.setPreState(this.type);
            return StateList.getState(StateType.ATTACK1,0);
        }
        if (Keys.JUMP.use())
        {
            stateMachine.setOnGround(false);
            stateMachine.setPreState(this.type);
            stateMachine.getSprite().setYSpeed(-1600);
            return StateList.getState(StateType.JUMPING,0);
        }
        if (!Keys.LEFT.use()&&!Keys.RIGHT.use())
        {
            stateMachine.setPreState(this.type);
            return StateList.getState(StateType.IDLE,0);
        }
        stateMachine.setPreState(this.type);
        return this;
    }
}
