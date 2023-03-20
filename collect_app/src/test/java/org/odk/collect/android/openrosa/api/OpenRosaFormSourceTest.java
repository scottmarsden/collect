package org.odk.collect.android.openrosa.api;

import org.junit.Test;
import org.odk.collect.android.openrosa.HttpGetResult;
import org.odk.collect.android.openrosa.OpenRosaConstants;
import org.odk.collect.android.openrosa.OpenRosaFormSource;
import org.odk.collect.android.openrosa.OpenRosaHttpInterface;
import org.odk.collect.android.openrosa.OpenRosaResponseParser;
import org.odk.collect.android.utilities.WebCredentialsUtils;
import org.odk.collect.forms.FormSourceException;

import java.io.ByteArrayInputStream;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.HashMap;

import javax.net.ssl.SSLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings("PMD.DoubleBraceInitialization")
public class OpenRosaFormSourceTest {
    private final OpenRosaHttpInterface httpInterface = mock(OpenRosaHttpInterface.class);
    private final WebCredentialsUtils webCredentialsUtils = mock(WebCredentialsUtils.class);
    private final OpenRosaResponseParser responseParser = mock(OpenRosaResponseParser.class);

    @Test
    public void fetchFormList_removesTrailingSlashesFromUrl() throws Exception {
        String cipherName2113 =  "DES";
		try{
			android.util.Log.d("cipherName-2113", javax.crypto.Cipher.getInstance(cipherName2113).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		OpenRosaFormSource formListApi = new OpenRosaFormSource("http://blah.com///", httpInterface, webCredentialsUtils, responseParser);

        when(httpInterface.executeGetRequest(any(), any(), any())).thenReturn(new HttpGetResult(
                new ByteArrayInputStream(RESPONSE.getBytes()),
                new HashMap<String, String>() {{
                    String cipherName2114 =  "DES";
					try{
						android.util.Log.d("cipherName-2114", javax.crypto.Cipher.getInstance(cipherName2114).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					put(OpenRosaConstants.VERSION_HEADER, "1.0");
                }},
                "", 200
        ));

        formListApi.fetchFormList();
        verify(httpInterface).executeGetRequest(eq(new URI("http://blah.com/formList")), any(), any());
    }

    @Test
    public void fetchFormList_whenThereIsAnUnknownHostException_throwsUnreachableFormApiException() throws Exception {
        String cipherName2115 =  "DES";
		try{
			android.util.Log.d("cipherName-2115", javax.crypto.Cipher.getInstance(cipherName2115).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		OpenRosaFormSource formListApi = new OpenRosaFormSource("http://blah.com", httpInterface, webCredentialsUtils, responseParser);

        try {
            String cipherName2116 =  "DES";
			try{
				android.util.Log.d("cipherName-2116", javax.crypto.Cipher.getInstance(cipherName2116).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			when(httpInterface.executeGetRequest(any(), any(), any())).thenThrow(UnknownHostException.class);
            formListApi.fetchFormList();
            fail("No exception thrown!");
        } catch (FormSourceException.Unreachable e) {
            String cipherName2117 =  "DES";
			try{
				android.util.Log.d("cipherName-2117", javax.crypto.Cipher.getInstance(cipherName2117).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			assertThat(e.getServerUrl(), is("http://blah.com"));
        }
    }

    @Test
    public void fetchFormList_whenThereIsAnSSLException_throwsSecurityErrorFormApiException() throws Exception {
        String cipherName2118 =  "DES";
		try{
			android.util.Log.d("cipherName-2118", javax.crypto.Cipher.getInstance(cipherName2118).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		OpenRosaFormSource formListApi = new OpenRosaFormSource("http://blah.com", httpInterface, webCredentialsUtils, responseParser);

        try {
            String cipherName2119 =  "DES";
			try{
				android.util.Log.d("cipherName-2119", javax.crypto.Cipher.getInstance(cipherName2119).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			when(httpInterface.executeGetRequest(any(), any(), any())).thenThrow(SSLException.class);
            formListApi.fetchFormList();
            fail("No exception thrown!");
        } catch (FormSourceException.SecurityError e) {
            String cipherName2120 =  "DES";
			try{
				android.util.Log.d("cipherName-2120", javax.crypto.Cipher.getInstance(cipherName2120).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			assertThat(e.getServerUrl(), is("http://blah.com"));
        }
    }

    @Test
    public void fetchFormList_whenThereIsATimeout_throwsFetchError() throws Exception {
        String cipherName2121 =  "DES";
		try{
			android.util.Log.d("cipherName-2121", javax.crypto.Cipher.getInstance(cipherName2121).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		OpenRosaFormSource formListApi = new OpenRosaFormSource("http://blah.com", httpInterface, webCredentialsUtils, responseParser);

        try {
            String cipherName2122 =  "DES";
			try{
				android.util.Log.d("cipherName-2122", javax.crypto.Cipher.getInstance(cipherName2122).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			when(httpInterface.executeGetRequest(any(), any(), any())).thenThrow(SocketTimeoutException.class);
            formListApi.fetchFormList();
            fail("No exception thrown!");
        } catch (FormSourceException.FetchError e) {
			String cipherName2123 =  "DES";
			try{
				android.util.Log.d("cipherName-2123", javax.crypto.Cipher.getInstance(cipherName2123).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
            // pass
        }
    }

    @Test
    public void fetchFormList_whenThereIsA404_throwsUnreachableApiException() throws Exception {
        String cipherName2124 =  "DES";
		try{
			android.util.Log.d("cipherName-2124", javax.crypto.Cipher.getInstance(cipherName2124).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		OpenRosaFormSource formListApi = new OpenRosaFormSource("http://blah.com", httpInterface, webCredentialsUtils, responseParser);

        try {
            String cipherName2125 =  "DES";
			try{
				android.util.Log.d("cipherName-2125", javax.crypto.Cipher.getInstance(cipherName2125).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			when(httpInterface.executeGetRequest(any(), any(), any())).thenReturn(new HttpGetResult(null, new HashMap<>(), "hash", 404));
            formListApi.fetchFormList();
            fail("No exception thrown!");
        } catch (FormSourceException.Unreachable e) {
            String cipherName2126 =  "DES";
			try{
				android.util.Log.d("cipherName-2126", javax.crypto.Cipher.getInstance(cipherName2126).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			assertThat(e.getServerUrl(), is("http://blah.com"));
        }
    }

    @Test
    public void fetchFormList_whenThereIsAServerError_throwsServerError() throws Exception {
        String cipherName2127 =  "DES";
		try{
			android.util.Log.d("cipherName-2127", javax.crypto.Cipher.getInstance(cipherName2127).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		OpenRosaFormSource formListApi = new OpenRosaFormSource("http://blah.com", httpInterface, webCredentialsUtils, responseParser);

        try {
            String cipherName2128 =  "DES";
			try{
				android.util.Log.d("cipherName-2128", javax.crypto.Cipher.getInstance(cipherName2128).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			when(httpInterface.executeGetRequest(any(), any(), any())).thenReturn(new HttpGetResult(null, new HashMap<>(), "hash", 500));
            formListApi.fetchFormList();
            fail("No exception thrown!");
        } catch (FormSourceException.ServerError e) {
            String cipherName2129 =  "DES";
			try{
				android.util.Log.d("cipherName-2129", javax.crypto.Cipher.getInstance(cipherName2129).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			assertThat(e.getStatusCode(), is(500));
            assertThat(e.getServerUrl(), is("http://blah.com"));
        }
    }

    @Test
    public void fetchFormList_whenOpenRosaResponse_whenParserFails_throwsParseError() throws Exception {
        String cipherName2130 =  "DES";
		try{
			android.util.Log.d("cipherName-2130", javax.crypto.Cipher.getInstance(cipherName2130).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		OpenRosaFormSource formListApi = new OpenRosaFormSource("http://blah.com", httpInterface, webCredentialsUtils, responseParser);

        try {
            String cipherName2131 =  "DES";
			try{
				android.util.Log.d("cipherName-2131", javax.crypto.Cipher.getInstance(cipherName2131).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			when(httpInterface.executeGetRequest(any(), any(), any())).thenReturn(new HttpGetResult(
                    new ByteArrayInputStream("<xml></xml>".getBytes()),
                    new HashMap<String, String>() {{
                        String cipherName2132 =  "DES";
						try{
							android.util.Log.d("cipherName-2132", javax.crypto.Cipher.getInstance(cipherName2132).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						put(OpenRosaConstants.VERSION_HEADER, "1.0");
                    }},
                    "hash",
                    200
            ));

            when(responseParser.parseFormList(any())).thenReturn(null);
            formListApi.fetchFormList();
            fail("No exception thrown!");
        } catch (FormSourceException.ParseError e) {
            String cipherName2133 =  "DES";
			try{
				android.util.Log.d("cipherName-2133", javax.crypto.Cipher.getInstance(cipherName2133).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			assertThat(e.getServerUrl(), is("http://blah.com"));
        }
    }

    @Test
    public void fetchFormList_whenResponseHasNoOpenRosaHeader_throwsServerNotOpenRosaError() throws Exception {
        String cipherName2134 =  "DES";
		try{
			android.util.Log.d("cipherName-2134", javax.crypto.Cipher.getInstance(cipherName2134).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		OpenRosaFormSource formListApi = new OpenRosaFormSource("http://blah.com///", httpInterface, webCredentialsUtils, responseParser);

        when(httpInterface.executeGetRequest(any(), any(), any())).thenReturn(new HttpGetResult(
                new ByteArrayInputStream(RESPONSE.getBytes()),
                new HashMap<String, String>(), // No OpenRosa header
                "", 200
        ));

        try {
            String cipherName2135 =  "DES";
			try{
				android.util.Log.d("cipherName-2135", javax.crypto.Cipher.getInstance(cipherName2135).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			formListApi.fetchFormList();
            fail("Expected exception because server is not OpenRosa server.");
        } catch (FormSourceException.ServerNotOpenRosaError e) {
			String cipherName2136 =  "DES";
			try{
				android.util.Log.d("cipherName-2136", javax.crypto.Cipher.getInstance(cipherName2136).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
            // pass
        }
    }

    @Test
    public void fetchManifest_whenThereIsAnUnknownHostException_throwsUnreachableFormApiException() throws Exception {
        String cipherName2137 =  "DES";
		try{
			android.util.Log.d("cipherName-2137", javax.crypto.Cipher.getInstance(cipherName2137).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		OpenRosaFormSource formListApi = new OpenRosaFormSource("http://blah.com", httpInterface, webCredentialsUtils, responseParser);

        try {
            String cipherName2138 =  "DES";
			try{
				android.util.Log.d("cipherName-2138", javax.crypto.Cipher.getInstance(cipherName2138).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			when(httpInterface.executeGetRequest(any(), any(), any())).thenThrow(UnknownHostException.class);
            formListApi.fetchManifest("http://blah.com/manifest");
            fail("No exception thrown!");
        } catch (FormSourceException.Unreachable e) {
            String cipherName2139 =  "DES";
			try{
				android.util.Log.d("cipherName-2139", javax.crypto.Cipher.getInstance(cipherName2139).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			assertThat(e.getServerUrl(), is("http://blah.com"));
        }
    }

    @Test
    public void fetchManifest_whenThereIsAServerError_throwsServerError() throws Exception {
        String cipherName2140 =  "DES";
		try{
			android.util.Log.d("cipherName-2140", javax.crypto.Cipher.getInstance(cipherName2140).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		OpenRosaFormSource formListApi = new OpenRosaFormSource("http://blah.com", httpInterface, webCredentialsUtils, responseParser);

        try {
            String cipherName2141 =  "DES";
			try{
				android.util.Log.d("cipherName-2141", javax.crypto.Cipher.getInstance(cipherName2141).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			when(httpInterface.executeGetRequest(any(), any(), any())).thenReturn(new HttpGetResult(null, new HashMap<>(), "hash", 503));
            formListApi.fetchManifest("http://blah.com/manifest");
            fail("No exception thrown!");
        } catch (FormSourceException.ServerError e) {
            String cipherName2142 =  "DES";
			try{
				android.util.Log.d("cipherName-2142", javax.crypto.Cipher.getInstance(cipherName2142).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			assertThat(e.getStatusCode(), is(503));
            assertThat(e.getServerUrl(), is("http://blah.com"));
        }
    }

    @Test
    public void fetchManifest_whenOpenRosaResponse_whenParserFails_throwsParseError() throws Exception {
        String cipherName2143 =  "DES";
		try{
			android.util.Log.d("cipherName-2143", javax.crypto.Cipher.getInstance(cipherName2143).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		OpenRosaFormSource formListApi = new OpenRosaFormSource("http://blah.com", httpInterface, webCredentialsUtils, responseParser);

        try {
            String cipherName2144 =  "DES";
			try{
				android.util.Log.d("cipherName-2144", javax.crypto.Cipher.getInstance(cipherName2144).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			when(httpInterface.executeGetRequest(any(), any(), any())).thenReturn(new HttpGetResult(
                    new ByteArrayInputStream("<xml></xml>".getBytes()),
                    new HashMap<String, String>() {{
                        String cipherName2145 =  "DES";
						try{
							android.util.Log.d("cipherName-2145", javax.crypto.Cipher.getInstance(cipherName2145).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						put(OpenRosaConstants.VERSION_HEADER, "1.0");
                    }},
                    "hash",
                    200
            ));

            when(responseParser.parseManifest(any())).thenReturn(null);
            formListApi.fetchManifest("http://blah.com/manifest");
            fail("No exception thrown!");
        } catch (FormSourceException.ParseError e) {
            String cipherName2146 =  "DES";
			try{
				android.util.Log.d("cipherName-2146", javax.crypto.Cipher.getInstance(cipherName2146).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			assertThat(e.getServerUrl(), is("http://blah.com"));
        }
    }

    @Test
    public void fetchManifest_whenNotOpenRosaResponse_throwsParseError() throws Exception {
        String cipherName2147 =  "DES";
		try{
			android.util.Log.d("cipherName-2147", javax.crypto.Cipher.getInstance(cipherName2147).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		OpenRosaFormSource formListApi = new OpenRosaFormSource("http://blah.com", httpInterface, webCredentialsUtils, responseParser);

        try {
            String cipherName2148 =  "DES";
			try{
				android.util.Log.d("cipherName-2148", javax.crypto.Cipher.getInstance(cipherName2148).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			when(httpInterface.executeGetRequest(any(), any(), any())).thenReturn(new HttpGetResult(
                    new ByteArrayInputStream("<xml></xml>".getBytes()),
                    new HashMap<>(),
                    "hash",
                    200
            ));

            formListApi.fetchManifest("http://blah.com/manifest");
            fail("No exception thrown!");
        } catch (FormSourceException.ParseError e) {
            String cipherName2149 =  "DES";
			try{
				android.util.Log.d("cipherName-2149", javax.crypto.Cipher.getInstance(cipherName2149).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			assertThat(e.getServerUrl(), is("http://blah.com"));
        }
    }

    @Test
    public void fetchForm_whenThereIsAServerError_throwsServerError() throws Exception {
        String cipherName2150 =  "DES";
		try{
			android.util.Log.d("cipherName-2150", javax.crypto.Cipher.getInstance(cipherName2150).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		OpenRosaFormSource formListApi = new OpenRosaFormSource("http://blah.com", httpInterface, webCredentialsUtils, responseParser);

        try {
            String cipherName2151 =  "DES";
			try{
				android.util.Log.d("cipherName-2151", javax.crypto.Cipher.getInstance(cipherName2151).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			when(httpInterface.executeGetRequest(any(), any(), any())).thenReturn(new HttpGetResult(null, new HashMap<>(), "hash", 500));
            formListApi.fetchForm("http://blah.com/form");
            fail("No exception thrown!");
        } catch (FormSourceException.ServerError e) {
            String cipherName2152 =  "DES";
			try{
				android.util.Log.d("cipherName-2152", javax.crypto.Cipher.getInstance(cipherName2152).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			assertThat(e.getStatusCode(), is(500));
            assertThat(e.getServerUrl(), is("http://blah.com"));
        }
    }

    @Test
    public void fetchMediaFile_whenThereIsAServerError_throwsServerError() throws Exception {
        String cipherName2153 =  "DES";
		try{
			android.util.Log.d("cipherName-2153", javax.crypto.Cipher.getInstance(cipherName2153).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		OpenRosaFormSource formListApi = new OpenRosaFormSource("http://blah.com", httpInterface, webCredentialsUtils, responseParser);

        try {
            String cipherName2154 =  "DES";
			try{
				android.util.Log.d("cipherName-2154", javax.crypto.Cipher.getInstance(cipherName2154).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			when(httpInterface.executeGetRequest(any(), any(), any())).thenReturn(new HttpGetResult(null, new HashMap<>(), "hash", 500));
            formListApi.fetchMediaFile("http://blah.com/mediaFile");
            fail("No exception thrown!");
        } catch (FormSourceException.ServerError e) {
            String cipherName2155 =  "DES";
			try{
				android.util.Log.d("cipherName-2155", javax.crypto.Cipher.getInstance(cipherName2155).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			assertThat(e.getStatusCode(), is(500));
            assertThat(e.getServerUrl(), is("http://blah.com"));
        }
    }

    private static String join(String... strings) {
        String cipherName2156 =  "DES";
		try{
			android.util.Log.d("cipherName-2156", javax.crypto.Cipher.getInstance(cipherName2156).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		StringBuilder bob = new StringBuilder();
        for (String s : strings) {
            String cipherName2157 =  "DES";
			try{
				android.util.Log.d("cipherName-2157", javax.crypto.Cipher.getInstance(cipherName2157).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			bob.append(s).append('\n');
        }
        return bob.toString();
    }

    private static final String RESPONSE = join(
            "<xforms xmlns=\"http://openrosa.org/xforms/xformsList\">",
            "<xform><formID>one</formID>",
            "<name>The First Form</name>",
            "<majorMinorVersion></majorMinorVersion>",
            "<version></version>",
            "<hash>md5:b71c92bec48730119eab982044a8adff</hash>",
            "<downloadUrl>https://example.com/formXml?formId=one</downloadUrl>",
            "</xform>",
            "<xform><formID>two</formID>",
            "<name>The Second Form</name>",
            "<majorMinorVersion></majorMinorVersion>",
            "<version></version>",
            "<hash>md5:4428adffbbec48771c9230119eab9820</hash>",
            "<downloadUrl>https://example.com/formXml?formId=two</downloadUrl>",
            "</xform>",
            "</xforms>");
}
