package server.backgroundServices;

import server.backgroundServices.services.authInput;
import server.backgroundServices.services.loginTimeout;
import server.backgroundServices.services.updateActiveUsers;
import server.backgroundServices.services.updateFCMKey;
import server.cache.BGServicesThreadPool;
import server.jaxws.beans.wrappers.RegisterLoginInfo;

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

    public String rpcInit(RegisterLoginInfo input) throws ExecutionException, InterruptedException
    {
        if (!authInput(input))
            return "auth failed";
        if (!loginTimeout(input))
            return "login timeout";
        if (!updateFCMKey(input))
            return "updateFCM failed";
        if (!updateActiveUsers(input))
            return "updateActiveUser failed";
        return "rpcInit passed";
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

    public boolean loginTimeout(RegisterLoginInfo input) throws ExecutionException, InterruptedException
    {
        return BGServicesThreadPool.getInstance().getThreadPool().submit(new loginTimeout(input)).get();
    }
}
