package server.serverBase;


import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import server.jaxws.JAXWSEndpoint;
import server.cache.FCMThreadPool;
import server.cache.RPCThreadPool;
import server.cache.ConnectionPool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.concurrent.Executors;

public class Server
{
    BoneCPConfig config;
    ConnectionPool cp;
    RPCThreadPool rp;
    FCMThreadPool fp;

    public Server() throws ClassNotFoundException, SQLException
    {
        //SETUP RELEVANT POOLS:
        //BoneCP Connection Pool:
        Class.forName("com.mysql.jdbc.Driver");
        config = new BoneCPConfig();
        config.setJdbcUrl("jdbc:mysql://104.236.229.251:3306/card?useSSL=false");
        config.setUsername("XAR");
        config.setPassword("barb");
        cp = ConnectionPool.getInstance();
        cp.setConnectionPool(new BoneCP(config));

        //RPC Callable Pool:
        rp = RPCThreadPool.getInstance();
        rp.setThreadPool(Executors.newFixedThreadPool(5));

        //FCM Runnable Pool:
        fp = FCMThreadPool.getInstance();
        fp.setThreadPool(Executors.newFixedThreadPool(5));
    }

    private Thread jaxws;

    public void start() throws IOException
    {
        jaxws = new Thread(new JAXWSEndpoint(), "JAXWSEndpoint");
        jaxws.start();

        System.out.println(Thread.currentThread());
        boolean flag = true;
        while (flag)
        {
            System.out.println("Input Command: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String command = br.readLine();
            switch (command)
            {
                case "help":
                    System.out.println("Available Commands:");
                    break;
                default:
                    System.out.println("Invalid Command.");
            }
        }
        cp.getConnectionPool().shutdown();
        rp.getThreadPool().shutdown();
        fp.getThreadPool().shutdown();
    }
}