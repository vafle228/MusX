import music.types.TestSearchItemContainer;
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

public class SearchCommandHandlingTest {
    private final RequestHandler testHandler = new RequestHandler();
    private final TestViewFactory viewFactory = new TestViewFactory();

    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();

    @AfterEach
    void backSystemOutStream() { System.setOut(originalOut); }

    @BeforeEach
    void changeSystemOutStream() { System.setOut(new PrintStream(outStream)); }

    @Test
    void testSearchStartCommandHandling() {
        TestRequest searchStartRequest = new TestRequest("/search");
        testHandler.startNewCommand(searchStartRequest);

        TestRequest searchRequest = new TestRequest(".");
        testHandler.handleRequest(searchRequest, viewFactory).render(searchRequest);
        Assertions.assertEquals(RequestReplies.SEARCH_START.getReply(), outStream.toString());
    }

    @Test
    void testSearchCommandHandling() {
        TestRequest searchRequest = new TestRequest(".");
        TestRequest searchStartRequest = new TestRequest("/search");

        testHandler.startNewCommand(searchStartRequest);
        testHandler.handleRequest(searchRequest, viewFactory);  // start command
        testHandler.handleRequest(searchRequest, viewFactory).render(searchRequest);

        Assertions.assertEquals("Test title|Test video id|Test channel title|Test thumbnail", outStream.toString());
    }

    @Test
    void testSearchCommandNextHandling() {
        TestRequest searchRequest = new TestRequest(".");
        TestRequest searchStartRequest = new TestRequest("/search");

        TestSearchItemContainer container = new TestSearchItemContainer();
        container.getSearchItems();

        testHandler.startNewCommand(searchStartRequest);
        testHandler.handleRequest(searchRequest, viewFactory);  // start command
        testHandler.handleRequest(searchRequest, viewFactory);  // first search
        testHandler.handleRequest(new TestRequest("next"), viewFactory).render(searchRequest);

        Assertions.assertEquals("Test title|Test video id|Test channel title|Test thumbnail", outStream.toString());
    }

    @Test
    void testSearchCommandPrevHandling() {
        TestRequest searchRequest = new TestRequest(".");
        TestRequest searchStartRequest = new TestRequest("/search");

        testHandler.startNewCommand(searchStartRequest);
        testHandler.handleRequest(searchRequest, viewFactory);  // start command
        testHandler.handleRequest(searchRequest, viewFactory);  // first search
        testHandler.handleRequest(new TestRequest("next"), viewFactory);
        testHandler.handleRequest(new TestRequest("prev"), viewFactory).render(searchRequest);

        Assertions.assertEquals("Test title|Test video id|Test channel title|Test thumbnail", outStream.toString());
    }

    @Test
    void testSearchCommandIterateIllegalArgumentsHandling() {
        TestRequest searchRequest = new TestRequest(".");
        TestRequest searchStartRequest = new TestRequest("/search");

        testHandler.startNewCommand(searchStartRequest);
        testHandler.handleRequest(searchRequest, viewFactory);  // start command
        testHandler.handleRequest(searchRequest, viewFactory);  // first search
        testHandler.handleRequest(searchRequest, viewFactory).render(searchRequest);

        Assertions.assertEquals("Illegal argument", outStream.toString());
    }
}
