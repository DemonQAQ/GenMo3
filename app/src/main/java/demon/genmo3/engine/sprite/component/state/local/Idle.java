package demon.genmo3.engine.sprite.component.state.local;

import android.util.Log;

import demon.genmo3.engine.control.Keys;
import demon.genmo3.engine.sprite.component.state.StateMachine;
import demon.genmo3.engine.sprite.component.state.State;
import demon.genmo3.engine.sprite.component.state.StateList;
import demon.genmo3.engine.sprite.component.state.StateType;
import demon.genmo3.engine.utils.TimerUtils;

public class Idle extends State
{
    private boolean isAttack1;
    private boolean isAttack2;
    private float delta;

    public Idle()
    {
        this.type = StateType.IDLE;
        this.level = 0;
    }

    @Override
    public State tryTranslate(StateMachine stateMachine)
    {
        Log.d("state", String.valueOf(this.type));
        attackCheck(stateMachine);
        //切换方向
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
            if (isAttack1)
            {
                isAttack1 = false;
                delta = 0;
                stateMachine.setPreState(StateType.ATTACK1);
                return StateList.getState(StateType.ATTACK2,0);
            }
            else if (isAttack2)
            {
                isAttack2 = false;
                delta = 0;
                stateMachine.setPreState(StateType.ATTACK2);
                return StateList.getState(StateType.ATTACK3,0);
            }
            stateMachine.setPreState(this.type);
            return StateList.getState(StateType.ATTACK1,0);
        }
        if (Keys.JUMP.use() && stateMachine.isOnGround())
        {
            stateMachine.setPreState(this.type);
            return StateList.getState(StateType.JUMP,0);
        }
        if (!stateMachine.isOnGround())
        {
            stateMachine.setPreState(this.type);
            return StateList.getState(StateType.FLOATING,0);
        }
        if (Keys.ITEM.use())
        {
            stateMachine.setPreState(this.type);
        }
        if (Keys.LEFT.use() || Keys.RIGHT.use())
        {
            stateMachine.setPreState(this.type);
            return StateList.getState(StateType.RUN,0);
        }
        stateMachine.setPreState(this.type);
        return this;
    }

    private void attackCheck(StateMachine stateMachine)
    {
        if (stateMachine.getPreState().getState().equals(StateType.ATTACK1.getState()))
            isAttack1 = true;
        else if (stateMachine.getPreState().getState().equals(StateType.ATTACK2.getState()))
            isAttack2 = true;
        if (isAttack1 || isAttack2) delta += TimerUtils.getDelta() * 1000;
        if (delta >= 100)
        {
            delta = 0;
            isAttack1 = false;
            isAttack2 = false;
        }
    }
}
