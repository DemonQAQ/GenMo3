package demon.genmo3.engine.sprite.component.state.local;

import demon.genmo3.engine.control.KeyEvent;
import demon.genmo3.engine.sprite.component.state.State;
import demon.genmo3.engine.sprite.component.state.StateList;
import demon.genmo3.engine.sprite.component.state.StateMachine;
import demon.genmo3.engine.sprite.component.state.StateType;
import demon.genmo3.engine.utils.TimerUtils;
import demon.genmo3.game.entity.LocalPlayer;

public class Hurt extends State
{
    private float delta;

    public Hurt()
    {
        this.type = StateType.HURT;
        this.level = 5;
    }

    @Override
    public State tryTranslate(StateMachine stateMachine)
    {
        LocalPlayer player = (LocalPlayer) stateMachine.getSprite();
        player.setNumbness(true);
        delta += TimerUtils.getDelta() * 1000;
        if (delta >= 150)
        {
            delta = 0;
            player.setNumbness(false);
            if (player.getAttribute().getHp() <= 0)
            {
                stateMachine.setPreState(this.type);
                return StateList.getState(StateType.DEATH, 0);
            } else
            {
                stateMachine.setPreState(this.type);
                return StateList.getState(StateType.IDLE, 0);
            }
        }
        stateMachine.setPreState(this.type);
        return this;
    }
}
