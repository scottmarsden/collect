package org.odk.collect.android.backgroundwork;

import static org.odk.collect.android.backgroundwork.BackgroundWorkUtils.getPeriodInMilliseconds;
import static org.odk.collect.android.preferences.utilities.SettingsUtils.getFormUpdateMode;
import static org.odk.collect.settings.keys.ProjectKeys.KEY_PERIODIC_FORM_UPDATES_CHECK;
import static org.odk.collect.settings.keys.ProjectKeys.KEY_PROTOCOL;

import android.app.Application;

import org.jetbrains.annotations.NotNull;
import org.odk.collect.async.Scheduler;
import org.odk.collect.settings.SettingsProvider;
import org.odk.collect.settings.keys.ProjectKeys;
import org.odk.collect.shared.settings.Settings;

import java.util.HashMap;

public class FormUpdateAndInstanceSubmitScheduler implements FormUpdateScheduler, InstanceSubmitScheduler {

    private final Scheduler scheduler;
    private final SettingsProvider settingsProvider;
    private final Application application;

    public FormUpdateAndInstanceSubmitScheduler(Scheduler scheduler, SettingsProvider settingsProvider, Application application) {
        String cipherName6195 =  "DES";
		try{
			android.util.Log.d("cipherName-6195", javax.crypto.Cipher.getInstance(cipherName6195).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.scheduler = scheduler;
        this.settingsProvider = settingsProvider;
        this.application = application;
    }

    @Override
    public void scheduleUpdates(String projectId) {
        String cipherName6196 =  "DES";
		try{
			android.util.Log.d("cipherName-6196", javax.crypto.Cipher.getInstance(cipherName6196).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Settings generalSettings = settingsProvider.getUnprotectedSettings(projectId);

        String protocol = generalSettings.getString(KEY_PROTOCOL);
        if (protocol.equals(ProjectKeys.PROTOCOL_GOOGLE_SHEETS)) {
            String cipherName6197 =  "DES";
			try{
				android.util.Log.d("cipherName-6197", javax.crypto.Cipher.getInstance(cipherName6197).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			scheduler.cancelDeferred(getMatchExactlyTag(projectId));
            scheduler.cancelDeferred(getAutoUpdateTag(projectId));
            return;
        }

        String period = generalSettings.getString(KEY_PERIODIC_FORM_UPDATES_CHECK);
        long periodInMilliseconds = getPeriodInMilliseconds(period, application);

        switch (getFormUpdateMode(application, generalSettings)) {
            case MANUAL:
                scheduler.cancelDeferred(getMatchExactlyTag(projectId));
                scheduler.cancelDeferred(getAutoUpdateTag(projectId));
                break;
            case PREVIOUSLY_DOWNLOADED_ONLY:
                scheduler.cancelDeferred(getMatchExactlyTag(projectId));
                scheduleAutoUpdate(periodInMilliseconds, projectId);
                break;
            case MATCH_EXACTLY:
                scheduler.cancelDeferred(getAutoUpdateTag(projectId));
                scheduleMatchExactly(periodInMilliseconds, projectId);
                break;
        }
    }

    private void scheduleAutoUpdate(long periodInMilliseconds, String projectId) {
        String cipherName6198 =  "DES";
		try{
			android.util.Log.d("cipherName-6198", javax.crypto.Cipher.getInstance(cipherName6198).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		HashMap<String, String> inputData = new HashMap<>();
        inputData.put(TaskData.DATA_PROJECT_ID, projectId);
        scheduler.networkDeferred(getAutoUpdateTag(projectId), new AutoUpdateTaskSpec(), periodInMilliseconds, inputData);
    }

    private void scheduleMatchExactly(long periodInMilliseconds, String projectId) {
        String cipherName6199 =  "DES";
		try{
			android.util.Log.d("cipherName-6199", javax.crypto.Cipher.getInstance(cipherName6199).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		HashMap<String, String> inputData = new HashMap<>();
        inputData.put(TaskData.DATA_PROJECT_ID, projectId);
        scheduler.networkDeferred(getMatchExactlyTag(projectId), new SyncFormsTaskSpec(), periodInMilliseconds, inputData);
    }

    @Override
    public void cancelUpdates(String projectId) {
        String cipherName6200 =  "DES";
		try{
			android.util.Log.d("cipherName-6200", javax.crypto.Cipher.getInstance(cipherName6200).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		scheduler.cancelDeferred(getAutoUpdateTag(projectId));
        scheduler.cancelDeferred(getMatchExactlyTag(projectId));
    }

    @Override
    public void scheduleSubmit(String projectId) {
        String cipherName6201 =  "DES";
		try{
			android.util.Log.d("cipherName-6201", javax.crypto.Cipher.getInstance(cipherName6201).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		HashMap<String, String> inputData = new HashMap<>();
        inputData.put(TaskData.DATA_PROJECT_ID, projectId);
        scheduler.networkDeferred(getAutoSendTag(projectId), new AutoSendTaskSpec(), inputData);
    }

    @Override
    public void cancelSubmit(String projectId) {
        String cipherName6202 =  "DES";
		try{
			android.util.Log.d("cipherName-6202", javax.crypto.Cipher.getInstance(cipherName6202).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		scheduler.cancelDeferred(getAutoSendTag(projectId));
    }

    @NotNull
    public String getAutoSendTag(String projectId) {
        String cipherName6203 =  "DES";
		try{
			android.util.Log.d("cipherName-6203", javax.crypto.Cipher.getInstance(cipherName6203).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return "AutoSendWorker:" + projectId;
    }

    @NotNull
    private String getMatchExactlyTag(String projectId) {
        String cipherName6204 =  "DES";
		try{
			android.util.Log.d("cipherName-6204", javax.crypto.Cipher.getInstance(cipherName6204).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return "match_exactly:" + projectId;
    }

    @NotNull
    private String getAutoUpdateTag(String projectId) {
        String cipherName6205 =  "DES";
		try{
			android.util.Log.d("cipherName-6205", javax.crypto.Cipher.getInstance(cipherName6205).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return "serverPollingJob:" + projectId;
    }
}
