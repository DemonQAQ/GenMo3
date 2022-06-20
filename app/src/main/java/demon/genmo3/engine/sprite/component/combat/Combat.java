package demon.genmo3.engine.sprite.component.combat;


import demon.genmo3.engine.sprite.component.CollisionBox;

public interface Combat
{
    //获取对象上的属性集合
    Attributes getAttribute();
    //判断是否相交
    boolean intersect(CollisionBox e);
    //相交时执行此方法，处理伤害逻辑
    void damage(Combat e);
    //获取伤害源的伤害区域
    DamageArea getDamageArea();
    //获取受击对象的碰撞箱，用于判断是否相交
    CollisionBox getCollisionBox();
    //是否僵直中
    boolean isNumbness();

    void setNumbness(boolean flag);
    //移动伤害区域
    void move();

    void checkEnd();

    boolean isDeath();

    void death();
}
