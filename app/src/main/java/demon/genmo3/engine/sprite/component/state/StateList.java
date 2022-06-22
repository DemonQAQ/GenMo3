package demon.genmo3.engine.sprite.component.state;

import demon.genmo3.engine.sprite.component.state.local.Attack1;
import demon.genmo3.engine.sprite.component.state.local.Attack2;
import demon.genmo3.engine.sprite.component.state.local.Attack3;
import demon.genmo3.engine.sprite.component.state.local.Death;
import demon.genmo3.engine.sprite.component.state.local.Floating;
import demon.genmo3.engine.sprite.component.state.local.Hurt;
import demon.genmo3.engine.sprite.component.state.local.Idle;
import demon.genmo3.engine.sprite.component.state.local.Jump;
import demon.genmo3.engine.sprite.component.state.local.Jumping;
import demon.genmo3.engine.sprite.component.state.local.Run;
import demon.genmo3.engine.sprite.component.state.local.Skill1;
import demon.genmo3.engine.sprite.component.state.local.Skill2;
import demon.genmo3.engine.sprite.component.state.local.Skill3;
import demon.genmo3.engine.sprite.component.state.local.Skill4;
import demon.genmo3.engine.sprite.component.state.mob.Attack1Mob;
import demon.genmo3.engine.sprite.component.state.mob.DeathMob;
import demon.genmo3.engine.sprite.component.state.mob.FloatingMob;
import demon.genmo3.engine.sprite.component.state.mob.HurtMob;
import demon.genmo3.engine.sprite.component.state.mob.IdleMob;
import demon.genmo3.engine.sprite.component.state.mob.JumpMob;
import demon.genmo3.engine.sprite.component.state.mob.RunMob;
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
    private static final Skill1 SKILL1 = new Skill1();
    private static final Skill2 SKILL2 = new Skill2();
    private static final Skill3 SKILL3 = new Skill3();
    private static final Skill4 SKILL4 = new Skill4();
    private static final Hurt HURT = new Hurt();
    private static final Death DEATH = new Death();


    //todo 暂时使用玩家的idle给mob
    //type: 0(本地玩家),1(网络玩家),2(怪物)
    public static State getState(StateType state, int type)
    {
        switch (state)
        {
            case IDLE:
                if (type == 2) return new IdleMob();
                else if (type == 1) return new IdleNetwork();
                else return IDLE;
            case RUN:
                if (type == 2) return new RunMob();
                else if (type == 1) return new RunNetwork();
                else return RUN;
            case JUMP:
                if (type == 2) return new JumpMob();
                else if (type == 1) return new JumpNetwork();
                else return JUMP;
            case JUMPING:
                if (type == 2) return new JumpMob();
                else if (type == 1) return new JumpingNetwork();
                else return JUMPING;
            case FLOATING:
                if (type == 2) return new FloatingMob();
                else if (type == 1) return new FloatingNetwork();
                else return FLOATING;
            case ATTACK1:
                if (type == 2) return new Attack1Mob();
                else if (type == 1) return new Attack1Network();
                else return ATTACK1;
            case ATTACK2:
                if (type == 2) return new Attack1Mob();
                else if (type == 1) return new Attack2Network();
                else return ATTACK2;
            case ATTACK3:
                if (type == 2) return new Attack1Mob();
                else if (type == 1) return new Attack3Network();
                else return ATTACK3;
            case SKILL1:
                if (type == 2) return new Attack1Mob();
                else if (type == 1) return new Attack3Network();
                else return SKILL1;
            case SKILL2:
                if (type == 2) return new Attack1Mob();
                else if (type == 1) return new Attack3Network();
                else return SKILL2;
            case SKILL3:
                if (type == 2) return new Attack1Mob();
                else if (type == 1) return new Attack3Network();
                else return SKILL3;
            case SKILL4:
                if (type == 2) return new Attack1Mob();
                else if (type == 1) return new Attack3Network();
                else return SKILL4;
            case HURT:
                if (type == 2) return new HurtMob();
                else if (type == 1) return new Attack3Network();
                else return HURT;
            case DEATH:
                if (type == 2) return new DeathMob();
                else if (type == 1) return new Attack3Network();
                else return DEATH;
            default:
                if (type == 2) return new IdleMob();
                else if (type == 1) return new IdleNetwork();
                else return IDLE;
        }
    }
}
