package demon.genmo3.engine.sprite.component.state.mob;

import demon.genmo3.engine.control.KeyEvent;
import demon.genmo3.engine.sprite.component.state.State;
import demon.genmo3.engine.sprite.component.state.StateList;
import demon.genmo3.engine.sprite.component.state.StateMachine;
import demon.genmo3.engine.sprite.component.state.StateType;
import demon.genmo3.game.entity.MobEntity;

public class IdleMob extends State
{
    public IdleMob()
    {
        this.type = StateType.IDLE;
        this.level = 0;
    }

    @Override
    public State tryTranslate(StateMachine stateMachine)
    {
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
        if (mob.getKeyValue() == KeyEvent.LEFT || mob.getKeyValue() == KeyEvent.RIGHT)
        {
            stateMachine.setPreState(this.type);
            return StateList.getState(StateType.RUN,2);
        }
        stateMachine.setPreState(this.type);
        return this;
    }
}
