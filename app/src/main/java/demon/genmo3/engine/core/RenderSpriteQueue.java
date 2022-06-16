package demon.genmo3.engine.core;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

import demon.genmo3.engine.render.Drawable;

/*
 * 渲染管线，处理渲染任务
 * 内置三条队列用于处理渲染任务，请按渲染的优先级先后将待渲染物体加入对应队列
 * */
public class RenderSpriteQueue
{
    private static final ArrayList<Drawable> PRE_RENDER_LIST = new ArrayList<>();
    private static final ArrayList<Drawable> RENDER_LIST = new ArrayList<>();
    private static final ArrayList<Drawable> END_RENDER_LIST = new ArrayList<>();
    private static final Paint PAINT = new Paint();

    public RenderSpriteQueue()
    {
    }

    public void onDraw(Canvas canvas)
    {
        PRE_RENDER_LIST.forEach(e->
        {
            e.onDraw(canvas,PAINT);
        });
        RENDER_LIST.forEach(e->
        {
            e.onDraw(canvas,PAINT);
        });
        END_RENDER_LIST.forEach(e->
        {
            e.onDraw(canvas,PAINT);
        });
    }

    public void add(Drawable e)
    {
        if (!RENDER_LIST.contains(e))
        {
            if (PRE_RENDER_LIST.contains(e))removePre(e);
            if (END_RENDER_LIST.contains(e))removeEnd(e);
            RENDER_LIST.add(e);
        }
    }

    public void remove(Drawable e)
    {
        RENDER_LIST.remove(e);
    }

    public void addPre(Drawable e)
    {
        if (!PRE_RENDER_LIST.contains(e))
        {
            if (RENDER_LIST.contains(e))remove(e);
            if (END_RENDER_LIST.contains(e))removeEnd(e);
            PRE_RENDER_LIST.add(e);
        }
    }

    public void removePre(Drawable e)
    {
        PRE_RENDER_LIST.remove(e);
    }

    public void addEnd(Drawable e)
    {
        if (!PRE_RENDER_LIST.contains(e))
        {
            if (PRE_RENDER_LIST.contains(e))removePre(e);
            if (RENDER_LIST.contains(e))remove(e);
            END_RENDER_LIST.add(e);
        }
    }

    public void removeEnd(Drawable e)
    {
        END_RENDER_LIST.remove(e);
    }
}
