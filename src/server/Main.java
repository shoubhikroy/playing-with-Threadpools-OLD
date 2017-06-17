package server;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.bytefish.fcmjava.client.FcmClient;
import de.bytefish.fcmjava.client.settings.PropertiesBasedSettings;
import de.bytefish.fcmjava.model.options.FcmMessageOptions;
import de.bytefish.fcmjava.model.topics.Topic;
import de.bytefish.fcmjava.requests.notification.NotificationUnicastMessage;
import de.bytefish.fcmjava.requests.topic.TopicUnicastMessage;
import de.bytefish.fcmjava.responses.TopicMessageResponse;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Assert;
import org.junit.Test;
import server.serverBase.Server;

import javax.xml.ws.Endpoint;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Duration;

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
