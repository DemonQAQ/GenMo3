package demon.genmo3.engine.render;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import demon.genmo3.engine.utils.TextureUtils;

public class Texture implements Drawable
{
    private Bitmap img;
    private Bitmap imgL;
    private int x = 0;
    private int y = 0;
    private boolean left = false;

    public Texture(Bitmap img)
    {
        setImg(img);
    }

    private void setImg(Bitmap img)
    {
        this.img = img;
        this.imgL = TextureUtils.flip(img);
    }

    public Bitmap getImg(boolean left)
    {
        if(left) return imgL;
        else return img;
    }

    public void setLocation(int x,int y)
    {
        setX(x);
        setY(y);
    }

    public void setX(int x){ this.x = x;}

    public void setY(int y){ this.y = y;}

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public void setLeft(boolean left)
    {
        this.left = left;
    }

    @Override
    public void onDraw(Canvas canvas, Paint p)
    {
        canvas.drawBitmap(getImg(this.left),this.x,this.y,p);
    }
}
