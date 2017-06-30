package server.serverBase.subSystems.activeUsers.routines;

import server.jaxws.beans.wrappers.RegisterLoginInfo;
import server.model.User;
import server.model.UserDao;
import server.serverBase.subSystems.activeUsers.ActiveUserList;
import server.serverBase.subSystems.activeUsers.activeUser;

import java.util.concurrent.Callable;

public class updateActiveUser implements Runnable
{
    private final User u;

    public updateActiveUser(User u)
    {
        this.u = u;
    }


    @Override
    public void run()
    {
        ActiveUserList.getInstance().pruneList();
        ActiveUserList.getInstance().getActiveUserList().put(u.getuserId(), new activeUser(u));
    }
}
