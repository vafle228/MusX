import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.musxteam.core.RequestReplies;
import org.musxteam.core.command.HelpCommand;
import org.musxteam.core.requests.TestRequest;
import org.musxteam.core.types.HandlingState;

public class HelpCommandTest {
    @Test
    void testHelpCommandReply() {
        HelpCommand command = new HelpCommand();
        HandlingState state = new HandlingState(RequestReplies.HELP.getReply(), true);
        Assertions.assertEquals(state, command.handleRequest(new TestRequest()));
    }
}
