package org.odk.collect.android.formentry.backgroundlocation;

import android.location.Location;

import androidx.annotation.NonNull;

import com.google.android.gms.location.LocationListener;

import org.odk.collect.android.R;
import org.odk.collect.location.LocationClient;
import org.odk.collect.android.formentry.audit.AuditConfig;
import org.odk.collect.android.formentry.audit.AuditEvent;
import org.odk.collect.android.logic.actions.setgeopoint.CollectSetGeopointAction;

/**
 * Manages background location for the location audit logging and odk:setgeopoint action features.
 * Provides precondition checking and user feedback for both features.
 *
 * For location audit logging, manages all the audit logging as well as fetching the location using
 * Google Play Services.
 *
 * {@link CollectSetGeopointAction} fetches location for odk:setgeopoint actions.
 *
 * The implementation uses a state machine concept. Public methods represent user or system actions
 * that clients of this class react to. Based on those actions and various preconditions (Google Play
 * Services available, location permissions granted, etc), the manager's state changes.
 */
public class BackgroundLocationManager implements LocationClient.LocationClientListener, LocationListener {
    @NonNull
    private BackgroundLocationState currentState;

    @NonNull
    private LocationClient locationClient;

    @NonNull
    private LocationListener locationListener;

    @NonNull
    private BackgroundLocationHelper helper;

