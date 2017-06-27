package server.cache;

import java.util.concurrent.ExecutorService;

public class RPCPool
{
    private static RPCPool ourInstance = new RPCPool();

    public static RPCPool getInstance()
    {
        return ourInstance;
    }

    private RPCPool()
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
