package server.jaxws;

import javax.xml.ws.Endpoint;

public class JAXWSEndpoint implements Runnable
{
    Thread runner;

    public JAXWSEndpoint()
    {
    }

    public JAXWSEndpoint(String threadName)
    {
        runner = new Thread(this, threadName);
        System.out.println(runner.getName());
        runner.start();
    }

    public void run()
    {
        System.out.println("Attached Soap End Point");
        //Endpoint.publish("http://egfyz29u.xyz:9999/ws/hello", new RemoteCallsImpl());
        Endpoint.publish("http://localhost:9999/ws/hello", new RemoteCallsImpl());
    }
}
