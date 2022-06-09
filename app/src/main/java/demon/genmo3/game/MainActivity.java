package demon.genmo3.game;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import demon.genmo3.R;
import demon.genmo3.engine.control.ButtonListener;
import demon.genmo3.engine.control.KeyEvent;
import demon.genmo3.engine.render.Texture;
import demon.genmo3.engine.sprite.EntitySprite;
import demon.genmo3.engine.sprite.MapSprite;
import demon.genmo3.engine.sprite.component.CollisionBox;
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
        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)actionBar.hide();
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
        player = new EntitySprite(999,500,TextureUtils.getTexture(R.drawable.player),50,50);
        Texture img =TextureUtils.getTexture(R.drawable.background);
        m = new MapSprite(img,2160,1080,player,0,0);
        player.setOnGround(false);
        engine.executableSpriteQueue.add(player);
        engine.renderSpriteQueue.add(m);
        engine.renderSpriteQueue.add(player);
        engine.renderSpriteQueue.add(player.getCollisionBox());
        engine.physicsSpriteQueue.add(player);
        //engine.renderSpriteQueue.add(m);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

}