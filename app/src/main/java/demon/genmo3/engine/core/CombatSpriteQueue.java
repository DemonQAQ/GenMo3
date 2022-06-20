package demon.genmo3.engine.core;

import android.util.Log;

import java.util.ArrayList;

import demon.genmo3.engine.sprite.component.combat.Combat;

public class CombatSpriteQueue
{
    private static final ArrayList<Combat> COMBATS_LIST = new ArrayList<>();

    public void onCombat()
    {
        Combat[] combat = COMBATS_LIST.toArray(new Combat[0]);
        for (Combat value : combat) value.checkEnd();
        //刷新技能的伤害区域和特效区域
        COMBATS_LIST.forEach(Combat::move);
        //伤害判定
        Combat[] combats = COMBATS_LIST.toArray(new Combat[0]);
        for (int i=0;i<combats.length;i++)
        {
            for (int j=0;j<combats.length;j++)
            {
                if (combats[i].intersect(combats[j].getCollisionBox()))combats[i].damage(combats[j]);
            }
        }
        combat = COMBATS_LIST.toArray(new Combat[0]);
        for (Combat value : combat) if (value.isDeath())value.death();
    }

    public void add(Combat e)
    {
        if (!COMBATS_LIST.contains(e))COMBATS_LIST.add(e);
    }

    public void remove(Combat e)
    {
        COMBATS_LIST.remove(e);
    }

}
