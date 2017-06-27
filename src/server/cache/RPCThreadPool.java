package server.cache;

import java.util.concurrent.ExecutorService;

public class RPCThreadPool
{
    private static RPCThreadPool ourInstance = new RPCThreadPool();

    public static RPCThreadPool getInstance()
    {
        return ourInstance;
    }

    private RPCThreadPool()
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
