package server.serverBase.subSystems.activeUsers;

public class ActiveUserList
{
    private static ActiveUserList ourInstance = new ActiveUserList();

    public static ActiveUserList getInstance()
    {
        return ourInstance;
    }

    private ActiveUserList()
    {
    }
}
