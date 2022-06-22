package demon.genmo3.engine.network;

import java.util.ArrayList;

import demon.genmo3.engine.sprite.EntitySprite;

public class MessageCenter
{
    private static final ArrayList<String> output = new ArrayList<>();
    private static final ArrayList<String> input = new ArrayList<>();

    public static void sendMessage(String message, Object o)
    {
        String name;
        if (o instanceof EntitySprite)
        {
            name = ((EntitySprite) o).uuid;
        } else name = "demon";
        String str = name + "," + message + "\n";
        output.add(str);
    }

    public static ArrayList<String> getOutput()
    {
        return output;
    }

    public static void getMessage(String message)
    {
        input.add(message);
    }
}
