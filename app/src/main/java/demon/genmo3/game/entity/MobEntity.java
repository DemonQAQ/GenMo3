package demon.genmo3.game.entity;

import demon.genmo3.engine.render.Texture;
import demon.genmo3.engine.sprite.EntitySprite;

public class MobEntity extends EntitySprite
{
    public MobEntity(float x, float y, Texture texture, float width, float height, boolean network)
    {
        super(x, y, texture, width, height, 2);
    }

    @Override
    public void speedEvent()
    {

    }
}
