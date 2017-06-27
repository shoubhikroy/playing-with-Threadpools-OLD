package server.rpc;

import server.beans.RegisterLoginInfo;
import server.beans.RegisterLoginResult;

import javax.jws.WebService;

//Service Implementation
@WebService(endpointInterface = "server.rpc.RemoteCalls")
public class RemoteCallsImpl implements RemoteCalls
{

    @Override
    public RegisterLoginResult registerLogin(RegisterLoginInfo li)
    {
        return null;
    }

}