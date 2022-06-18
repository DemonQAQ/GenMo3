package demon.genmo3.engine.sprite.component.map;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

import demon.genmo3.engine.core.RenderSpriteQueue;
import demon.genmo3.engine.physics.Gravity;
import demon.genmo3.engine.physics.Movable;
import demon.genmo3.engine.render.Drawable;
import demon.genmo3.engine.render.DynamicTexture;
import demon.genmo3.engine.render.Texture;
import demon.genmo3.engine.sprite.EntitySprite;
import demon.genmo3.engine.sprite.Sprite;
import demon.genmo3.engine.sprite.component.CollisionBox;
import demon.genmo3.engine.utils.EngineUtils;
import demon.genmo3.engine.utils.TimerUtils;
import demon.genmo3.engine.utils.ValueUtils;

public class MapSprite extends Sprite implements Drawable, Movable
{
    private final int screenWidth;
    private final int screenHeight;
    private final int mapWidth;
    private final int mapHeight;
    private final boolean dynamic;
    private boolean intersect;
    private Texture background;
    private DynamicTexture backgroundD;
    private Bitmap renderRange;
    private final EntitySprite lockOnSprite;
    //玩家在地图里的坐标
    private float lX;
    private float lY;
    private float preX;
    private float preY;
    private float xSpeed;
    private float ySpeed;
    private static final ArrayList<Building> BUILDINGS = new ArrayList<>();


    public MapSprite(Texture bg, int screenWidth, int screenHeight, EntitySprite sprite, float lX, float lY)
    {
        lockOnSprite = sprite;
        updatePlayerPoint();
        dynamic = bg instanceof DynamicTexture;
        if (dynamic)
        {
            backgroundD = (DynamicTexture) bg;
            backgroundD.start();
        } else background = bg;
        mapWidth = bg.getWidth();
        mapHeight = bg.getHeight();

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.lX = lX;
        this.lY = lY;
        this.preX = lX;
        this.preY = lY;
        lookOnSprite();
        getBackground();
    }

    /*
     * 判断地图碰撞的独立update
     * 早于基于总线的update执行
     * */
    public void onUpdate(Movable[] movables)
    {
        for (Movable m : movables)
        {
            BUILDINGS.forEach(e ->
            {
                if (e.getContain() & e.intersect(m))
                {
                    intersect = true;
                    e.onIntersect(m);
                }
            });
            if (!intersect & m instanceof Gravity)
            {
                Gravity gravity = (Gravity) m;
                if (gravity.isOnGround()) gravity.setOnGround(false);
            }
            intersect = false;
        }
    }

    @Override
    public void onUpdate()
    {
        updatePlayerPoint();
        updateRenderRange();
        updateBuilding(getX(), getY());
        checkContain();
    }

    private void updatePlayerPoint()
    {
        this.preX = this.lX;
        this.preY = this.lY;
        this.lX = (getX() + lockOnSprite.getXPoint());
        if (this.lX < 0) this.lX = 0;
        if (this.lX > this.mapWidth) this.lX = this.mapWidth;
        this.lY = (getY() + lockOnSprite.getYPoint());
        if (this.lY < 0) this.lY = 0;
        if (this.lY > this.mapHeight) this.lY = this.mapHeight;
        if (lockOnSprite.getXPoint() < 0 || lockOnSprite.getXPoint() > screenWidth || lockOnSprite.getYPoint() < 0 || lockOnSprite.getYPoint() > screenHeight)
            lookOnSprite();
    }

    private void updateRenderRange()
    {
        boolean xLeft = this.lX < (getX() + 0.2f * screenWidth);
        boolean xRight = this.lX > (getX() + 0.8f * screenWidth);
        if (xLeft || xRight)
        {
            if (xLeft && !lockOnSprite.getDirection() || xRight && lockOnSprite.getDirection())
                this.xSpeed = 0;
            else this.xSpeed = lockOnSprite.getXSpeed();
        } else this.xSpeed = 0;

        boolean yTop = this.lY < (getY() + 0.2f * screenHeight);
        boolean yBottom = this.lY > (getY() + 0.8f * screenHeight);
        if (yTop || yBottom)
        {
            if (yTop && lockOnSprite.getYSpeed() > 0) this.ySpeed = 0;
            if (yBottom && lockOnSprite.getYSpeed() < 0) this.ySpeed = 0;
            this.ySpeed = lockOnSprite.getYSpeed();
        } else this.ySpeed = 0;
    }

    private void updateBuilding(float mX, float mY)
    {
        BUILDINGS.forEach(e ->
        {
            e.onUpdate(mX, mY);
        });
    }

    private void checkContain()
    {
        RenderSpriteQueue renderSpriteQueue = EngineUtils.getRender();
        BUILDINGS.forEach(e ->
        {
            if (e.getContain())
            {
                renderSpriteQueue.add(e);
            } else
            {
                renderSpriteQueue.remove(e);
            }
        });
    }

