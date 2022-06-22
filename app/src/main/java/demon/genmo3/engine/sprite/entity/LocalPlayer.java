package demon.genmo3.engine.sprite.entity;


import android.util.Log;

import java.util.UUID;

import demon.genmo3.engine.control.Keys;
import demon.genmo3.engine.network.MessageCenter;
import demon.genmo3.engine.render.Texture;
import demon.genmo3.engine.sprite.EntitySprite;
import demon.genmo3.engine.utils.MapUtils;
import demon.genmo3.engine.utils.TimerUtils;
import demon.genmo3.engine.utils.ValueUtils;
import demon.genmo3.game.skill.AirBurst;
import demon.genmo3.game.skill.Attack;
import demon.genmo3.game.skill.Explosion;
import demon.genmo3.game.skill.ThumpAttack;
import demon.genmo3.game.skill.WaterSplash;
import demon.genmo3.game.skill.WindBreath;

public class LocalPlayer extends EntitySprite
{
    public LocalPlayer(float x, float y, Texture texture, float width, float height)
    {
        super(x, y, texture, width, height, 0);
        setSkill1(WaterSplash.getSkill(this));
        setSkill2(Explosion.getSkill(this));
        setSkill3(AirBurst.getSkill(this));
        setSkill4(WindBreath.getSkill(this));
        setAttack(Attack.getSkill(this));
        setThumpAttack(ThumpAttack.getSkill(this));
        this.uuid = UUID.randomUUID().toString();
    }

    @Override
    public void move()
    {
        super.move();
        MessageCenter.sendMessage("move"+","+ MapUtils.getPX() +","+MapUtils.getPY(),this);
    }

    @Override
    public void speedEvent()
    {
        //松开按键
        if (getXSpeed() != 0 && !Keys.LEFT.use() && !Keys.RIGHT.use())
        {
            if (getStateMachine().getDirection())
            {
                setxAccelerate(getBrakePower() * getxRunAccelerate());
                if (getXSpeed() > 0)
                {
                    setXSpeed(0);
                    setxAccelerate(0);
                }
            } else
            {
                setxAccelerate(getBrakePower() * -getxRunAccelerate());
                if (getXSpeed() < 0)
                {
                    setXSpeed(0);
                    setxAccelerate(0);
                }
            }
        }
        //按下一侧方向键
        if (Keys.LEFT.use() && !Keys.RIGHT.use() || !Keys.LEFT.use() && Keys.RIGHT.use())
        {
            if (Keys.LEFT.use())
            {
                if (getXSpeed() > 0)
                {
                    setXSpeed(0.5f * getXSpeed());
                } else setxAccelerate(-getxRunAccelerate());
            } else
            {
                if (getXSpeed() < 0)
                {
                    setXSpeed(0.5f * getXSpeed());
                } else setxAccelerate(getxRunAccelerate());
            }
        }
        if (getStateMachine().getDirection())
        {
            setXSpeed(Math.max((getXSpeed() + getxAccelerate() * TimerUtils.getDelta()), -getXSpeedMax()));
        } else
            setXSpeed(Math.min((getXSpeed() + getxAccelerate() * TimerUtils.getDelta()), getXSpeedMax()));
        if (!getStateMachine().isOnGround())
        {
            setYSpeed(getYSpeed() + getyAccelerate() * TimerUtils.getDelta());
            if (getYSpeed() < 0) setYSpeed(Math.max(getYSpeed(), -getySpeedMax()));
            if (getYSpeed() > 0) setYSpeed(Math.min(getYSpeed(), getySpeedMax()));
        }
    }
}
