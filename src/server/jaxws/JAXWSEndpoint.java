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
        Endpoint.publish("http://egfyz29u.xyz:9999/ws/hello", new RemoteCallsImpl());
        //Endpoint.publish("http://localhost:9999/ws/hello", new RemoteCallsImpl());
    }
}
