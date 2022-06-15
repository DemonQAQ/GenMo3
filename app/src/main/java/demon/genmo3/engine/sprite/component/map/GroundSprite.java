package demon.genmo3.engine.sprite.component.map;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import demon.genmo3.engine.core.GameEngine;
import demon.genmo3.engine.physics.Gravity;
import demon.genmo3.engine.physics.Movable;
import demon.genmo3.engine.render.Drawable;
import demon.genmo3.engine.render.DynamicTexture;
import demon.genmo3.engine.render.Texture;
import demon.genmo3.engine.sprite.EntitySprite;
import demon.genmo3.engine.sprite.Sprite;
import demon.genmo3.engine.sprite.component.CollisionBox;
import demon.genmo3.engine.utils.ValueUtils;

/*
* X,Y代表当前渲染坐标
* 地图内坐标储存在mapSprite内
* */
public class GroundSprite extends Sprite implements Building
{
    private final boolean left = false;
    private boolean contain = true;
    private final boolean dynamic;
    private final float rX;
    private final float rY;
    private Texture background;
    private DynamicTexture backgroundD;
    private final CollisionBox collisionBox;

    public GroundSprite(Texture bg, int x, int y, int width, int height)
    {
        dynamic = bg instanceof DynamicTexture;
        if (dynamic)
        {
            backgroundD = (DynamicTexture) bg;
            backgroundD.start();
        }
        else background = bg;
        this.rX = x;
        this.rY = y;
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
        this.collisionBox.x = getX();
        this.collisionBox.y = getY();
    }


    @Override
    public boolean intersect(Movable e)
    {
        if (getCollisionBox().checkIntersect(e.getCollisionBox())) return getCollisionBox().checkAboveIntersect(e.getCollisionBox());
        return false;
    }

    @Override
    public void onIntersect(Movable e)
    {
        if (e instanceof Gravity)
        {
            Gravity sprite = (Gravity) e;
            if (!sprite.isOnGround())
            {
                sprite.setOnGround(true);
                sprite.setYOnGround(this.getCollisionBox().y);
            }
        }
    }

    @Override
    public void setXOnWall(float x,boolean left)
    {

    }

    @Override
    public CollisionBox getCollisionBox()
    {
        return this.collisionBox;
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

    @Override
    public void onUpdate(float mX, float mY)
    {
        setX(rX - mX);
        setY(rY - mY);
        contain = !(getX() + getCollisionBox().width < 0) && !(getX() > ValueUtils.SCREEN_WIDTH);
        if (getY() + getCollisionBox().height < 0 || getY() > ValueUtils.SCREEN_HEIGHT)contain = false;
    }

    @Override
    public boolean getContain()
    {
        return contain;
    }
}
