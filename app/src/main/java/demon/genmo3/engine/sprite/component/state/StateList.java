package demon.genmo3.engine.sprite.component.state;

public class StateList
{
    private static final Idle IDLE = new Idle();
    private static final Run RUN = new Run();
    private static final Jump JUMP = new Jump();
    private static final Jumping JUMPING = new Jumping();
    private static final Floating FLOATING = new Floating();
    private static final Attack1 ATTACK1 = new Attack1();
    private static final Attack2 ATTACK2 = new Attack2();
    private static final Attack3 ATTACK3 = new Attack3();


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
            case ATTACK1:
                return ATTACK1;
            case ATTACK2:
                return ATTACK2;
            case ATTACK3:
                return ATTACK3;
            default:
                return IDLE;
        }
    }
}
