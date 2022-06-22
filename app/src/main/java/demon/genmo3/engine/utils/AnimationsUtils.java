package demon.genmo3.engine.utils;

import java.util.HashMap;

import demon.genmo3.R;
import demon.genmo3.engine.render.DynamicTexture;

public class AnimationsUtils
{
    private static DynamicTexture PLAYER_IDLE;
    private static DynamicTexture PLAYER_RUN;
    private static DynamicTexture PLAYER_JUMPING;
    private static DynamicTexture PLAYER_JUMP;
    private static DynamicTexture PLAYER_FLOATING;
    private static DynamicTexture PLAYER_ATTACK1;
    private static DynamicTexture PLAYER_ATTACK2;
    private static DynamicTexture PLAYER_ATTACK3;
    private static DynamicTexture PLAYER_CAST;
    private static DynamicTexture PLAYER_HURT;
    private static DynamicTexture PLAYER_DIE;
    private static DynamicTexture PLAYER_SKILL1;
    private static DynamicTexture PLAYER_SKILL2;
    private static DynamicTexture PLAYER_SKILL3;
    private static DynamicTexture PLAYER_SKILL4;

    private static DynamicTexture GOLEM_IDLE;
    private static DynamicTexture GOLEM_RUN;
    private static DynamicTexture GOLEM_ATTACK;
    private static DynamicTexture GOLEM_HURT;

    private static final HashMap<AnimationsID, DynamicTexture> IMG = new HashMap<>();
    //每个怪物对应一个hashmap
    private static final HashMap<AnimationsID, DynamicTexture> MOB_0 = new HashMap<>();

    public static void init()
    {
        initPlayer();
        initMob();
    }

    private static void initPlayer()
    {
        PLAYER_IDLE = TextureUtils.getDynamicTexture(R.drawable.idle, 4, 1, 4, 100, true);
        PLAYER_RUN = TextureUtils.getDynamicTexture(R.drawable.run, 6, 1, 6, 50, true);
        PLAYER_JUMP = TextureUtils.getDynamicTexture(R.drawable.jump, 4, 1, 4, 50, true);
        PLAYER_JUMPING = TextureUtils.getDynamicTexture(R.drawable.jumping, 2, 1, 2, 50, true);
        PLAYER_FLOATING = TextureUtils.getDynamicTexture(R.drawable.floating, 2, 1, 2, 50, true);
        PLAYER_ATTACK1 = TextureUtils.getDynamicTexture(R.drawable.attack1, 5, 1, 5, 50, true);
        ValueUtils.setAttack1Time(250);
        PLAYER_ATTACK2 = TextureUtils.getDynamicTexture(R.drawable.attack2, 3, 2, 6, 50, true);
        ValueUtils.setAttack2Time(300);
        PLAYER_ATTACK3 = TextureUtils.getDynamicTexture(R.drawable.attack3, 3, 2, 6, 50, true);
        ValueUtils.setAttack3Time(300);
        PLAYER_CAST = TextureUtils.getDynamicTexture(R.drawable.cast, 4, 1, 4, 75, true);
        ValueUtils.setCastTime(300);
        PLAYER_HURT = TextureUtils.getDynamicTexture(R.drawable.hurt, 3, 1, 3, 50, true);
        PLAYER_DIE = TextureUtils.getDynamicTexture(R.drawable.hurt, 4, 2, 7, 100, true);

        IMG.put(AnimationsID.PLAYER_IDLE, PLAYER_IDLE);
        IMG.put(AnimationsID.PLAYER_RUN, PLAYER_RUN);
        IMG.put(AnimationsID.PLAYER_JUMPING, PLAYER_JUMPING);
        IMG.put(AnimationsID.PLAYER_FLOATING, PLAYER_FLOATING);
        IMG.put(AnimationsID.PLAYER_JUMP, PLAYER_JUMP);
        IMG.put(AnimationsID.PLAYER_ATTACK1, PLAYER_ATTACK1);
        IMG.put(AnimationsID.PLAYER_ATTACK2, PLAYER_ATTACK2);
        IMG.put(AnimationsID.PLAYER_ATTACK3, PLAYER_ATTACK3);
        IMG.put(AnimationsID.PLAYER_HURT, PLAYER_HURT);
        IMG.put(AnimationsID.PLAYER_CAST, PLAYER_CAST);
        IMG.put(AnimationsID.PLAYER_DEATH, PLAYER_DIE);
    }

