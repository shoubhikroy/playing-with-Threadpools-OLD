package server.jaxws;

import server.cache.RPCThreadPool;
import server.jaxws.beans.bUser;
import server.jaxws.beans.wrappers.RegisterLoginInfo;
import server.jaxws.beans.wrappers.RegisterLoginResult;
import server.jaxws.beans.wrappers.UserListResult;
import server.jaxws.calls.registerLogin;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

//Service Implementation
@WebService(endpointInterface = "server.jaxws.RemoteCalls")
public class RemoteCallsImpl implements RemoteCalls
{
    @Override
    public RegisterLoginResult registerLogin(RegisterLoginInfo li) throws InterruptedException, ExecutionException
    {
        return RPCThreadPool.getInstance().getThreadPool().submit(new registerLogin(li)).get();
    }

    @Override
    public UserListResult getUserList(RegisterLoginInfo li) throws InterruptedException, ExecutionException
    {
        bUser bU = new bUser();
        bU.setOnline(true);
        bU.setUsername("asdf");
        bU.setUserid(1);
        bU.setGameActive(false);

        bUser bU2 = new bUser();
        bU.setOnline(false);
        bU.setUsername("qwer");
        bU.setUserid(2);
        bU.setGameActive(false);

        ArrayList<bUser> bUList = new ArrayList<>();
        UserListResult ulr = new UserListResult();
        ulr.userlist = bUList;
        ulr.setMessage("yay");
        ulr.setSuccessFlag(true);

        return ulr;
    }
}