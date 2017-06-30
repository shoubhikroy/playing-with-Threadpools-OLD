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

public class getAllUsers implements Callable<ArrayList<User>>
{

    @Override
    public ArrayList<User> call()
    {
        ActiveUserList.getInstance().pruneList();
        ArrayList<User> userList = new ArrayList<>();
        Iterator it = ActiveUserList.getInstance().getActiveUserList().entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            activeUser aU = (activeUser) pair.getValue();
            userList.add(aU.getuser());
        }
        return userList;
    }
}
