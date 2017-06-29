package server.jaxws.calls;

import server.jaxws.beans.wrappers.RegisterLoginInfo;
import server.jaxws.beans.wrappers.RegisterLoginResult;
import server.jaxws.calls.templates.RPCCalls;
import server.model.User;
import server.model.UserDao;

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
        String fcmkey = input.getKey();
        String username = input.getUserName();
        String password = input.getPassword();

        RegisterLoginResult rlr = new RegisterLoginResult();
        rlr.setKey(fcmkey);
        rlr.setSuccessFlag(false);
        rlr.setMessage("error");
        //check if user exists
        UserDao userDao = UserDao.getInstance();
        User user = userDao.getUserById(userDao.checkUserExistsReturnId(username, password));
        //if new user
        if (null == user)
        {
            if (userDao.checkUserExistsReturnId(username) == -1)
            {
                //create user
                user = userDao.createUser(username, password, fcmkey, 0, 0);
                rlr.setSuccessFlag(true);
                rlr.setMessage("user created");
            }
        }
        //if user exists
        else
        {
            //update key, and return success
            user.setFcmkey(fcmkey);
            user.save();
            rlr.setSuccessFlag(true);
            rlr.setMessage("login success");
        }
        return rlr;
    }
}
