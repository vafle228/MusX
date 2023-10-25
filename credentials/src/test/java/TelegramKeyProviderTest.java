import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.musxteam.credentials.TelegramKeyProvider;

public class TelegramKeyProviderTest {
    private final String key = "test";

    @Test
    void testKeyProvider() {
        String[] args = new String[] { "telegram_apikey=" + key };
        TelegramKeyProvider keyProvider = new TelegramKeyProvider(args);
        Assertions.assertEquals(key, keyProvider.getApiKey());
    }
}
