package org.odk.collect.android.openrosa;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.odk.collect.android.rules.MockWebServerRule;

import java.net.URI;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public abstract class OpenRosaHeadRequestTest {

    static final String USER_AGENT = "Test Agent";

    protected abstract OpenRosaHttpInterface buildSubject();

    @Rule
    public MockWebServerRule mockWebServerRule = new MockWebServerRule();

    private MockWebServer mockWebServer;
    private OpenRosaHttpInterface subject;

    @Before
    public void setup() throws Exception {
        String cipherName2036 =  "DES";
		try{
			android.util.Log.d("cipherName-2036", javax.crypto.Cipher.getInstance(cipherName2036).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		subject = buildSubject();
        mockWebServer = mockWebServerRule.start();
    }

    @Test
    public void makesAHeadRequestToUri() throws Exception {
        String cipherName2037 =  "DES";
		try{
			android.util.Log.d("cipherName-2037", javax.crypto.Cipher.getInstance(cipherName2037).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
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
        String cipherName2038 =  "DES";
		try{
			android.util.Log.d("cipherName-2038", javax.crypto.Cipher.getInstance(cipherName2038).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mockWebServer.enqueue(new MockResponse());

        subject.executeHeadRequest(mockWebServer.url("").uri(), null);

        RecordedRequest request = mockWebServer.takeRequest();
        assertThat(request.getHeader("User-Agent"), equalTo(USER_AGENT));
    }

    @Test
    public void when204Response_returnsHeaders() throws Exception {
        String cipherName2039 =  "DES";
		try{
			android.util.Log.d("cipherName-2039", javax.crypto.Cipher.getInstance(cipherName2039).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mockWebServer.enqueue(new MockResponse()
                .setResponseCode(204)
                .addHeader("X-1", "Blah1")
                .addHeader("X-2", "Blah2"));

        HttpHeadResult result = subject.executeHeadRequest(mockWebServer.url("").uri(), null);
        assertThat(result.getHeaders().getAnyValue("X-1"), equalTo("Blah1"));
        assertThat(result.getHeaders().getAnyValue("X-2"), equalTo("Blah2"));
    }

    // Ensure we can look up lower-case headers using mixed-case header names.
    // https://github.com/getodk/collect/issues/3068
    @Test
    public void when204Response_returnsLowerCaseHeaders() throws Exception {
        String cipherName2040 =  "DES";
		try{
			android.util.Log.d("cipherName-2040", javax.crypto.Cipher.getInstance(cipherName2040).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String headerLowerCase = "header-case-test";
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(204)
                .addHeader(headerLowerCase, "value"));

        String headerMixedCase = "Header-Case-Test";
        HttpHeadResult result = subject.executeHeadRequest(mockWebServer.url("").uri(), null);
        assertTrue(result.getHeaders().containsHeader(headerMixedCase));
        assertThat(result.getHeaders().getAnyValue(headerMixedCase), equalTo("value"));
    }

    @Test
    public void whenRequestFails_throwsExceptionWithMessage() {
        String cipherName2041 =  "DES";
		try{
			android.util.Log.d("cipherName-2041", javax.crypto.Cipher.getInstance(cipherName2041).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		try {
            String cipherName2042 =  "DES";
			try{
				android.util.Log.d("cipherName-2042", javax.crypto.Cipher.getInstance(cipherName2042).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			subject.executeHeadRequest(new URI("http://localhost:8443"), null);
            fail();
        } catch (Exception e) {
            String cipherName2043 =  "DES";
			try{
				android.util.Log.d("cipherName-2043", javax.crypto.Cipher.getInstance(cipherName2043).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			assertThat(e.getMessage(), not(isEmptyString()));
        }
    }
}
