package demon.genmo3.engine.sprite.component;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

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
        float maxLeft = 0;
        float maxTop = 0;
        float minRight = 0;
        float minBottom = 0;

        maxLeft = Math.max(this.x, e.x);
        maxTop = Math.max(this.x, e.x);
        minRight = Math.min(this.x + this.width, e.x + e.width);
        minBottom = Math.min(this.y + this.height, e.y + e.height);

        return maxLeft > minRight || maxTop > minBottom;
    }

    public boolean checkAboveIntersect(CollisionBox e)
    {
        if (this.x + this.width > e.x && e.x + e.width < this.x)
        {
            return this.y < e.y && this.y + this.height > e.y;
        }
        return false;
    }

    public boolean checkSideIntersect(CollisionBox e)
    {
        if (e.y < this.y && this.y < e.y + e.height)
        {
            //左边相交
            if (this.x < e.x)
            {
                return this.x + this.width > e.x;
            }
            //右边相交
            if (e.x < this.x)
            {
                return this.x < e.x + e.width;
            }
        }
        return false;
    }

    @Override
    public void onDraw(Canvas canvas, Paint p)
    {
        Paint paint = new Paint();
        paint.setARGB(50,255,44,44);
        canvas.drawRect(x,y,x+width,y+height,paint);
    }
}
