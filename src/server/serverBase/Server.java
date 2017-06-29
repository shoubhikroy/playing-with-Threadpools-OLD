package server.serverBase;


import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.cache.BGServicesThreadPool;
import server.cache.ConnectionPool;
import server.cache.FCMThreadPool;
import server.cache.RPCThreadPool;
import server.jaxws.JAXWSEndpoint;

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
    BGServicesThreadPool bp;
    Logger logger = LoggerFactory.getLogger(Server.class);
    private Thread jaxws;

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
        logger.info("created connection pool");

        //RPC Callable Pool:
        //# of calls
        rp = RPCThreadPool.getInstance();
        rp.setThreadPool(Executors.newFixedThreadPool(2));
        logger.info("created RPCThreadPool");

        //FCM Runnable Pool:
        fp = FCMThreadPool.getInstance();
        fp.setThreadPool(Executors.newFixedThreadPool(4));
        logger.info("created FCMThreadPool");

        //BG Services Pool
        bp = BGServicesThreadPool.getInstance();
        bp.setThreadPool(Executors.newFixedThreadPool(4));
        logger.info("created FCMThreadPool");
    }

    public void start() throws IOException
    {
        jaxws = new Thread(new JAXWSEndpoint(), "JAXWSEndpoint");
        jaxws.start();

        System.out.println(Thread.currentThread());
        boolean flag = true;
        while (flag)
        {
            logger.info("Input Command: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String command = br.readLine();
            switch (command)
            {
                case "help":
                    logger.info("Available Commands:");
                    logger.info("shutdown - close pools and exit:");
                    break;
                case "shutdown":
                    flag = false;
                    logger.info("shutting down");
                    break;
                default:
                    logger.info("Invalid Command.");
            }
        }
        cp.getConnectionPool().shutdown();
        rp.getThreadPool().shutdown();
        fp.getThreadPool().shutdown();
        System.exit(0);
    }
}