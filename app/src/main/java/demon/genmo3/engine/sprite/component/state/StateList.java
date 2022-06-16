package demon.genmo3.engine.sprite.component.state;

import demon.genmo3.engine.sprite.component.state.local.Attack1;
import demon.genmo3.engine.sprite.component.state.local.Attack2;
import demon.genmo3.engine.sprite.component.state.local.Attack3;
import demon.genmo3.engine.sprite.component.state.local.Floating;
import demon.genmo3.engine.sprite.component.state.local.Idle;
import demon.genmo3.engine.sprite.component.state.local.Jump;
import demon.genmo3.engine.sprite.component.state.local.Jumping;
import demon.genmo3.engine.sprite.component.state.local.Run;
import demon.genmo3.engine.sprite.component.state.network.Attack1Network;
import demon.genmo3.engine.sprite.component.state.network.Attack2Network;
import demon.genmo3.engine.sprite.component.state.network.Attack3Network;
import demon.genmo3.engine.sprite.component.state.network.FloatingNetwork;
import demon.genmo3.engine.sprite.component.state.network.IdleNetwork;
import demon.genmo3.engine.sprite.component.state.network.JumpNetwork;
import demon.genmo3.engine.sprite.component.state.network.JumpingNetwork;
import demon.genmo3.engine.sprite.component.state.network.RunNetwork;

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

    private static final IdleNetwork IDLE_NETWORK = new IdleNetwork();
    private static final RunNetwork RUN_NETWORK = new RunNetwork();
    private static final JumpNetwork JUMP_NETWORK = new JumpNetwork();
    private static final JumpingNetwork JUMPING_NETWORK = new JumpingNetwork();
    private static final FloatingNetwork FLOATING_NETWORK = new FloatingNetwork();
    private static final Attack1Network ATTACK1_NETWORK = new Attack1Network();
    private static final Attack2Network ATTACK2_NETWORK = new Attack2Network();
    private static final Attack3Network ATTACK3_NETWORK = new Attack3Network();

    public static State getState(StateType type,boolean network)
    {
        switch (type)
        {
            case IDLE:
                if (network) return IDLE_NETWORK;
                else return IDLE;
            case RUN:
                if (network) return RUN_NETWORK;
                else return RUN;
            case JUMP:
                if (network) return JUMP_NETWORK;
                else return JUMP;
            case JUMPING:
                if (network) return JUMPING_NETWORK;
                else return JUMPING;
            case FLOATING:
                if (network) return FLOATING_NETWORK;
                else return FLOATING;
            case ATTACK1:
                if (network) return ATTACK1_NETWORK;
                else return ATTACK1;
            case ATTACK2:
                if (network) return ATTACK2_NETWORK;
                else return ATTACK2;
            case ATTACK3:
                if (network) return ATTACK3_NETWORK;
                else return ATTACK3;
            default:
                if (network) return IDLE_NETWORK;
                return IDLE;
        }
    }
}
