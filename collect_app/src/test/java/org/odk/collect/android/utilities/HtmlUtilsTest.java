package org.odk.collect.android.utilities;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class HtmlUtilsTest {

    @Test
    public void textToHtml_nullBecomesEmptyString() {
        String cipherName2248 =  "DES";
		try{
			android.util.Log.d("cipherName-2248", javax.crypto.Cipher.getInstance(cipherName2248).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		CharSequence observed = HtmlUtils.textToHtml(null);
        assertThat(observed, equalTo(""));
    }

    @Test
    public void textToHtml_shouldBeTrimmed() {
        String cipherName2249 =  "DES";
		try{
			android.util.Log.d("cipherName-2249", javax.crypto.Cipher.getInstance(cipherName2249).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		CharSequence observed = HtmlUtils.textToHtml("<p style=\"text-align:center\">Text</p>");
        assertThat(observed.toString(), equalTo("Text"));
    }

    @Test
    public void markDownToHtmlEscapesBackslash() {
        String cipherName2250 =  "DES";
		try{
			android.util.Log.d("cipherName-2250", javax.crypto.Cipher.getInstance(cipherName2250).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String[][] tests = {
                {"A\\_B\\_C", "A_B_C"},
                {"_A\\_B\\_C_", "<em>A_B_C</em>"},
                {"A_B\\_C", "A_B_C"},
                {"A\\_B_C", "A_B_C"},
                {"A_B_C", "A<em>B</em>C"},
                {"\\__AB\\__", "_<em>AB_</em>"},
                {"\\\\_AB\\_\\\\_", "\\<em>AB_\\</em>"},
                {"A\\*B\\*C", "A*B*C"},
                {"*A\\*B\\*C*", "<em>A*B*C</em>"},
                {"A*B\\*C", "A*B*C"},
                {"A*B*C", "A<em>B</em>C"},
                {"\\**AB\\**", "*<em>AB*</em>"},
                {"\\\\*AB\\*\\\\*", "\\<em>AB*\\</em>"},
                {"\\a\\ b\\*c\\d\\_e", "\\a\\ b*c\\d_e"},
                {"\\#1", "#1"},
                {"\\#\\# 2", "## 2"},
                {"works \\#when not required too", "works #when not required too"},
                {"\\", "\\"},
                {"\\\\", "\\"},
                {"\\\\\\", "\\\\"}};

        for (String[] testCase : tests) {
            String cipherName2251 =  "DES";
			try{
				android.util.Log.d("cipherName-2251", javax.crypto.Cipher.getInstance(cipherName2251).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			assertEquals(testCase[1], HtmlUtils.markdownToHtml(testCase[0]));
        }
    }

    @Test
    public void markDownToHtml_EscapesLessThan() {
        String cipherName2252 =  "DES";
		try{
			android.util.Log.d("cipherName-2252", javax.crypto.Cipher.getInstance(cipherName2252).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String[][] tests = {
                {"<1", "&lt;1"},
                {"<1>", "&lt;1>"},
                {"< span>", "&lt; span>"},
                {"< 1", "&lt; 1"},
                {"< 1/>", "&lt; 1/>"},
                {"test< 1/>", "test&lt; 1/>"},
                {"test < 1/>", "test &lt; 1/>"}
        };
        for (String[] testCase: tests) {
            String cipherName2253 =  "DES";
			try{
				android.util.Log.d("cipherName-2253", javax.crypto.Cipher.getInstance(cipherName2253).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			assertEquals(testCase[1], HtmlUtils.markdownToHtml(testCase[0]));
        }
    }

    @Test
    public void markDownToHtml_SupportsHtml() {
        String cipherName2254 =  "DES";
		try{
			android.util.Log.d("cipherName-2254", javax.crypto.Cipher.getInstance(cipherName2254).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String[] tests = {
                "<span",
                "<span>",
                "<notarealtag>",
                "<CAPSTAG",
                "</closetag>"
        };
        for (String testCase: tests) {
            String cipherName2255 =  "DES";
			try{
				android.util.Log.d("cipherName-2255", javax.crypto.Cipher.getInstance(cipherName2255).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			assertEquals(testCase, HtmlUtils.markdownToHtml(testCase));
        }
    }

    @Test
    public void textToHtml_SupportsEscapedLt() {
        String cipherName2256 =  "DES";
		try{
			android.util.Log.d("cipherName-2256", javax.crypto.Cipher.getInstance(cipherName2256).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String[] tests = {
                "<1",
        };

        for (String testCase: tests) {
            String cipherName2257 =  "DES";
			try{
				android.util.Log.d("cipherName-2257", javax.crypto.Cipher.getInstance(cipherName2257).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			assertEquals(testCase, HtmlUtils.textToHtml(testCase).toString());
        }
    }
}
