package server.backgroundServices;

import server.backgroundServices.services.rpcInit;
import server.cache.BGServicesThreadPool;
import server.jaxws.beans.wrappers.RegisterLoginInfo;
import server.model.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class BackgroundServices
{
    private static BackgroundServices ourInstance = new BackgroundServices();

    public static BackgroundServices getInstance()
    {
        return ourInstance;
    }

    private BackgroundServices()
    {
    }

    public boolean inniateRPC(RegisterLoginInfo input) throws ExecutionException, InterruptedException
    {
        return BGServicesThreadPool.getInstance().getThreadPool().submit(new rpcInit(input)).get();
    }
}
