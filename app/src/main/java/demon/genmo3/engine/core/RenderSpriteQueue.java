package demon.genmo3.engine.core;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

import demon.genmo3.engine.render.Drawable;

public class RenderSpriteQueue
{
    private static final ArrayList<Drawable> RENDER_LIST = new ArrayList<>();
    private static final Paint PAINT = new Paint();

    public RenderSpriteQueue()
    {
    }

    public void onDraw(Canvas canvas)
    {
        RENDER_LIST.forEach(e->
        {
            e.onDraw(canvas,PAINT);
        });
    }

    public void add(Drawable e)
    {
        if (!RENDER_LIST.contains(e)) RENDER_LIST.add(e);
    }

    public void remove(Drawable e)
    {
        if (RENDER_LIST.contains(e)) RENDER_LIST.remove(e);
    }
}
