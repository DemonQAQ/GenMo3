package demon.genmo3.engine.control;

import android.view.View;

import demon.genmo3.R;

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

    public static void findResById(View view, int keyValue,boolean down)
    {
        switch (keyValue)
        {
            case 10001:
                if (down)view.setBackgroundResource(R.drawable.left_down);
                else view.setBackgroundResource(R.drawable.left_up);
                break;
            case 10002:
                if (down)view.setBackgroundResource(R.drawable.right_down);
                else view.setBackgroundResource(R.drawable.right_up);
                break;
            case 10003:
                if (down)view.setBackgroundResource(R.drawable.jump_down);
                else view.setBackgroundResource(R.drawable.jump_up);
                break;
            case 10004:
                if (down)view.setBackgroundResource(R.drawable.attack_down);
                else view.setBackgroundResource(R.drawable.attack_up);
                break;
            default:
                 view.setBackgroundResource(R.drawable.item_null);
        }
    }
}
