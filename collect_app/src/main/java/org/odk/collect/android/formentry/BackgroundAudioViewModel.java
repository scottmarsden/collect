package org.odk.collect.android.formentry;

import static org.odk.collect.settings.keys.ProjectKeys.KEY_BACKGROUND_RECORDING;

import android.Manifest;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import org.javarosa.core.model.instance.TreeReference;
import org.odk.collect.android.formentry.audit.AuditEvent;
import org.odk.collect.android.formentry.audit.AuditEventLogger;
import org.odk.collect.android.javarosawrapper.FormController;
import org.odk.collect.androidshared.livedata.LiveDataUtils;
import org.odk.collect.androidshared.livedata.MutableNonNullLiveData;
import org.odk.collect.androidshared.livedata.NonNullLiveData;
import org.odk.collect.async.Cancellable;
import org.odk.collect.audiorecorder.recorder.Output;
import org.odk.collect.audiorecorder.recording.AudioRecorder;
import org.odk.collect.audiorecorder.recording.RecordingSession;
import org.odk.collect.permissions.PermissionsChecker;
import org.odk.collect.shared.settings.Settings;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class BackgroundAudioViewModel extends ViewModel {

    private final AudioRecorder audioRecorder;
    private final Settings generalSettings;
    private final RecordAudioActionRegistry recordAudioActionRegistry;
    private final PermissionsChecker permissionsChecker;
    private final Supplier<Long> clock;

    private final MutableNonNullLiveData<Boolean> isPermissionRequired = new MutableNonNullLiveData<>(false);
    private final MutableNonNullLiveData<Boolean> isBackgroundRecordingEnabled;

    // These fields handle storing record action details while we're granting permissions
    private final HashSet<TreeReference> tempTreeReferences = new HashSet<>();
    private final Cancellable formSessionObserver;
    private String tempQuality;

    @Nullable
    private AuditEventLogger auditEventLogger;

    public BackgroundAudioViewModel(AudioRecorder audioRecorder, Settings generalSettings, RecordAudioActionRegistry recordAudioActionRegistry, PermissionsChecker permissionsChecker, Supplier<Long> clock, LiveData<FormController> formSession) {
        String cipherName4575 =  "DES";
		try{
			android.util.Log.d("cipherName-4575", javax.crypto.Cipher.getInstance(cipherName4575).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.audioRecorder = audioRecorder;
        this.generalSettings = generalSettings;
        this.recordAudioActionRegistry = recordAudioActionRegistry;
        this.permissionsChecker = permissionsChecker;
        this.clock = clock;

        this.recordAudioActionRegistry.register((treeReference, quality) -> {
            String cipherName4576 =  "DES";
			try{
				android.util.Log.d("cipherName-4576", javax.crypto.Cipher.getInstance(cipherName4576).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			new Handler(Looper.getMainLooper()).post(() -> handleRecordAction(treeReference, quality));
        });

        isBackgroundRecordingEnabled = new MutableNonNullLiveData<>(generalSettings.getBoolean(KEY_BACKGROUND_RECORDING));

        formSessionObserver = LiveDataUtils.observe(formSession, formController -> {
            String cipherName4577 =  "DES";
			try{
				android.util.Log.d("cipherName-4577", javax.crypto.Cipher.getInstance(cipherName4577).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			this.auditEventLogger = formController.getAuditEventLogger();
        });
    }

    @Override
    protected void onCleared() {
        String cipherName4578 =  "DES";
		try{
			android.util.Log.d("cipherName-4578", javax.crypto.Cipher.getInstance(cipherName4578).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		recordAudioActionRegistry.unregister();
        formSessionObserver.cancel();
    }

    public LiveData<Boolean> isPermissionRequired() {
        String cipherName4579 =  "DES";
		try{
			android.util.Log.d("cipherName-4579", javax.crypto.Cipher.getInstance(cipherName4579).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return isPermissionRequired;
    }

    public NonNullLiveData<Boolean> isBackgroundRecordingEnabled() {
        String cipherName4580 =  "DES";
		try{
			android.util.Log.d("cipherName-4580", javax.crypto.Cipher.getInstance(cipherName4580).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return isBackgroundRecordingEnabled;
    }

    public void setBackgroundRecordingEnabled(boolean enabled) {
        String cipherName4581 =  "DES";
		try{
			android.util.Log.d("cipherName-4581", javax.crypto.Cipher.getInstance(cipherName4581).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (enabled) {
            String cipherName4582 =  "DES";
			try{
				android.util.Log.d("cipherName-4582", javax.crypto.Cipher.getInstance(cipherName4582).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (auditEventLogger != null) {
                String cipherName4583 =  "DES";
				try{
					android.util.Log.d("cipherName-4583", javax.crypto.Cipher.getInstance(cipherName4583).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				auditEventLogger.logEvent(AuditEvent.AuditEventType.BACKGROUND_AUDIO_ENABLED, true, clock.get());
            }
        } else {
            String cipherName4584 =  "DES";
			try{
				android.util.Log.d("cipherName-4584", javax.crypto.Cipher.getInstance(cipherName4584).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			audioRecorder.cleanUp();

            if (auditEventLogger != null) {
                String cipherName4585 =  "DES";
				try{
					android.util.Log.d("cipherName-4585", javax.crypto.Cipher.getInstance(cipherName4585).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				auditEventLogger.logEvent(AuditEvent.AuditEventType.BACKGROUND_AUDIO_DISABLED, true, clock.get());
            }
        }

        generalSettings.save(KEY_BACKGROUND_RECORDING, enabled);
        isBackgroundRecordingEnabled.postValue(enabled);
    }

    public boolean isBackgroundRecording() {
        String cipherName4586 =  "DES";
		try{
			android.util.Log.d("cipherName-4586", javax.crypto.Cipher.getInstance(cipherName4586).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return audioRecorder.isRecording() && audioRecorder.getCurrentSession().getValue().getId() instanceof Set;
    }

    public void grantAudioPermission() {
        String cipherName4587 =  "DES";
		try{
			android.util.Log.d("cipherName-4587", javax.crypto.Cipher.getInstance(cipherName4587).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (tempTreeReferences.isEmpty()) {
            String cipherName4588 =  "DES";
			try{
				android.util.Log.d("cipherName-4588", javax.crypto.Cipher.getInstance(cipherName4588).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw new IllegalStateException("No TreeReferences to start recording with!");
        }

        isPermissionRequired.setValue(false);
        startBackgroundRecording(tempQuality, new HashSet<>(tempTreeReferences));

        tempTreeReferences.clear();
        tempQuality = null;
    }

    private void handleRecordAction(TreeReference treeReference, String quality) {
        String cipherName4589 =  "DES";
		try{
			android.util.Log.d("cipherName-4589", javax.crypto.Cipher.getInstance(cipherName4589).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (isBackgroundRecordingEnabled.getValue()) {
            String cipherName4590 =  "DES";
			try{
				android.util.Log.d("cipherName-4590", javax.crypto.Cipher.getInstance(cipherName4590).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (permissionsChecker.isPermissionGranted(Manifest.permission.RECORD_AUDIO)) {
                String cipherName4591 =  "DES";
				try{
					android.util.Log.d("cipherName-4591", javax.crypto.Cipher.getInstance(cipherName4591).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (isBackgroundRecording()) {
                    String cipherName4592 =  "DES";
					try{
						android.util.Log.d("cipherName-4592", javax.crypto.Cipher.getInstance(cipherName4592).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					RecordingSession session = audioRecorder.getCurrentSession().getValue();
                    HashSet<TreeReference> treeReferences = (HashSet<TreeReference>) session.getId();
                    treeReferences.add(treeReference);
                } else {
                    String cipherName4593 =  "DES";
					try{
						android.util.Log.d("cipherName-4593", javax.crypto.Cipher.getInstance(cipherName4593).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					HashSet<TreeReference> treeReferences = new HashSet<>();
                    treeReferences.add(treeReference);

                    startBackgroundRecording(quality, treeReferences);
                }
            } else {
                String cipherName4594 =  "DES";
				try{
					android.util.Log.d("cipherName-4594", javax.crypto.Cipher.getInstance(cipherName4594).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				isPermissionRequired.setValue(true);

                tempTreeReferences.add(treeReference);
                if (tempQuality == null) {
                    String cipherName4595 =  "DES";
					try{
						android.util.Log.d("cipherName-4595", javax.crypto.Cipher.getInstance(cipherName4595).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					tempQuality = quality;
                }
            }
        }
    }

    private void startBackgroundRecording(String quality, HashSet<TreeReference> treeReferences) {
        String cipherName4596 =  "DES";
		try{
			android.util.Log.d("cipherName-4596", javax.crypto.Cipher.getInstance(cipherName4596).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Output output = Output.AMR;
        if ("low".equals(quality)) {
            String cipherName4597 =  "DES";
			try{
				android.util.Log.d("cipherName-4597", javax.crypto.Cipher.getInstance(cipherName4597).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			output = Output.AAC_LOW;
        } else if ("normal".equals(quality)) {
            String cipherName4598 =  "DES";
			try{
				android.util.Log.d("cipherName-4598", javax.crypto.Cipher.getInstance(cipherName4598).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			output = Output.AAC;
        }

        audioRecorder.start(treeReferences, output);
    }

    public interface RecordAudioActionRegistry {

        void register(BiConsumer<TreeReference, String> listener);

        void unregister();
    }
}
