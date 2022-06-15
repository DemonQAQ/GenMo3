package demon.genmo3.engine.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import demon.genmo3.R;
import demon.genmo3.engine.render.DynamicTexture;
import demon.genmo3.engine.render.Texture;

public class TextureUtils
{
    private static Resources resources;

    public static void init(Resources resources)
    {
        TextureUtils.resources = resources;
    }

    public static Bitmap flip(Bitmap img)
    {
        Matrix FLIP_MATRIX = new Matrix();
        FLIP_MATRIX.postScale(-1, 1);
        return Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), FLIP_MATRIX, true);
    }

    public static Texture getTexture(int id)
    {
        if (resources == null) return null;
        return new Texture(BitmapFactory.decodeResource(resources, id));
    }

    /*
    * 资源id,x轴帧数,y轴帧数,总帧数,间隔时间,是否循环
    * */
    public static DynamicTexture getDynamicTexture(int id, int xFrame, int yFrame, int frame, long interval, boolean loop)
    {
        if (resources == null) return null;
        return new DynamicTexture(BitmapFactory.decodeResource(resources, id), xFrame, yFrame, frame, interval, loop);
    }
}
