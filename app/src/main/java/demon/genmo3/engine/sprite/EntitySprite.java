package demon.genmo3.engine.sprite;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.telephony.CellSignalStrength;
import android.util.Log;

import demon.genmo3.engine.control.Keys;
import demon.genmo3.engine.physics.Gravity;
import demon.genmo3.engine.physics.Movable;
import demon.genmo3.engine.render.Drawable;
import demon.genmo3.engine.render.DynamicTexture;
import demon.genmo3.engine.render.Texture;
import demon.genmo3.engine.sprite.component.Animations;
import demon.genmo3.engine.sprite.component.CollisionBox;
import demon.genmo3.engine.sprite.component.StateMachine;
import demon.genmo3.engine.utils.MapUtils;
import demon.genmo3.engine.utils.TextureUtils;
import demon.genmo3.engine.utils.TimerUtils;

/*
 * 玩家实体的基本实现
 * */
public class EntitySprite extends Sprite implements Gravity, Movable, Drawable
{
    private boolean dynamic;
    private float xSpeed = 0;
    private float xAccelerate = 0;
    private float xRunAccelerate = 1000;
    //影响减速时的加速度。值越大，停止移动后速度就越快回到0
    private float brakePower = 3.0f;
    private float xSpeedMax = 1500;
    private float ySpeed = 0;
    private float yAccelerate = 0;
    private float ySpeedMax = 2000;
    private CollisionBox collisionBox;
    private Animations animations;
    private StateMachine stateMachine;
    private DynamicTexture texture1;
    private Texture texture;

    //todo  暂时直接给texture
    public EntitySprite(float x, float y, Texture texture, float width, float height)
    {
        setX(x);
        setY(y);
        if (texture instanceof DynamicTexture)
        {
            dynamic = true;
            this.texture1 = (DynamicTexture) texture;
        } else this.texture = texture;
        this.collisionBox = new CollisionBox(getXPoint() - width / 2f, getYPoint() - height / 2f, width, height);
        this.stateMachine = new StateMachine(this);
    }

    @Override
    protected void finalize() throws Throwable
    {
        super.finalize();
        stateMachine.destroy();
    }

    @Override
    public void onUpdate()
    {
        stateEvent();
        speedEvent();
    }

    private void stateEvent()
    {
        stateMachine.update();
//        if (stateMachine.isTranslate())
//        {
//            texture = animations.get(stateMachine.getState());
//        }

    }

    private void speedEvent()
    {
        //松开按键
        if (xSpeed != 0 && !Keys.LEFT.use() && !Keys.RIGHT.use())
        {
            if (stateMachine.getDirection())
            {
                xAccelerate = brakePower * xRunAccelerate;
                if (xSpeed > 0)
                {
                    xSpeed = 0;
                    xAccelerate = 0;
                }
            } else
            {
                xAccelerate = brakePower * -xRunAccelerate;
                if (xSpeed < 0)
                {
                    xSpeed = 0;
                    xAccelerate = 0;
                }
            }
        }
        //按下一侧方向键
        if (Keys.LEFT.use() && !Keys.RIGHT.use() || !Keys.LEFT.use() && Keys.RIGHT.use())
        {
            if (Keys.LEFT.use())
            {
                if (xSpeed > 0)
                {
                    xSpeed = 0.5f * xSpeed;
                } else xAccelerate = -xRunAccelerate;
            } else
            {
                if (xSpeed < 0)
                {
                    xSpeed = 0.5f * xSpeed;
                } else xAccelerate = xRunAccelerate;
            }
        }
        if (stateMachine.getDirection())
        {
            xSpeed = Math.max((xSpeed + xAccelerate * TimerUtils.getDelta()), -xSpeedMax);
        } else xSpeed = Math.min((xSpeed + xAccelerate * TimerUtils.getDelta()), xSpeedMax);
        if (!stateMachine.isOnGround())
        {
            ySpeed = ySpeed + yAccelerate * TimerUtils.getDelta();
            Log.d("yA", String.valueOf(yAccelerate));
            Log.d("ySpeed", String.valueOf(ySpeed));
            if (ySpeed < 0) ySpeed = Math.max(ySpeed, -ySpeedMax);
            if (ySpeed > 0) ySpeed = Math.min(ySpeed, ySpeedMax);
        }
    }

    @Override
    public boolean isOnGround()
    {
        return stateMachine.isOnGround();
    }

    @Override
    public void setOnGround(boolean flag)
    {
        stateMachine.setOnGround(flag);
    }


    //处理x轴和y轴的移动逻辑
    @Override
    public void move()
    {
        if (MapUtils.canMoveX()) setX(getX() + (xSpeed * TimerUtils.getDelta()));
        if (MapUtils.canMoveY()) setY(getY() + (ySpeed * TimerUtils.getDelta()));
        moveCollisionBox();
    }

    private void moveCollisionBox()
    {
        collisionBox.x = getXPoint() - collisionBox.width / 2f;
        collisionBox.y = getYPoint() - collisionBox.height / 2f;
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
    public void onDraw(Canvas canvas, Paint p)
    {
        Log.i("pPoint", "(" + getX() + "," + getY() + ")");
        if (dynamic)
        {
            canvas.drawBitmap(texture1.getImg(false), getX(), getY(), p);
        } else canvas.drawBitmap(texture.getImg(false), getX(), getY(), p);
    }

    public float getXSpeed()
    {
        return xSpeed;
    }

    public void setXSpeed(float xSpeed)
    {
        this.xSpeed = Math.min(xSpeedMax, xSpeed);
    }

    @Override
    public float getYSpeed()
    {
        return ySpeed;
    }

    @Override
    public void setYSpeed(float ySpeed)
    {
        this.ySpeed = Math.min(ySpeedMax, ySpeed);
    }

    public float getXAccelerate()
    {
        return xAccelerate;
    }

    public void setXAccelerate(float xAccelerate)
    {
        this.xAccelerate = xAccelerate;
    }

    public float getYAccelerate()
    {
        return yAccelerate;
    }

    @Override
    public void setYAccelerate(float yAccelerate)
    {
        this.yAccelerate = yAccelerate;
    }

    public float getXPoint()
    {
        return getX() + (getImgWidth() / 2f);
    }

    public float getYPoint()
    {
        return getY() + (getImgHeight() / 2f);
    }

    public float getWidth()
    {
        return collisionBox.width;
    }

    public float getHeight()
    {
        return collisionBox.height;
    }

    private float getImgWidth()
    {
        if (dynamic) return texture1.getWidth();
        else return texture.getWidth();
    }

    private float getImgHeight()
    {
        if (dynamic) return texture1.getHeight();
        else return texture.getHeight();
    }

    public void setYOnGround(float y)
    {
        super.setY(y - (0.5f * (this.getCollisionBox().height + getImgHeight())));
    }

    @Override
    public void setXOnWall(float x, boolean left)
    {
        if (left) setX(x - (0.5f * (this.getCollisionBox().width + getImgWidth())));
        else setX(x - (0.5f * (getImgWidth() - this.getCollisionBox().width)));
    }

    @Override
    public CollisionBox getCollisionBox()
    {
        return collisionBox;
    }

    public boolean getDirection()
    {
        return this.stateMachine.getDirection();
    }
}