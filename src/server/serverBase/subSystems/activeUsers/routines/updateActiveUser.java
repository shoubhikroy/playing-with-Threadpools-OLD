package server.serverBase.subSystems.activeUsers.routines;

import org.omg.PortableInterceptor.ACTIVE;
import server.jaxws.beans.wrappers.RegisterLoginInfo;
import server.model.User;
import server.model.UserDao;
import server.serverBase.subSystems.activeUsers.ActiveUserList;
import server.serverBase.subSystems.activeUsers.activeUser;

import java.util.Map;
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
        Map<Integer, activeUser> x = ActiveUserList.getInstance().getActiveUserList();
        activeUser aU = ActiveUserList.getInstance().getActiveUserList().get(u.getuserId());
        if (null != aU)
        {
            aU.resetActiveTime();
        } else
        {
            ActiveUserList.getInstance().getActiveUserList().put(u.getuserId(), new activeUser(u));
            ActiveUserList.getInstance().getActiveUserId().add(u.getuserId());
        }
    }
}
