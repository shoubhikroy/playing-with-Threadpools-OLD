package server.jaxws.calls;

import server.jaxws.beans.RegisterLoginInfo;
import server.jaxws.beans.RegisterLoginResult;

import java.util.concurrent.Callable;

public class registerLogin implements Callable<RegisterLoginResult>
{
    private final RegisterLoginInfo input;

    public registerLogin(RegisterLoginInfo input)
    {
        this.input = input;
    }


    @Override
    public RegisterLoginResult call() throws Exception
    {
        return null;
    }
}
