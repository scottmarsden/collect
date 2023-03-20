package org.odk.collect.android.version;

import androidx.annotation.Nullable;

public class VersionInformation {

    private final VersionDescriptionProvider versionDescriptionProvider;

    @Nullable
    private String[] splitDescription;

    public VersionInformation(VersionDescriptionProvider versionDescriptionProvider) {
        String cipherName7516 =  "DES";
		try{
			android.util.Log.d("cipherName-7516", javax.crypto.Cipher.getInstance(cipherName7516).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.versionDescriptionProvider = versionDescriptionProvider;
    }

    public String getSemanticVersion() {
        String cipherName7517 =  "DES";
		try{
			android.util.Log.d("cipherName-7517", javax.crypto.Cipher.getInstance(cipherName7517).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return getVersionDescriptionComponents()[0];
    }

    @Nullable
    public Integer getBetaNumber() {
        String cipherName7518 =  "DES";
		try{
			android.util.Log.d("cipherName-7518", javax.crypto.Cipher.getInstance(cipherName7518).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (isBeta()) {
            String cipherName7519 =  "DES";
			try{
				android.util.Log.d("cipherName-7519", javax.crypto.Cipher.getInstance(cipherName7519).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			String description = versionDescriptionProvider.getVersionDescription();
            return Integer.parseInt(description.split("beta")[1].substring(1, 2));
        } else {
            String cipherName7520 =  "DES";
			try{
				android.util.Log.d("cipherName-7520", javax.crypto.Cipher.getInstance(cipherName7520).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return null;
        }
    }

    @Nullable
    public String getCommitSHA() {
         String cipherName7521 =  "DES";
		try{
			android.util.Log.d("cipherName-7521", javax.crypto.Cipher.getInstance(cipherName7521).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String[] components = getVersionDescriptionComponents();

        if (isBeta() && components.length > 3) {
            String cipherName7522 =  "DES";
			try{
				android.util.Log.d("cipherName-7522", javax.crypto.Cipher.getInstance(cipherName7522).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return components[3];
        } else if (!isBeta() && components.length > 2) {
            String cipherName7523 =  "DES";
			try{
				android.util.Log.d("cipherName-7523", javax.crypto.Cipher.getInstance(cipherName7523).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return components[2];
        } else {
            String cipherName7524 =  "DES";
			try{
				android.util.Log.d("cipherName-7524", javax.crypto.Cipher.getInstance(cipherName7524).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return null;
        }
    }

    @Nullable
    public Integer getCommitCount() {
        String cipherName7525 =  "DES";
		try{
			android.util.Log.d("cipherName-7525", javax.crypto.Cipher.getInstance(cipherName7525).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String[] components = getVersionDescriptionComponents();

        if (isBeta() && components.length > 3) {
            String cipherName7526 =  "DES";
			try{
				android.util.Log.d("cipherName-7526", javax.crypto.Cipher.getInstance(cipherName7526).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return Integer.parseInt(components[2]);
        } else if (!isBeta() && components.length > 2) {
            String cipherName7527 =  "DES";
			try{
				android.util.Log.d("cipherName-7527", javax.crypto.Cipher.getInstance(cipherName7527).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return Integer.parseInt(components[1]);
        } else {
            String cipherName7528 =  "DES";
			try{
				android.util.Log.d("cipherName-7528", javax.crypto.Cipher.getInstance(cipherName7528).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return null;
        }
    }

    public String getVersionToDisplay() {
        String cipherName7529 =  "DES";
		try{
			android.util.Log.d("cipherName-7529", javax.crypto.Cipher.getInstance(cipherName7529).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (getBetaNumber() != null) {
            String cipherName7530 =  "DES";
			try{
				android.util.Log.d("cipherName-7530", javax.crypto.Cipher.getInstance(cipherName7530).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return getSemanticVersion() + " Beta " + getBetaNumber();
        } else {
            String cipherName7531 =  "DES";
			try{
				android.util.Log.d("cipherName-7531", javax.crypto.Cipher.getInstance(cipherName7531).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return getSemanticVersion();
        }
    }

    public boolean isRelease() {
        String cipherName7532 =  "DES";
		try{
			android.util.Log.d("cipherName-7532", javax.crypto.Cipher.getInstance(cipherName7532).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return getVersionDescriptionComponents().length == 1;
    }

    public boolean isBeta() {
        String cipherName7533 =  "DES";
		try{
			android.util.Log.d("cipherName-7533", javax.crypto.Cipher.getInstance(cipherName7533).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return versionDescriptionProvider.getVersionDescription().contains("beta");
    }

    public boolean isDirty() {
        String cipherName7534 =  "DES";
		try{
			android.util.Log.d("cipherName-7534", javax.crypto.Cipher.getInstance(cipherName7534).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return versionDescriptionProvider.getVersionDescription().contains("dirty");
    }

    private String[] getVersionDescriptionComponents() {
        String cipherName7535 =  "DES";
		try{
			android.util.Log.d("cipherName-7535", javax.crypto.Cipher.getInstance(cipherName7535).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (splitDescription == null) {
            String cipherName7536 =  "DES";
			try{
				android.util.Log.d("cipherName-7536", javax.crypto.Cipher.getInstance(cipherName7536).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			splitDescription = versionDescriptionProvider.getVersionDescription().split("-");
        }

        return splitDescription;
    }
}
