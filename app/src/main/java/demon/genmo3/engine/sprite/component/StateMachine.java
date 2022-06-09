package demon.genmo3.engine.sprite.component;

import demon.genmo3.engine.sprite.EntitySprite;
import demon.genmo3.engine.sprite.component.state.State;
import demon.genmo3.engine.sprite.component.state.StateList;
import demon.genmo3.engine.sprite.component.state.StateType;

/*
 *  根据输入控制状态的有限状态机
 * */
public class StateMachine
{
    private EntitySprite sprite;
    private StateType state;
    private StateType preState;
    private State state_;
    //true代表左边，false代表右边
    private boolean direction;
    private boolean onGround = true;
    private boolean translate = false;

    public StateMachine(EntitySprite sprite)
    {
        this.state = StateType.IDLE;
        this.preState = state;
        this.sprite = sprite;
        this.state_ = StateList.getState(state);
    }

    //Sprite析构时解除对其的引用
    public void destroy()
    {
        sprite = null;
    }

    public void update()
    {
        state_ = state_.tryTranslate(this);
    }

    public String getState()
    {
        return state_.getState();
    }

    public boolean getDirection()
    {
        return this.direction;
    }

    public void setState(StateType state)
    {
        this.state = state;
    }

    public StateType getPreState()
    {
        return preState;
    }

    public void setPreState(StateType preState)
    {
        this.preState = preState;
    }

    public void setState_(State state_)
    {
        this.state_ = state_;
    }

    public void setDirection(boolean direction)
    {
        this.direction = direction;
    }

    public void setOnGround(boolean onGround)
    {
        this.onGround = onGround;
    }

    public boolean isOnGround()
    {
        return this.onGround;
    }

    public EntitySprite getSprite()
    {
        return this.sprite;
    }

    public void setTranslate(boolean flag)
    {
        this.translate = flag;
    }
    public boolean isTranslate()
    {
        return this.translate;
    }
}
