package u;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class unittests {
    private HttpClient client;
    private String baseUrl;

    @Before
    public void setUp() {
        client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        baseUrl = "http://localhost:5000";
    }

    @Test
    public void testRootEndpoint() throws Exception {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(new URI(baseUrl + "/"))
                .GET()
                .build();
        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, res.statusCode());
        assertTrue(res.body().contains("\"main endpoint\""));
    }

    @Test
    public void testAddSubscriber() throws Exception {
        String jsonBody = "{\"name\":\"Alice\",\"URI\":\"http://good.site.com\"}";
        HttpRequest req = HttpRequest.newBuilder()
                .uri(new URI(baseUrl + "/add-subscriber"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, res.statusCode());
        assertTrue(res.body().contains("Alice"));
        assertTrue(res.body().contains("http://good.site.com"));
    }

    @Test
    public void testListSubscribers() throws Exception {
        String jsonBody = "{\"name\":\"Will\",\"URI\":\"http://example.com\"}";
        HttpRequest addReq = HttpRequest.newBuilder()
                .uri(new URI(baseUrl + "/add-subscriber"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        client.send(addReq, HttpResponse.BodyHandlers.ofString());

        HttpRequest listReq = HttpRequest.newBuilder()
                .uri(new URI(baseUrl + "/list-subscribers"))
                .GET()
                .build();
        HttpResponse<String> res = client.send(listReq, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, res.statusCode());
        assertTrue("Expected subscriber Will in list", res.body().contains("Will"));
    }


    @Test
    public void testUpdateSubject() throws Exception {
        String jsonBody = "{\"subject\":\"Breaking News\"}";
        HttpRequest req = HttpRequest.newBuilder()
                .uri(new URI(baseUrl + "/update-sub"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, res.statusCode());
        assertTrue(res.body().contains("Breaking News"));
        assertTrue(res.body().contains("Subject published"));
    }

    @Test
    public void testDeleteSubscriber() throws Exception {
        String addJson = "{\"name\":\"Alice\",\"URI\":\"http://good.site.com\"}";
        HttpRequest addReq = HttpRequest.newBuilder()
                .uri(new URI(baseUrl + "/add-subscriber"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(addJson))
                .build();
        client.send(addReq, HttpResponse.BodyHandlers.ofString());

        String deleteJson = "{\"name\":\"Alice\"}";
        HttpRequest delReq = HttpRequest.newBuilder()
                .uri(new URI(baseUrl + "/deleting-subscriber"))
                .header("Content-Type", "application/json")
                .method("DELETE", HttpRequest.BodyPublishers.ofString(deleteJson))
                .build();
        HttpResponse<String> res = client.send(delReq, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, res.statusCode());
        assertTrue(res.body().contains("Alice"));
        assertTrue(res.body().contains("deleted"));
    }
}
