package demon.genmo3.engine.sprite;

import android.graphics.Canvas;
import android.graphics.Paint;

import demon.genmo3.engine.control.Keys;
import demon.genmo3.engine.physics.Gravity;
import demon.genmo3.engine.physics.Movable;
import demon.genmo3.engine.render.Drawable;
import demon.genmo3.engine.render.DynamicTexture;
import demon.genmo3.engine.render.Texture;
import demon.genmo3.engine.sprite.component.Animations;
import demon.genmo3.engine.sprite.component.CollisionBox;
import demon.genmo3.engine.sprite.component.StateMachine;
import demon.genmo3.engine.sprite.component.state.StateType;
import demon.genmo3.engine.utils.TimerUtils;

/*
 * 玩家实体的基本实现
 * */
public class EntitySprite extends Sprite implements Gravity, Movable, Drawable
{
    private float xSpeed = 0;
    private float xAccelerate = 250;
    private float xSpeedMax = 1000;
    private float ySpeed = 0;
    private float yAccelerate = 100;
    private float ySpeedMax = 200;
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
        this.texture = texture;
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
        stateMachine.update();
//        if (stateMachine.isTranslate())
//        {
//            texture = animations.get(stateMachine.getState());
//        }
        if (!Keys.LEFT.use() && !Keys.RIGHT.use())
        {
            xSpeed -= 0.25 * xAccelerate * (TimerUtils.getDelta() / 1000f);
            if (xSpeed<0)xSpeed=0;
        }
        if (Keys.LEFT.use() && !Keys.RIGHT.use() || !Keys.LEFT.use() && Keys.RIGHT.use())
        {
            xSpeed = Math.min((xSpeed + xAccelerate * (TimerUtils.getDelta() / 1000f)), xSpeedMax);
        }
        if (stateMachine.getState().equals(StateType.JUMPING.getState()))
        {
            ySpeed = Math.min((ySpeed + yAccelerate * (TimerUtils.getDelta() / 1000f)), xSpeedMax);
        }
    }

    @Override
    public boolean isOnGround()
    {
        return stateMachine.isOnGround();
    }

    public void setOnGround(boolean flag)
    {
        stateMachine.setOnGround(flag);
    }


    //处理x轴和y轴的移动逻辑
    @Override
    public void move()
    {
        //左边
        if (stateMachine.getDirection()) setX(getX() - (xSpeed * (TimerUtils.getDelta() / 1000f)));
        else setX(getX() + (xSpeed * (TimerUtils.getDelta() / 1000f)));
        setY(getY() + (ySpeed * (TimerUtils.getDelta() / 1000f)));
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
        canvas.drawBitmap(texture.getImg(false), getX(), getY(), p);
        //canvas.drawBitmap(texture.getImg(stateMachine.getDirection()),getX(),getY(),p);
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

    public void setYAccelerate(float yAccelerate)
    {
        this.yAccelerate = yAccelerate;
    }

    public float getXPoint()
    {
        return getX() + (texture.getWidth() / 2f);
    }

    public float getYPoint()
    {
        return getY() + (texture.getHeight() / 2f);
    }

    public float getWidth()
    {
        return collisionBox.width;
    }

    public float getHeight()
    {
        return collisionBox.height;
    }

    public CollisionBox getCollisionBox()
    {
        return collisionBox;
    }
}
