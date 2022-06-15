package demon.genmo3.game;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import demon.genmo3.R;
import demon.genmo3.engine.control.ButtonListener;
import demon.genmo3.engine.control.KeyEvent;
import demon.genmo3.engine.render.DynamicTexture;
import demon.genmo3.engine.render.Texture;
import demon.genmo3.engine.sprite.EntitySprite;
import demon.genmo3.engine.sprite.component.map.GroundSprite;
import demon.genmo3.engine.sprite.component.map.MapSprite;
import demon.genmo3.engine.sprite.component.map.WallSprite;
import demon.genmo3.engine.utils.MapUtils;
import demon.genmo3.engine.utils.QueueUtils;
import demon.genmo3.engine.utils.TextureUtils;
import demon.genmo3.game.core.Engine;

public class MainActivity extends AppCompatActivity
{
    private Resources resources;
    private Engine engine;

    private ImageButton attack;
    private ImageButton jump;
    private ImageButton left;
    private ImageButton right;
    private ImageButton skill1;
    private ImageButton skill2;
    private ImageButton skill3;
    private ImageButton skill4;
    private ImageButton item;

    EntitySprite player;
    MapSprite m;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        appInit();
        gameInit();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void appInit()
    {
        //隐藏状态栏标题栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();
        setContentView(R.layout.activity_main);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        engine = findViewById(R.id.gameView);
        //初始化按钮
        attack = findViewById(R.id.attack);
        jump = findViewById(R.id.jump);
        left = findViewById(R.id.left);
        right = findViewById(R.id.right);
        skill1 = findViewById(R.id.skill1);
        skill2 = findViewById(R.id.skill2);
        skill3 = findViewById(R.id.skill3);
        skill4 = findViewById(R.id.skill4);
        item = findViewById(R.id.item);

        attack.setOnTouchListener(new ButtonListener(KeyEvent.ATTACK));
        jump.setOnTouchListener(new ButtonListener(KeyEvent.JUMP));
        left.setOnTouchListener(new ButtonListener(KeyEvent.LEFT));
        right.setOnTouchListener(new ButtonListener(KeyEvent.RIGHT));
        skill1.setOnTouchListener(new ButtonListener(KeyEvent.SKILL1));
        skill2.setOnTouchListener(new ButtonListener(KeyEvent.SKILL2));
        skill3.setOnTouchListener(new ButtonListener(KeyEvent.SKILL3));
        skill4.setOnTouchListener(new ButtonListener(KeyEvent.SKILL4));
        item.setOnTouchListener(new ButtonListener(KeyEvent.ITEM));
    }

    @SuppressLint("WrongViewCast")
    private void gameInit()
    {
        resources = this.getResources();
        TextureUtils.init(resources);
        //创建引擎部分
        DynamicTexture dynamicTexture = TextureUtils.getDynamicTexture(R.drawable.idle,4,4,15,100,true);
        player = new EntitySprite(1000, 500, dynamicTexture, 50, 50);
        Texture img = TextureUtils.getTexture(R.drawable.background);
        m = new MapSprite(img, 2160, 1080, player, 2080, 1580);
        MapUtils.changeMap(m);
        QueueUtils.init(engine);
        player.setOnGround(false);
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
        engine.executableSpriteQueue.add(player);
        engine.executableSpriteQueue.add(m);
        engine.physicsSpriteQueue.setMap(m);
        engine.renderSpriteQueue.add(m);
        engine.renderSpriteQueue.add(player);
        engine.renderSpriteQueue.add(player.getCollisionBox());
        engine.physicsSpriteQueue.add(player);
        engine.physicsSpriteQueue.addMDI(m);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

}