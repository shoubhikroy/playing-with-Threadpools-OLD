package server.jaxws.calls;

import server.jaxws.beans.wrappers.RegisterLoginInfo;
import server.jaxws.beans.wrappers.UserListResult;
import server.jaxws.calls.templates.RPCCalls;
import server.model.User;
import server.model.UserDao;

import java.util.concurrent.Callable;

public class GetUserList implements Callable<UserListResult>
{
    private final RegisterLoginInfo input;

    public GetUserList(RegisterLoginInfo input)
    {
        this.input = input;
    }


    @Override
    public UserListResult call() throws Exception
    {
        String fcmkey = input.getKey();
        String username = input.getUserName();
        String password = input.getPassword();

        UserListResult rlr = new UserListResult();
        rlr.setKey(fcmkey);
        rlr.setSuccessFlag(false);

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
            }
        }
        //if user exists
        else
        {
            //update key, and return success
            user.setFcmkey(fcmkey);
            user.save();
            rlr.setSuccessFlag(true);
        }
        return rlr;
    }
}
