package server.jaxws;

import server.jaxws.beans.RegisterLoginInfo;
import server.jaxws.beans.RegisterLoginResult;
import server.cache.RPCThreadPool;
import server.jaxws.calls.registerLogin;

import javax.jws.WebService;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;

//Service Implementation
@WebService(endpointInterface = "server.jaxws.RemoteCalls")
public class RemoteCallsImpl implements RemoteCalls
{
    @Override
    public RegisterLoginResult registerLogin(RegisterLoginInfo li) throws InterruptedException, ExecutionException
    {
        CompletionService<RegisterLoginResult> pool = new ExecutorCompletionService<>(RPCThreadPool.getInstance().getThreadPool());
        pool.submit(new registerLogin(li));
        return pool.take().get();
    }

}