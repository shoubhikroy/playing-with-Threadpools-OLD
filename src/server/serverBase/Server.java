package server.serverBase;


import server.connectors.pushHandler;
import server.connectors.soapHandler;

public class Server
{
    public Server()
    {
    }

    private Thread soapHandler;
    private Thread sendMsg;

    public void run()
    {
        Thread soapHandler = new Thread(new soapHandler(), "soapHandler");
        Thread pushHandler = new Thread(new pushHandler(), "pushHandler");

        soapHandler.start();
        pushHandler.start();

        System.out.println(Thread.currentThread());
    }
}