package demon.genmo3.game.core;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;

import androidx.annotation.NonNull;

import demon.genmo3.engine.core.GameEngine;

public class Engine extends GameEngine
{

    public Engine(Context context)
    {
        super(context);
    }

    public Engine(Context context, AttributeSet attrs)
    {
        super(context,attrs);
    }

    public Engine(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);
    }

    public Engine(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void update()
    {
        super.update();
    }

    @Override
    public void physics()
    {
        super.physics();
    }

    @Override
    public void init()
    {
        super.init();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder)
    {
        super.surfaceCreated(surfaceHolder);
    }
}
