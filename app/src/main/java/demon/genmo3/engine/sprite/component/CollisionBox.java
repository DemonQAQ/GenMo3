package demon.genmo3.engine.sprite.component;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import demon.genmo3.engine.render.Drawable;

/*
 * 以一个矩形区域表示实体的体积
 * */
public class CollisionBox implements Drawable
{
    public float x;
    public float y;
    public float width;
    public float height;

    public CollisionBox(float x, float y, float width, float height)
    {
        setLocation(x, y);
        setArea(width, height);
    }

    public void setLocation(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public void setArea(float width, float height)
    {
        this.width = width;
        this.height = height;
    }

    public boolean checkIntersect(CollisionBox e)
    {
        return Math.max(this.x, e.x) <= Math.min(this.x + this.width, e.x + e.width) && Math.max(this.y, e.y) <= Math.min(this.y + this.height, e.y + e.height);
    }

    public boolean checkAboveIntersect(CollisionBox e)
    {
        return e.y + e.height <= this.y + this.height;
    }

    public boolean checkSideIntersect(CollisionBox e)
    {
        if (e.y + 0.5f * e.height > this.y & e.y < this.y + this.height)
        {
            return e.x + e.width < this.x + 0.5f * e.width || e.x > this.x + this.width - 0.5f * e.width;
        }
        return false;
    }

    public boolean checkBelowIntersect(CollisionBox e)
    {
        if (e.x + 0.75f * e.width < this.x || e.x > this.x + this.width) return false;
        return (this.y + this.height > e.y + (0.5f * e.height));
    }

    @Override
    public void onDraw(Canvas canvas, Paint p)
    {
        Paint paint = new Paint();
        paint.setARGB(50, 255, 44, 44);
        canvas.drawRect(x, y, x + width, y + height, paint);
    }
}
