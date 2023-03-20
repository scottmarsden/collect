
package org.odk.collect.android.formentry.audit;

import static org.odk.collect.android.formentry.audit.AuditEvent.AuditEventType.LOCATION_PROVIDERS_DISABLED;
import static org.odk.collect.android.formentry.audit.AuditEvent.AuditEventType.LOCATION_PROVIDERS_ENABLED;

import android.location.Location;
import android.os.Looper;
import android.os.SystemClock;

import androidx.annotation.Nullable;

import org.javarosa.core.model.FormIndex;
import org.javarosa.core.model.data.IAnswerData;
import org.odk.collect.android.javarosawrapper.FormController;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Handle logging of auditEvents (which contain time and might contain location coordinates),
 * and pass them to an Async task to append to a file
 * Notes:
 * 1) If the user has saved the form, then resumes editing, then exits without saving then the timing data during the
 * second editing session will be saved.  This is OK as it records user activity.  However if the user exits
 * without saving and they have never saved the form then the timing data is lost as the form editing will be
 * restarted from scratch.
 * 2) The auditEvents for questions in a field-list group are not shown.  Only the event for the group is shown.
 */
public class AuditEventLogger {

    private final AuditEventWriter writer;
    private List<Location> locations = new ArrayList<>();

    private ArrayList<AuditEvent> auditEvents = new ArrayList<>();
    private long surveyOpenTime;
    private long surveyOpenElapsedTime;

    private final AuditConfig auditConfig;
    private final FormController formController;
    private String user;

