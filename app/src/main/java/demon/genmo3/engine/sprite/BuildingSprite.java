package demon.genmo3.engine.sprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import demon.genmo3.engine.physics.Movable;
import demon.genmo3.engine.render.Drawable;
import demon.genmo3.engine.render.DynamicTexture;
import demon.genmo3.engine.render.Texture;
import demon.genmo3.engine.sprite.component.CollisionBox;

/*
* X,Y代表当前渲染坐标
* 地图内坐标储存在mapSprite内
* */
public class BuildingSprite extends Sprite implements Drawable, Movable
{
    private boolean left = false;
    private boolean dynamic;
    private Texture background;
    private DynamicTexture backgroundD;
    private CollisionBox collisionBox;

    public BuildingSprite(Texture bg,int x,int y, int width, int height)
    {
        dynamic = bg instanceof DynamicTexture;
        if (dynamic)
        {
            backgroundD = (DynamicTexture) bg;
            backgroundD.start();
        }
        else background = bg;
        collisionBox = new CollisionBox(x,y,width,height);
    }

    @Override
    public void onDraw(Canvas canvas, Paint p)
    {
        canvas.drawBitmap(getImage(),getX(),getY(),p);
    }

    @Override
    public void move()
    {

    }


    @Override
    public boolean intersect(Movable e)
    {
        return false;
    }

    @Override
    public void onIntersect(Movable e)
    {

    }

    private Bitmap getImage()
    {
        if (dynamic)
        {
           return backgroundD.getImg(left);
        }
        else
        {
            return background.getImg(left);
        }
    }
}
