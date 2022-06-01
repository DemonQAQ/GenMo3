package demon.genmo3.engine.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class TextureUtils
{
    private final static Matrix FLIP_MATRIX = new Matrix();

    public static Bitmap flip(Bitmap img)
    {
        FLIP_MATRIX.postScale(-1f,1f);
        Bitmap IMG = Bitmap.createBitmap(img,0,0,img.getWidth(),img.getHeight(),FLIP_MATRIX,true);
        return Bitmap.createBitmap(img,0,0,img.getWidth(),img.getHeight(),FLIP_MATRIX,true);
    }
}
