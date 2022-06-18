package demon.genmo3.engine.sprite.component.combat;

import android.graphics.Canvas;
import android.graphics.Paint;

import demon.genmo3.engine.render.Drawable;
import demon.genmo3.engine.render.DynamicTexture;
import demon.genmo3.engine.render.Texture;

public class EffectTexture implements Drawable
{
    //表示特效渲染的坐标
    private float x;
    private float y;
    private boolean dynamic;
    private boolean direction;
    private Texture texture;
    private DynamicTexture texture1;

    public EffectTexture(Texture texture)
    {
        if (texture instanceof DynamicTexture)
        {
            dynamic = true;
            texture1 = (DynamicTexture) texture;
        } else this.texture = texture;
    }

    public void init(boolean direction)
    {
        if (dynamic) texture1.start();
        this.direction = direction;
    }

    //移动渲染的坐标
    public void move(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public void onDraw(Canvas canvas, Paint p)
    {
        if (dynamic) canvas.drawBitmap(texture1.getImg(direction), x, y, p);
        else canvas.drawBitmap(texture.getImg(direction), x, y, p);
    }
}
