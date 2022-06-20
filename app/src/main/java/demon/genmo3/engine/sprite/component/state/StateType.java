package demon.genmo3.engine.sprite.component.state;

public enum StateType
{
    IDLE("IDLE"),
    JUMP("JUMP"),
    JUMPING("JUMPING"),
    FLOATING("FLOATING"),
    DEATH("DEATH"),
    RUN("RUN"),
    ATTACK1("ATTACK1"),
    ATTACK2("ATTACK2"),
    ATTACK3("ATTACK3"),
    ATTACKED("ATTACKED"),
    SKILL1("SKILL1"),
    SKILL2("SKILL2"),
    SKILL3("SKILL3"),
    SKILL4("SKILL4"),
    HURT("HURT"),
    ITEM("ITEM");

    private final String state;

    StateType(String state)
    {
        this.state = state;
    }

    public String getState()
    {
        return state;
    }
}
