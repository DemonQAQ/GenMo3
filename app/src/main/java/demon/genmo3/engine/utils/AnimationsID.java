package demon.genmo3.engine.utils;

public enum AnimationsID
{
    PLAYER_IDLE("IDLE"),
    PLAYER_RUN("RUN"),
    PLAYER_JUMPING("JUMPING"),
    PLAYER_FLOATING("FLOATING");

    private final String ID;

    AnimationsID(String ID)
    {
        this.ID = ID;
    }
}
