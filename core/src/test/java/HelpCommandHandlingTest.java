import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.musxteam.core.RequestHandler;
import org.musxteam.core.RequestReplies;
import types.TestRequest;
import views.TestViewFactory;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class HelpCommandHandlingTest {
    private final RequestHandler testHandler = new RequestHandler();
    private final TestViewFactory viewFactory = new TestViewFactory();

    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();

    @AfterEach
    void backSystemOutStream() { System.setOut(originalOut); }

    @BeforeEach
    void changeSystemOutStream() { System.setOut(new PrintStream(outStream)); }

    @Test
    void testHelpCommandHandling() {
        TestRequest helpStartRequest = new TestRequest("/help");
        testHandler.startNewCommand(helpStartRequest);

        TestRequest startTestRequest = new TestRequest(".");
        testHandler.handleRequest(startTestRequest, viewFactory).render(startTestRequest);
        Assertions.assertEquals(RequestReplies.HELP.getReply(), outStream.toString());
    }
}
