package demon.genmo3.engine.network;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

import demon.genmo3.engine.utils.TimerUtils;

public class ClientSocket
{
    private static Socket socket;
    static BufferedWriter bufferedWriter;
    static BufferedReader bufferedReader;

    public static void connect()
    {
        try
        {
            socket = new Socket("192.168.58.1",11451);
            bufferedWriter =new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public static void run()
    {
        if (socket == null)connect();
        String str;
        ArrayList<String> output = MessageCenter.getOutput();
        try
        {
            while (!output.isEmpty())
            {
                str = output.get(0);
                output.remove(0);
                bufferedWriter.write(str);
                bufferedWriter.flush();
            }
            str = bufferedReader.readLine();
            System.out.println("服务端说：" + str);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
