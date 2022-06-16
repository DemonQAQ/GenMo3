package demon.genmo3.engine.utils;

public enum AnimationsID
{
    PLAYER_IDLE("IDLE"),
    PLAYER_RUN("RUN"),
    PLAYER_JUMP("JUMP"),
    PLAYER_JUMPING("JUMPING"),
    PLAYER_FLOATING("FLOATING"),
    PLAYER_ATTACK1("ATTACK1"),
    PLAYER_ATTACK2("ATTACK2"),
    PLAYER_ATTACK3("ATTACK3");

    private final String ID;

    AnimationsID(String ID)
    {
        this.ID = ID;
    }
}
