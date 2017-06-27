package server.broadcasts;

import de.bytefish.fcmjava.client.FcmClient;
import de.bytefish.fcmjava.client.settings.PropertiesBasedSettings;
import de.bytefish.fcmjava.model.options.FcmMessageOptions;
import de.bytefish.fcmjava.responses.FcmMessageResponse;
import server.broadcasts.casts.DataUnicastMsg;
import server.broadcasts.casts.DataUnicastNotificationMsg;
import server.cache.FCMThreadPool;

import javax.management.Notification;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;

public class FCMBroadcaster
{
    private static FCMBroadcaster ourInstance = new FCMBroadcaster();

    public static FCMBroadcaster getInstance()
    {
        return ourInstance;
    }

    FcmMessageOptions options;

    private FCMBroadcaster()
    {
        client = new FcmClient(PropertiesBasedSettings.createFromFile(defaultPropertiesLocationPath, StandardCharsets.UTF_8));
        options = FcmMessageOptions.builder().build();
    }

    String defaultPropertiesLocationString = System.getProperty("user.home") + "/fcmjava.properties";
    Path defaultPropertiesLocationPath = FileSystems.getDefault().getPath(defaultPropertiesLocationString, new String[0]);

    public FcmClient getClient()
    {
        return client;
    }

    public void setClient(FcmClient client)
    {
        this.client = client;
    }

    FcmClient client;

    public FcmMessageResponse sendDataUnicastMsg(String to, Map<String, String> data) throws InterruptedException, ExecutionException
    {
        CompletionService<FcmMessageResponse> pool = new ExecutorCompletionService<>(FCMThreadPool.getInstance().getThreadPool());
        pool.submit(new DataUnicastMsg(options, to, data));
        return pool.take().get();
    }

    public FcmMessageResponse sendDataUnicastNotificationMsg(String to, Map<String, String> data, Notification np) throws InterruptedException, ExecutionException
    {
        CompletionService<FcmMessageResponse> pool = new ExecutorCompletionService<>(FCMThreadPool.getInstance().getThreadPool());
        pool.submit(new DataUnicastNotificationMsg(options, to, data, np));
        return pool.take().get();
    }
}
