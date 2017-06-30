package server.backgroundServices.services;

import server.jaxws.beans.wrappers.RegisterLoginInfo;
import server.jaxws.beans.wrappers.RegisterLoginResult;
import server.model.User;
import server.model.UserDao;

import java.util.concurrent.Callable;

public class loginTimeout implements Callable<Boolean>
{
    private final RegisterLoginInfo input;

    public loginTimeout(RegisterLoginInfo input)
    {
        this.input = input;
    }


    @Override
    public Boolean call() throws Exception
    {
        User u = UserDao.getInstance().getUserFromInput(input);
        long userTime = u.getLastLogin();
        long currentTime = java.time.Instant.now().getEpochSecond();
        long difference = currentTime - userTime;
        if (difference > 86400)
            return false;
        return true;
    }
}
