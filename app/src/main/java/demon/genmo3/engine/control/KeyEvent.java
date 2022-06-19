package demon.genmo3.engine.control;

import android.graphics.BitmapFactory;
import android.view.View;

import demon.genmo3.R;
import demon.genmo3.engine.sprite.EntitySprite;
import demon.genmo3.engine.utils.MapUtils;

/*
 * 按键内部id
 * */
public abstract class KeyEvent
{
    public final static int LEFT = 10001;
    public final static int RIGHT = 10002;
    public final static int JUMP = 10003;
    public final static int ATTACK = 10004;
    public final static int SKILL1 = 10005;
    public final static int SKILL2 = 10006;
    public final static int SKILL3 = 10007;
    public final static int SKILL4 = 10008;
    public final static int ITEM = 10009;
    public final static int INVENTORY = 10010;

    public static void findResById(View view, int keyValue, boolean down)
    {
        if (keyValue >= 10005 && keyValue <= 10009) skillKey(view, keyValue, down);
        else normalKey(view, keyValue, down);
    }

    private static void normalKey(View view, int keyValue, boolean down)
    {
        switch (keyValue)
        {
            case 10001:
                if (down) view.setBackgroundResource(R.drawable.left_down);
                else view.setBackgroundResource(R.drawable.left_up);
                break;
            case 10002:
                if (down) view.setBackgroundResource(R.drawable.right_down);
                else view.setBackgroundResource(R.drawable.right_up);
                break;
            case 10003:
                if (down) view.setBackgroundResource(R.drawable.jump_down);
                else view.setBackgroundResource(R.drawable.jump_up);
                break;
            case 10004:
                if (down) view.setBackgroundResource(R.drawable.attack_down);
                else view.setBackgroundResource(R.drawable.attack_up);
                break;
            default:
                view.setBackgroundResource(R.drawable.item_null);
        }
    }

    private static void skillKey(View view, int keyValue, boolean down)
    {
        EntitySprite player = MapUtils.getPlayer();
        if (player == null) return;
        switch (keyValue)
        {
            case 10005:
                if (player.getSkill1() != null)
                {

                    if (down)
                    {
                        player.cast(1);
                        view.setBackgroundResource(player.getSkill1().icon_up > 0 ? player.getSkill1().icon_down : R.drawable.item_null);
                    } else
                        view.setBackgroundResource(player.getSkill1().icon_up > 0 ? player.getSkill1().icon_up : R.drawable.item_null);
                } else view.setBackgroundResource(R.drawable.item_null);
                break;
            case 10006:
                if (player.getSkill2() != null)
                {
                    if (down)
                    {
                        player.cast(2);
                        view.setBackgroundResource(player.getSkill2().icon_up > 0 ? player.getSkill2().icon_down : R.drawable.item_null);
                    } else
                        view.setBackgroundResource(player.getSkill2().icon_up > 0 ? player.getSkill2().icon_up : R.drawable.item_null);
                } else view.setBackgroundResource(R.drawable.item_null);
                break;
            case 10007:
                if (player.getSkill3() != null)
                {
                    if (down)
                    {
                        player.cast(3);
                        view.setBackgroundResource(player.getSkill3().icon_up > 0 ? player.getSkill3().icon_down : R.drawable.item_null);
                    } else
                        view.setBackgroundResource(player.getSkill3().icon_up > 0 ? player.getSkill3().icon_up : R.drawable.item_null);
                } else view.setBackgroundResource(R.drawable.item_null);
                break;
            case 10008:
                if (player.getSkill4() != null)
                {
                    if (down)
                    {
                        player.cast(4);
                        view.setBackgroundResource(player.getSkill4().icon_up > 0 ? player.getSkill4().icon_down : R.drawable.item_null);
                    } else
                        view.setBackgroundResource(player.getSkill4().icon_up > 0 ? player.getSkill4().icon_up : R.drawable.item_null);
                } else view.setBackgroundResource(R.drawable.item_null);
                break;
            default:
                view.setBackgroundResource(R.drawable.item_null);
        }
    }
}
