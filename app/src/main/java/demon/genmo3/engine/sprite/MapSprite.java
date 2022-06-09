package demon.genmo3.engine.sprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import demon.genmo3.engine.core.PhysicsSpriteQueue;
import demon.genmo3.engine.render.Drawable;
import demon.genmo3.engine.render.DynamicTexture;
import demon.genmo3.engine.render.Texture;

public class MapSprite extends Sprite implements Drawable
{
    private int screenWidth;
    private int screenHeight;
    private int mapWidth;
    private int mapHeight;
    private boolean dynamic;
    private Texture background;
    private DynamicTexture backgroundD;
    private Bitmap renderRange;
    private EntitySprite lockOnSprite;
    private int lX;
    private int lY;


    public MapSprite(Texture bg,int screenWidth,int screenHeight,EntitySprite sprite,int lX,int lY)
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

    @Override
    public void onUpdate()
    {

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

}
