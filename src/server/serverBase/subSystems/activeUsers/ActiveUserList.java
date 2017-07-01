package server.serverBase.subSystems.activeUsers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        return MyWrapper.INSTANCE;
    }

    private ActiveUserList()
    {
    }

    private static class MyWrapper
    {
        static ActiveUserList INSTANCE = new ActiveUserList();
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
    List<Integer> activeUserId = Collections.synchronizedList(new ArrayList<>());

    public List<Integer> getActiveUserId()
    {
        return activeUserId;
    }

    public void setActiveUserId(List<Integer> activeUserId)
    {
        this.activeUserId = activeUserId;
    }

    Logger logger = LoggerFactory.getLogger(ActiveUserList.class);

    public void pruneList()
    {
        logger.info("pruneList");
        //iterate through hashmap and remote all users that have a time difference greater than currenttime - activetime
        for (int i = 0; i < activeUserId.size(); i++)
        {
            activeUser aU = activeUserList.get(activeUserId.get(i));
            long activeTime = aU.getActiveTime();
            long currentTime = java.time.Instant.now().getEpochSecond();
            long difference = currentTime - activeTime;
            if (difference > 300)
            {
                activeUserList.remove(activeUserId.get(i));
                activeUserId.remove(i);
            }
        }
    }

    public void addUser(User user)
    {
        logger.info("addUser");
        BGServicesThreadPool.getInstance().getThreadPool().submit(new updateActiveUser(user));
    }

    public ArrayList<User> getAllUsers() throws ExecutionException, InterruptedException
    {
        logger.info("getAllUsers");
        return BGServicesThreadPool.getInstance().getThreadPool().submit(new getAllUsers()).get();
    }

    public boolean isUserActive(User u) throws ExecutionException, InterruptedException
    {
        logger.info("isUserActive");
        return BGServicesThreadPool.getInstance().getThreadPool().submit(new isUserActive(u)).get();
    }
}
