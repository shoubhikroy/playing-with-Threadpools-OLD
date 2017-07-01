package server.backgroundServices.services;

import server.jaxws.beans.wrappers.RegisterLoginInfo;
import server.model.UserDao;

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
        String username = input.getUserName();
        String password = input.getPassword();
        UserDao userDao = UserDao.getInstance();
        int userid = userDao.checkUserPasswordExistsReturnId(username,password);

        if (userid > -1)
            return true;

        return false;
    }
}
