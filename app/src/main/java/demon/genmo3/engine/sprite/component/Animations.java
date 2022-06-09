package demon.genmo3.engine.sprite.component;


import android.graphics.Bitmap;

import java.util.HashMap;

import demon.genmo3.engine.render.DynamicTexture;

/*
* 可渲染类的动画列表
* */
public class Animations
{
    private HashMap<String, DynamicTexture> animations = new HashMap<>();

    public Animations()
    {

    }

    public void add(String stateName,DynamicTexture dynamicTexture)
    {
        animations.put(stateName,dynamicTexture);
    }

    public DynamicTexture get(String stateName)
    {
        return animations.get(stateName);
    }
}
