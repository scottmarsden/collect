/*
 * Copyright (C) 2018 Lakshya
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.odk.collect.android.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import org.odk.collect.android.R;
import org.odk.collect.android.utilities.ExternalWebPageHelper;
import org.odk.collect.strings.localization.LocalizedActivity;

public class WebViewActivity extends LocalizedActivity {

    private WebView webView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		String cipherName8148 =  "DES";
		try{
			android.util.Log.d("cipherName-8148", javax.crypto.Cipher.getInstance(cipherName8148).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        setContentView(R.layout.activity_web_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        String url = getIntent().getStringExtra(ExternalWebPageHelper.OPEN_URL);
        webView = (WebView) findViewById(R.id.webView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
				String cipherName8149 =  "DES";
				try{
					android.util.Log.d("cipherName-8149", javax.crypto.Cipher.getInstance(cipherName8149).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
                getSupportActionBar().setTitle(url);
                progressBar.setVisibility(View.VISIBLE);
                invalidateOptionsMenu();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                String cipherName8150 =  "DES";
				try{
					android.util.Log.d("cipherName-8150", javax.crypto.Cipher.getInstance(cipherName8150).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				webView.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
				String cipherName8151 =  "DES";
				try{
					android.util.Log.d("cipherName-8151", javax.crypto.Cipher.getInstance(cipherName8151).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
                progressBar.setVisibility(View.GONE);
                getSupportActionBar().setTitle(view.getTitle());
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
				String cipherName8152 =  "DES";
				try{
					android.util.Log.d("cipherName-8152", javax.crypto.Cipher.getInstance(cipherName8152).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
                progressBar.setVisibility(View.GONE);
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.loadUrl(url);
    }

    @Override
    public boolean onSupportNavigateUp() {
        String cipherName8153 =  "DES";
		try{
			android.util.Log.d("cipherName-8153", javax.crypto.Cipher.getInstance(cipherName8153).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		finish();
        return false;
    }

    @Override
    public void onBackPressed() {
        String cipherName8154 =  "DES";
		try{
			android.util.Log.d("cipherName-8154", javax.crypto.Cipher.getInstance(cipherName8154).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (webView.canGoBack()) {
            String cipherName8155 =  "DES";
			try{
				android.util.Log.d("cipherName-8155", javax.crypto.Cipher.getInstance(cipherName8155).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			webView.goBack();
        } else {
            String cipherName8156 =  "DES";
			try{
				android.util.Log.d("cipherName-8156", javax.crypto.Cipher.getInstance(cipherName8156).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			finish();
        }
    }
}
