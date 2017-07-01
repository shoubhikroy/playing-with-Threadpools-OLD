package server.broadcasts;

import de.bytefish.fcmjava.client.FcmClient;
import de.bytefish.fcmjava.client.settings.PropertiesBasedSettings;
import de.bytefish.fcmjava.model.options.FcmMessageOptions;
import de.bytefish.fcmjava.requests.notification.NotificationPayload;
import de.bytefish.fcmjava.responses.FcmMessageResponse;
import server.broadcasts.casts.*;
import server.cache.FCMThreadPool;

import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FCMBroadcaster
{
    private static FCMBroadcaster ourInstance = new FCMBroadcaster();
    FcmMessageOptions options;
    String defaultPropertiesLocationString = System.getProperty("user.home") + "/fcmjava.properties";
    Path defaultPropertiesLocationPath = FileSystems.getDefault().getPath(defaultPropertiesLocationString, new String[0]);
    FcmClient client;
    private FCMBroadcaster()
    {
        client = new FcmClient(PropertiesBasedSettings.createFromFile(defaultPropertiesLocationPath, StandardCharsets.UTF_8));
        options = FcmMessageOptions.builder().build();
    }

    public static FCMBroadcaster getInstance()
    {
        return ourInstance;
    }

    public FcmClient getClient()
    {
        return client;
    }

    public void setClient(FcmClient client)
    {
        this.client = client;
    }

    public FcmMessageResponse sendDataUnicastMsg(String to, Map<String, String> data) throws InterruptedException, ExecutionException
    {
        return FCMThreadPool.getInstance().getThreadPool().submit(new DataUnicastMsg(options, to, data)).get();
    }

    private FcmMessageResponse sendDataUnicastNotificationMsg(String to, Map<String, String> data, NotificationPayload np) throws InterruptedException, ExecutionException
    {
        return FCMThreadPool.getInstance().getThreadPool().submit(new DataUnicastNotificationMsg(options, to, data, np)).get();
    }

    protected FcmMessageResponse sendDataUnicastNotificationMsg(String to, Map<String, String> data, String title, String body) throws ExecutionException, InterruptedException
    {
        NotificationPayload np = NotificationPayload.builder()
                .setTitle(title)
                .setBody(body)
                .build();
        return sendDataUnicastNotificationMsg(to, data, np);
    }

    public FcmMessageResponse sendDataMulticastMsg(List<String> keySet, Map<String, String> data) throws InterruptedException, ExecutionException
    {
        return FCMThreadPool.getInstance().getThreadPool().submit(new DataMulticastMsg(options, keySet, data)).get();
    }

    private FcmMessageResponse sendDataMulticastNotificationMsg(List<String> keySet, Map<String, String> data, NotificationPayload np) throws InterruptedException, ExecutionException
    {
        return FCMThreadPool.getInstance().getThreadPool().submit(new DataMulticastNotificationMsg(options, keySet, data, np)).get();
    }

    public FcmMessageResponse sendDataMulticastNotificationMsg(List<String> keySet, Map<String, String> data, String title, String body) throws ExecutionException, InterruptedException
    {
        NotificationPayload np = NotificationPayload.builder()
                .setTitle(title)
                .setBody(body)
                .build();
        return sendDataMulticastNotificationMsg(keySet, data, np);
    }

    private FcmMessageResponse sendNotificationUnicastMsg(String to, NotificationPayload notificationPayload) throws InterruptedException, ExecutionException
    {
        return FCMThreadPool.getInstance().getThreadPool().submit(new NotificationUnicastMsg(options, to, notificationPayload)).get();
    }

    public FcmMessageResponse sendNotificationUnicastMsg(String to, String title, String body)
    {
        NotificationPayload np = NotificationPayload.builder()
                .setTitle(title)
                .setBody(body)
                .build();
        try
        {
            return sendNotificationUnicastMsg(to, np);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } catch (ExecutionException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private FcmMessageResponse sendNotificationMulticastNotificationMsg(List<String> keySet, NotificationPayload notificationPayload) throws InterruptedException, ExecutionException
    {
        return FCMThreadPool.getInstance().getThreadPool().submit(new NotificationMulticastNotificationMsg(options, keySet, notificationPayload)).get();
    }

    public FcmMessageResponse sendNotificationMulticastNotificationMsg(List<String> keySet, String title, String body) throws ExecutionException, InterruptedException
    {
        NotificationPayload np = NotificationPayload.builder()
                .setTitle(title)
                .setBody(body)
                .build();
        return sendNotificationMulticastNotificationMsg(keySet, np);
    }
}