    private void lookOnSprite()
    {
        float x = 0;
        if ((this.lX - (0.5f * screenWidth)) > 0 && this.lX - (0.5f * screenWidth) < mapWidth - screenWidth)
            x = (this.lX - (0.5f * screenWidth));
        else if (this.lX - (0.5f * screenWidth) >= mapWidth - screenWidth)
            x = mapHeight - screenHeight;
        else if ((this.lX - (0.5f * screenWidth)) <= 0)
            x = 0;
        setX(x);

        float y = 0;
        if ((this.lY - (0.5f * screenHeight)) > 0 && this.lY - (0.5f * screenHeight) < mapHeight - screenHeight)
            y = (this.lY - (0.5f * screenHeight));
        else if (this.lY - (0.5f * screenHeight) >= mapHeight - screenHeight)
            y = mapHeight - screenHeight;
        else if ((this.lY - (0.5f * screenHeight)) < 0)
            y = 0;
        setY(y);
    }

    @Override
    public void onDraw(Canvas canvas, Paint p)
    {
        getBackground();
        canvas.drawBitmap(renderRange, 0, 0, p);
    }

    private void getBackground()
    {
        if (dynamic)
        {
            renderRange = Bitmap.createBitmap(backgroundD.getImg(false), (int) getX(), (int) getY(), screenWidth, screenHeight);
        } else
            renderRange = Bitmap.createBitmap(background.getImg(false), (int) getX(), (int) getY(), screenWidth, screenHeight);
    }

    public void add(Building building)
    {
        BUILDINGS.add(building);
    }

    public void remove(Building building)
    {
        BUILDINGS.remove(building);
    }

    @Override
    public void move()
    {
        moveRenderRange();
        moveBuilding();
    }

    private void moveRenderRange()
    {
        float x = getX() + this.xSpeed * TimerUtils.getDelta();
        if (x < 0) x = 0;
        if (x > mapWidth - screenWidth) x = mapWidth - screenWidth;
        float y = getY() + this.ySpeed * TimerUtils.getDelta();
        if (y < 0) y = 0;
        if (y > mapHeight - screenHeight) y = mapHeight - screenHeight;
        setX(x);
        setY(y);
    }

    private void moveBuilding()
    {
        BUILDINGS.forEach(Movable::move);
    }

    public boolean canMoveX()
    {
        boolean playerInArea = this.lX >= (getX() + 0.2f * screenWidth) & this.lX <= (getX() + 0.8f * screenWidth);
        if (!playerInArea)
        {
            if (this.lX < (getX() + 0.2f * screenWidth) & !lockOnSprite.getDirection())
                playerInArea = true;
            else if (this.lX > (getX() + 0.8f * screenWidth) & lockOnSprite.getDirection())
                playerInArea = true;
        }
        boolean mapInEdge = getX() <= 0 || getX() >= mapWidth - ValueUtils.SCREEN_WIDTH;
        if (mapInEdge)
        {
            if (getX() <= 0 & lockOnSprite.getXPoint() <= 0)
            {
                mapInEdge = false;
            } else if (getX() >= mapWidth - ValueUtils.SCREEN_WIDTH & lockOnSprite.getXPoint() >= mapWidth)
            {
                mapInEdge = false;
            }
        }
        return playerInArea || mapInEdge;
    }

    public boolean canMoveY()
    {
        boolean playerInArea = this.lY >= (getY() + 0.2f * screenHeight) & this.lY <= (getY() + 0.8f * screenHeight);
        if (!playerInArea)
        {
            if (lockOnSprite.isOnGround()) playerInArea = true;
            if (this.lY < (getY() + 0.2f * screenHeight) && this.lockOnSprite.getYSpeed() > 0)
                playerInArea = true;
            if (this.lY > (getY() + 0.8f * screenHeight) && this.lockOnSprite.getYSpeed() < 0)
                playerInArea = true;
        }
        boolean mapInEdge = getY() == 0 || getY() == mapHeight;
        if (mapInEdge)
        {
            if (getY() <= 0 & lockOnSprite.getYPoint() <= 0)
            {
                mapInEdge = false;
            } else if (getY() >= mapWidth - ValueUtils.SCREEN_HEIGHT & lockOnSprite.getYPoint() >= mapWidth)
            {
                mapInEdge = false;
            }
        }
        return playerInArea || mapInEdge;
    }

    public float getMapWidth()
    {
        return this.mapWidth;
    }

    public float getMapHeight()
    {
        return this.mapHeight;
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

    @Override
    public void setXOnWall(float x, boolean left)
    {

    }

    @Override
    public CollisionBox getCollisionBox()
    {
        return null;
    }

    public float getlX()
    {
        return lX - getX();
    }

    public float getlY()
    {
        return lY - getY();
    }

    public EntitySprite getLockOnSprite()
    {
        return this.lockOnSprite;
    }
}
