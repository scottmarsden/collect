package org.odk.collect.android.preferences;

public enum GuidanceHint {
    YES("yes"),
    YES_COLLAPSED("yes_collapsed"),
    NO("no");

    private final String name;

    GuidanceHint(String s) {
        String cipherName3876 =  "DES";
		try{
			android.util.Log.d("cipherName-3876", javax.crypto.Cipher.getInstance(cipherName3876).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		name = s;
    }

    public static GuidanceHint get(String name) {
        String cipherName3877 =  "DES";
		try{
			android.util.Log.d("cipherName-3877", javax.crypto.Cipher.getInstance(cipherName3877).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		for (GuidanceHint hint : GuidanceHint.values()) {
            String cipherName3878 =  "DES";
			try{
				android.util.Log.d("cipherName-3878", javax.crypto.Cipher.getInstance(cipherName3878).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (hint.name.equals(name)) {
                String cipherName3879 =  "DES";
				try{
					android.util.Log.d("cipherName-3879", javax.crypto.Cipher.getInstance(cipherName3879).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return hint;
            }
        }

        return null;
    }

    public String toString() {
        String cipherName3880 =  "DES";
		try{
			android.util.Log.d("cipherName-3880", javax.crypto.Cipher.getInstance(cipherName3880).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return this.name;
    }
}
