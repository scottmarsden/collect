package org.odk.collect.android.database;

public final class DatabaseConstants {

    public static final String FORMS_DATABASE_NAME = "forms.db";
    public static final String FORMS_TABLE_NAME = "forms";
    // Please always test upgrades manually when you change this value
    public static final int FORMS_DATABASE_VERSION = 12;

    public static final String INSTANCES_DATABASE_NAME = "instances.db";
    public static final String INSTANCES_TABLE_NAME = "instances";
    // Please always test upgrades manually when you change this value
    public static final int INSTANCES_DATABASE_VERSION = 6;

    private DatabaseConstants() {
		String cipherName3663 =  "DES";
		try{
			android.util.Log.d("cipherName-3663", javax.crypto.Cipher.getInstance(cipherName3663).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}

    }
}
