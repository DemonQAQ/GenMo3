package demon.genmo3.engine.sprite;

import android.graphics.Canvas;
import android.graphics.Paint;

import demon.genmo3.engine.physics.Gravity;
import demon.genmo3.engine.physics.Movable;
import demon.genmo3.engine.render.Drawable;
import demon.genmo3.engine.render.DynamicTexture;
import demon.genmo3.engine.render.Texture;
import demon.genmo3.engine.sprite.component.CollisionBox;
import demon.genmo3.engine.sprite.component.combat.Attributes;
import demon.genmo3.engine.sprite.component.combat.Combat;
import demon.genmo3.engine.sprite.component.combat.DamageArea;
import demon.genmo3.engine.sprite.component.state.StateMachine;
import demon.genmo3.engine.utils.AnimationsUtils;

/*
 * 玩家实体
 * 主要方法列表:
 * - onUpdate() ··· 此方法会在GameEngine类的实例执行update()时调用
 * - move() ··· 此方法会在GameEngine类的实例执行physics()时候调用，且调用时间晚于重力系统调用
 * - onDraw() ··· 此方法会在GameEngine类的实例执行主循环时最后调用，用于将图像绘制到画面上
 *
 * 主要功能类:
 * - CollisionBox ··· 碰撞箱，代表玩家的体积。用于碰撞检测
 * - StateMachine ··· 状态机，同一时刻只会处于一种状态，changeAnimation()方法会根据状态切换动画
 * - DynamicTexture ··· 动画实例，储存有当前的动画
 * */
public class EntitySprite extends Sprite implements Gravity, Movable, Drawable, Combat
{
    private boolean dynamic;
    private float xSpeed = 0;
    private float xAccelerate = 0;
    private final float xRunAccelerate = 2000f;
    //影响减速时的加速度。值越大，停止移动后速度就越快回到0
    private final float brakePower = 4.0f;
    private final float xSpeedMax = 1500;
    private float ySpeed = 0;
    private float yAccelerate = 0;
    private final float ySpeedMax = 3000;
    private final CollisionBox collisionBox;
    private final StateMachine stateMachine;
    private DynamicTexture texture1;
    private Texture texture;

    public EntitySprite(float x, float y, Texture texture, float width, float height,boolean network)
    {
        setX(x);
        setY(y);
        if (texture instanceof DynamicTexture)
        {
            dynamic = true;
            this.texture1 = (DynamicTexture) texture;
        } else this.texture = texture;
        this.collisionBox = new CollisionBox(getXPoint() - width / 2f, getYPoint() - height / 2f, width, height);
        this.stateMachine = new StateMachine(this,network);
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

    //调用状态机
    private void stateEvent()
    {
        stateMachine.update();
        if (stateMachine.isTranslate())changeAnimation();
    }

    //根据当前状态切换动画
    private void changeAnimation()
    {
        if (dynamic)
        {
            DynamicTexture texture = AnimationsUtils.getAnimation(stateMachine.getState());
            if (texture != null)
            {
                texture1 = texture;
                texture1.start();
            }
        }
    }

    //此处处理速度事件，本地玩家通过监听按键改变速度，网络玩家通过事件通知改变速度
    public void speedEvent()
    {
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
        if (dynamic)
        {
            canvas.drawBitmap(texture1.getImg(stateMachine.getDirection()), getX(), getY(), p);
        } else canvas.drawBitmap(texture.getImg(stateMachine.getDirection()), getX(), getY(), p);
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

    public float getxAccelerate()
    {
        return xAccelerate;
    }

    public void setxAccelerate(float xAccelerate)
    {
        this.xAccelerate = xAccelerate;
    }

    public float getxRunAccelerate()
    {
        return xRunAccelerate;
    }

    public float getBrakePower()
    {
        return brakePower;
    }

    public float getXSpeedMax()
    {
        return xSpeedMax;
    }

    public float getyAccelerate()
    {
        return yAccelerate;
    }

    public void setyAccelerate(float yAccelerate)
    {
        this.yAccelerate = yAccelerate;
    }

    public float getySpeedMax()
    {
        return ySpeedMax;
    }

    public StateMachine getStateMachine()
    {
        return stateMachine;
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

    @Override
    public Attributes getAttribute()
    {
        return null;
    }

    @Override
    public boolean intersect(Combat e)
    {
        return false;
    }

    @Override
    public void damage(Combat e)
    {

    }

    @Override
    public DamageArea getDamageArea()
    {
        return null;
    }

    @Override
    public boolean isNumbness()
    {
        return this.stateMachine.isNumbness();
    }

    @Override
    public void setNumbness(boolean flag)
    {
        this.stateMachine.setNumbness(flag);
    }
}
