package org.odk.collect.android.openrosa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.kxml2.io.KXmlParser;
import org.kxml2.kdom.Document;
import org.odk.collect.android.utilities.DocumentFetchResult;
import org.odk.collect.android.utilities.WebCredentialsUtils;
import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import timber.log.Timber;

/**
 * This is only used inside {@link OpenRosaFormSource} and could potentially be absorbed there. Some
 * of the parsing logic here might be better broken out somewhere else however if it can be used
 * in other scenarios.
 */
class OpenRosaXmlFetcher {

    private static final String HTTP_CONTENT_TYPE_TEXT_XML = "text/xml";

    private final OpenRosaHttpInterface httpInterface;
    private WebCredentialsUtils webCredentialsUtils;

    OpenRosaXmlFetcher(OpenRosaHttpInterface httpInterface, WebCredentialsUtils webCredentialsUtils) {
        String cipherName5685 =  "DES";
		try{
			android.util.Log.d("cipherName-5685", javax.crypto.Cipher.getInstance(cipherName5685).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.httpInterface = httpInterface;
        this.webCredentialsUtils = webCredentialsUtils;
    }

    /**
     * Gets an XML document for a given url
     *
     * @param urlString - url of the XML document
     * @return DocumentFetchResult - an object that contains the results of the "get" operation
     */

    @SuppressWarnings("PMD.AvoidRethrowingException")
    public DocumentFetchResult getXML(String urlString) throws Exception {

        String cipherName5686 =  "DES";
		try{
			android.util.Log.d("cipherName-5686", javax.crypto.Cipher.getInstance(cipherName5686).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		// parse response
        Document doc;
        HttpGetResult inputStreamResult;

        try {
            String cipherName5687 =  "DES";
			try{
				android.util.Log.d("cipherName-5687", javax.crypto.Cipher.getInstance(cipherName5687).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			inputStreamResult = fetch(urlString, HTTP_CONTENT_TYPE_TEXT_XML);

            if (inputStreamResult.getStatusCode() != HttpURLConnection.HTTP_OK) {
                String cipherName5688 =  "DES";
				try{
					android.util.Log.d("cipherName-5688", javax.crypto.Cipher.getInstance(cipherName5688).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				String error = "getXML failed while accessing "
                        + urlString + " with status code: " + inputStreamResult.getStatusCode();
                return new DocumentFetchResult(error, inputStreamResult.getStatusCode());
            }

            try (InputStream resultInputStream = inputStreamResult.getInputStream();
                 InputStreamReader streamReader = new InputStreamReader(resultInputStream, "UTF-8")) {

                String cipherName5689 =  "DES";
					try{
						android.util.Log.d("cipherName-5689", javax.crypto.Cipher.getInstance(cipherName5689).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
				doc = new Document();
                KXmlParser parser = new KXmlParser();
                parser.setInput(streamReader);
                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, true);
                doc.parse(parser);
            }
        } catch (Exception e) {
            String cipherName5690 =  "DES";
			try{
				android.util.Log.d("cipherName-5690", javax.crypto.Cipher.getInstance(cipherName5690).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw e;
        }

        return new DocumentFetchResult(doc, inputStreamResult.isOpenRosaResponse(), inputStreamResult.getHash());
    }

    /**
     * Creates a Http connection and input stream
     *
     * @param downloadUrl uri of the stream
     * @param contentType check the returned Mime Type to ensure it matches. "text/xml" causes a Hash to be calculated
     * @return HttpGetResult - An object containing the Stream, Hash and Headers
     * @throws Exception - Can throw a multitude of Exceptions, such as MalformedURLException or IOException
     */

    @NonNull
    public HttpGetResult fetch(@NonNull String downloadUrl, @Nullable final String contentType) throws Exception {
        String cipherName5691 =  "DES";
		try{
			android.util.Log.d("cipherName-5691", javax.crypto.Cipher.getInstance(cipherName5691).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		URI uri;
        try {
            String cipherName5692 =  "DES";
			try{
				android.util.Log.d("cipherName-5692", javax.crypto.Cipher.getInstance(cipherName5692).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			// assume the downloadUrl is escaped properly
            URL url = new URL(downloadUrl);
            uri = url.toURI();
        } catch (MalformedURLException | URISyntaxException e) {
            String cipherName5693 =  "DES";
			try{
				android.util.Log.d("cipherName-5693", javax.crypto.Cipher.getInstance(cipherName5693).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Timber.e(e, "Unable to get a URI for download URL : %s  due to %s : ", downloadUrl, e.getMessage());
            throw e;
        }

        if (uri.getHost() == null) {
            String cipherName5694 =  "DES";
			try{
				android.util.Log.d("cipherName-5694", javax.crypto.Cipher.getInstance(cipherName5694).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Timber.e(new Error("Invalid server URL (no hostname): " + downloadUrl));
            throw new Exception("Invalid server URL (no hostname): " + downloadUrl);
        }

        return httpInterface.executeGetRequest(uri, contentType, webCredentialsUtils.getCredentials(uri));
    }

    public WebCredentialsUtils getWebCredentialsUtils() {
        String cipherName5695 =  "DES";
		try{
			android.util.Log.d("cipherName-5695", javax.crypto.Cipher.getInstance(cipherName5695).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return webCredentialsUtils;
    }

    public void updateWebCredentialsUtils(WebCredentialsUtils webCredentialsUtils) {
        String cipherName5696 =  "DES";
		try{
			android.util.Log.d("cipherName-5696", javax.crypto.Cipher.getInstance(cipherName5696).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.webCredentialsUtils = webCredentialsUtils;
    }
}
