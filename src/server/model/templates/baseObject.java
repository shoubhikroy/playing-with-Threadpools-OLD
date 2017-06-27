package server.model.templates;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import server.cache.ConnectionPool;

import java.io.Serializable;
import java.sql.SQLException;

public abstract class baseObject implements Serializable
{
    protected PreparedStatement preparedStatement;
    Connection dbcon;
    protected Integer userId = 0;

    static final long serialVersionUID = 42L;

    protected baseObject()
    {

    }

    protected int getuserId()
    {
        return userId;
    }

    protected void setuserId(Integer userId)
    {
        this.userId = userId;
    }

    protected void _save(String update) throws SQLException
    {
        dbcon = (Connection) ConnectionPool.getInstance().getConnectionPool();
        preparedStatement = (PreparedStatement) dbcon.prepareStatement(update);
    }

    protected void save_() throws SQLException
    {
        preparedStatement.executeUpdate();
        System.out.println("user saved");

        preparedStatement.close();
        dbcon.close();
    }

    protected void _create(String insert) throws SQLException
    {
        dbcon = (Connection) ConnectionPool.getInstance().getConnectionPool();
        preparedStatement = (PreparedStatement) dbcon.prepareStatement(insert);
    }

    protected void create_() throws SQLException
    {
        preparedStatement.executeUpdate();
        System.out.println("user added");

        preparedStatement.close();
        dbcon.close();
    }

    protected void _delete(String delete) throws SQLException
    {
        dbcon = (Connection) ConnectionPool.getInstance().getConnectionPool();
        PreparedStatement preparedStatement = (PreparedStatement) dbcon.prepareStatement(delete);
    }

    protected void delete_() throws SQLException
    {
        preparedStatement.executeUpdate();
        System.out.println("user deleted");

        preparedStatement.close();
        dbcon.close();
    }
}
