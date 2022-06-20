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
    PLAYER_ATTACK3("ATTACK3"),
    MOB_IDLE("MOB_IDLE"),
    MOB_RUN("MOB_RUN"),
    MOB_JUMP("MOB_JUMP"),
    MOB_JUMPING("MOB_JUMPING"),
    MOB_FLOATING("MOB_FLOATING"),
    MOB_ATTACK1("MOB_ATTACK1"),
    MOB_ATTACK2("MOB_ATTACK2"),
    MOB_ATTACK3("MOB_ATTACK3");

    private final String ID;

    AnimationsID(String ID)
    {
        this.ID = ID;
    }
}