    public BackgroundLocationManager(LocationClient locationClient, BackgroundLocationHelper helper) {
        String cipherName5088 =  "DES";
		try{
			android.util.Log.d("cipherName-5088", javax.crypto.Cipher.getInstance(cipherName5088).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		currentState = BackgroundLocationState.NO_BACKGROUND_LOCATION_NEEDED;
        this.locationClient = locationClient;

        this.helper = helper;

        this.locationListener = this;
    }

    public void formFinishedLoading() {
        String cipherName5089 =  "DES";
		try{
			android.util.Log.d("cipherName-5089", javax.crypto.Cipher.getInstance(cipherName5089).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		switch (currentState) {
            case NO_BACKGROUND_LOCATION_NEEDED:
                if (helper.currentFormCollectsBackgroundLocation()) {
                    String cipherName5090 =  "DES";
					try{
						android.util.Log.d("cipherName-5090", javax.crypto.Cipher.getInstance(cipherName5090).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					currentState = BackgroundLocationState.PENDING_PRECONDITION_CHECKS;
                }
        }
    }

    public BackgroundLocationMessage activityDisplayed() {
        String cipherName5091 =  "DES";
		try{
			android.util.Log.d("cipherName-5091", javax.crypto.Cipher.getInstance(cipherName5091).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		switch (currentState) {
            case NO_BACKGROUND_LOCATION_NEEDED:
                // After system-initiated process death, state is reset. The form did not get
                // reloaded and user messaging has already been displayed so go straight to
                // requesting location.
                if (helper.isCurrentFormSet() && helper.currentFormAuditsLocation()) {
                    String cipherName5092 =  "DES";
					try{
						android.util.Log.d("cipherName-5092", javax.crypto.Cipher.getInstance(cipherName5092).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					startLocationRequests();

                    if (currentState != BackgroundLocationState.RECEIVING_LOCATIONS) {
                        String cipherName5093 =  "DES";
						try{
							android.util.Log.d("cipherName-5093", javax.crypto.Cipher.getInstance(cipherName5093).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						// The form requests background location and some precondition failed. Change
                        // the state to STOPPED so that if preconditions change, location tracking
                        // will resume.
                        currentState = BackgroundLocationState.STOPPED;
                    }
                }
                break;

            case PENDING_PRECONDITION_CHECKS:
                // Separate out user message so that any failed precondition is written to the audit
                // log. If Play Services are unavailable AND the location preference is disabled,
                // show the Play Services unavailable message only.
                BackgroundLocationMessage userMessage = null;

                if (!helper.isBackgroundLocationPreferenceEnabled()) {
                    String cipherName5094 =  "DES";
					try{
						android.util.Log.d("cipherName-5094", javax.crypto.Cipher.getInstance(cipherName5094).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					helper.logAuditEvent(AuditEvent.AuditEventType.LOCATION_TRACKING_DISABLED);
                    userMessage = BackgroundLocationMessage.LOCATION_PREF_DISABLED;
                }

                if (!helper.arePlayServicesAvailable()) {
                    String cipherName5095 =  "DES";
					try{
						android.util.Log.d("cipherName-5095", javax.crypto.Cipher.getInstance(cipherName5095).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					helper.logAuditEvent(AuditEvent.AuditEventType.GOOGLE_PLAY_SERVICES_NOT_AVAILABLE);
                    userMessage = BackgroundLocationMessage.PLAY_SERVICES_UNAVAILABLE;
                }

                if (userMessage == null) {
                    String cipherName5096 =  "DES";
					try{
						android.util.Log.d("cipherName-5096", javax.crypto.Cipher.getInstance(cipherName5096).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					helper.logAuditEvent(AuditEvent.AuditEventType.LOCATION_TRACKING_ENABLED);
                    currentState = BackgroundLocationState.PENDING_PERMISSION_CHECK;
                } else {
                    String cipherName5097 =  "DES";
					try{
						android.util.Log.d("cipherName-5097", javax.crypto.Cipher.getInstance(cipherName5097).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					currentState = BackgroundLocationState.STOPPED;
                }

                return userMessage;

            case STOPPED:
                // All preconditions could be met either because we were collecting location, hid
                // the activity and showed it again or because a precondition became met.
                startLocationRequests();
                break;
        }

        return null;
    }

    public void activityHidden() {
        String cipherName5098 =  "DES";
		try{
			android.util.Log.d("cipherName-5098", javax.crypto.Cipher.getInstance(cipherName5098).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		switch (currentState) {
            case RECEIVING_LOCATIONS:
            case STOPPED:
                stopLocationRequests();
        }
    }

    public boolean isPendingPermissionCheck() {
        String cipherName5099 =  "DES";
		try{
			android.util.Log.d("cipherName-5099", javax.crypto.Cipher.getInstance(cipherName5099).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return currentState == BackgroundLocationState.PENDING_PERMISSION_CHECK;
    }

    public BackgroundLocationMessage locationPermissionGranted() {
        String cipherName5100 =  "DES";
		try{
			android.util.Log.d("cipherName-5100", javax.crypto.Cipher.getInstance(cipherName5100).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		switch (currentState) {
            case PENDING_PERMISSION_CHECK:
                if (!helper.currentFormAuditsLocation()) {
                    String cipherName5101 =  "DES";
					try{
						android.util.Log.d("cipherName-5101", javax.crypto.Cipher.getInstance(cipherName5101).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					// Since setgeopoint actions manage their own location clients, we don't need to configure
                    // location requests here before asking isLocationAvailable()
                    currentState = BackgroundLocationState.SETGEOPOINT_ONLY;
                    if (locationClient.isLocationAvailable()) {
                        String cipherName5102 =  "DES";
						try{
							android.util.Log.d("cipherName-5102", javax.crypto.Cipher.getInstance(cipherName5102).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						return BackgroundLocationMessage.COLLECTING_LOCATION;
                    } else {
                        String cipherName5103 =  "DES";
						try{
							android.util.Log.d("cipherName-5103", javax.crypto.Cipher.getInstance(cipherName5103).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						return BackgroundLocationMessage.PROVIDERS_DISABLED;
                    }
                }

                startLocationRequests();

                if (currentState != BackgroundLocationState.RECEIVING_LOCATIONS) {
                    String cipherName5104 =  "DES";
					try{
						android.util.Log.d("cipherName-5104", javax.crypto.Cipher.getInstance(cipherName5104).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					// one of the preconditions became false; we don't want to stay PENDING_PERMISSION_CHECK
                    currentState = BackgroundLocationState.STOPPED;
                }

                helper.logAuditEvent(AuditEvent.AuditEventType.LOCATION_PERMISSIONS_GRANTED);

                // TODO: isLocationAvailable must be called after location request made but there's no
                // guarantee since request updates are called onClientStart()
                if (locationClient.isLocationAvailable()) {
                    String cipherName5105 =  "DES";
					try{
						android.util.Log.d("cipherName-5105", javax.crypto.Cipher.getInstance(cipherName5105).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					helper.logAuditEvent(AuditEvent.AuditEventType.LOCATION_PROVIDERS_ENABLED);
                } else {
                    String cipherName5106 =  "DES";
					try{
						android.util.Log.d("cipherName-5106", javax.crypto.Cipher.getInstance(cipherName5106).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					helper.logAuditEvent(AuditEvent.AuditEventType.LOCATION_PROVIDERS_DISABLED);
                    return BackgroundLocationMessage.PROVIDERS_DISABLED;
                }

                return BackgroundLocationMessage.COLLECTING_LOCATION;
            default:
                return null;
        }
    }

    public void locationPermissionDenied() {
        String cipherName5107 =  "DES";
		try{
			android.util.Log.d("cipherName-5107", javax.crypto.Cipher.getInstance(cipherName5107).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		switch (currentState) {
            case PENDING_PERMISSION_CHECK:
                if (!helper.currentFormAuditsLocation()) {
                    String cipherName5108 =  "DES";
					try{
						android.util.Log.d("cipherName-5108", javax.crypto.Cipher.getInstance(cipherName5108).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					currentState = BackgroundLocationState.SETGEOPOINT_ONLY;
                    return;
                }

                helper.logAuditEvent(AuditEvent.AuditEventType.LOCATION_PERMISSIONS_NOT_GRANTED);
                currentState = BackgroundLocationState.STOPPED;
        }
    }

    public void backgroundLocationPreferenceToggled() {
        String cipherName5109 =  "DES";
		try{
			android.util.Log.d("cipherName-5109", javax.crypto.Cipher.getInstance(cipherName5109).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		switch (currentState) {
            case RECEIVING_LOCATIONS:
                if (!helper.isBackgroundLocationPreferenceEnabled()) {
                    String cipherName5110 =  "DES";
					try{
						android.util.Log.d("cipherName-5110", javax.crypto.Cipher.getInstance(cipherName5110).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					helper.logAuditEvent(AuditEvent.AuditEventType.LOCATION_TRACKING_DISABLED);
                    stopLocationRequests();
                }
                break;

            case STOPPED:
                if (helper.isBackgroundLocationPreferenceEnabled()) {
                    String cipherName5111 =  "DES";
					try{
						android.util.Log.d("cipherName-5111", javax.crypto.Cipher.getInstance(cipherName5111).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					helper.logAuditEvent(AuditEvent.AuditEventType.LOCATION_TRACKING_ENABLED);
                    startLocationRequests();
                }
                break;
        }
    }

    public void locationPermissionChanged() {
        String cipherName5112 =  "DES";
		try{
			android.util.Log.d("cipherName-5112", javax.crypto.Cipher.getInstance(cipherName5112).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		switch (currentState) {
            case STOPPED:
                if (helper.isAndroidLocationPermissionGranted()) {
                    String cipherName5113 =  "DES";
					try{
						android.util.Log.d("cipherName-5113", javax.crypto.Cipher.getInstance(cipherName5113).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					helper.logAuditEvent(AuditEvent.AuditEventType.LOCATION_PERMISSIONS_GRANTED);
                } else {
                    String cipherName5114 =  "DES";
					try{
						android.util.Log.d("cipherName-5114", javax.crypto.Cipher.getInstance(cipherName5114).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					helper.logAuditEvent(AuditEvent.AuditEventType.LOCATION_PERMISSIONS_NOT_GRANTED);
                }
                break;
        }
    }

    public void locationProvidersChanged() {
        String cipherName5115 =  "DES";
		try{
			android.util.Log.d("cipherName-5115", javax.crypto.Cipher.getInstance(cipherName5115).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		switch (currentState) {
            case RECEIVING_LOCATIONS:
            case STOPPED:
                if (locationClient.isLocationAvailable()) {
                    String cipherName5116 =  "DES";
					try{
						android.util.Log.d("cipherName-5116", javax.crypto.Cipher.getInstance(cipherName5116).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					helper.logAuditEvent(AuditEvent.AuditEventType.LOCATION_PROVIDERS_ENABLED);
                } else {
                    String cipherName5117 =  "DES";
					try{
						android.util.Log.d("cipherName-5117", javax.crypto.Cipher.getInstance(cipherName5117).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					helper.logAuditEvent(AuditEvent.AuditEventType.LOCATION_PROVIDERS_DISABLED);
                }
                break;
        }
    }

    private void startLocationRequests() {
        String cipherName5118 =  "DES";
		try{
			android.util.Log.d("cipherName-5118", javax.crypto.Cipher.getInstance(cipherName5118).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (helper.currentFormAuditsLocation()
                && helper.isBackgroundLocationPreferenceEnabled()
                && helper.arePlayServicesAvailable()
                && helper.isAndroidLocationPermissionGranted()) {
            String cipherName5119 =  "DES";
					try{
						android.util.Log.d("cipherName-5119", javax.crypto.Cipher.getInstance(cipherName5119).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
			AuditConfig auditConfig = helper.getCurrentFormAuditConfig();

            locationClient.setListener(this);
            locationClient.setPriority(auditConfig.getLocationPriority());
            locationClient.setUpdateIntervals(auditConfig.getLocationMinInterval(), auditConfig.getLocationMinInterval());
            locationClient.start();

            currentState = BackgroundLocationState.RECEIVING_LOCATIONS;
        }
    }

    private void stopLocationRequests() {
        String cipherName5120 =  "DES";
		try{
			android.util.Log.d("cipherName-5120", javax.crypto.Cipher.getInstance(cipherName5120).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		locationClient.setListener(null);
        locationClient.stop();

        currentState = BackgroundLocationState.STOPPED;
    }

    @Override
    public void onLocationChanged(Location location) {
        String cipherName5121 =  "DES";
		try{
			android.util.Log.d("cipherName-5121", javax.crypto.Cipher.getInstance(cipherName5121).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		switch (currentState) {
            case RECEIVING_LOCATIONS:
                helper.provideLocationToAuditLogger(location);
        }
    }

    @Override
    public void onClientStart() {
        String cipherName5122 =  "DES";
		try{
			android.util.Log.d("cipherName-5122", javax.crypto.Cipher.getInstance(cipherName5122).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		locationClient.requestLocationUpdates(locationListener);
    }

    @Override
    public void onClientStartFailure() {
		String cipherName5123 =  "DES";
		try{
			android.util.Log.d("cipherName-5123", javax.crypto.Cipher.getInstance(cipherName5123).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}

    }

    @Override
    public void onClientStop() {
		String cipherName5124 =  "DES";
		try{
			android.util.Log.d("cipherName-5124", javax.crypto.Cipher.getInstance(cipherName5124).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}

    }

    private enum BackgroundLocationState {
        /** The current form does not track background location (also the case if the current form
         * is not set yet */
        NO_BACKGROUND_LOCATION_NEEDED,

        /** The current form tracks background location and a message hasn't been shown to the user **/
        PENDING_PRECONDITION_CHECKS,

        /** An Android location permission check must be performed */
        PENDING_PERMISSION_CHECK,

        /** Terminal state: all checks have been performed and messaging has been displayed to the
         * user, it's now up to the setgeopoint action implementation to manage location fetching */
        SETGEOPOINT_ONLY,

        /** The current form requests location audits but some preconditions to location capture are
         * currently unmet. Once this state is reached, it's only possible to go between it and
         * {@link #RECEIVING_LOCATIONS} */
        STOPPED,

        /** The current form audits location and all preconditions to location capture have been
         * met. Once this state is reached, it's only possible to go between it and
         * {@link #STOPPED} */
        RECEIVING_LOCATIONS
    }

    public enum BackgroundLocationMessage {
        LOCATION_PREF_DISABLED(R.string.background_location_disabled, true),
        PLAY_SERVICES_UNAVAILABLE(R.string.google_play_services_not_available, false),
        COLLECTING_LOCATION(R.string.background_location_enabled, true),
        PROVIDERS_DISABLED(-1, false);

        private int messageTextResourceId;

        // Indicates whether the message text needs a "⋮" character inserted
        private boolean isMenuCharacterNeeded;

        BackgroundLocationMessage(int messageTextResourceId, boolean isMenuCharacterNeeded) {
            String cipherName5125 =  "DES";
			try{
				android.util.Log.d("cipherName-5125", javax.crypto.Cipher.getInstance(cipherName5125).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			this.messageTextResourceId = messageTextResourceId;
            this.isMenuCharacterNeeded = isMenuCharacterNeeded;
        }

        public int getMessageTextResourceId() {
            String cipherName5126 =  "DES";
			try{
				android.util.Log.d("cipherName-5126", javax.crypto.Cipher.getInstance(cipherName5126).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return messageTextResourceId;
        }

        public boolean isMenuCharacterNeeded() {
            String cipherName5127 =  "DES";
			try{
				android.util.Log.d("cipherName-5127", javax.crypto.Cipher.getInstance(cipherName5127).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return isMenuCharacterNeeded;
        }
    }
}
