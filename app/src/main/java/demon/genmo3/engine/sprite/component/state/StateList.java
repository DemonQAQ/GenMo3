package demon.genmo3.engine.sprite.component.state;

public class StateList
{
    private static final Idle IDLE = new Idle();
    private static final Run RUN = new Run();
    private static final Jump JUMP = new Jump();
    private static final Jumping JUMPING = new Jumping();
    private static final Floating FLOATING = new Floating();


    public static State getState(StateType type)
    {
        switch (type)
        {
            case IDLE:
                return IDLE;
            case RUN:
                return RUN;
            case JUMP:
                return JUMP;
            case JUMPING:
                return JUMPING;
            case FLOATING:
                return FLOATING;
            default:
                return IDLE;
        }
    }
}
