package u;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class unitTests {
    private HttpClient client;
    private String baseUrl;

    @Before
    public void setUp() {
        client = HttpClient.newHttpClient();
        baseUrl = "http://localhost:5000";
    }

    @Test
    public void testHelloEndpoint() throws Exception {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(new URI(baseUrl + "/"))
                .build();

        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, res.statusCode());
        assertTrue(res.body().contains("You called"));
    }

    @Test
    public void testEchoEndpoint() throws Exception {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(new URI(baseUrl + "/echo"))
                .POST(HttpRequest.BodyPublishers.ofString("text=HelloWorld"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, res.statusCode());
        assertTrue(res.body().contains("You said: HelloWorld"));
    }

    @Test
    public void testFactorEndpoint() throws Exception {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(new URI(baseUrl + "/factor?inINT=12"))
                .build();

        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, res.statusCode());
        assertTrue(res.body().contains("1") || res.body().contains("2") || res.body().contains("3"));
    }
}