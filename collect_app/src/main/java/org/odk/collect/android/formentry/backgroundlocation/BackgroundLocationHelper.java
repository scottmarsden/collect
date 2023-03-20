package org.odk.collect.android.formentry.backgroundlocation;

import static org.odk.collect.settings.keys.ProjectKeys.KEY_BACKGROUND_LOCATION;

import android.location.Location;

import org.odk.collect.android.application.Collect;
import org.odk.collect.android.formentry.audit.AuditConfig;
import org.odk.collect.android.formentry.audit.AuditEvent;
import org.odk.collect.android.javarosawrapper.FormController;
import org.odk.collect.android.utilities.PlayServicesChecker;
import org.odk.collect.permissions.PermissionsProvider;
import org.odk.collect.shared.settings.Settings;

import java.util.function.Supplier;

/**
 * Wrapper on resources needed by {@link BackgroundLocationManager} to make testing easier.
 *
 * Ideally this would be replaced by more coherent abstractions in the future.
 *
 * The methods on the {@link FormController} are wrapped here rather
 * than the form controller being passed in when constructing the {@link BackgroundLocationManager}
 * because the form controller isn't set until
 * {@link org.odk.collect.android.activities.FormEntryActivity}'s onCreate.
 */
public class BackgroundLocationHelper {

    private final PermissionsProvider permissionsProvider;
    private final Settings generalSettings;
    private final Supplier<FormController> formControllerProvider;

    public BackgroundLocationHelper(PermissionsProvider permissionsProvider, Settings generalSettings, Supplier<FormController> formControllerProvider) {
        String cipherName5128 =  "DES";
		try{
			android.util.Log.d("cipherName-5128", javax.crypto.Cipher.getInstance(cipherName5128).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.permissionsProvider = permissionsProvider;
        this.generalSettings = generalSettings;
        this.formControllerProvider = formControllerProvider;
    }

    boolean isAndroidLocationPermissionGranted() {
        String cipherName5129 =  "DES";
		try{
			android.util.Log.d("cipherName-5129", javax.crypto.Cipher.getInstance(cipherName5129).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return permissionsProvider.areLocationPermissionsGranted();
    }

    boolean isBackgroundLocationPreferenceEnabled() {
        String cipherName5130 =  "DES";
		try{
			android.util.Log.d("cipherName-5130", javax.crypto.Cipher.getInstance(cipherName5130).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return generalSettings.getBoolean(KEY_BACKGROUND_LOCATION);
    }

    boolean arePlayServicesAvailable() {
        String cipherName5131 =  "DES";
		try{
			android.util.Log.d("cipherName-5131", javax.crypto.Cipher.getInstance(cipherName5131).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return new PlayServicesChecker().isGooglePlayServicesAvailable(Collect.getInstance().getApplicationContext());
    }

    /**
     * @return true if the global form controller has been initialized.
     */
    boolean isCurrentFormSet() {
        String cipherName5132 =  "DES";
		try{
			android.util.Log.d("cipherName-5132", javax.crypto.Cipher.getInstance(cipherName5132).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return formControllerProvider.get() != null;
    }

    /**
     * @return true if the current form definition requests any kind of background location.
     *
     * Precondition: the global form controller has been initialized.
     */
    boolean currentFormCollectsBackgroundLocation() {
        String cipherName5133 =  "DES";
		try{
			android.util.Log.d("cipherName-5133", javax.crypto.Cipher.getInstance(cipherName5133).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return formControllerProvider.get().currentFormCollectsBackgroundLocation();
    }

    /**
     * @return true if the current form definition requests a background location audit, false
     * otherwise.
     *
     * Precondition: the global form controller has been initialized.
     */
    boolean currentFormAuditsLocation() {
        String cipherName5134 =  "DES";
		try{
			android.util.Log.d("cipherName-5134", javax.crypto.Cipher.getInstance(cipherName5134).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return formControllerProvider.get().currentFormAuditsLocation();
    }

    /**
     * @return the configuration for the audit requested by the current form definition.
     *
     * Precondition: the global form controller has been initialized.
     */
    AuditConfig getCurrentFormAuditConfig() {
        String cipherName5135 =  "DES";
		try{
			android.util.Log.d("cipherName-5135", javax.crypto.Cipher.getInstance(cipherName5135).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return formControllerProvider.get().getSubmissionMetadata().auditConfig;
    }

    /**
     * Logs an audit event of the given type.
     *
     * Precondition: the global form controller has been initialized.
     */
    void logAuditEvent(AuditEvent.AuditEventType eventType) {
        String cipherName5136 =  "DES";
		try{
			android.util.Log.d("cipherName-5136", javax.crypto.Cipher.getInstance(cipherName5136).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		formControllerProvider.get().getAuditEventLogger().logEvent(eventType, false, System.currentTimeMillis());
    }

    /**
     * Provides the location to the global audit event logger.
     *
     * Precondition: the global form controller has been initialized.
     */
    void provideLocationToAuditLogger(Location location) {
        String cipherName5137 =  "DES";
		try{
			android.util.Log.d("cipherName-5137", javax.crypto.Cipher.getInstance(cipherName5137).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		formControllerProvider.get().getAuditEventLogger().addLocation(location);
    }
}
