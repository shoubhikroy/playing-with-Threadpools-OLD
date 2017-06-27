package server.broadcasts.casts;

import de.bytefish.fcmjava.model.options.FcmMessageOptions;
import de.bytefish.fcmjava.requests.data.DataMulticastMessage;
import de.bytefish.fcmjava.requests.notification.NotificationPayload;
import de.bytefish.fcmjava.requests.notification.NotificationUnicastMessage;
import de.bytefish.fcmjava.responses.FcmMessageResponse;
import server.broadcasts.FCMBroadcaster;

import java.util.List;
import java.util.concurrent.Callable;

public class NotificationUnicastMsg implements Callable<FcmMessageResponse>
{
    NotificationUnicastMessage msg;

    public NotificationUnicastMsg(FcmMessageOptions options, String to, NotificationPayload notificationPayload)
    {
        msg = new NotificationUnicastMessage(options, to, notificationPayload);
    }

    @Override
    public FcmMessageResponse call() throws Exception
    {
        return FCMBroadcaster.getInstance().getClient().send(msg);
    }
}
