package server.model;

import com.mysql.jdbc.PreparedStatement;
import server.cache.ConnectionPool;

import com.mysql.jdbc.Connection;
import server.model.templates.ObjectDao;

import java.sql.SQLException;
import java.util.HashMap;

public class UserDao extends ObjectDao
{
    private static UserDao ourInstance = new UserDao();

    public static UserDao getInstance()
    {
        return ourInstance;
    }

    private UserDao()
    {
    }

    public User createUser(String username, String password, String fcmkey, int wins, int losses) throws SQLException
    {
        _create("t_user;\n");

        User user = new User(userId, username, password, fcmkey, wins, losses);
        user.create();
        return user;
    }

    public User getUserById(Integer userId) throws SQLException
    {
        _ById("select username,password,fcmkey,wins,losses from t_user where userId = ?;\n");

        User user = null;
        preparedStatement.setInt(1, userId);

        ById_();

        while (rs.next())
        {
            resLength++;
            user = new User(userId, rs.getString("username"), rs.getString("password"), rs.getString("fcmkey"), rs.getInt("wins"), rs.getInt("losses"));
        }
        return user;
    }

    public HashMap<Integer, User> getAllUsers() throws SQLException
    {
        _AllUsers("select * from t_user;\n");

        HashMap<Integer, User> users = new HashMap<>();
        while (rs.next())
        {
            resLength++;
            users.put(rs.getInt("userId"), new User(rs.getInt("userId"), rs.getString("username"), rs.getString("password"), rs.getString("fcmkey"), rs.getInt("wins"), rs.getInt("losses")));
        }

        return users;
    }

    public User getUserByUsernamePassword(String username, String password) throws SQLException
    {
        dbcon = (Connection) ConnectionPool.getInstance().getConnectionPool();
        int userid = checkUserExistsReturnId(username, password);
        User user = null;
        if (userid > -1)
        {
            preparedStatement = (PreparedStatement) dbcon.prepareStatement("select * from t_user where userid = ?;\n");
            preparedStatement.setInt(1, userid);
            resLength = 0;
            rs = preparedStatement.executeQuery();

            while (rs.next())
            {
                resLength++;
                user = new User(userId, rs.getString("username"), rs.getString("password"), rs.getString("fcmkey"), rs.getInt("wins"), rs.getInt("losses"));
            }
            logger.info("Select statement executed, " + resLength + " rows retrieved");

            //close everything
            preparedStatement.close();
            dbcon.close();
        }
        return user;
    }

    protected Integer checkUserExistsReturnId(String username, String password) throws SQLException
    {
        if (checkUserExistsReturnId(username) != -1)
            return checkUserExistsReturnId(username);

        dbcon = (Connection) ConnectionPool.getInstance().getConnectionPool();
        String selectString = "select userId from t_user where username = ? and password = ?;\n";
        preparedStatement = (PreparedStatement) dbcon.prepareStatement(selectString);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        resLength = 0;
        rs = preparedStatement.executeQuery();
        int userId = -1;
        //iterate through the resultSet
        while (rs.next())
        {
            userId = rs.getInt("userId");
        }
        logger.info("Select statement executed, " + resLength + " rows retrieved");

        //close everything
        preparedStatement.close();
        dbcon.close();
        return userId == -1 ? -1 : userId;
    }

    protected Integer checkUserExistsReturnId(String username) throws SQLException
    {
        dbcon = (Connection) ConnectionPool.getInstance().getConnectionPool();
        String selectString = "select userId from t_user where username = ?;\n";
        preparedStatement = (PreparedStatement) dbcon.prepareStatement(selectString);
        preparedStatement.setString(1, username);
        resLength = 0;
        rs = preparedStatement.executeQuery();
        int userId = -1;
        //iterate through the resultSet
        while (rs.next())
        {
            userId = rs.getInt("userId");
        }
        logger.info("Select statement executed, " + resLength + " rows retrieved");

        //close everything
        preparedStatement.close();
        dbcon.close();
        return userId == -1 ? -1 : userId;
    }

    public Integer checkUserPassword(String username, String password) throws SQLException
    {
        dbcon = (Connection) ConnectionPool.getInstance().getConnectionPool();
        String selectString = "select userid from t_user where username = ? and password = ?;\n";
        PreparedStatement preparedStatement = (PreparedStatement) dbcon.prepareStatement(selectString);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        resLength = 0;
        //creating a ResultSet
        rs = preparedStatement.executeQuery();
        int userid = -1;
        //iterate through the resultSet
        while (rs.next())
        {
            userid = rs.getInt("userid");
        }
        logger.info("Select statement executed, " + resLength + " rows retrieved");

        //close everything
        preparedStatement.close();
        dbcon.close();
        return userid == -1 ? -1 : userid;
    }
}
