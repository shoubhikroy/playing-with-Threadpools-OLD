package server.broadcasts.casts;

import de.bytefish.fcmjava.model.options.FcmMessageOptions;
import de.bytefish.fcmjava.requests.notification.NotificationMulticastMessage;
import de.bytefish.fcmjava.requests.notification.NotificationPayload;
import de.bytefish.fcmjava.responses.FcmMessageResponse;
import server.broadcasts.FCMBroadcaster;

import java.util.List;
import java.util.concurrent.Callable;

public class NotificationMulticastNotificationMsg implements Callable<FcmMessageResponse>
{
    NotificationMulticastMessage msg;

    public NotificationMulticastNotificationMsg(FcmMessageOptions options, List<String> keySet, NotificationPayload notificationPayload)
    {
        msg = new NotificationMulticastMessage(options, keySet, notificationPayload);
    }

    @Override
    public FcmMessageResponse call() throws Exception
    {
        return FCMBroadcaster.getInstance().getClient().send(msg);
    }
}
