package server.model;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import server.cache.ConnectionPool;
import server.model.templates.baseObject;

import java.sql.SQLException;

public class User extends baseObject
{
    String username;
    String password;
    String fcmkey;
    int wins, losses;

    public User(Integer userId, String username, String password, String fcmkey, int wins, int losses)
    {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.fcmkey = fcmkey;
        this.wins = wins;
        this.losses = losses;
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

    public void save() throws SQLException
    {
        _save("UPDATE t_user SET username=?, password=?, fcmkey=?, wins=?, losses=? WHERE userId=?;\n");
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        preparedStatement.setString(3, fcmkey);
        preparedStatement.setInt(4, wins);
        preparedStatement.setInt(5, losses);
        preparedStatement.setInt(8, userId);
        save_();
    }

    public void create() throws SQLException
    {
        _create("INSERT INTO t_user VALUES(?,?,?,?,?,?); ");
        preparedStatement.setInt(1, userId);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        preparedStatement.setString(3, fcmkey);
        preparedStatement.setInt(4, wins);
        preparedStatement.setInt(5, losses);
        create_();
    }

    public void delete() throws SQLException
    {
        _delete("DELETE FROM t_user where username = ?; ");
        preparedStatement.setString(1, username);
        delete_();
    }
}
