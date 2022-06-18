package demon.genmo3.engine.core;

import java.util.ArrayList;

import demon.genmo3.engine.sprite.component.combat.Combat;

public class CombatSpriteQueue
{
    private static final ArrayList<Combat> COMBATS_LIST = new ArrayList<>();

    public void onCombat()
    {
        //刷新技能的伤害区域和特效区域
        COMBATS_LIST.forEach(Combat::move);
        //伤害判定
        Combat[] combats = COMBATS_LIST.toArray(new Combat[0]);
        COMBATS_LIST.forEach(e->
        {
            for (Combat e_:combats)
            {
                if (e.intersect(e_.getCollisionBox()))e.damage(e_);
            }
        });
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
