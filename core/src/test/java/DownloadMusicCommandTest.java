import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.musxteam.core.RequestReplies;
import org.musxteam.core.command.DownloadMusicCommand;
import org.musxteam.core.requests.TestRequest;
import org.musxteam.core.types.HandlingState;

public class DownloadMusicCommandTest {
    private static final String message = "Test";
    private static final DownloadMusicCommand command = new DownloadMusicCommand();

    @Test
    void testDownloadCommandReply() {
        TestRequest request = new TestRequest("");
        HandlingState state = new HandlingState(RequestReplies.DOWNLOAD_START.getReply(), false);
        Assertions.assertEquals(state, command.handleRequest(request));
    }
}
