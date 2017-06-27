package server.broadcasts.casts;

import de.bytefish.fcmjava.model.options.FcmMessageOptions;
import de.bytefish.fcmjava.requests.data.DataMulticastMessage;
import de.bytefish.fcmjava.requests.data.DataUnicastMessage;
import de.bytefish.fcmjava.requests.notification.NotificationPayload;
import server.broadcasts.FCMBroadcaster;
import server.jaxws.beans.RegisterLoginInfo;
import de.bytefish.fcmjava.responses.FcmMessageResponse;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class DataMulticastNotificationMsg implements Callable<FcmMessageResponse>
{
    DataMulticastMessage msg;

    public DataMulticastNotificationMsg(FcmMessageOptions options, List<String> keySet, Object data, NotificationPayload notification)
    {
        msg = new DataMulticastMessage(options, keySet, data, notification);
    }

    @Override
    public FcmMessageResponse call() throws Exception
    {
        return FCMBroadcaster.getInstance().getClient().send(msg);
    }
}
