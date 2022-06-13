package demon.genmo3.engine.physics;

import demon.genmo3.engine.sprite.component.CollisionBox;

/*
* 实现此接口的类能受到物理系统的影响
* */
public interface Movable
{
    //移动时触发
    void move();
    //判断是否碰撞
    boolean intersect(Movable e);
    //碰撞后触发
    void onIntersect(Movable e);

    void setXOnWall(float x,boolean left);

    CollisionBox getCollisionBox();
}
