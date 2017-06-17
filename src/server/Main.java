package server;

import server.serverBase.Server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main
{
    public static void main(String[] args) throws IOException
    {

        System.out.println("-------- MySQL JDBC Connection Testing ------------");

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e)
        {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }

        System.out.println("MySQL JDBC Driver Registered!");
        Connection connection = null;

        try
        {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/gogs", "root", "ccbf15efc0b2083fcb3da3f0d673f4cd11432a4e66b95a2e");

        } catch (SQLException e)
        {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }

        if (connection != null)
        {
            System.out.println("You made it, take control your database now!");
        } else
        {
            System.out.println("Failed to make connection!");
        }

        Server s = new Server();
        s.run();
    }
}
