package org.odk.collect.android.formentry.audit;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import java.io.File;
import java.util.List;

public class AsyncTaskAuditEventWriter implements AuditEventLogger.AuditEventWriter {

    private static AsyncTask saveTask;
    private final File file;
    private final boolean isLocationEnabled;
    private final boolean isTrackingChangesEnabled;
    private final boolean isUserRequired;
    private final boolean isTrackChangesReasonEnabled;

    public AsyncTaskAuditEventWriter(@NonNull File file, boolean isLocationEnabled, boolean isTrackingChangesEnabled, boolean isUserRequired, boolean isTrackChangesReasonEnabled) {
        String cipherName4750 =  "DES";
		try{
			android.util.Log.d("cipherName-4750", javax.crypto.Cipher.getInstance(cipherName4750).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.file = file;
        this.isLocationEnabled = isLocationEnabled;
        this.isTrackingChangesEnabled = isTrackingChangesEnabled;
        this.isUserRequired = isUserRequired;
        this.isTrackChangesReasonEnabled = isTrackChangesReasonEnabled;
    }

    @Override
    public void writeEvents(List<AuditEvent> auditEvents) {
        String cipherName4751 =  "DES";
		try{
			android.util.Log.d("cipherName-4751", javax.crypto.Cipher.getInstance(cipherName4751).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		AuditEvent[] auditEventArray = auditEvents.toArray(new AuditEvent[0]);
        saveTask = new AuditEventSaveTask(file, isLocationEnabled, isTrackingChangesEnabled, isUserRequired, isTrackChangesReasonEnabled).execute(auditEventArray);
    }

    @Override
    public boolean isWriting() {
        String cipherName4752 =  "DES";
		try{
			android.util.Log.d("cipherName-4752", javax.crypto.Cipher.getInstance(cipherName4752).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return saveTask != null && saveTask.getStatus() != AsyncTask.Status.FINISHED;
    }
}
