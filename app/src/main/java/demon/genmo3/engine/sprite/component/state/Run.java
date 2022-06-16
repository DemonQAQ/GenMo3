package demon.genmo3.engine.sprite.component.state;

import android.util.Log;

import demon.genmo3.engine.control.Keys;
import demon.genmo3.engine.sprite.component.StateMachine;

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
        if (Keys.JUMP.use())
        {
            stateMachine.setOnGround(false);
            stateMachine.setPreState(this.type);
            stateMachine.getSprite().setYSpeed(-1600);
            return StateList.getState(StateType.JUMPING);
        }
        if (!Keys.LEFT.use()&&!Keys.RIGHT.use())
        {
            stateMachine.setPreState(this.type);
            return StateList.getState(StateType.IDLE);
        }
        stateMachine.setPreState(this.type);
        return this;
    }
}
