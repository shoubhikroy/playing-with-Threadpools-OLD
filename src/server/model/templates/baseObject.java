package server.model.templates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public abstract class baseObject implements Serializable
{
    static final long serialVersionUID = 42L;
    protected Logger logger = LoggerFactory.getLogger(baseObject.class);
    protected Integer userId = 0;

    protected baseObject()
    {

    }

    public int getuserId()
    {
        return userId;
    }

    public void setuserId(Integer userId)
    {
        this.userId = userId;
    }

}
