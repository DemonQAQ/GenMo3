package demon.genmo3.engine.sprite.component.map;

import demon.genmo3.engine.physics.Movable;
import demon.genmo3.engine.render.Texture;
import demon.genmo3.engine.sprite.EntitySprite;

public class WallSprite extends GroundSprite
{
    public WallSprite(Texture bg, int x, int y, int width, int height)
    {
        super(bg, x, y, width, height);
    }

    @Override
    public boolean intersect(Movable e)
    {
        return this.getCollisionBox().checkIntersect(e.getCollisionBox());
    }

    @Override
    public void onIntersect(Movable e)
    {
        if (this.getCollisionBox().checkAboveIntersect(e.getCollisionBox()))
        {
            if (e.getCollisionBox().y + e.getCollisionBox().height < this.getCollisionBox().y + this.getCollisionBox().height * 0.5f)
                if (e.getCollisionBox().x + e.getCollisionBox().width > this.getCollisionBox().x + (0.1f * e.getCollisionBox().width) && e.getCollisionBox().x < this.getCollisionBox().x + this.getCollisionBox().width - (0.1f * e.getCollisionBox().width))
                    super.onIntersect(e);
        }
        if (this.getCollisionBox().checkSideIntersect(e.getCollisionBox()))
        {
            if (e instanceof EntitySprite)
            {
                EntitySprite sprite = (EntitySprite) e;
                sprite.setXSpeed(0);
                sprite.setXAccelerate(0);
                if (sprite.getCollisionBox().x < this.getCollisionBox().x)
                    sprite.setXOnWall(this.getCollisionBox().x, true);
                else
                    sprite.setXOnWall(this.getCollisionBox().x + this.getCollisionBox().width, false);
            }
        }
        if (this.getCollisionBox().checkBelowIntersect(e.getCollisionBox()))
        {
            if (e.getCollisionBox().x + e.getCollisionBox().width > this.getCollisionBox().x + 50 && e.getCollisionBox().x < this.getCollisionBox().x + this.getCollisionBox().width - 50)
                if (e instanceof EntitySprite)
                {
                    EntitySprite sprite = (EntitySprite) e;
                    sprite.setYSpeed(10);
                }
        }
    }
}
