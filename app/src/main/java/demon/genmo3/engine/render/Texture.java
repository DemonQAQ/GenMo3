package demon.genmo3.engine.render;

import android.graphics.Bitmap;

public abstract class Texture
{
    private Bitmap img;
    private int x;
    private int y;

    public void setImg(Bitmap img)
    {
        this.img = img;
    }

    public Bitmap getImg()
    {
        return img;
    }

    public void setLocation(int x,int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }
}
