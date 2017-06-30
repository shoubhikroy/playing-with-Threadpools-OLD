package server.backgroundServices.services;

import server.jaxws.beans.wrappers.RegisterLoginInfo;
import server.jaxws.beans.wrappers.RegisterLoginResult;
import server.model.User;
import server.model.UserDao;

import java.util.concurrent.Callable;

public class updateFCMKey implements Callable<Boolean>
{
    private final RegisterLoginInfo input;

    public updateFCMKey(RegisterLoginInfo input)
    {
        this.input = input;
    }


    @Override
    public Boolean call() throws Exception
    {
        UserDao userDao = UserDao.getInstance();
        User user = userDao.getUserFromInput(input);
        if (user == null)
            return false;
        user.setFcmkey(input.getKey());
        user.save();
        return true;
    }
}
