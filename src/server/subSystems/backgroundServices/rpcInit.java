package server.subSystems.backgroundServices;

import server.jaxws.beans.wrappers.RegisterLoginInfo;

import java.util.concurrent.Callable;

public class rpcInit implements Callable<Boolean>
{
    private final RegisterLoginInfo input;

    public rpcInit(RegisterLoginInfo input)
    {
        this.input = input;
    }


    @Override
    public Boolean call() throws Exception
    {
        // auth the user
        // check last login
        // move in and out of active
        return true;
    }
}
