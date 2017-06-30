package server.backgroundServices;

import server.backgroundServices.services.authInput;
import server.backgroundServices.services.updateActiveUsers;
import server.backgroundServices.services.updateFCMKey;
import server.cache.BGServicesThreadPool;
import server.jaxws.beans.wrappers.RegisterLoginInfo;w

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

    public boolean authInput(RegisterLoginInfo input) throws ExecutionException, InterruptedException
    {
        return BGServicesThreadPool.getInstance().getThreadPool().submit(new authInput(input)).get();
    }

    public boolean updateFCMKey(RegisterLoginInfo input) throws ExecutionException, InterruptedException
    {
        return BGServicesThreadPool.getInstance().getThreadPool().submit(new updateFCMKey(input)).get();
    }

    public boolean updateActiveUsers(RegisterLoginInfo input) throws ExecutionException, InterruptedException
    {
        return BGServicesThreadPool.getInstance().getThreadPool().submit(new updateActiveUsers(input)).get();
    }
}
