package server.serverBase.subSystems.activeUsers;

import server.cache.BGServicesThreadPool;
import server.model.User;
import server.serverBase.subSystems.activeUsers.routines.updateActiveUser;
import server.serverBase.subSystems.activeUsers.routines.getAllUsers;
import server.serverBase.subSystems.activeUsers.routines.isUserActive;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class ActiveUserList
{
    private static ActiveUserList ourInstance = new ActiveUserList();

    public static ActiveUserList getInstance()
    {
        return ourInstance;
    }

    public ActiveUserList()
    {
    }


    public Map<Integer, activeUser> getActiveUserList()
    {
        return activeUserList;
    }

    public void setActiveUserList(Map<Integer, activeUser> activeUserList)
    {
        this.activeUserList = activeUserList;
    }

    Map<Integer, activeUser> activeUserList = Collections.synchronizedMap(new HashMap<>());

    public void pruneList()
    {
        //iterate through hashmap and remote all users that have a time difference greater than currenttime - activetime
        Iterator it = activeUserList.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            activeUser aU = (activeUser) pair.getValue();
            long activeTime = aU.getActiveTime();
            long currentTime = java.time.Instant.now().getEpochSecond();
            long difference = currentTime - activeTime;
            if (difference > 300)
            {
                //remove from hashmap
                activeUserList.remove(pair.getKey());
            }
            it.remove();
        }
    }

    public void addUser(User user)
    {
        BGServicesThreadPool.getInstance().getThreadPool().submit(new updateActiveUser(user));
    }

    public ArrayList<User> getAllUsers() throws ExecutionException, InterruptedException
    {
        return BGServicesThreadPool.getInstance().getThreadPool().submit(new getAllUsers()).get();
    }

    public boolean isUserActive(User u) throws ExecutionException, InterruptedException
    {
        return BGServicesThreadPool.getInstance().getThreadPool().submit(new isUserActive(u)).get();
    }
}
