package server.jaxws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.cache.RPCThreadPool;
import server.jaxws.beans.wrappers.RegisterLoginInfo;
import server.jaxws.beans.wrappers.RegisterLoginResult;
import server.jaxws.beans.wrappers.UserListResult;
import server.jaxws.calls.getUserList;
import server.jaxws.calls.registerLogin;

import javax.jws.WebService;
import java.util.concurrent.ExecutionException;

//Service Implementation
@WebService(endpointInterface = "server.jaxws.RemoteCalls")
public class RemoteCallsImpl implements RemoteCalls
{
    @Override
    public RegisterLoginResult registerLogin(RegisterLoginInfo li) throws InterruptedException, ExecutionException
    {
        Logger logger = LoggerFactory.getLogger(RemoteCallsImpl.class);
        logger.info("registerLogin");

        return RPCThreadPool.getInstance().getThreadPool().submit(new registerLogin(li)).get();
    }

    @Override
    public UserListResult getUserList(RegisterLoginInfo li) throws InterruptedException, ExecutionException
    {
        Logger logger = LoggerFactory.getLogger(RemoteCallsImpl.class);
        logger.info("getUserList");

        return RPCThreadPool.getInstance().getThreadPool().submit(new getUserList(li)).get();
    }
}