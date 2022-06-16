package demon.genmo3.engine.sprite.component.state;

public abstract class State
{
    public StateType type;
    public int level;

    public State tryTranslate(StateMachine stateMachine)
    {
        return this;
    }

    public String getState()
    {
        return type.getState();
    }

}
