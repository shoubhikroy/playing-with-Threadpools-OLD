package server.jaxws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.ws.Endpoint;

public class JAXWSEndpoint implements Runnable
{
    Thread runner;
    Logger logger = LoggerFactory.getLogger(JAXWSEndpoint.class);

    public JAXWSEndpoint()
    {
    }

    public JAXWSEndpoint(String threadName)
    {
        runner = new Thread(this, threadName);
        logger.info(runner.getName());
        runner.start();
    }

    public void run()
    {
        logger.info("Attached Soap End Point");
        //Endpoint.publish("http://egfyz29u.xyz:9999/ws/rpc", new RemoteCallsImpl());
        Endpoint.publish("http://192.168.1.16 :9999/ws/rpc", new RemoteCallsImpl());
    }
}
