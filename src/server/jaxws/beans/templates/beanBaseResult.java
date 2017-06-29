package server.jaxws.beans.templates;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlTransient
public abstract class beanBaseResult
{
    @XmlElement(name = "key")
    protected String key;

    @XmlElement(name = "successFlag")
    protected boolean successFlag;

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public boolean isSuccessFlag()
    {
        return successFlag;
    }

    public void setSuccessFlag(boolean successFlag)
    {
        this.successFlag = successFlag;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    @XmlElement(name = "message")
    protected String message;
}
