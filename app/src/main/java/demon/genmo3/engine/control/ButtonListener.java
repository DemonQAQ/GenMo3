package demon.genmo3.engine.control;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class ButtonListener implements View.OnTouchListener
{
    private int keyValue;

    public ButtonListener(int keyValue)
    {
        this.keyValue = keyValue;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent)
    {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN)
        {
            Log.d("down", String.valueOf(keyValue));
            Keys.add(keyValue);
        }
        if (motionEvent.getAction() == MotionEvent.ACTION_UP)
        {
            Log.d("up", String.valueOf(keyValue));
            Keys.remove(keyValue);
        }
        return false;
    }
}
