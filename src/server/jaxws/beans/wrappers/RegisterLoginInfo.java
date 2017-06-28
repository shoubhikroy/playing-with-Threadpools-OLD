package server.jaxws.beans.wrappers;

import server.jaxws.beans.templates.beanBaseInput;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegisterLoginInfo")
public class RegisterLoginInfo extends beanBaseInput
{
    @XmlElement(name = "userName")
    protected String userName;
    @XmlElement(name = "password")
    protected String password;

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String value)
    {
        this.userName = value;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String value)
    {
        this.password = value;
    }
}