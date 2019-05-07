package org.odk.collect.android.http;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.odk.collect.android.BuildConfig;

import java.net.URI;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public abstract class OpenRosaHttpInterfaceExecuteHeadRequestTest {

    protected abstract OpenRosaHttpInterface buildSubject();

    private final MockWebServer mockWebServer = new MockWebServer();
    private OpenRosaHttpInterface subject;

    @Before
    public void setup() throws Exception {
        mockWebServer.start();
        subject = buildSubject();
    }

    @After
    public void teardown() throws Exception {
        mockWebServer.shutdown();
    }

    @Test
    public void makesAHeadRequestToUri() throws Exception {
        mockWebServer.enqueue(new MockResponse());

        URI uri = mockWebServer.url("/blah").uri();
        subject.executeHeadRequest(uri, null);

        assertThat(mockWebServer.getRequestCount(), equalTo(1));

        RecordedRequest request = mockWebServer.takeRequest();
        assertThat(request.getMethod(), equalTo("HEAD"));
        assertThat(request.getRequestUrl().uri(), equalTo(uri));
    }

    @Test
    public void sendsCollectHeaders() throws Exception {
        mockWebServer.enqueue(new MockResponse());

        subject.executeHeadRequest(mockWebServer.url("").uri(), null);

        RecordedRequest request = mockWebServer.takeRequest();
        assertThat(request.getHeader("User-Agent"), equalTo(String.format(
                "null %s/%s",
                BuildConfig.APPLICATION_ID,
                BuildConfig.VERSION_NAME)));
    }

    @Test
    public void sendsOpenRosaHeaders() throws Exception {
        mockWebServer.enqueue(new MockResponse());

        subject.executeHeadRequest(mockWebServer.url("").uri(), null);

        RecordedRequest request = mockWebServer.takeRequest();
        assertThat(request.getHeader("X-OpenRosa-Version"), equalTo("1.0"));
    }

    @Test
    public void when204Response_returnsHeaders() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(204)
                .addHeader("X-1", "Blah1")
                .addHeader("X-2", "Blah2"));

        HttpHeadResult result = subject.executeHeadRequest(mockWebServer.url("").uri(), null);
        assertThat(result.getHeaders().get("X-1"), equalTo("Blah1"));
        assertThat(result.getHeaders().get("X-2"), equalTo("Blah2"));
    }

    @Test
    public void withCredentials_whenHttp_doesNotRetryWithCredentials() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(401)
                .addHeader("WWW-Authenticate: Basic realm=\"protected area\""));
        mockWebServer.enqueue(new MockResponse());

        subject.executeHeadRequest(mockWebServer.url("").uri(), new HttpCredentials("user", "pass"));

        assertThat(mockWebServer.getRequestCount(), equalTo(1));
    }
}