package server.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import server.cache.ConnectionPool;
import server.model.templates.ObjectDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class UserDao extends ObjectDao
{
    private static UserDao ourInstance = new UserDao();

    private UserDao()
    {
    }

    public static UserDao getInstance()
    {
        return ourInstance;
    }

    public User createUser(String username, String password, String fcmkey, int wins, int losses) throws SQLException
    {
        Connection dbcon = (Connection) ConnectionPool.getInstance().getConnectionPool().getConnection();
        PreparedStatement preparedStatement = (PreparedStatement) dbcon.prepareStatement("SELECT MAX(userId) AS userId FROM t_user");

        ResultSet rs = preparedStatement.executeQuery();
        //iterate through the resultSet
        while (rs.next())
        {
            userId = rs.getInt("userId");
        }
        userId = userId + 1;
        logger.info("Select statement executed, " + userId + " rows retrieved");
        preparedStatement.close();
        dbcon.close();

        User user = new User(userId, username, password, fcmkey, wins, losses);
        user.create();
        return user;
    }

    public User getUserById(Integer userId) throws SQLException
    {
        Connection dbcon = (Connection) ConnectionPool.getInstance().getConnectionPool().getConnection();
        PreparedStatement preparedStatement = (PreparedStatement) dbcon.prepareStatement("select username,password,fcmkey,wins,losses from t_user where userId = ?;\n");
        User user = null;
        preparedStatement.setInt(1, userId);

        int resLength = 0;
        ResultSet rs = preparedStatement.executeQuery();

        logger.info("Select statement executed, " + resLength + " rows retrieved");

        while (rs.next())
        {
            resLength++;
            user = new User(userId, rs.getString("username"), rs.getString("password"), rs.getString("fcmkey"), rs.getInt("wins"), rs.getInt("losses"));
        }
        preparedStatement.close();
        dbcon.close();
        return user;
    }

    public HashMap<Integer, User> getAllUsers() throws SQLException
    {
        Connection dbcon = (Connection) ConnectionPool.getInstance().getConnectionPool().getConnection();
        PreparedStatement preparedStatement = (PreparedStatement) dbcon.prepareStatement("select * from t_user;\n");
        int resLength = 0;
        ResultSet rs = preparedStatement.executeQuery();
        logger.info("Select statement executed, " + resLength + " rows retrieved");

        HashMap<Integer, User> users = new HashMap<>();
        while (rs.next())
        {
            resLength++;
            users.put(rs.getInt("userId"), new User(rs.getInt("userId"), rs.getString("username"), rs.getString("password"), rs.getString("fcmkey"), rs.getInt("wins"), rs.getInt("losses")));
        }
        //close everything
        preparedStatement.close();
        dbcon.close();
        return users;
    }

    public User getUserByUsernamePassword(String username, String password) throws SQLException
    {
        Connection dbcon = (Connection) ConnectionPool.getInstance().getConnectionPool().getConnection();
        int userid = checkUserExistsReturnId(username, password);
        User user = null;
        if (userid > -1)
        {
            PreparedStatement preparedStatement = (PreparedStatement) dbcon.prepareStatement("select * from t_user where userid = ?;\n");
            preparedStatement.setInt(1, userid);
            int resLength = 0;
            ResultSet rs = preparedStatement.executeQuery();

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

    public Integer checkUserExistsReturnId(String username, String password) throws SQLException
    {
        Connection dbcon = (Connection) ConnectionPool.getInstance().getConnectionPool().getConnection();
        String selectString = "select userId from t_user where username = ? and password = ?;\n";
        PreparedStatement preparedStatement = (PreparedStatement) dbcon.prepareStatement(selectString);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        int resLength = 0;
        ResultSet rs = preparedStatement.executeQuery();
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

    public Integer checkUserExistsReturnId(String username) throws SQLException
    {
        Connection dbcon = (Connection) ConnectionPool.getInstance().getConnectionPool().getConnection();
        String selectString = "select userId from t_user where username = ?;\n";
        PreparedStatement preparedStatement = (PreparedStatement) dbcon.prepareStatement(selectString);
        preparedStatement.setString(1, username);
        int resLength = 0;
        ResultSet rs = preparedStatement.executeQuery();
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
}
