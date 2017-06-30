package server.jaxws.calls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.backgroundServices.BackgroundServices;
import server.jaxws.beans.bUser;
import server.jaxws.beans.bUserList;
import server.jaxws.beans.wrappers.RegisterLoginInfo;
import server.jaxws.beans.wrappers.UserListResult;
import server.model.User;
import server.model.UserDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;

public class getUserList implements Callable<UserListResult>
{
    private final RegisterLoginInfo input;

    public getUserList(RegisterLoginInfo input)
    {
        this.input = input;
    }


    @Override
    public UserListResult call() throws Exception
    {
        UserListResult ulr = new UserListResult();
        Logger logger = LoggerFactory.getLogger(getUserList.class);
        ulr.setSuccessFlag(false);
        String rpcInit = BackgroundServices.getInstance().rpcInit(input);
        if (rpcInit.equals("rpcInit passed"))
        {
            logger.info("rpcInitFailed: " + rpcInit);
            ulr.setMessage("rpcInitFailed: " + rpcInit);
            return ulr;
        }

        ArrayList<bUser> bUList = new ArrayList<>();
        HashMap<Integer, User> userList = UserDao.getInstance().getAllUsers();
        Iterator it = userList.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            User u = (User) pair.getValue();

            bUser bU = new bUser();

            bU.setOnline(false);
            bU.setUsername(u.getUsername());
            bU.setUserid(u.getuserId());
            bU.setGameActive(false);

            bUList.add(bU);
            it.remove();
        }

        //bind the list
        bUserList userListBinding = new bUserList();
        userListBinding.userlist = bUList;
        ulr.userlist = userListBinding;
        ulr.setSuccessFlag(true);
        ulr.setMessage("fullUserList");
        return ulr;
    }
}
