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
import demon.genmo3.engine.network.ClientSocket;
import demon.genmo3.game.core.Engine;
import demon.genmo3.engine.sprite.entity.LocalPlayer;
import demon.genmo3.game.manager.ContentManager;

public class MainActivity extends AppCompatActivity
{
    private Resources resources;
    private Engine engine;
    private ImageButton attack;
    private ImageButton jump;
    private ImageButton left;
    private ImageButton right;
    public ImageButton skill1;
    public ImageButton skill2;
    public ImageButton skill3;
    public ImageButton skill4;
    public ImageButton item;
    public LocalPlayer player;

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
        engine = findViewById(R.id.gameView);
        resources = this.getResources();
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
        player = ContentManager.init(resources,engine);
        buttonCheck();
    }

    private void buttonCheck()
    {
        if (player.getSkill1()!=null)this.skill1.setBackgroundResource(player.getSkill1().icon_up);
        if (player.getSkill2()!=null)this.skill2.setBackgroundResource(player.getSkill2().icon_up);
        if (player.getSkill3()!=null)this.skill3.setBackgroundResource(player.getSkill3().icon_up);
        if (player.getSkill4()!=null)this.skill4.setBackgroundResource(player.getSkill4().icon_up);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

}