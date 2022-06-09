package demon.genmo3.engine.render;

import android.graphics.Canvas;
import android.graphics.Paint;

/*
* 实现此接口的类带有Textures,能被渲染到屏幕上
* */
public interface Drawable
{
    void onDraw(Canvas canvas, Paint p);
}
