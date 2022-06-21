package demon.genmo3.engine.sprite.component.combat;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import demon.genmo3.R;
import demon.genmo3.engine.render.Texture;
import demon.genmo3.engine.utils.TextureUtils;

public class Attributes
{
    private float hp;
    private float hpMax;
    private float mp;
    private float mpMax;
    private float attack;
    private float defence;
    private float crit;
    private float critRate;
    private float hpRecover;
    private float mpRecover;
    private Texture healthNull;
    private Texture health;
    private int width;

    public Attributes()
    {
        setHp(1);
        setHpMax(1);
        setMp(1);
        setMpMax(1);
        setAttack(1);
        setDefence(1);
        setCrit(1);
        setCritRate(1);
        setHpRecover(1);
        setMpRecover(1);
        init();
    }

    public Attributes(float hp, float mp, float attack, float defence, float crit, float critRate, float hpRecover, float mpRecover)
    {
        setHp(hp);
        setHpMax(hp);
        setMp(mp);
        setMpMax(mp);
        setAttack(attack);
        setDefence(defence);
        setCrit(crit);
        setCritRate(critRate);
        setHpRecover(hpRecover);
        setMpRecover(mpRecover);
        init();
    }

    private void init()
    {
        healthNull = TextureUtils.getTexture(R.drawable.healthnull);
        health = TextureUtils.getTexture(R.drawable.health);
        if (health != null) width = health.getWidth();
    }

    public void onDraw(Canvas canvas, Paint p,float x,float y)
    {
        canvas.drawBitmap(this.healthNull.getImg(false), x, y, p);
        if (hp>0)
        {
            width = (int)(health.getWidth() * (hp/hpMax));
            if (width>0)
            {
                Bitmap nowHealth = Bitmap.createBitmap(this.health.getImg(false), 0, 0, width, this.health.getHeight());
                canvas.drawBitmap(nowHealth, x, y, p);
            }
        }
    }

    public float getHeight()
    {
        return this.healthNull.getHeight();
    }

    public float getWidth()
    {
        return this.healthNull.getWidth();
    }

    public float getHp()
    {
        return hp;
    }

    public void setHp(float hp)
    {
        this.hp = hp;
    }

    public float getMp()
    {
        return mp;
    }

    public void setMp(float mp)
    {
        this.mp = mp;
    }

    public float getAttack()
    {
        return attack;
    }

    public void setAttack(float attack)
    {
        this.attack = attack;
    }

    public float getDefence()
    {
        return defence;
    }

    public void setDefence(float defence)
    {
        this.defence = defence;
    }

    public float getCrit()
    {
        return crit;
    }

    public void setCrit(float crit)
    {
        this.crit = crit;
    }

    public float getCritRate()
    {
        return critRate;
    }

    public void setCritRate(float critRate)
    {
        this.critRate = critRate;
    }

    public float getHpRecover()
    {
        return hpRecover;
    }

    public void setHpRecover(float hpRecover)
    {
        this.hpRecover = hpRecover;
    }

    public float getMpRecover()
    {
        return mpRecover;
    }

    public void setMpRecover(float mpRecover)
    {
        this.mpRecover = mpRecover;
    }

    public float getHpMax()
    {
        return hpMax;
    }

    public void setHpMax(float hpMax)
    {
        this.hpMax = hpMax;
    }

    public float getMpMax()
    {
        return mpMax;
    }

    public void setMpMax(float mpMax)
    {
        this.mpMax = mpMax;
    }
}
