package server.model;

import javax.jws.WebService;

//Service Implementation
@WebService(endpointInterface = "server.model.RemoteCalls")
public class RemoteCallsImpl implements RemoteCalls
{

    @Override
    public String getHelloWorldAsString(String name)
    {
        return "Hello World JAX-WS " + name;
    }

}