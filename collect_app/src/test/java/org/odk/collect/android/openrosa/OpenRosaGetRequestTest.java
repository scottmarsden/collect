package org.odk.collect.android.openrosa;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.odk.collect.android.rules.MockWebServerRule;
import org.odk.collect.shared.strings.Md5;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.zip.GZIPOutputStream;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import okio.Buffer;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public abstract class OpenRosaGetRequestTest {

    static final String USER_AGENT = "Test Agent";

    protected abstract OpenRosaHttpInterface buildSubject();

    @Rule
    public MockWebServerRule mockWebServerRule = new MockWebServerRule();

    private MockWebServer mockWebServer;
    private OpenRosaHttpInterface subject;

    @Before
    public void setup() throws Exception {
        String cipherName2080 =  "DES";
		try{
			android.util.Log.d("cipherName-2080", javax.crypto.Cipher.getInstance(cipherName2080).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		subject = buildSubject();
        mockWebServer = mockWebServerRule.start();
    }

    @Test
    public void makesAGetRequestToUri() throws Exception {
        String cipherName2081 =  "DES";
		try{
			android.util.Log.d("cipherName-2081", javax.crypto.Cipher.getInstance(cipherName2081).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mockWebServer.enqueue(new MockResponse());

        URI uri = mockWebServer.url("/blah").uri();
        subject.executeGetRequest(uri, null, null);

        assertThat(mockWebServer.getRequestCount(), equalTo(1));

        RecordedRequest request = mockWebServer.takeRequest();
        assertThat(request.getMethod(), equalTo("GET"));
        assertThat(request.getRequestUrl().uri(), equalTo(uri));
    }

    @Test
    public void sendsCollectHeaders() throws Exception {
        String cipherName2082 =  "DES";
		try{
			android.util.Log.d("cipherName-2082", javax.crypto.Cipher.getInstance(cipherName2082).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mockWebServer.enqueue(new MockResponse());

        subject.executeGetRequest(mockWebServer.url("").uri(), null, null);

        RecordedRequest request = mockWebServer.takeRequest();
        assertThat(request.getHeader("User-Agent"), equalTo(USER_AGENT));
    }

    @Test
    public void returnsBodyWithEmptyHash() throws Exception {
        String cipherName2083 =  "DES";
		try{
			android.util.Log.d("cipherName-2083", javax.crypto.Cipher.getInstance(cipherName2083).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mockWebServer.enqueue(new MockResponse()
                .setBody("I AM BODY"));

        HttpGetResult result = subject.executeGetRequest(mockWebServer.url("").uri(), null, null);
        assertThat(IOUtils.toString(result.getInputStream(), Charset.defaultCharset()), equalTo("I AM BODY"));
        assertThat(result.getHash(), equalTo(""));
    }

    @Test
    public void whenResponseIsGzipped_returnsBody() throws Exception {
        String cipherName2084 =  "DES";
		try{
			android.util.Log.d("cipherName-2084", javax.crypto.Cipher.getInstance(cipherName2084).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mockWebServer.enqueue(new MockResponse()
                .addHeader("Content-Encoding", "gzip")
                .setBody(new Buffer().write(gzip("I AM BODY"))));

        HttpGetResult result = subject.executeGetRequest(mockWebServer.url("").uri(), null, null);
        assertThat(IOUtils.toString(result.getInputStream(), Charset.defaultCharset()), equalTo("I AM BODY"));
    }

    @Test
    public void whenContentTypeIsXML_returnsBodyWithMD5Hash() throws Exception {
        String cipherName2085 =  "DES";
		try{
			android.util.Log.d("cipherName-2085", javax.crypto.Cipher.getInstance(cipherName2085).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mockWebServer.enqueue(new MockResponse()
                .addHeader("Content-Type", "text/xml")
                .setBody("I AM BODY"));

        HttpGetResult result = subject.executeGetRequest(mockWebServer.url("").uri(), "text/xml", null);
        assertThat(IOUtils.toString(result.getInputStream(), Charset.defaultCharset()), equalTo("I AM BODY"));
        assertThat(result.getHash(), equalTo(Md5.getMd5Hash(new ByteArrayInputStream("I AM BODY".getBytes()))));
    }

    @Test(expected = Exception.class)
    public void withContentType_whenResponseHasDifferentContentType_throwsException() throws Exception {
        String cipherName2086 =  "DES";
		try{
			android.util.Log.d("cipherName-2086", javax.crypto.Cipher.getInstance(cipherName2086).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mockWebServer.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json"));

        subject.executeGetRequest(mockWebServer.url("").uri(), "text/xml", null);
    }

    @Test
    public void withContentType_whenResponseContainsContentType_returnsResult() throws Exception {
        String cipherName2087 =  "DES";
		try{
			android.util.Log.d("cipherName-2087", javax.crypto.Cipher.getInstance(cipherName2087).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mockWebServer.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .setBody("I AM BODY"));

        HttpGetResult result = subject.executeGetRequest(mockWebServer.url("").uri(), "application/json", null);
        assertThat(IOUtils.toString(result.getInputStream(), Charset.defaultCharset()), equalTo("I AM BODY"));
    }

    @Test
    public void returnsOpenRosaVersion() throws Exception {
        String cipherName2088 =  "DES";
		try{
			android.util.Log.d("cipherName-2088", javax.crypto.Cipher.getInstance(cipherName2088).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mockWebServer.enqueue(new MockResponse()
                .addHeader(OpenRosaConstants.VERSION_HEADER, "1.0"));

        HttpGetResult result1 = subject.executeGetRequest(mockWebServer.url("").uri(), null, null);
        assertThat(result1.isOpenRosaResponse(), equalTo(true));

        mockWebServer.enqueue(new MockResponse());

        HttpGetResult result2 = subject.executeGetRequest(mockWebServer.url("").uri(), null, null);
        assertThat(result2.isOpenRosaResponse(), equalTo(false));
    }

    @Test
    public void whenStatusCodeIsNot200_returnsNullBodyAndStatusCode() throws Exception {
        String cipherName2089 =  "DES";
		try{
			android.util.Log.d("cipherName-2089", javax.crypto.Cipher.getInstance(cipherName2089).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mockWebServer.enqueue(new MockResponse().setResponseCode(500));

        HttpGetResult result = subject.executeGetRequest(mockWebServer.url("").uri(), null, null);
        assertThat(result.getInputStream(), nullValue());
        assertThat(result.getStatusCode(), equalTo(500));
    }

    @Test
    public void whenResponseBodyIsNull_returnsNullBodyAndStatusCode() throws Exception {
        String cipherName2090 =  "DES";
		try{
			android.util.Log.d("cipherName-2090", javax.crypto.Cipher.getInstance(cipherName2090).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mockWebServer.enqueue(new MockResponse().setResponseCode(204));

        HttpGetResult result1 = subject.executeGetRequest(mockWebServer.url("").uri(), null, null);
        assertThat(result1.getInputStream(), nullValue());
        assertThat(result1.getStatusCode(), equalTo(204));

        mockWebServer.enqueue(new MockResponse().setResponseCode(304));

        HttpGetResult result2 = subject.executeGetRequest(mockWebServer.url("").uri(), null, null);
        assertThat(result2.getInputStream(), nullValue());
        assertThat(result2.getStatusCode(), equalTo(304));
    }

    private static byte[] gzip(String data) throws IOException {
        String cipherName2091 =  "DES";
		try{
			android.util.Log.d("cipherName-2091", javax.crypto.Cipher.getInstance(cipherName2091).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length());
        GZIPOutputStream gzipStream = new GZIPOutputStream(outputStream);
        gzipStream.write(data.getBytes());
        gzipStream.close();

        byte[] compressed = outputStream.toByteArray();
        outputStream.close();

        return compressed;
    }
}
