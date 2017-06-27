package server.connectors;

import server.rpc.RemoteCallsImpl;

import javax.xml.ws.Endpoint;

public class jaxws implements Runnable
{
    Thread runner;

    public jaxws()
    {
    }

    public jaxws(String threadName)
    {
        runner = new Thread(this, threadName);
        System.out.println(runner.getName());
        runner.start();
    }

    public void run()
    {
        System.out.println("Attached Soap End Point");
        Endpoint.publish("http://egfyz29u.xyz:9999/ws/hello", new RemoteCallsImpl());
    }
}
