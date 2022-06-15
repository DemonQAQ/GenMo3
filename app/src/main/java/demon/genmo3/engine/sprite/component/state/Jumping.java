package demon.genmo3.engine.sprite.component.state;

import android.util.Log;

import demon.genmo3.engine.sprite.component.StateMachine;

public class Jumping extends State
{
    public Jumping()
    {
        this.type = StateType.JUMPING;
        this.level = 2;
    }

    @Override
    public State tryTranslate(StateMachine stateMachine)
    {
//        Log.d("state", String.valueOf(this.type));
//        stateMachine.setOnGround(false);
//        boolean i = stateMachine.isOnGround();
//        Log.d("onGround", String.valueOf(i));
        //达到最高点时切换为漂浮状态
        if (!stateMachine.isOnGround() && stateMachine.getSprite().getYSpeed() >= 0)
        {
            stateMachine.setPreState(this.type);
            return StateList.getState(StateType.FLOATING);
        }
            return this;
    }
}