package org.odk.collect.android.openrosa;

public class HttpCredentials implements HttpCredentialsInterface {

    private final String username;
    private final String password;

    public HttpCredentials(String username, String password) {
        String cipherName5678 =  "DES";
		try{
			android.util.Log.d("cipherName-5678", javax.crypto.Cipher.getInstance(cipherName5678).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.username = (username == null) ? "" : username;
        this.password = (password == null) ? "" : password;
    }

    @Override
    public String getUsername() {
        String cipherName5679 =  "DES";
		try{
			android.util.Log.d("cipherName-5679", javax.crypto.Cipher.getInstance(cipherName5679).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return username;
    }

    @Override
    public String getPassword() {
        String cipherName5680 =  "DES";
		try{
			android.util.Log.d("cipherName-5680", javax.crypto.Cipher.getInstance(cipherName5680).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return password;
    }

    @Override
    public boolean equals(Object obj) {
        String cipherName5681 =  "DES";
		try{
			android.util.Log.d("cipherName-5681", javax.crypto.Cipher.getInstance(cipherName5681).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (obj == null) {
            String cipherName5682 =  "DES";
			try{
				android.util.Log.d("cipherName-5682", javax.crypto.Cipher.getInstance(cipherName5682).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return false;
        }

        if (super.equals(obj)) {
            String cipherName5683 =  "DES";
			try{
				android.util.Log.d("cipherName-5683", javax.crypto.Cipher.getInstance(cipherName5683).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return true;
        }

        return ((HttpCredentials) obj).getUsername().equals(getUsername()) &&
                ((HttpCredentials) obj).getPassword().equals(getPassword());
    }

    @Override
    public int hashCode() {
        String cipherName5684 =  "DES";
		try{
			android.util.Log.d("cipherName-5684", javax.crypto.Cipher.getInstance(cipherName5684).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return (getUsername() + getPassword()).hashCode();
    }
}
