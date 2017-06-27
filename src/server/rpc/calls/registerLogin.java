package server.rpc.calls;

import server.beans.RegisterLoginInfo;
import server.beans.RegisterLoginResult;

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
