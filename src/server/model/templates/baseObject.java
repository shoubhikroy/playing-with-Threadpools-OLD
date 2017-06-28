package server.model.templates;

import java.sql.Connection;
import java.sql.PreparedStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.cache.ConnectionPool;

import java.io.Serializable;
import java.sql.SQLException;

public abstract class baseObject implements Serializable
{
    static final long serialVersionUID = 42L;
    protected Logger logger = LoggerFactory.getLogger(baseObject.class);
    protected Integer userId = 0;

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

}
