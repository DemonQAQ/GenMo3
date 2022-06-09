package demon.genmo3.engine.control;

import java.util.HashSet;
import java.util.Set;

public enum Keys
{
    LEFT(KeyEvent.LEFT),
    RIGHT(KeyEvent.RIGHT),
    JUMP(KeyEvent.JUMP),
    ATTACK(KeyEvent.ATTACK),
    SKILL1(KeyEvent.SKILL1),
    SKILL2(KeyEvent.SKILL2),
    SKILL3(KeyEvent.SKILL3),
    SKILL4(KeyEvent.SKILL4),
    ITEM(KeyEvent.ITEM),
    INVENTORY(KeyEvent.INVENTORY);

    private final static Set<Integer> keySet = new HashSet<>();
    private int keyValue;

    Keys(int keys)
    {
        this.keyValue = keys;
    }

    public boolean use()
    {
        return keySet.contains(keyValue);
    }
    public static void add(int keyCode)
    {
        keySet.add(keyCode);
    }
    public static void remove(int keyCode)
    {
        keySet.remove(keyCode);
    }
}



