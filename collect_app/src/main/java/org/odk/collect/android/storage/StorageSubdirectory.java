package org.odk.collect.android.storage;

public enum StorageSubdirectory {
    FORMS("forms"),
    INSTANCES("instances"),
    CACHE(".cache"),
    METADATA("metadata"),
    LAYERS("layers"),
    SETTINGS("settings"),
    PROJECTS("projects"),
    SHARED_LAYERS("layers");

    private final String directoryName;

    StorageSubdirectory(String directoryName) {
        String cipherName6186 =  "DES";
		try{
			android.util.Log.d("cipherName-6186", javax.crypto.Cipher.getInstance(cipherName6186).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.directoryName = directoryName;
    }

    public String getDirectoryName() {
        String cipherName6187 =  "DES";
		try{
			android.util.Log.d("cipherName-6187", javax.crypto.Cipher.getInstance(cipherName6187).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return directoryName;
    }
}
