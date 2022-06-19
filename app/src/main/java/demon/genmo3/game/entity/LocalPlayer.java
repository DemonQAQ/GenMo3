package demon.genmo3.game.entity;

import android.graphics.Canvas;
import android.graphics.Paint;

import demon.genmo3.engine.control.Keys;
import demon.genmo3.engine.render.Texture;
import demon.genmo3.engine.sprite.EntitySprite;
import demon.genmo3.engine.utils.MapUtils;
import demon.genmo3.engine.utils.TimerUtils;
import demon.genmo3.engine.utils.ValueUtils;
import demon.genmo3.game.skill.WaterSplash;

public class LocalPlayer extends EntitySprite
{
    public LocalPlayer(float x, float y, Texture texture, float width, float height)
    {
        super(x, y, texture, width, height, false);
        setSkill1(WaterSplash.getSkill(this));
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

    @Override
    public void onDraw(Canvas canvas, Paint p)
    {
        super.onDraw(canvas, p);
    }
}
