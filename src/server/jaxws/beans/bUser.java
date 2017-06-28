package server.jaxws.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bUser")
public class bUser
{
    @XmlElement(name = "username")
    String username;
    @XmlElement(name = "userid")
    int userid;
    @XmlElement(name = "online")
    boolean online;
    @XmlElement(name = "gameActive")
    boolean gameActive;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public int getUserid()
    {
        return userid;
    }

    public void setUserid(int userid)
    {
        this.userid = userid;
    }

    public boolean isOnline()
    {
        return online;
    }

    public void setOnline(boolean online)
    {
        this.online = online;
    }

    public boolean isGameActive()
    {
        return gameActive;
    }

    public void setGameActive(boolean gameActive)
    {
        this.gameActive = gameActive;
    }
}
