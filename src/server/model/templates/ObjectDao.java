package server.model.templates;

import java.sql.Connection;
import java.sql.PreparedStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.cache.ConnectionPool;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ObjectDao
{
    protected Integer userId = 0;
    protected Logger logger = LoggerFactory.getLogger(ObjectDao.class);

}