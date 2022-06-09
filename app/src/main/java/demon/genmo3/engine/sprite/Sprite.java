package demon.genmo3.engine.sprite;

import demon.genmo3.engine.core.Executable;

/*
* 游戏内带有坐标的实体的抽象
* */
public class Sprite implements Executable
{
    private float x;
    private float y;
    private int z;

    public Sprite()
    {

    }

    public void setX(float x)
    {
        this.x = x;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public void setZ(int z)
    {
        this.z = z;
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public int getZ()
    {
        return z;
    }

    @Override
    public void onUpdate()
    {

    }
}
