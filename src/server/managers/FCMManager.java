package server.managers;

import de.bytefish.fcmjava.client.FcmClient;
import de.bytefish.fcmjava.client.settings.PropertiesBasedSettings;
import de.bytefish.fcmjava.model.options.FcmMessageOptions;
import de.bytefish.fcmjava.requests.data.DataUnicastMessage;
import de.bytefish.fcmjava.requests.notification.NotificationPayload;
import de.bytefish.fcmjava.responses.FcmMessageResponse;
import de.bytefish.fcmjava.responses.FcmMessageResultItem;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FCMManager
{

    private static FCMManager instance = null;

    protected FCMManager()
    {
        // Exists only to defeat instantiation.
    }

    public static FCMManager getInstance()
    {
        if (instance == null)
        {
            instance = new FCMManager();
        }
        return instance;
    }

    @Test
    // @Ignore("This is an Integration Test using system properties to contact the FCM Server")
    public void SendTopicMessageTest() throws Exception
    {

        // Create the Client using system-properties-based settings:\
        String defaultPropertiesLocationString = System.getProperty("user.home") + "/fcmjava.properties";
        Path defaultPropertiesLocationPath = FileSystems.getDefault().getPath(defaultPropertiesLocationString, new String[0]);
        try (FcmClient client = new FcmClient(PropertiesBasedSettings.createFromFile(defaultPropertiesLocationPath, StandardCharsets.UTF_8)))
        {

            // Message Options:
            FcmMessageOptions options = FcmMessageOptions.builder()
                    .build();

            // Send a Message:
            NotificationPayload np = NotificationPayload.builder()
                    .setTitle("title")
                    .setBody("body")
                    .build();

            String key = "eZTbmP-h8xk:APA91bGTk5MnmtpL7KddKrXzG2LKAtVnI_p3jHuMCAMltl1s4Lw5afpwbkKjyP7Gtt3Mr1I2bW6E6R9lcBi8t7LFA6-CEBrY4C1tF-2IZrytdX4fFYuzppLjzTmdnMaZrKYFUZatjzWL";

            Map<String, String> data = new HashMap();
            data.put("123", "abc");
            data.put("321", "bas");
            data.put("456", "asd");

            DataUnicastMessage msg = new DataUnicastMessage(options, key, data);
            //= new NotificationUnicastMessage(options, key, np);
            //TopicMessageResponse response = client.send(new TopicUnicastMessage(options, new Topic("news"), new KeyValuePair("test123", "msg321")));
            FcmMessageResponse fmr = client.send(msg);
            // Assert Results:
            Assert.assertNotNull(fmr);

            // Make sure there are no errors:
            Assert.assertNotNull(fmr.getResults());
            List<FcmMessageResultItem> x = fmr.getResults();
            for (FcmMessageResultItem fri : x)
            {
                Assert.assertNotNull(fri.getMessageId());
                Assert.assertNull(fri.getErrorCode());
            }
        }
    }
}
