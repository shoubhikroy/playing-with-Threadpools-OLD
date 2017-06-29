package server.jaxws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.cache.RPCThreadPool;
import server.jaxws.beans.bUser;
import server.jaxws.beans.bUserList;
import server.jaxws.beans.wrappers.RegisterLoginInfo;
import server.jaxws.beans.wrappers.RegisterLoginResult;
import server.jaxws.beans.wrappers.UserListResult;
import server.jaxws.calls.registerLogin;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;
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
        bUser bU = new bUser();
        bU.setOnline(true);
        bU.setUsername("asdf");
        bU.setUserid(1);
        bU.setGameActive(false);

        bUser bU2 = new bUser();
        bU2.setOnline(false);
        bU2.setUsername("qwer");
        bU2.setUserid(2);
        bU2.setGameActive(false);

        ArrayList<bUser> bUList = new ArrayList<>();
        bUList.add(bU);
        bUList.add(bU2);

        UserListResult ulr = new UserListResult();
        bUserList userListBinding = new bUserList();
        userListBinding.userlist = bUList;
        ulr.userlist = userListBinding;
        ulr.setMessage("yay");
        ulr.setSuccessFlag(true);

        return ulr;
    }
}