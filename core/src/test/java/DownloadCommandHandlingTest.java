import org.junit.jupiter.api.*;
import org.musxteam.core.RequestHandler;
import org.musxteam.core.RequestReplies;
import types.TestRequest;
import views.TestViewFactory;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class DownloadCommandHandlingTest {
    private final RequestHandler testHandler = new RequestHandler();
    private final TestViewFactory viewFactory = new TestViewFactory();

    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();

    @AfterEach
    void backSystemOutStream() { System.setOut(originalOut); }

    @BeforeEach
    void changeSystemOutStream() { System.setOut(new PrintStream(outStream)); }


    @Test
    void testDownloadStartCommandHandling() {
        TestRequest downloadStartRequest = new TestRequest("/download");
        testHandler.startNewCommand(downloadStartRequest);

        TestRequest startTestRequest = new TestRequest(".");
        testHandler.handleRequest(startTestRequest, viewFactory).render(startTestRequest);
        Assertions.assertEquals(RequestReplies.DOWNLOAD_START.getReply(), outStream.toString());
    }

    @Test
    void testDownloadCommandHandling() {
        TestRequest downloadRequest = new TestRequest(".");
        TestRequest downloadStartRequest = new TestRequest("/download");

        testHandler.startNewCommand(downloadStartRequest);
        testHandler.handleRequest(downloadRequest, viewFactory);
        testHandler.handleRequest(downloadRequest, viewFactory).render(downloadRequest);

        Assertions.assertEquals("Test title|Test music url|Test thumbnail url", outStream.toString());
    }
}
