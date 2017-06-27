package server.cache;

import com.jolbox.bonecp.BoneCP;

public class ConnectionPool
{
    private static ConnectionPool ourInstance = new ConnectionPool();
    BoneCP connectionPool;

    private ConnectionPool()
    {
    }

    public static ConnectionPool getInstance()
    {
        return ourInstance;
    }

    public BoneCP getConnectionPool()
    {
        return connectionPool;
    }

    public void setConnectionPool(BoneCP connectionPool)
    {
        this.connectionPool = connectionPool;
    }
}
