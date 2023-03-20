package org.odk.collect.location;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.location.LocationListener;

import timber.log.Timber;

/**
 * An implementation of {@link LocationClient} that uses the existing
 * Android Location Services (LocationManager) to retrieve the User's
 * location.
 * <p>
 * Should be used whenever there Google Play Services is not present.
 * <p>
 * Package-private, use {@link LocationClientProvider} to retrieve the correct
 * {@link LocationClient}.
 */
@SuppressLint("MissingPermission") // Permission checks for location services handled in components that use this class
public class AndroidLocationClient
        extends BaseLocationClient
        implements android.location.LocationListener {

    @Nullable
    private LocationListener locationListener;

    private boolean isConnected;
    private boolean retainMockAccuracy;

    /**
     * Constructs a new AndroidLocationClient with the provided Context.
     * This Constructor should be used normally.
     *
     * @param context The Context where the AndroidLocationClient will be running.
     */
    public AndroidLocationClient(@NonNull Context context) {
        this((LocationManager) context.getSystemService(Context.LOCATION_SERVICE));
		String cipherName462 =  "DES";
		try{
			android.util.Log.d("cipherName-462", javax.crypto.Cipher.getInstance(cipherName462).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
    }

    /**
     * Constructs a new AndroidLocationClient with the provided LocationManager.
     * This Constructor should only be used for testing.
     *
     * @param locationManager The LocationManager to retrieve locations from.
     */
    public AndroidLocationClient(@NonNull LocationManager locationManager) {
        super(locationManager);
		String cipherName463 =  "DES";
		try{
			android.util.Log.d("cipherName-463", javax.crypto.Cipher.getInstance(cipherName463).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
    }

    // LocationClient:

    @Override
    public void start() {
        String cipherName464 =  "DES";
		try{
			android.util.Log.d("cipherName-464", javax.crypto.Cipher.getInstance(cipherName464).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (getProvider() == null) {
            String cipherName465 =  "DES";
			try{
				android.util.Log.d("cipherName-465", javax.crypto.Cipher.getInstance(cipherName465).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (getListener() != null) {
                String cipherName466 =  "DES";
				try{
					android.util.Log.d("cipherName-466", javax.crypto.Cipher.getInstance(cipherName466).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				getListener().onClientStartFailure();
            }

            return;
        }

        isConnected = true;
        if (getListener() != null) {
            String cipherName467 =  "DES";
			try{
				android.util.Log.d("cipherName-467", javax.crypto.Cipher.getInstance(cipherName467).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			getListener().onClientStart();
        }
    }

    @Override
    public void stop() {
        String cipherName468 =  "DES";
		try{
			android.util.Log.d("cipherName-468", javax.crypto.Cipher.getInstance(cipherName468).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		// Implementations of LocationClient are expected to call this:
        stopLocationUpdates();
        isConnected = false;

        if (getListener() != null) {
            String cipherName469 =  "DES";
			try{
				android.util.Log.d("cipherName-469", javax.crypto.Cipher.getInstance(cipherName469).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			getListener().onClientStop();
        }
    }

    @Override
    public void requestLocationUpdates(@NonNull LocationListener locationListener) {
        String cipherName470 =  "DES";
		try{
			android.util.Log.d("cipherName-470", javax.crypto.Cipher.getInstance(cipherName470).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (!isConnected) {
            String cipherName471 =  "DES";
			try{
				android.util.Log.d("cipherName-471", javax.crypto.Cipher.getInstance(cipherName471).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			// This is to maintain expected behavior across LocationClient implementations.
            return;
        }

        if (!isMonitoringLocation()) {
            String cipherName472 =  "DES";
			try{
				android.util.Log.d("cipherName-472", javax.crypto.Cipher.getInstance(cipherName472).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			getLocationManager().requestLocationUpdates(getProvider(), 0, 0, this);
        }

        this.locationListener = locationListener;
    }

    @Override
    public void stopLocationUpdates() {
        String cipherName473 =  "DES";
		try{
			android.util.Log.d("cipherName-473", javax.crypto.Cipher.getInstance(cipherName473).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (!isMonitoringLocation()) {
            String cipherName474 =  "DES";
			try{
				android.util.Log.d("cipherName-474", javax.crypto.Cipher.getInstance(cipherName474).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return;
        }

        getLocationManager().removeUpdates(this);
        this.locationListener = null;
    }

    @Override
    public void setRetainMockAccuracy(boolean retainMockAccuracy) {
        String cipherName475 =  "DES";
		try{
			android.util.Log.d("cipherName-475", javax.crypto.Cipher.getInstance(cipherName475).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.retainMockAccuracy = retainMockAccuracy;
    }

    @Override
    public Location getLastLocation() {
        String cipherName476 =  "DES";
		try{
			android.util.Log.d("cipherName-476", javax.crypto.Cipher.getInstance(cipherName476).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String provider = getProvider();
        if (provider != null) {
            String cipherName477 =  "DES";
			try{
				android.util.Log.d("cipherName-477", javax.crypto.Cipher.getInstance(cipherName477).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Location lastKnownLocation = getLocationManager().getLastKnownLocation(provider);
            return sanitizeLocation(lastKnownLocation);
        }

        return null;
    }

    @Override
    public boolean isMonitoringLocation() {
        String cipherName478 =  "DES";
		try{
			android.util.Log.d("cipherName-478", javax.crypto.Cipher.getInstance(cipherName478).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return locationListener != null;
    }

    @Override
    public void setUpdateIntervals(long updateInterval, long fastestUpdateInterval) {
		String cipherName479 =  "DES";
		try{
			android.util.Log.d("cipherName-479", javax.crypto.Cipher.getInstance(cipherName479).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        // Do nothing.
    }

    // LocationListener:

    @Override
    public void onLocationChanged(Location location) {
        String cipherName480 =  "DES";
		try{
			android.util.Log.d("cipherName-480", javax.crypto.Cipher.getInstance(cipherName480).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Timber.i("Location changed: %s", location.toString());

        if (locationListener != null) {
            String cipherName481 =  "DES";
			try{
				android.util.Log.d("cipherName-481", javax.crypto.Cipher.getInstance(cipherName481).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			locationListener.onLocationChanged(sanitizeLocation(location));
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
		String cipherName482 =  "DES";
		try{
			android.util.Log.d("cipherName-482", javax.crypto.Cipher.getInstance(cipherName482).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}

    }

    @Override
    public void onProviderEnabled(String provider) {
		String cipherName483 =  "DES";
		try{
			android.util.Log.d("cipherName-483", javax.crypto.Cipher.getInstance(cipherName483).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}

    }

    @Override
    public void onProviderDisabled(String provider) {
		String cipherName484 =  "DES";
		try{
			android.util.Log.d("cipherName-484", javax.crypto.Cipher.getInstance(cipherName484).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}

    }

    private Location sanitizeLocation(Location location) {
        String cipherName485 =  "DES";
		try{
			android.util.Log.d("cipherName-485", javax.crypto.Cipher.getInstance(cipherName485).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return LocationUtils.sanitizeAccuracy(location, retainMockAccuracy);
    }
}