    private static void initMob()
    {
        GOLEM_IDLE = TextureUtils.getDynamicTexture(R.drawable.golemidle, 3, 3, 9, 100, true);
        GOLEM_ATTACK = TextureUtils.getDynamicTexture(R.drawable.golemattack, 4, 5, 19, 100, true);
        GOLEM_HURT = TextureUtils.getDynamicTexture(R.drawable.golemhurt, 4, 2, 8, 100, true);
        GOLEM_RUN = TextureUtils.getDynamicTexture(R.drawable.golemrun, 4, 4, 14, 100, true);

        MOB_0.put(AnimationsID.MOB_IDLE, GOLEM_IDLE);
        MOB_0.put(AnimationsID.MOB_ATTACK1, GOLEM_ATTACK);
        MOB_0.put(AnimationsID.MOB_RUN, GOLEM_RUN);
        MOB_0.put(AnimationsID.MOB_HURT, GOLEM_HURT);
    }

    public static DynamicTexture getAnimation(String key)
    {
        return IMG.get(findByStr(key));
    }

    public static DynamicTexture getAnimationMob(String key, int mobID)
    {
        switch (mobID)
        {
            case 0:
                return MOB_0.get(findByStrMob(key));
            default:
                return MOB_0.get(findByStrMob(key));
        }

    }

    private static AnimationsID findByStrMob(String key)
    {
        switch (key)
        {
            case "IDLE":
                return AnimationsID.MOB_IDLE;
            case "RUN":
                return AnimationsID.MOB_RUN;
            case "JUMP":
                return AnimationsID.MOB_JUMP;
            case "JUMPING":
                return AnimationsID.MOB_JUMPING;
            case "FLOATING":
                return AnimationsID.MOB_FLOATING;
            case "ATTACK1":
                return AnimationsID.MOB_ATTACK1;
            case "ATTACK2":
                return AnimationsID.MOB_ATTACK2;
            case "ATTACK3":
                return AnimationsID.MOB_ATTACK3;
            case "HURT":
                return AnimationsID.MOB_HURT;
            default:
                return AnimationsID.MOB_IDLE;
        }
    }

    private static AnimationsID findByStr(String key)
    {
        if (key.contains("SKILL"))
        {
            switch (key)
            {
                case "SKILL1":
                    if (PLAYER_SKILL1 != null) return AnimationsID.PLAYER_SKILL1;
                    else return AnimationsID.PLAYER_CAST;
                case "SKILL2":
                    if (PLAYER_SKILL2 != null) return AnimationsID.PLAYER_SKILL2;
                    else return AnimationsID.PLAYER_CAST;
                case "SKILL3":
                    if (PLAYER_SKILL3 != null) return AnimationsID.PLAYER_SKILL3;
                    else return AnimationsID.PLAYER_CAST;
                case "SKILL4":
                    if (PLAYER_SKILL4 != null) return AnimationsID.PLAYER_SKILL4;
                    else return AnimationsID.PLAYER_CAST;
                default:
                    return AnimationsID.PLAYER_CAST;
            }
        } else
        {
            switch (key)
            {
                case "IDLE":
                    return AnimationsID.PLAYER_IDLE;
                case "RUN":
                    return AnimationsID.PLAYER_RUN;
                case "JUMP":
                    return AnimationsID.PLAYER_JUMP;
                case "JUMPING":
                    return AnimationsID.PLAYER_JUMPING;
                case "FLOATING":
                    return AnimationsID.PLAYER_FLOATING;
                case "ATTACK1":
                    return AnimationsID.PLAYER_ATTACK1;
                case "ATTACK2":
                    return AnimationsID.PLAYER_ATTACK2;
                case "ATTACK3":
                    return AnimationsID.PLAYER_ATTACK3;
                case "HURT":
                    return AnimationsID.PLAYER_HURT;
                case "CAST":
                    return AnimationsID.PLAYER_CAST;
                case "DEATH":
                    return AnimationsID.PLAYER_DEATH;
                default:
                    return AnimationsID.PLAYER_IDLE;
            }
        }
    }

}
