package demon.genmo3.engine.sprite.component.state;

import android.util.Log;

import demon.genmo3.engine.sprite.component.StateMachine;
import demon.genmo3.engine.utils.TimerUtils;

public class Jump extends State
{
    private float delta = 0;

    public Jump()
    {
        this.type = StateType.JUMP;
        this.level = 2;
    }

    @Override
    public State tryTranslate(StateMachine stateMachine)
    {
        Log.d("state", this.type +","+delta);
        delta += TimerUtils.getDelta()*1000;
        if (delta>=100)
        {
            delta = 0;
            stateMachine.getSprite().setYSpeed(-1600);
            stateMachine.setOnGround(false);
            stateMachine.setPreState(this.type);
            return StateList.getState(StateType.JUMPING);
        }
        stateMachine.setPreState(this.type);
        return this;
    }
}