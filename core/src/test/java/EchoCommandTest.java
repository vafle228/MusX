import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import org.musxteam.core.RequestReplies;
import org.musxteam.core.command.EchoCommand;
import org.musxteam.core.requests.TestRequest;
import org.musxteam.core.types.HandlingState;

import static org.junit.jupiter.params.provider.Arguments.arguments;


public class EchoCommandTest {
    private static final String message = "Test";
    private static final EchoCommand command = new EchoCommand();

    @ParameterizedTest(name = "Echo command {0} state test")
    @MethodSource("requestAndHandlingStateProvider")
    void testEchoCommandReply(TestRequest request, HandlingState state){
        Assertions.assertEquals(state, command.handleRequest(request));
    }

    static Stream<Arguments> requestAndHandlingStateProvider() {
        return Stream.of(
                arguments(new TestRequest(""), new HandlingState(
                        RequestReplies.ECHO_START.getReply(), false)
                ),
                arguments(new TestRequest(message), new HandlingState(message, true))
        );
    }
}
