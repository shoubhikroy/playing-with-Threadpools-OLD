package server;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import server.serverBase.Server;

import java.io.IOException;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class Main
{
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException
    {
        Server s = new Server();
        s.start();
    }
}
