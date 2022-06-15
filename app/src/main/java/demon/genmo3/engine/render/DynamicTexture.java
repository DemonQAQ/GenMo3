package demon.genmo3.engine.render;

import android.graphics.Bitmap;

import demon.genmo3.engine.utils.TimerUtils;

public class DynamicTexture extends Texture
{
    private Bitmap nowFrame;
    private Bitmap nowFrameL;
    private final int xFrame;
    private final int yFrame;
    private final int frame;
    private final int frameWidth;
    private final int frameHeight;
    private int index = 0;
    private int indexCache = 0;
    private int y = 0;
    private final long interval;
    private long startTime;
    private boolean loop;
    private boolean flash;
    private boolean stop;

    /*
    * 资源id,x轴帧数,y轴帧数,总帧数,间隔时间,是否循环
    * */
    public DynamicTexture(Bitmap img, int xFrame, int yFrame, int frame, long interval, boolean loop)
    {
        super(img);
        this.xFrame = xFrame > 0 ? xFrame : 1;
        this.yFrame = yFrame > 0 ? yFrame : 1;
        this.frame = frame > 0 ? frame : 1;
        this.frameWidth = img.getWidth() / xFrame;
        this.frameHeight = img.getHeight() / yFrame;
        this.interval = interval > 0 ? interval : 1;
        this.loop = loop;
        this.nowFrame = Bitmap.createBitmap(super.getImg(false), 0, 0, frameWidth, frameHeight);
        this.nowFrameL = Bitmap.createBitmap(super.getImg(true), frameWidth * (xFrame - 1), 0, frameWidth, frameHeight);
    }

    private void setIndex()
    {
        this.indexCache = index;
        index = ((int) ((TimerUtils.getTime() - startTime) / interval)) % frame;
        if (indexCache == frame - 1 && index == 0) y = 0;
        else y = index / xFrame;
        if (!loop && !stop) stop = ((TimerUtils.getTime() - startTime) / interval) / frame >= 1;
        if (stop) this.index = 0;
        else this.flash = index != indexCache;
    }

    //每次切换时都需要调用此函数，记录开始时间，用于计算当前帧数
    public void start()
    {
        this.startTime = TimerUtils.getTime();
    }

    private int getIndex()
    {
        if (!stop)
        {
            setIndex();
        }
        return index;
    }

    public void setLoop(boolean loop)
    {
        this.loop = loop;
    }

    public boolean getLoop()
    {
        return this.loop;
    }

    private void changeImg(int index)
    {
        if (flash)
        {
            this.nowFrame = Bitmap.createBitmap(super.getImg(false), (index % xFrame) * frameWidth, this.y * frameHeight, frameWidth, frameHeight);
            this.nowFrameL = Bitmap.createBitmap(super.getImg(true), (xFrame - (index % xFrame) - 1) * frameWidth, this.y * frameHeight, frameWidth, frameHeight);
        }
    }

    @Override
    public Bitmap getImg(boolean left)
    {
        changeImg(getIndex());
        if (left) return nowFrameL;
        return nowFrame;
    }

    @Override
    public int getWidth()
    {
        return frameWidth;
    }

    @Override
    public int getHeight()
    {
        return frameHeight;
    }
}
