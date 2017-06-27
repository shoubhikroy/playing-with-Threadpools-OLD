package server.connectors;

import server.cache.FCMManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class pushHandler implements Runnable
{
    Thread runner;

    public pushHandler()
    {
    }

    public pushHandler(String threadName)
    {
        runner = new Thread(this, threadName);
        System.out.println(runner.getName());
        runner.start();
    }

    public void run()
    {
        while (true)
        {
            System.out.println("Started Push Handler");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try
            {
                br.readLine();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            try
            {
                FCMManager.getInstance().SendTopicMessageTest();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
