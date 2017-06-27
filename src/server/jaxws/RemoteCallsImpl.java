package server.jaxws;

import server.jaxws.beans.RegisterLoginInfo;
import server.jaxws.beans.RegisterLoginResult;
import server.cache.RPCThreadPool;
import server.jaxws.calls.registerLogin;

import javax.jws.WebService;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;

//Service Implementation
@WebService(endpointInterface = "server.jaxws.RemoteCalls")
public class RemoteCallsImpl implements RemoteCalls
{
    @Override
    public RegisterLoginResult registerLogin(RegisterLoginInfo li) throws InterruptedException, ExecutionException
    {
        return RPCThreadPool.getInstance().getThreadPool().submit(new registerLogin(li)).get();
    }

}