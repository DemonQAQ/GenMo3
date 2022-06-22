package demon.genmo3.game.manager;

import android.content.res.Resources;
import android.util.Log;

import demon.genmo3.R;
import demon.genmo3.engine.render.DynamicTexture;
import demon.genmo3.engine.render.Texture;
import demon.genmo3.engine.sprite.component.map.GroundSprite;
import demon.genmo3.engine.sprite.component.map.MapSprite;
import demon.genmo3.engine.sprite.component.map.WallSprite;
import demon.genmo3.engine.utils.AnimationsUtils;
import demon.genmo3.engine.utils.EngineUtils;
import demon.genmo3.engine.utils.MapUtils;
import demon.genmo3.engine.utils.TextureUtils;
import demon.genmo3.engine.utils.ValueUtils;
import demon.genmo3.game.core.Engine;
import demon.genmo3.engine.sprite.entity.LocalPlayer;
import demon.genmo3.engine.sprite.entity.MobEntity;

public class ContentManager
{
    private static LocalPlayer player;
    private static MapSprite m;
    static MobEntity mob;

    public static LocalPlayer init(Resources resources, Engine engine)
    {
        engineInit(resources,engine);
        entityInit();
        mapInit();
        endInit(engine);
        return player;
    }

    private static void engineInit(Resources resources, Engine engine)
    {
        TextureUtils.init(resources);
        AnimationsUtils.init();
        ValueUtils.init(2160,1080);
        EngineUtils.init(engine);
    }

    private static void mapInit()
    {
        Texture img = TextureUtils.getTexture(R.drawable.background);
        m = new MapSprite(img, 2160, 1080, player, 2280, 1528);
        MapUtils.changeMap(m);
        GroundSprite ground = new GroundSprite(TextureUtils.getTexture(R.drawable.ground), 0, 1845, 3489, 313);
        WallSprite ground1 = new WallSprite(TextureUtils.getTexture(R.drawable.wall1), 3489, 1715, 831, 445);
        WallSprite ground2 = new WallSprite(TextureUtils.getTexture(R.drawable.wall2), 0, 1341, 1144, 231);
        WallSprite ground3 = new WallSprite(TextureUtils.getTexture(R.drawable.wall3), 1576, 1043, 492, 187);
        WallSprite ground4 = new WallSprite(TextureUtils.getTexture(R.drawable.wall3), 2242, 813, 492, 187);
        WallSprite ground5 = new WallSprite(TextureUtils.getTexture(R.drawable.wall3), 2833, 1172, 492, 187);
        m.add(ground);
        m.add(ground1);
        m.add(ground2);
        m.add(ground3);
        m.add(ground4);
        m.add(ground5);
    }

    private static void entityInit()
    {
        DynamicTexture dynamicTexture = TextureUtils.getDynamicTexture(R.drawable.idle,4,1,4,100,true);
        DynamicTexture GOLEM_IDLE = TextureUtils.getDynamicTexture(R.drawable.golemidle,3,3,9,100,true);
        player = new LocalPlayer(1000, 300, dynamicTexture, 160, 250);
        player.setOnGround(false);
        mob = new MobEntity(1200,300,GOLEM_IDLE,160,250);
    }

    private static void endInit(Engine engine)
    {
        engine.combatSpriteQueue.add(player);
        engine.combatSpriteQueue.add(mob);
        engine.executableSpriteQueue.add(player);
        engine.executableSpriteQueue.add(mob);
        engine.executableSpriteQueue.add(m);
        engine.physicsSpriteQueue.setMap(m);
        engine.physicsSpriteQueue.add(mob);
        engine.renderSpriteQueue.add(m);
        engine.renderSpriteQueue.add(mob);
        engine.renderSpriteQueue.add(player);
        engine.physicsSpriteQueue.add(player);
        engine.physicsSpriteQueue.addMDI(m);
    }

}
