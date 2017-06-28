package server.jaxws.beans.templates;

import javax.xml.bind.annotation.XmlElement;

public abstract class beanBaseInput
{
    @XmlElement(name = "key")
    protected String key;

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }
}
