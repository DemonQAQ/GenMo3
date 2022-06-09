package demon.genmo3.engine.physics;

/*
* 实现此接口的类会受到重力的影响
* */
public interface Gravity
{
    float G = -98;
    boolean isOnGround();
    void setYSpeed(float value);
    float getYSpeed();
}
