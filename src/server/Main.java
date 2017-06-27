package server;

import server.serverBase.Server;

import java.io.IOException;
import java.sql.SQLException;

public class Main
{
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException
    {
        Server s = new Server();
        s.start();
    }
}
