package server.jaxws.beans;

import server.jaxws.beans.templates.beanBaseResult;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

public class bUserList
{

    @XmlElement(name = "user")
    public List<bUser> userlist;

    public List<bUser> getUserlist()
    {
        if (userlist == null)
        {
            userlist = new ArrayList<>();
        }
        return this.userlist;
    }
}