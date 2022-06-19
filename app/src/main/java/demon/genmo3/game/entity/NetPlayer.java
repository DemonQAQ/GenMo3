package demon.genmo3.game.entity;

import demon.genmo3.engine.render.Texture;
import demon.genmo3.engine.sprite.EntitySprite;

public class NetPlayer extends EntitySprite
{
    public NetPlayer(float x, float y, Texture texture, float width, float height)
    {
        super(x, y, texture, width, height,1);
    }
}
