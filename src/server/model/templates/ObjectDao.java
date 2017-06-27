package server.model.templates;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import server.cache.ConnectionPool;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ObjectDao
{
    protected Connection dbcon;
    protected PreparedStatement preparedStatement;
    protected Integer userId = 0;
    protected ResultSet rs;
    protected int resLength = 0;

    protected void _create(String select) throws SQLException
    {
        dbcon = (Connection) ConnectionPool.getInstance().getConnectionPool();
        preparedStatement = (PreparedStatement) dbcon.prepareStatement("SELECT MAX(userId) AS userId FROM " + select);
        ResultSet rs = preparedStatement.executeQuery();
        //iterate through the resultSet
        while (rs.next())
        {
            userId = rs.getInt("userId");
        }
        userId = userId + 1;
        System.out.println("Select statement executed, " + userId + " rows retrieved");
        preparedStatement.close();
        dbcon.close();
    }

    public void _ById(String select) throws SQLException
    {
        dbcon = (Connection) ConnectionPool.getInstance().getConnectionPool();
        preparedStatement = (PreparedStatement) dbcon.prepareStatement(select);
    }

    public void ById_() throws SQLException
    {
        resLength = 0;
        rs = preparedStatement.executeQuery();
        preparedStatement.close();
        dbcon.close();
        System.out.println("Select statement executed, " + resLength + " rows retrieved");
    }

    public void _AllUsers(String select) throws SQLException
    {
        dbcon = (Connection) ConnectionPool.getInstance().getConnectionPool();
        preparedStatement = (PreparedStatement) dbcon.prepareStatement(select);
        resLength = 0;
        rs = preparedStatement.executeQuery();
        System.out.println("Select statement executed, " + resLength + " rows retrieved");

        //close everything
        preparedStatement.close();
        dbcon.close();
    }
}