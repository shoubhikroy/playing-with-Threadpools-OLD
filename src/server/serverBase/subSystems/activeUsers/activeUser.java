package server.serverBase.subSystems.activeUsers;

import server.model.User;

public class activeUser
{
    long activeTime;
    User u;

    public activeUser(User u)
    {
        this.u = u;
        activeTime = java.time.Instant.now().getEpochSecond();
    }

    public long getActiveTime()
    {
        return activeTime;
    }

    public void setActiveTime(long activeTime)
    {
        this.activeTime = activeTime;
    }

    public User getuser()
    {
        return u;
    }

    public void setUser(User u)
    {
        this.u = u;
    }
}