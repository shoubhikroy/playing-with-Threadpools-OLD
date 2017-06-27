package server;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import server.serverBase.Server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main
{
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException
    {
        Server s = new Server();
        s.start();
    }
}
