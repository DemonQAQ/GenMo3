package demon.genmo3.engine.sprite.component.map;

import demon.genmo3.engine.physics.Movable;
import demon.genmo3.engine.render.Drawable;

public interface Building extends Movable, Drawable
{
    void onUpdate(float mX,float mY);
    boolean getContain();
}
