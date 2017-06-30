package server.backgroundServices.services;

import server.jaxws.beans.wrappers.RegisterLoginInfo;
import server.jaxws.beans.wrappers.RegisterLoginResult;
import server.model.User;
import server.model.UserDao;

import java.util.concurrent.Callable;

public class updateActiveUsers implements Callable<Boolean>
{
    private final RegisterLoginInfo input;

    public updateActiveUsers(RegisterLoginInfo input)
    {
        this.input = input;
    }


    @Override
    public Boolean call() throws Exception
    {
        // updateActiveUsers
        return true;
    }
}
