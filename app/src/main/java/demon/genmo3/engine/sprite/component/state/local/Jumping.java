package demon.genmo3.engine.sprite.component.state.local;

import demon.genmo3.engine.control.Keys;
import demon.genmo3.engine.sprite.component.state.StateMachine;
import demon.genmo3.engine.sprite.component.state.State;
import demon.genmo3.engine.sprite.component.state.StateList;
import demon.genmo3.engine.sprite.component.state.StateType;

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
        if (Keys.LEFT.use() && !Keys.RIGHT.use())
        {
            stateMachine.setDirection(true);
        }
        if (Keys.RIGHT.use() && !Keys.LEFT.use())
        {
            stateMachine.setDirection(false);
        }
        //达到最高点时切换为漂浮状态
        if (!stateMachine.isOnGround() && stateMachine.getSprite().getYSpeed() >= 0)
        {
            stateMachine.setPreState(this.type);
            return StateList.getState(StateType.FLOATING,0);
        }
        stateMachine.setPreState(this.type);
        return this;
    }
}
