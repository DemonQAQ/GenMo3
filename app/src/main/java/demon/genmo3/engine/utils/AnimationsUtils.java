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

    private static final HashMap<AnimationsID, DynamicTexture> IMG = new HashMap<>();

    public static void init()
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

        IMG.put(AnimationsID.PLAYER_IDLE, PLAYER_IDLE);
        IMG.put(AnimationsID.PLAYER_RUN, PLAYER_RUN);
        IMG.put(AnimationsID.PLAYER_JUMPING, PLAYER_JUMPING);
        IMG.put(AnimationsID.PLAYER_FLOATING, PLAYER_FLOATING);
        IMG.put(AnimationsID.PLAYER_JUMP, PLAYER_JUMP);
        IMG.put(AnimationsID.PLAYER_ATTACK1, PLAYER_ATTACK1);
        IMG.put(AnimationsID.PLAYER_ATTACK2, PLAYER_ATTACK2);
        IMG.put(AnimationsID.PLAYER_ATTACK3, PLAYER_ATTACK3);
    }

    public static DynamicTexture getAnimation(String key)
    {
        return IMG.get(findByStr(key));
    }

    private static AnimationsID findByStr(String key)
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

            default:
                return AnimationsID.PLAYER_IDLE;
        }
    }

}