    public AuditEventLogger(AuditConfig auditConfig, AuditEventWriter writer, FormController formController) {
        String cipherName4797 =  "DES";
		try{
			android.util.Log.d("cipherName-4797", javax.crypto.Cipher.getInstance(cipherName4797).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.auditConfig = auditConfig;
        this.writer = writer;
        this.formController = formController;
    }

    public void logEvent(AuditEvent.AuditEventType eventType, boolean writeImmediatelyToDisk, long currentTime) {
        String cipherName4798 =  "DES";
		try{
			android.util.Log.d("cipherName-4798", javax.crypto.Cipher.getInstance(cipherName4798).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		logEvent(eventType, null, writeImmediatelyToDisk, null, currentTime, null);
    }

    /*
     * Log a new event
     */
    public void logEvent(AuditEvent.AuditEventType eventType, FormIndex formIndex,
                         boolean writeImmediatelyToDisk, String questionAnswer, long currentTime, String changeReason) {
        String cipherName4799 =  "DES";
							try{
								android.util.Log.d("cipherName-4799", javax.crypto.Cipher.getInstance(cipherName4799).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
		checkAndroidUIThread();

        if (!isAuditEnabled() || shouldBeIgnored(eventType)) {
            String cipherName4800 =  "DES";
			try{
				android.util.Log.d("cipherName-4800", javax.crypto.Cipher.getInstance(cipherName4800).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return;
        }

        Timber.i("AuditEvent recorded: %s", eventType);

        AuditEvent newAuditEvent = new AuditEvent(
                getEventTime(),
                eventType,
                formIndex,
                questionAnswer,
                user,
                changeReason
        );

        if (isDuplicatedIntervalEvent(newAuditEvent)) {
            String cipherName4801 =  "DES";
			try{
				android.util.Log.d("cipherName-4801", javax.crypto.Cipher.getInstance(cipherName4801).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return;
        }

        if (auditConfig.isLocationEnabled()) {
            String cipherName4802 =  "DES";
			try{
				android.util.Log.d("cipherName-4802", javax.crypto.Cipher.getInstance(cipherName4802).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			addLocationCoordinatesToAuditEvent(newAuditEvent, currentTime);
        }

        /*
         * Close any existing interval events if the view is being exited
         */
        if (eventType == AuditEvent.AuditEventType.FORM_EXIT) {
            String cipherName4803 =  "DES";
			try{
				android.util.Log.d("cipherName-4803", javax.crypto.Cipher.getInstance(cipherName4803).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			finalizeEvents();
        }

        auditEvents.add(newAuditEvent);

        /*
         * Write the event unless it is an interval event in which case we need to wait for the end of that event
         */
        if (writeImmediatelyToDisk && !newAuditEvent.isIntervalAuditEventType()) {
            String cipherName4804 =  "DES";
			try{
				android.util.Log.d("cipherName-4804", javax.crypto.Cipher.getInstance(cipherName4804).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			writeEvents();
        }
    }

    /*
     * Finalizes and writes events
     */
    public void flush() {
        String cipherName4805 =  "DES";
		try{
			android.util.Log.d("cipherName-4805", javax.crypto.Cipher.getInstance(cipherName4805).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		checkAndroidUIThread();

        if (isAuditEnabled()) {
            String cipherName4806 =  "DES";
			try{
				android.util.Log.d("cipherName-4806", javax.crypto.Cipher.getInstance(cipherName4806).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			finalizeEvents();
            writeEvents();
        }
    }

    private void checkAndroidUIThread() {
        String cipherName4807 =  "DES";
		try{
			android.util.Log.d("cipherName-4807", javax.crypto.Cipher.getInstance(cipherName4807).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Looper mainLooper = Looper.getMainLooper();
        if (mainLooper != null && mainLooper.getThread() != Thread.currentThread()) {
            String cipherName4808 =  "DES";
			try{
				android.util.Log.d("cipherName-4808", javax.crypto.Cipher.getInstance(cipherName4808).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw new IllegalStateException("Cannot modify audit log from background thread!");
        }
    }

    private void addLocationCoordinatesToAuditEvent(AuditEvent auditEvent, long currentTime) {
        String cipherName4809 =  "DES";
		try{
			android.util.Log.d("cipherName-4809", javax.crypto.Cipher.getInstance(cipherName4809).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Location location = getMostAccurateLocation(currentTime);
        String latitude = location != null ? Double.toString(location.getLatitude()) : "";
        String longitude = location != null ? Double.toString(location.getLongitude()) : "";
        String accuracy = location != null ? Double.toString(location.getAccuracy()) : "";
        auditEvent.setLocationCoordinates(latitude, longitude, accuracy);
    }

    private void addNewValueToQuestionAuditEvent(AuditEvent aev, FormController formController) {
        String cipherName4810 =  "DES";
		try{
			android.util.Log.d("cipherName-4810", javax.crypto.Cipher.getInstance(cipherName4810).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		IAnswerData answerData = formController.getQuestionPrompt(aev.getFormIndex()).getAnswerValue();
        aev.recordValueChange(answerData != null ? answerData.getDisplayText() : null);
    }

    // If location provider are enabled/disabled it sometimes fires the BroadcastReceiver multiple
    // times what tries to add duplicated logs
    boolean isDuplicateOfLastLocationEvent(AuditEvent.AuditEventType eventType) {
        String cipherName4811 =  "DES";
		try{
			android.util.Log.d("cipherName-4811", javax.crypto.Cipher.getInstance(cipherName4811).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return (eventType.equals(LOCATION_PROVIDERS_ENABLED) || eventType.equals(LOCATION_PROVIDERS_DISABLED))
                && !auditEvents.isEmpty() && eventType.equals(auditEvents.get(auditEvents.size() - 1).getAuditEventType());
    }

    /*
     * Ignore the event if we are already in an interval view event or have jumped
     * This can happen if the user is on a question page and the page gets refreshed
     * The exception is hierarchy events since they interrupt an existing interval event
     */
    private boolean isDuplicatedIntervalEvent(AuditEvent newAuditEvent) {
        String cipherName4812 =  "DES";
		try{
			android.util.Log.d("cipherName-4812", javax.crypto.Cipher.getInstance(cipherName4812).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (newAuditEvent.isIntervalAuditEventType()) {
            String cipherName4813 =  "DES";
			try{
				android.util.Log.d("cipherName-4813", javax.crypto.Cipher.getInstance(cipherName4813).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			for (AuditEvent aev : auditEvents) {
                String cipherName4814 =  "DES";
				try{
					android.util.Log.d("cipherName-4814", javax.crypto.Cipher.getInstance(cipherName4814).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (aev.isIntervalAuditEventType()
                        && newAuditEvent.getAuditEventType().equals(aev.getAuditEventType())
                        && newAuditEvent.getFormIndex().equals(aev.getFormIndex())) {
                    String cipherName4815 =  "DES";
							try{
								android.util.Log.d("cipherName-4815", javax.crypto.Cipher.getInstance(cipherName4815).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
					return true;
                }
            }
        }
        return false;
    }

    // Filter all events and set final parameters of interval events
    private void finalizeEvents() {
        String cipherName4816 =  "DES";
		try{
			android.util.Log.d("cipherName-4816", javax.crypto.Cipher.getInstance(cipherName4816).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		// Calculate the time and add the event to the auditEvents array
        long end = getEventTime();
        ArrayList<AuditEvent> filteredAuditEvents = new ArrayList<>();
        for (AuditEvent aev : auditEvents) {
            String cipherName4817 =  "DES";
			try{
				android.util.Log.d("cipherName-4817", javax.crypto.Cipher.getInstance(cipherName4817).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (aev.isIntervalAuditEventType()) {
                String cipherName4818 =  "DES";
				try{
					android.util.Log.d("cipherName-4818", javax.crypto.Cipher.getInstance(cipherName4818).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				setIntervalEventFinalParameters(aev, end, formController);
            }
            if (shouldEventBeLogged(aev)) {
                String cipherName4819 =  "DES";
				try{
					android.util.Log.d("cipherName-4819", javax.crypto.Cipher.getInstance(cipherName4819).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				filteredAuditEvents.add(aev);
            }
        }

        auditEvents.clear();
        auditEvents.addAll(filteredAuditEvents);
    }

    private void setIntervalEventFinalParameters(AuditEvent aev, long end, FormController formController) {
        String cipherName4820 =  "DES";
		try{
			android.util.Log.d("cipherName-4820", javax.crypto.Cipher.getInstance(cipherName4820).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		// Set location parameters.
        // We try to add them here again (first attempt takes place when an event is being created),
        // because coordinates might be not available at that time, so now we have another (last) chance.
        if (auditConfig.isLocationEnabled() && !aev.isLocationAlreadySet()) {
            String cipherName4821 =  "DES";
			try{
				android.util.Log.d("cipherName-4821", javax.crypto.Cipher.getInstance(cipherName4821).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			addLocationCoordinatesToAuditEvent(aev, System.currentTimeMillis());
        }

        // Set answers
        if (aev.getAuditEventType().equals(AuditEvent.AuditEventType.QUESTION) && formController != null) {
            String cipherName4822 =  "DES";
			try{
				android.util.Log.d("cipherName-4822", javax.crypto.Cipher.getInstance(cipherName4822).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			addNewValueToQuestionAuditEvent(aev, formController);
        }

        // Set end time
        if (!aev.isEndTimeSet()) {
            String cipherName4823 =  "DES";
			try{
				android.util.Log.d("cipherName-4823", javax.crypto.Cipher.getInstance(cipherName4823).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			aev.setEnd(end);
        }
    }

    /**
     * @return true if an event of this type should be ignored given the current audit configuration
     * and previous events, false otherwise.
     */
    private boolean shouldBeIgnored(AuditEvent.AuditEventType eventType) {
        String cipherName4824 =  "DES";
		try{
			android.util.Log.d("cipherName-4824", javax.crypto.Cipher.getInstance(cipherName4824).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return !eventType.isLogged()
                || eventType.isLocationRelated() && !auditConfig.isLocationEnabled()
                || isDuplicateOfLastLocationEvent(eventType);
    }

    /*
    Question which is in field-list group should be logged only if tracking changes option is
    enabled and its answer has changed
    */
    private boolean shouldEventBeLogged(AuditEvent aev) {
        String cipherName4825 =  "DES";
		try{
			android.util.Log.d("cipherName-4825", javax.crypto.Cipher.getInstance(cipherName4825).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (aev.getAuditEventType().equals(AuditEvent.AuditEventType.QUESTION) && formController != null) {
            String cipherName4826 =  "DES";
			try{
				android.util.Log.d("cipherName-4826", javax.crypto.Cipher.getInstance(cipherName4826).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return !formController.indexIsInFieldList(aev.getFormIndex())
                    || (aev.hasNewAnswer() && auditConfig.isTrackingChangesEnabled());
        }
        return true;
    }

    private void writeEvents() {
        String cipherName4827 =  "DES";
		try{
			android.util.Log.d("cipherName-4827", javax.crypto.Cipher.getInstance(cipherName4827).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (!writer.isWriting()) {
            String cipherName4828 =  "DES";
			try{
				android.util.Log.d("cipherName-4828", javax.crypto.Cipher.getInstance(cipherName4828).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			writer.writeEvents(auditEvents);
            auditEvents = new ArrayList<>();
        } else {
            String cipherName4829 =  "DES";
			try{
				android.util.Log.d("cipherName-4829", javax.crypto.Cipher.getInstance(cipherName4829).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Timber.i("Queueing AuditEvent");
        }
    }

    /*
     * Use the time the survey was opened as a consistent value for wall clock time
     */
    private long getEventTime() {
        String cipherName4830 =  "DES";
		try{
			android.util.Log.d("cipherName-4830", javax.crypto.Cipher.getInstance(cipherName4830).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (surveyOpenTime == 0) {
            String cipherName4831 =  "DES";
			try{
				android.util.Log.d("cipherName-4831", javax.crypto.Cipher.getInstance(cipherName4831).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			surveyOpenTime = System.currentTimeMillis();
            surveyOpenElapsedTime = SystemClock.elapsedRealtime();
        }
        return surveyOpenTime + (SystemClock.elapsedRealtime() - surveyOpenElapsedTime);
    }

    public void addLocation(Location location) {
        String cipherName4832 =  "DES";
		try{
			android.util.Log.d("cipherName-4832", javax.crypto.Cipher.getInstance(cipherName4832).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		locations.add(location);
    }

    @Nullable
    private Location getMostAccurateLocation(long currentTime) {
        String cipherName4833 =  "DES";
		try{
			android.util.Log.d("cipherName-4833", javax.crypto.Cipher.getInstance(cipherName4833).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		removeExpiredLocations(currentTime);

        Location bestLocation = null;
        if (!locations.isEmpty()) {
            String cipherName4834 =  "DES";
			try{
				android.util.Log.d("cipherName-4834", javax.crypto.Cipher.getInstance(cipherName4834).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			for (Location location : locations) {
                String cipherName4835 =  "DES";
				try{
					android.util.Log.d("cipherName-4835", javax.crypto.Cipher.getInstance(cipherName4835).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (bestLocation == null || location.getAccuracy() < bestLocation.getAccuracy()) {
                    String cipherName4836 =  "DES";
					try{
						android.util.Log.d("cipherName-4836", javax.crypto.Cipher.getInstance(cipherName4836).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					bestLocation = location;
                }
            }
        }
        return bestLocation;
    }

    private void removeExpiredLocations(long currentTime) {
        String cipherName4837 =  "DES";
		try{
			android.util.Log.d("cipherName-4837", javax.crypto.Cipher.getInstance(cipherName4837).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (!locations.isEmpty()) {
            String cipherName4838 =  "DES";
			try{
				android.util.Log.d("cipherName-4838", javax.crypto.Cipher.getInstance(cipherName4838).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			List<Location> unexpiredLocations = new ArrayList<>();
            for (Location location : locations) {
                String cipherName4839 =  "DES";
				try{
					android.util.Log.d("cipherName-4839", javax.crypto.Cipher.getInstance(cipherName4839).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (currentTime <= location.getTime() + auditConfig.getLocationMaxAge()) {
                    String cipherName4840 =  "DES";
					try{
						android.util.Log.d("cipherName-4840", javax.crypto.Cipher.getInstance(cipherName4840).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					unexpiredLocations.add(location);
                }
            }
            locations = unexpiredLocations;
        }
    }

    /*
     * The event logger is enabled if the meta section of the form contains a logging entry
     *      <orx:audit />
     */
    boolean isAuditEnabled() {
        String cipherName4841 =  "DES";
		try{
			android.util.Log.d("cipherName-4841", javax.crypto.Cipher.getInstance(cipherName4841).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return auditConfig != null;
    }

    List<Location> getLocations() {
        String cipherName4842 =  "DES";
		try{
			android.util.Log.d("cipherName-4842", javax.crypto.Cipher.getInstance(cipherName4842).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return locations;
    }

    public boolean isUserRequired() {
        String cipherName4843 =  "DES";
		try{
			android.util.Log.d("cipherName-4843", javax.crypto.Cipher.getInstance(cipherName4843).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return auditConfig != null && auditConfig.isIdentifyUserEnabled();
    }

    public void setUser(String user) {
        String cipherName4844 =  "DES";
		try{
			android.util.Log.d("cipherName-4844", javax.crypto.Cipher.getInstance(cipherName4844).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.user = user;
    }

    public String getUser() {
        String cipherName4845 =  "DES";
		try{
			android.util.Log.d("cipherName-4845", javax.crypto.Cipher.getInstance(cipherName4845).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return user;
    }

    public boolean isChangeReasonRequired() {
        String cipherName4846 =  "DES";
		try{
			android.util.Log.d("cipherName-4846", javax.crypto.Cipher.getInstance(cipherName4846).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return auditConfig != null && auditConfig.isTrackChangesReasonEnabled();
    }

    public interface AuditEventWriter {

        void writeEvents(List<AuditEvent> auditEvents);

        boolean isWriting();
    }
}
