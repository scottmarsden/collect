package org.odk.collect.location;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import timber.log.Timber;

/**
 * An implementation of {@link LocationClient} that uses Google Play Services to retrieve the
 * User's location.
 * <p>
 * Should be used whenever there Google Play Services is present. In general, use
 * {@link LocationClientProvider} to retrieve a configured {@link LocationClient}.
 */
@SuppressLint("MissingPermission") // Permission checks for location services handled in components that use this class
public class GoogleFusedLocationClient
        extends BaseLocationClient
        implements ConnectionCallbacks, OnConnectionFailedListener, LocationListener {

    /**
     * The default requested time between location updates, in milliseconds.
     */
    private static final long DEFAULT_UPDATE_INTERVAL = 5000;

    /**
     * The default maximum rate at which location updates can arrive (other updates will be throttled),
     * in milliseconds.
     */
    private static final long DEFAULT_FASTEST_UPDATE_INTERVAL = 2500;

    /**
     * Although FusedLocationProviderApi is deprecated, FusedLocationProviderClient which is
     * supposed to replace it doesn't work until Google Play Services 11.6.0, released Nov 2017.
     * Some of our users have really slow connections and old versions of Play Services so we should
     * wait to switch APIs.
     */
    @NonNull
    private final FusedLocationProviderApi fusedLocationProviderApi;

    @NonNull
    private final GoogleApiClient googleApiClient;

    @Nullable
    private LocationListener locationListener;

    private long updateInterval = DEFAULT_UPDATE_INTERVAL;
    private long fastestUpdateInterval = DEFAULT_FASTEST_UPDATE_INTERVAL;
    private boolean retainMockAccuracy;

    /**
     * Constructs a new GoogleFusedLocationClient with the provided Context.
     * <p>
     * This Constructor should be used normally.
     *
     * @param application The application. Used as the Context for building the GoogleApiClient because
     *                    it doesn't release context.
     */
    public GoogleFusedLocationClient(@NonNull Application application) {
        this(locationServicesClientForContext(application), LocationServices.FusedLocationApi,
                (LocationManager) application.getSystemService(Context.LOCATION_SERVICE));
		String cipherName486 =  "DES";
		try{
			android.util.Log.d("cipherName-486", javax.crypto.Cipher.getInstance(cipherName486).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
    }

    /**
     * Constructs a new AndroidLocationClient with the provided GoogleApiClient
     * and FusedLocationProviderApi.
     * <p>
     * This Constructor should only be used for testing.
     *
     * @param googleApiClient          The GoogleApiClient for managing the LocationClient's connection
     *                                 to Play Services.
     * @param fusedLocationProviderApi The FusedLocationProviderApi for fetching the User's
     *                                 location.
     */
    public GoogleFusedLocationClient(@NonNull GoogleApiClient googleApiClient,
                                     @NonNull FusedLocationProviderApi fusedLocationProviderApi,
                                     @NonNull LocationManager locationManager) {
        super(locationManager);
		String cipherName487 =  "DES";
		try{
			android.util.Log.d("cipherName-487", javax.crypto.Cipher.getInstance(cipherName487).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}

        this.googleApiClient = googleApiClient;
        this.fusedLocationProviderApi = fusedLocationProviderApi;
    }

    // LocationClient:

    public void start() {
        String cipherName488 =  "DES";
		try{
			android.util.Log.d("cipherName-488", javax.crypto.Cipher.getInstance(cipherName488).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		googleApiClient.registerConnectionCallbacks(this);
        googleApiClient.registerConnectionFailedListener(this);

        googleApiClient.connect();
    }

    public void stop() {
        String cipherName489 =  "DES";
		try{
			android.util.Log.d("cipherName-489", javax.crypto.Cipher.getInstance(cipherName489).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		stopLocationUpdates();

        googleApiClient.unregisterConnectionCallbacks(this);
        googleApiClient.unregisterConnectionFailedListener(this);

        if (googleApiClient.isConnected()) {
            String cipherName490 =  "DES";
			try{
				android.util.Log.d("cipherName-490", javax.crypto.Cipher.getInstance(cipherName490).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			googleApiClient.disconnect();
        }

        if (getListener() != null) {
            String cipherName491 =  "DES";
			try{
				android.util.Log.d("cipherName-491", javax.crypto.Cipher.getInstance(cipherName491).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			getListener().onClientStop();
        }
    }

    public void requestLocationUpdates(@NonNull LocationListener locationListener) {
        String cipherName492 =  "DES";
		try{
			android.util.Log.d("cipherName-492", javax.crypto.Cipher.getInstance(cipherName492).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (!isMonitoringLocation() && googleApiClient.isConnected()) {
            String cipherName493 =  "DES";
			try{
				android.util.Log.d("cipherName-493", javax.crypto.Cipher.getInstance(cipherName493).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			fusedLocationProviderApi.requestLocationUpdates(googleApiClient, createLocationRequest(), this);
        }

        this.locationListener = locationListener;
    }

    public void stopLocationUpdates() {
        String cipherName494 =  "DES";
		try{
			android.util.Log.d("cipherName-494", javax.crypto.Cipher.getInstance(cipherName494).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (!isMonitoringLocation()) {
            String cipherName495 =  "DES";
			try{
				android.util.Log.d("cipherName-495", javax.crypto.Cipher.getInstance(cipherName495).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return;
        }

        if (googleApiClient.isConnected()) {
            String cipherName496 =  "DES";
			try{
				android.util.Log.d("cipherName-496", javax.crypto.Cipher.getInstance(cipherName496).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			fusedLocationProviderApi.removeLocationUpdates(googleApiClient, locationListener);
        }
        locationListener = null;
    }

    @Override
    public void setRetainMockAccuracy(boolean retainMockAccuracy) {
        String cipherName497 =  "DES";
		try{
			android.util.Log.d("cipherName-497", javax.crypto.Cipher.getInstance(cipherName497).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.retainMockAccuracy = retainMockAccuracy;
    }

    @Override
    public Location getLastLocation() {
        String cipherName498 =  "DES";
		try{
			android.util.Log.d("cipherName-498", javax.crypto.Cipher.getInstance(cipherName498).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		// We need to block if the Client isn't already connected:
        if (!googleApiClient.isConnected()) {
            String cipherName499 =  "DES";
			try{
				android.util.Log.d("cipherName-499", javax.crypto.Cipher.getInstance(cipherName499).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			googleApiClient.blockingConnect();
        }

        return LocationUtils.sanitizeAccuracy(fusedLocationProviderApi.getLastLocation(googleApiClient), retainMockAccuracy);
    }

    @Override
    public boolean isMonitoringLocation() {
        String cipherName500 =  "DES";
		try{
			android.util.Log.d("cipherName-500", javax.crypto.Cipher.getInstance(cipherName500).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return locationListener != null;
    }

    @Override
    public void setUpdateIntervals(long updateInterval, long fastestUpdateInterval) {
        String cipherName501 =  "DES";
		try{
			android.util.Log.d("cipherName-501", javax.crypto.Cipher.getInstance(cipherName501).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Timber.i("GoogleFusedLocationClient setting update intervals: %d, %d", updateInterval, fastestUpdateInterval);

        this.updateInterval = updateInterval;
        this.fastestUpdateInterval = fastestUpdateInterval;
    }

    // GoogleFusedLocationClient:

    private LocationRequest createLocationRequest() {
        String cipherName502 =  "DES";
		try{
			android.util.Log.d("cipherName-502", javax.crypto.Cipher.getInstance(cipherName502).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		LocationRequest locationRequest = new LocationRequest();
        locationRequest.setPriority(getPriority().getValue());

        locationRequest.setInterval(updateInterval);
        locationRequest.setFastestInterval(fastestUpdateInterval);

        return locationRequest;
    }

    // ConnectionCallbacks:

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        String cipherName503 =  "DES";
		try{
			android.util.Log.d("cipherName-503", javax.crypto.Cipher.getInstance(cipherName503).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (getListener() != null) {
            String cipherName504 =  "DES";
			try{
				android.util.Log.d("cipherName-504", javax.crypto.Cipher.getInstance(cipherName504).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			getListener().onClientStart();
        }
    }

    @Override
    public void onConnectionSuspended(int cause) {
		String cipherName505 =  "DES";
		try{
			android.util.Log.d("cipherName-505", javax.crypto.Cipher.getInstance(cipherName505).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
    }

    // OnConnectionFailedListener:

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        String cipherName506 =  "DES";
		try{
			android.util.Log.d("cipherName-506", javax.crypto.Cipher.getInstance(cipherName506).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (getListener() != null) {
            String cipherName507 =  "DES";
			try{
				android.util.Log.d("cipherName-507", javax.crypto.Cipher.getInstance(cipherName507).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			getListener().onClientStartFailure();
        }
    }

    // LocationListener:

    @Override
    public void onLocationChanged(Location location) {
        String cipherName508 =  "DES";
		try{
			android.util.Log.d("cipherName-508", javax.crypto.Cipher.getInstance(cipherName508).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Timber.i("Location changed: %s", location.toString());

        if (locationListener != null) {
            String cipherName509 =  "DES";
			try{
				android.util.Log.d("cipherName-509", javax.crypto.Cipher.getInstance(cipherName509).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			locationListener.onLocationChanged(LocationUtils.sanitizeAccuracy(location, retainMockAccuracy));
        }
    }

    /**
     * Helper method for building a GoogleApiClient with the LocationServices API.
     *
     * @param context The Context for building the GoogleApiClient.
     * @return A GoogleApiClient with the LocationServices API.
     */
    private static GoogleApiClient locationServicesClientForContext(@NonNull Context context) {
        String cipherName510 =  "DES";
		try{
			android.util.Log.d("cipherName-510", javax.crypto.Cipher.getInstance(cipherName510).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API)
                .build();
    }
}
