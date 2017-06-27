package server.broadcasts.casts;

import de.bytefish.fcmjava.model.options.FcmMessageOptions;
import de.bytefish.fcmjava.requests.data.DataUnicastMessage;
import de.bytefish.fcmjava.requests.notification.NotificationPayload;
import server.broadcasts.FCMBroadcaster;
import server.jaxws.beans.RegisterLoginInfo;
import de.bytefish.fcmjava.responses.FcmMessageResponse;

import java.util.Map;
import java.util.concurrent.Callable;

public class DataUnicastNotificationMsg implements Callable<FcmMessageResponse>
{
    DataUnicastMessage msg;

    public DataUnicastNotificationMsg(FcmMessageOptions options, String to, Map<String, String> data, NotificationPayload np)
    {
        msg = new DataUnicastMessage(options, to, data, np);
    }

    @Override
    public FcmMessageResponse call() throws Exception
    {
        return FCMBroadcaster.getInstance().getClient().send(msg);
    }
}
