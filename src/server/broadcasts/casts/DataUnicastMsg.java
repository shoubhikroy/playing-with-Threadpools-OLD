package server.broadcasts.casts;

import de.bytefish.fcmjava.model.options.FcmMessageOptions;
import de.bytefish.fcmjava.requests.data.DataUnicastMessage;
import de.bytefish.fcmjava.responses.FcmMessageResponse;
import server.broadcasts.FCMBroadcaster;

import java.util.Map;
import java.util.concurrent.Callable;

public class DataUnicastMsg implements Callable<FcmMessageResponse>
{
    DataUnicastMessage msg;

    public DataUnicastMsg(FcmMessageOptions options, String to, Map<String, String> data)
    {
        msg = new DataUnicastMessage(options, to, data);
    }

    @Override
    public FcmMessageResponse call() throws Exception
    {
        return FCMBroadcaster.getInstance().getClient().send(msg);
    }
}
