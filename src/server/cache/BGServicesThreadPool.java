package server.cache;

import java.util.concurrent.ExecutorService;

public class BGServicesThreadPool
{
    private static BGServicesThreadPool ourInstance = new BGServicesThreadPool();
    ExecutorService threadPool;

    private BGServicesThreadPool()
    {
    }

    public static BGServicesThreadPool getInstance()
    {
        return ourInstance;
    }

    public ExecutorService getThreadPool()
    {
        return threadPool;
    }

    public void setThreadPool(ExecutorService threadPool)
    {
        this.threadPool = threadPool;
    }
}
