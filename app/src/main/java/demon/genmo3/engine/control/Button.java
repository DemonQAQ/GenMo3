package demon.genmo3.engine.control;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;

import demon.genmo3.engine.render.Drawable;
import demon.genmo3.engine.render.Texture;

public class Button implements Drawable
{
    private boolean isClick;
    private Texture texture;
    private View.OnTouchListener listener;

    public Button(Texture texture)
    {
        this.texture = texture;
    }

    public boolean isClick(Point point)
    {
        Rect rect = new Rect(500,500,500+texture.getWidth(),500+texture.getHeight());
        isClick = rect.contains(point.x,point.y);
        return isClick;
    }

    public void setClickListener(ButtonListener buttonListener)
    {
        this.listener = buttonListener;
    }

    @Override
    public void onDraw(Canvas canvas, Paint p)
    {
        canvas.drawBitmap(texture.getImg(false),500,500,p);
    }
}
