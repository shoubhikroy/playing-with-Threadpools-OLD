package server.backgroundServices.services;

import server.jaxws.beans.wrappers.RegisterLoginInfo;

import java.util.concurrent.Callable;

public class authInput implements Callable<Boolean>
{
    private final RegisterLoginInfo input;

    public authInput(RegisterLoginInfo input)
    {
        this.input = input;
    }


    @Override
    public Boolean call() throws Exception
    {
        // auth the user
        // check last login
        return true;
    }
}
