package demon.genmo3.engine.sprite.component.state;

import android.util.Log;

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
    private StateType preState;
    private State state_;
    //true代表左边，false代表右边
    private boolean direction;
    private boolean numbness;
    private boolean onGround = true;
    private boolean translate = false;
    private boolean death;

    //type: 0(本地玩家),1(网络玩家),2(怪物)
    public StateMachine(EntitySprite sprite,int type)
    {
        this.preState = StateType.IDLE;
        this.state_ = StateList.getState(StateType.IDLE,type);
        this.sprite = sprite;
    }

    //Sprite析构时解除对其的引用
    public void destroy()
    {
        sprite = null;
    }

    public void update()
    {
        state_ = state_.tryTranslate(this);
        translate = !preState.getState().equals(state_.getState());
    }

    public String getState()
    {
        return state_.getState();
    }

    public boolean getDirection()
    {
        return this.direction;
    }

    public void setState(State state_)
    {
        this.state_ = state_;
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

    public boolean isNumbness()
    {
        return numbness;
    }

    public void setNumbness(boolean numbness)
    {
        this.numbness = numbness;
    }

    public boolean isDeath()
    {
        return death;
    }

    public void setDeath(boolean death)
    {
        this.death = death;
    }
}
