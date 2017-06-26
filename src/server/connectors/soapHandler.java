package server.connectors;

import server.rpc.RemoteCallsImpl;

import javax.xml.ws.Endpoint;

public class soapHandler implements Runnable
{
    Thread runner;

    public soapHandler()
    {
    }

    public soapHandler(String threadName)
    {
        runner = new Thread(this, threadName);
        System.out.println(runner.getName());
        runner.start();
    }

    public void run()
    {
        System.out.println("Started Soap Handler");
        Endpoint.publish("http://egfyz29u.xyz:9999/ws/hello", new RemoteCallsImpl());
    }
}
