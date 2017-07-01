package server.jaxws.calls;

import server.backgroundServices.BackgroundServices;
import server.jaxws.beans.wrappers.RegisterLoginInfo;
import server.jaxws.beans.wrappers.RegisterLoginResult;
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
        User user = userDao.getUserById(userDao.checkUserPasswordExistsReturnId(username, password));
        //if new user
        if (null == user)
        {
            if (userDao.checkUserPasswordExistsReturnId(username) == -1)
            {
                //create user
                user = userDao.createUser(username, password, fcmkey, 0, 0, (int) java.time.Instant.now().getEpochSecond());
                BackgroundServices.getInstance().updateActiveUsers(input);
                rlr.setSuccessFlag(true);
                rlr.setMessage("user created");
            }
        }
        //if user exists
        else
        {
            //update key, and return success
            BackgroundServices.getInstance().updateActiveUsers(input);
            user.setFcmkey(fcmkey);
            user.setLastLogin((int) java.time.Instant.now().getEpochSecond());
            user.save();
            rlr.setSuccessFlag(true);
            rlr.setMessage("login success");
        }
        return rlr;
    }
}
