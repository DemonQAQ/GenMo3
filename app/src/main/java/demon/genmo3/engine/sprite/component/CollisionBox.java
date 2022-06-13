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
//        boolean f = Math.max(this.x,e.x)<=Math.min(this.x+this.width,e.x+e.width)&&Math.max(this.y,e.y)<=Math.min(this.y+this.height,e.y+e.height);
//        Log.d("Intersect", String.valueOf(f));
//        String str = "["+this.x +","+ e.x +"],["+ (this.x+this.width) +","+ (e.x+e.width)+"],["+this.y+","+e.y+"],["+(this.y+this.height)+","+(e.y+e.height)+"]";
//        Log.d("ponit",str );
        return Math.max(this.x, e.x) <= Math.min(this.x + this.width, e.x + e.width) && Math.max(this.y, e.y) <= Math.min(this.y + this.height, e.y + e.height);
    }

    public boolean checkAboveIntersect(CollisionBox e)
    {
//            boolean f = e.y + e.height > this.y;
//            Log.d("AboveIntersect", String.valueOf(f));
            return e.y + e.height >= this.y;
    }

    public boolean checkSideIntersect(CollisionBox e)
    {
        if (this.y <= e.y && e.y <= this.y + e.height)
        {
            float centerX = (0.5f*(this.x + this.width));
            return e.x + e.width <= centerX || e.x >= centerX;
        }
        return false;
    }

    public boolean checkBelowIntersect(CollisionBox e)
    {
        return  (this.y + this.height > e.y - (0.5f*e.height));
    }

    @Override
    public void onDraw(Canvas canvas, Paint p)
    {
        Paint paint = new Paint();
        paint.setARGB(50, 255, 44, 44);
        canvas.drawRect(x, y, x + width, y + height, paint);
    }
}
