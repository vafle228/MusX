import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.musxteam.core.command.EchoCommand;
import org.musxteam.core.requests.TestRequest;
import org.musxteam.core.types.HandlingState;

public class EchoCommandTest {
    private final String message = "Test";
    @Test
    void testEchoCommandReply(){
        TestRequest test = new TestRequest();
        HandlingState state = new HandlingState(test.getText(), true);
        Assertions.assertEquals(state, new EchoCommand().handleRequest(test));
    }
}
