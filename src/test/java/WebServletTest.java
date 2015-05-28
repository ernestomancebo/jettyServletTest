import org.eclipse.jetty.testing.HttpTester;
import org.eclipse.jetty.testing.ServletTester;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WebServletTest {

    private ServletTester tester;
    private HttpTester request;
    private HttpTester response;

    @Before
    public void setUp() throws Exception {
        tester = new ServletTester();
        request = new HttpTester();
        response = new HttpTester();

        tester.setContextPath("/");
        tester.addServlet(WebServlet.class, "/");
        tester.start();

        request.setMethod("GET");
        request.setHeader("Host", "tester");
        request.setVersion("HTTP/1.0");
    }

    @Test
    public void testRootPage() throws Exception {
        request.setURI("/");
        response.parse(
                tester.getResponses(
                        request.generate()
                )
        );

        assertTrue(response.getMethod() == null);
        assertEquals(200, response.getStatus());
        assertEquals("Veveh", response.getContent());
    }
}