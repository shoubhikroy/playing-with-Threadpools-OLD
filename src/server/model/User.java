package server.model;

import server.cache.ConnectionPool;
import server.model.templates.baseObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class User extends baseObject
{
    String username;
    String password;
    String fcmkey;
    int wins;
    int losses;

    public int getLastLogin()
    {
        return lastLogin;
    }

    public void setLastLogin(int lastLogin)
    {
        this.lastLogin = lastLogin;
    }

    int lastLogin;

    public User(Integer userId, String username, String password, String fcmkey, int wins, int losses, int lastLogin)
    {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.fcmkey = fcmkey;
        this.wins = wins;
        this.losses = losses;
        this.lastLogin = lastLogin;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getFcmkey()
    {
        return fcmkey;
    }

    public void setFcmkey(String fcmkey)
    {
        this.fcmkey = fcmkey;
    }

    public int getWins()
    {
        return wins;
    }

    public void setWins(int wins)
    {
        this.wins = wins;
    }

    public int getLosses()
    {
        return losses;
    }

    public void setLosses(int losses)
    {
        this.losses = losses;
    }

    public void save()
    {
        PreparedStatement preparedStatement;
        Connection dbcon;
        try
        {
            dbcon = (Connection) ConnectionPool.getInstance().getConnectionPool().getConnection();
            preparedStatement = (PreparedStatement) dbcon.prepareStatement("UPDATE t_user SET username=?, password=?, fcmkey=?, wins=?, losses=?, lastlogin=? WHERE userId=?;\n");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, fcmkey);
            preparedStatement.setInt(4, wins);
            preparedStatement.setInt(5, losses);
            preparedStatement.setInt(6, userId);
            preparedStatement.setInt(7, lastLogin);
            preparedStatement.executeUpdate();
            logger.info("user saved");

            preparedStatement.close();
            dbcon.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void create()
    {
        Connection dbcon;
        PreparedStatement preparedStatement;
        try
        {
            dbcon = (Connection) ConnectionPool.getInstance().getConnectionPool().getConnection();
            preparedStatement = (PreparedStatement) dbcon.prepareStatement("INSERT INTO t_user VALUES(?,?,?,?,?,?,?); ");
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, fcmkey);
            preparedStatement.setInt(5, wins);
            preparedStatement.setInt(6, losses);
            preparedStatement.setInt(7, lastLogin);

            preparedStatement.executeUpdate();

            logger.info("user added");

            preparedStatement.close();
            dbcon.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void delete()
    {
        Connection dbcon;
        try
        {
            dbcon = (Connection) ConnectionPool.getInstance().getConnectionPool().getConnection();
            PreparedStatement preparedStatement = (PreparedStatement) dbcon.prepareStatement("DELETE FROM t_user where username = ?; ");
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
            logger.info("user deleted");

            preparedStatement.close();
            dbcon.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
