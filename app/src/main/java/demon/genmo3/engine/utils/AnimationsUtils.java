package demon.genmo3.engine.utils;

import java.util.HashMap;

import demon.genmo3.R;
import demon.genmo3.engine.render.DynamicTexture;

public class AnimationsUtils
{
    private static DynamicTexture PLAYER_IDLE;
    private static DynamicTexture PLAYER_RUN;
    private static DynamicTexture PLAYER_JUMPING;
    private static DynamicTexture PLAYER_FLOATING;

    private static final HashMap<AnimationsID,DynamicTexture> IMG = new HashMap<>();

    public static void init()
    {
        PLAYER_IDLE = TextureUtils.getDynamicTexture(R.drawable.idle,4,1,4,100,true);
        PLAYER_RUN = TextureUtils.getDynamicTexture(R.drawable.run,6,1,6,100,true);
        PLAYER_JUMPING = TextureUtils.getDynamicTexture(R.drawable.jumping,4,1,4,100,true);
        PLAYER_FLOATING = TextureUtils.getDynamicTexture(R.drawable.floating,2,1,2,100,true);

        IMG.put(AnimationsID.PLAYER_IDLE,PLAYER_IDLE);
        IMG.put(AnimationsID.PLAYER_RUN,PLAYER_RUN);
        IMG.put(AnimationsID.PLAYER_JUMPING,PLAYER_JUMPING);
        IMG.put(AnimationsID.PLAYER_FLOATING,PLAYER_FLOATING);
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
            case "JUMPING":
                return AnimationsID.PLAYER_JUMPING;
            case "FLOATING":
                return AnimationsID.PLAYER_FLOATING;
            default:
                return AnimationsID.PLAYER_IDLE;
        }
    }

}
