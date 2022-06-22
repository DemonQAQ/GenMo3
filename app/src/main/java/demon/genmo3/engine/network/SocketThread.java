package demon.genmo3.engine.network;

public class SocketThread implements Runnable
{
    @Override
    public void run()
    {
        ClientSocket.run();
    }
}
