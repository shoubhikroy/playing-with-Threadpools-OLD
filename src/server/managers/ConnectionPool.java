package server.managers;

import com.jolbox.bonecp.BoneCP;

public class ConnectionPool
{
    private static ConnectionPool ourInstance = new ConnectionPool();

    public static ConnectionPool getInstance()
    {
        return ourInstance;
    }

    BoneCP connectionPool;

    public BoneCP getConnectionPool()
    {
        return connectionPool;
    }

    public void setConnectionPool(BoneCP connectionPool)
    {
        this.connectionPool = connectionPool;
    }

    private ConnectionPool()
    {
    }
}
