package server.cache;

import java.util.concurrent.ExecutorService;

public class FCMThreadPool
{
    private static FCMThreadPool ourInstance = new FCMThreadPool();

    public static FCMThreadPool getInstance()
    {
        return ourInstance;
    }

    private FCMThreadPool()
    {
    }

    ExecutorService threadPool;

    public ExecutorService getThreadPool()
    {
        return threadPool;
    }

    public void setThreadPool(ExecutorService threadPool)
    {
        this.threadPool = threadPool;
    }
}
