package demon.genmo3.engine.sprite.component.combat;

import android.util.Log;

import demon.genmo3.engine.render.Texture;
import demon.genmo3.engine.sprite.EntitySprite;
import demon.genmo3.engine.sprite.component.CollisionBox;

public class DamageArea
{
    public float damage;
    public float duration;
    public int times;
    public CollisionBox area;
    public EntitySprite damageSource;

    public DamageArea(int x, int y, int width, int height, float damage, float duration, int times,EntitySprite sprite)
    {
        this.damage = damage;
        this.duration = duration;
        this.times = times;
        this.area = new CollisionBox(x, y, width, height);
        this.damageSource = sprite;
    }

    //todo clear
    public void move(float x, float y)
    {
        this.area.x = x;
        this.area.y = y;
        //Log.i("伤害区域坐标", "(" + this.area.x + "," + this.area.y + ")");
    }
}
