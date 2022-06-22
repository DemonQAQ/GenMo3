package demon.genmo3.engine.sprite.component.state.mob;

import demon.genmo3.engine.control.KeyEvent;
import demon.genmo3.engine.sprite.component.state.State;
import demon.genmo3.engine.sprite.component.state.StateList;
import demon.genmo3.engine.sprite.component.state.StateMachine;
import demon.genmo3.engine.sprite.component.state.StateType;
import demon.genmo3.engine.utils.TimerUtils;
import demon.genmo3.engine.sprite.entity.MobEntity;

public class HurtMob extends State
{
    private float delta;

    public HurtMob()
    {
        this.type = StateType.HURT;
        this.level = 4;
    }

    @Override
    public State tryTranslate(StateMachine stateMachine)
    {
        MobEntity mob = (MobEntity) stateMachine.getSprite();
        mob.setNumbness(true);
        delta += TimerUtils.getDelta()*1000;
        if (delta>=800)
        {
            delta = 0;
            mob.setKeyValue(KeyEvent.NONE);
            mob.setNumbness(false);
            if (mob.getAttribute().getHp()<=0)
            {
                stateMachine.setPreState(this.type);
                return StateList.getState(StateType.DEATH,2);
            }
            else
            {
                stateMachine.setPreState(this.type);
                return StateList.getState(StateType.IDLE,2);
            }
        }
        stateMachine.setPreState(this.type);
        return this;
    }
}
