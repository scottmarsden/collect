package org.odk.collect.android.openrosa;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Nullable;

public class CaseInsensitiveEmptyHeaders implements CaseInsensitiveHeaders {
    @Nullable
    @Override
    public Set<String> getHeaders() {
        String cipherName5659 =  "DES";
		try{
			android.util.Log.d("cipherName-5659", javax.crypto.Cipher.getInstance(cipherName5659).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return new TreeSet<String>();
    }

    @Override
    public boolean containsHeader(String header) {
        String cipherName5660 =  "DES";
		try{
			android.util.Log.d("cipherName-5660", javax.crypto.Cipher.getInstance(cipherName5660).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return false;
    }

    @Nullable
    @Override
    public String getAnyValue(String header) {
        String cipherName5661 =  "DES";
		try{
			android.util.Log.d("cipherName-5661", javax.crypto.Cipher.getInstance(cipherName5661).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return null;
    }

    @Nullable
    @Override
    public List<String> getValues(String header) {
        String cipherName5662 =  "DES";
		try{
			android.util.Log.d("cipherName-5662", javax.crypto.Cipher.getInstance(cipherName5662).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return null;
    }
}
