package server.jaxws.beans.wrappers;

import server.jaxws.beans.bUser;
import server.jaxws.beans.bUserList;
import server.jaxws.beans.templates.beanBaseResult;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserListResult")
public class UserListResult extends beanBaseResult
{

    @XmlElement(name = "userlist")
    public bUserList userlist;
}