package server.serverBase.subSystems.activeUsers.routines;

import server.jaxws.beans.wrappers.RegisterLoginInfo;
import server.model.User;
import server.model.UserDao;
import server.serverBase.subSystems.activeUsers.ActiveUserList;
import server.serverBase.subSystems.activeUsers.activeUser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;

public class isUserActive implements Callable<Boolean>
{
    private final User u;

    public isUserActive(User u)
    {
        this.u = u;
    }

    @Override
    public Boolean call()
    {
        ActiveUserList.getInstance().pruneList();

        if (ActiveUserList.getInstance().getActiveUserList().get(u.getuserId()) != null)
            return true;

        return false;
    }
}
