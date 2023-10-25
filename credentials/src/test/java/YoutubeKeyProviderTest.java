import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.musxteam.credentials.YoutubeKeyProvider;

public class YoutubeKeyProviderTest {
    private final String key = "test";

    @Test
    void testKeyProvider() {
        String[] args = new String[] { "youtube_apikey=" + key };
        YoutubeKeyProvider keyProvider = new YoutubeKeyProvider(args);
        Assertions.assertEquals(key, keyProvider.getApiKey());
    }
}
