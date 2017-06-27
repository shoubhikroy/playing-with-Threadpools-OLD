package server.rpc;

import server.beans.RegisterLoginInfo;
import server.beans.RegisterLoginResult;
import server.cache.RPCPool;
import server.rpc.calls.registerLogin;

import javax.jws.WebService;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;

//Service Implementation
@WebService(endpointInterface = "server.rpc.RemoteCalls")
public class RemoteCallsImpl implements RemoteCalls
{
    @Override
    public RegisterLoginResult registerLogin(RegisterLoginInfo li) throws InterruptedException, ExecutionException
    {
        CompletionService<RegisterLoginResult> pool = new ExecutorCompletionService<>(RPCPool.getInstance().getThreadPool());
        pool.submit(new registerLogin(li));
        return pool.take().get();
    }

}