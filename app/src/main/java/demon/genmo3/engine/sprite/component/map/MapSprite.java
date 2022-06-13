package demon.genmo3.engine.sprite.component.map;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;

import demon.genmo3.engine.core.GameEngine;
import demon.genmo3.engine.core.PhysicsSpriteQueue;
import demon.genmo3.engine.physics.Gravity;
import demon.genmo3.engine.physics.Movable;
import demon.genmo3.engine.render.Drawable;
import demon.genmo3.engine.render.DynamicTexture;
import demon.genmo3.engine.render.Texture;
import demon.genmo3.engine.sprite.EntitySprite;
import demon.genmo3.engine.sprite.Sprite;

public class MapSprite extends Sprite implements Drawable
{
    private int screenWidth;
    private int screenHeight;
    private int mapWidth;
    private int mapHeight;
    private boolean dynamic;
    private boolean intersect;
    private Texture background;
    private DynamicTexture backgroundD;
    private Bitmap renderRange;
    private EntitySprite lockOnSprite;
    //玩家在地图里的坐标
    private float lX;
    private float lY;
    private static final ArrayList<Building> BUILDINGS = new ArrayList<>();


    public MapSprite(Texture bg,int screenWidth,int screenHeight,EntitySprite sprite,float lX,float lY)
    {
        lockOnSprite = sprite;
        this.lX = lX;
        this.lY = lY;
        dynamic = bg instanceof DynamicTexture;
        if (dynamic)
        {
            backgroundD = (DynamicTexture) bg;
            backgroundD.start();
        }
        else background = bg;
        mapWidth = bg.getWidth();
        mapHeight = bg.getHeight();
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        lookOnSprite();
        getBackground();
    }

    public void onUpdate(Movable[] movables)
    {
        for (Movable m:movables)
        {
            BUILDINGS.forEach(e->
            {
                if (e.intersect(m))
                {
                    intersect = true;
                    e.onIntersect(m);
                }
            });
            if (!intersect && m instanceof Gravity)
            {
                Gravity gravity = (Gravity) m;
                if (gravity.isOnGround())gravity.setOnGround(false);
            }
            intersect = false;
        }
    }

    //todo 将玩家与所有地面实体做比较，如没有碰撞则代表进入浮空状态
    public boolean inGround(EntitySprite e)
    {
        return false;
    }

    //todo 清除debug变量
    @Override
    public void onDraw(Canvas canvas, Paint p)
    {
        getBackground();
        Bitmap bg = background.getImg(false);
        int width = bg.getWidth();
        int height = bg.getHeight();
        canvas.drawBitmap(background.getImg(false),0,0,p);
    }

    private void lookOnSprite()
    {
        if (this.lX+(screenWidth / 2)>screenWidth)
        {
            setX(mapWidth-screenWidth);
        }
        else if(this.lX<(screenWidth/2))
        {
            setX(0);
        }
        else
        {
            setX(this.lX-(screenWidth/2f));
        }

        if (this.lY+(screenHeight/2)>screenHeight)
        {
            setY(screenHeight);
        }
        else if (this.lY<(screenHeight/2))
        {
            setY(0);
        }
        else
        {
            setY(this.lY-(screenHeight/2f));
        };
    }
    private void getBackground()
    {
        if (dynamic)
        {
            renderRange = Bitmap.createBitmap(backgroundD.getImg(false),(int)getX(),(int)getY(),screenWidth,screenHeight);
        }
        else renderRange = background.getImg(false);
    }

    public void add(Building building)
    {
        BUILDINGS.add(building);
    }

    public void remove(Building building)
    {
        BUILDINGS.remove(building);
    }

}
