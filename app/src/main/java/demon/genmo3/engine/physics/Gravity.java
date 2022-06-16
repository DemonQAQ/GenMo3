package demon.genmo3.engine.physics;

/*
* 实现此接口的类会受到重力的影响
* */
public interface Gravity
{
    float G = 2500;
    boolean isOnGround();
    void setOnGround(boolean flag);
    void setYSpeed(float value);
    float getYSpeed();
    void setYOnGround(float y);
    void setYAccelerate(float yAccelerate);
}
