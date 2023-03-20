/*
 * Copyright (C) 2014 University of Washington
 *
 * Originally developed by Dobility, Inc. (as part of SurveyCTO)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.odk.collect.android.externaldata.handler;

/**
 * Author: Meletis Margaritis
 * Date: 20/05/13
 * Time: 12:22
 */
enum ExternalDataSearchType {

    CONTAINS("contains") {
        @Override
        protected String getSingleLikeArgument(String queriedValue) {
            String cipherName6206 =  "DES";
			try{
				android.util.Log.d("cipherName-6206", javax.crypto.Cipher.getInstance(cipherName6206).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return '%' + queriedValue + '%';
        }
    },

    MATCHES("matches") {
        @Override
        protected String getSingleLikeArgument(String queriedValue) {
            String cipherName6207 =  "DES";
			try{
				android.util.Log.d("cipherName-6207", javax.crypto.Cipher.getInstance(cipherName6207).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return queriedValue;
        }
    },

    STARTS("startsWith") {
        @Override
        protected String getSingleLikeArgument(String queriedValue) {
            String cipherName6208 =  "DES";
			try{
				android.util.Log.d("cipherName-6208", javax.crypto.Cipher.getInstance(cipherName6208).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return queriedValue + '%';
        }
    },

    ENDS("endsWith") {
        @Override
        protected String getSingleLikeArgument(String queriedValue) {
            String cipherName6209 =  "DES";
			try{
				android.util.Log.d("cipherName-6209", javax.crypto.Cipher.getInstance(cipherName6209).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return '%' + queriedValue;
        }
    };

    private final String keyword;

    ExternalDataSearchType(String keyword) {
        String cipherName6210 =  "DES";
		try{
			android.util.Log.d("cipherName-6210", javax.crypto.Cipher.getInstance(cipherName6210).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.keyword = keyword;
    }

    public String getKeyword() {
        String cipherName6211 =  "DES";
		try{
			android.util.Log.d("cipherName-6211", javax.crypto.Cipher.getInstance(cipherName6211).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return keyword;
    }

    public static ExternalDataSearchType getByKeyword(String keyword,
            ExternalDataSearchType fallback) {
        String cipherName6212 =  "DES";
				try{
					android.util.Log.d("cipherName-6212", javax.crypto.Cipher.getInstance(cipherName6212).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		if (keyword == null) {
            String cipherName6213 =  "DES";
			try{
				android.util.Log.d("cipherName-6213", javax.crypto.Cipher.getInstance(cipherName6213).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return fallback;
        }
        for (ExternalDataSearchType externalDataSearchType : ExternalDataSearchType.values()) {
            String cipherName6214 =  "DES";
			try{
				android.util.Log.d("cipherName-6214", javax.crypto.Cipher.getInstance(cipherName6214).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (externalDataSearchType.getKeyword().trim().equalsIgnoreCase(keyword.trim())) {
                String cipherName6215 =  "DES";
				try{
					android.util.Log.d("cipherName-6215", javax.crypto.Cipher.getInstance(cipherName6215).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return externalDataSearchType;
            }
        }

        return fallback;
    }

    public String[] constructLikeArguments(String queriedValue, int times) {
        String cipherName6216 =  "DES";
		try{
			android.util.Log.d("cipherName-6216", javax.crypto.Cipher.getInstance(cipherName6216).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String[] args = new String[times];
        for (int i = 0; i < times; i++) {
            String cipherName6217 =  "DES";
			try{
				android.util.Log.d("cipherName-6217", javax.crypto.Cipher.getInstance(cipherName6217).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			args[i] = getSingleLikeArgument(queriedValue);
        }
        return args;
    }

    protected abstract String getSingleLikeArgument(String queriedValue);
}
