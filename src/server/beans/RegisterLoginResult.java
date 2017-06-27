package server.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegisterLoginResult")
public class RegisterLoginResult
{
    @XmlElement(name = "key")
    protected String key;
    @XmlElement(name = "successFlag")
    protected boolean successFlag;

    public String getKey()
    {
        return key;
    }

    public boolean isSuccessFlag()
    {
        return successFlag;
    }

    public void setSuccessFlag(boolean successFlag)
    {
        this.successFlag = successFlag;
    }

    public void setKey(String key)
    {
        this.key = key;
    }
}