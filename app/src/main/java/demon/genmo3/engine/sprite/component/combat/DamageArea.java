package demon.genmo3.engine.sprite.component.combat;

import demon.genmo3.engine.render.Texture;
import demon.genmo3.engine.sprite.component.CollisionBox;

public class DamageArea
{
    public float damage;
    public float duration;
    public int times;
    public CollisionBox area;

    public DamageArea(int x, int y, int width, int height, float damage, float duration, int times)
    {
        this.damage = damage;
        this.duration = duration;
        this.times = times;
        this.area = new CollisionBox(x, y, width, height);
    }

    public void move(float x, float y)
    {
        this.area.x = x;
        this.area.y = y;
    }
}
