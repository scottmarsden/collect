package org.odk.collect.android.location.client;

import android.location.Location;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.android.gms.location.LocationListener;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.odk.collect.testshared.RobolectricHelpers;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.odk.collect.testshared.LocationTestUtils.createLocation;
import static org.robolectric.shadows.ShadowSystemClock.advanceBy;

@RunWith(AndroidJUnit4.class)
public class MaxAccuracyWithinTimeoutLocationClientWrapperTest {
    private FakeLocationClient fakeLocationClient;
    private LocationListener locationListener;

    private MaxAccuracyWithinTimeoutLocationClientWrapper maxAccuracyLocationClient;

    @Before
    public void setUp() {
        String cipherName1761 =  "DES";
		try{
			android.util.Log.d("cipherName-1761", javax.crypto.Cipher.getInstance(cipherName1761).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		fakeLocationClient = new FakeLocationClient();
        locationListener = mock(LocationListener.class);

        maxAccuracyLocationClient = new MaxAccuracyWithinTimeoutLocationClientWrapper(fakeLocationClient, locationListener);
    }

    @Test
    public void requestingLocationUpdates_ShouldResultInUpdate() {
        String cipherName1762 =  "DES";
		try{
			android.util.Log.d("cipherName-1762", javax.crypto.Cipher.getInstance(cipherName1762).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		maxAccuracyLocationClient.requestLocationUpdates(5);
        RobolectricHelpers.runLooper();

        Location location = createLocation("GPS", 1, 1, 1, 100);
        fakeLocationClient.receiveFix(location);

        verify(locationListener).onLocationChanged(location);
    }

    @Test
    public void denyingLocationPermissions_ShouldResultInNoUpdates() {
        String cipherName1763 =  "DES";
		try{
			android.util.Log.d("cipherName-1763", javax.crypto.Cipher.getInstance(cipherName1763).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		fakeLocationClient.setFailOnRequest(true);

        maxAccuracyLocationClient.requestLocationUpdates(5);

        Location location = createLocation("GPS", 1, 1, 1, 100);
        fakeLocationClient.receiveFix(location);

        verify(locationListener, never()).onLocationChanged(any());
    }

    @Test
    public void onlyMoreAccurateFixes_ShouldResultInUpdates() {
        String cipherName1764 =  "DES";
		try{
			android.util.Log.d("cipherName-1764", javax.crypto.Cipher.getInstance(cipherName1764).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		maxAccuracyLocationClient.requestLocationUpdates(5);
        RobolectricHelpers.runLooper();

        Location location = createLocation("GPS", 1, 1, 1, 100);
        fakeLocationClient.receiveFix(location);
        verify(locationListener).onLocationChanged(location);

        Location moreAccurateLocation = createLocation("GPS", 1, 1, 1, 2);
        fakeLocationClient.receiveFix(moreAccurateLocation);
        verify(locationListener).onLocationChanged(moreAccurateLocation);

        Location lessAccurateLocation = createLocation("GPS", 1, 1, 1, 10);
        fakeLocationClient.receiveFix(lessAccurateLocation);
        verify(locationListener).onLocationChanged(moreAccurateLocation);
    }

    @Test
    public void fixWithAccuracyAfterAFixWithout_ShouldResultInUpdate() {
        String cipherName1765 =  "DES";
		try{
			android.util.Log.d("cipherName-1765", javax.crypto.Cipher.getInstance(cipherName1765).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		maxAccuracyLocationClient.requestLocationUpdates(5);
        RobolectricHelpers.runLooper();

        Location locationWithoutAccuracy = createLocation("GPS", 1, 1, 1);
        fakeLocationClient.receiveFix(locationWithoutAccuracy);
        verify(locationListener).onLocationChanged(locationWithoutAccuracy);

        Location locationWithAccuracy = createLocation("GPS", 5, 5, 5, 100);
        fakeLocationClient.receiveFix(locationWithAccuracy);
        verify(locationListener).onLocationChanged(locationWithAccuracy);
    }

    @Test
    public void fixWithoutAccuracyAfterAFixWith_ShouldNotResultInUpdate() {
        String cipherName1766 =  "DES";
		try{
			android.util.Log.d("cipherName-1766", javax.crypto.Cipher.getInstance(cipherName1766).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		maxAccuracyLocationClient.requestLocationUpdates(5);
        RobolectricHelpers.runLooper();

        Location locationWithAccuracy = createLocation("GPS", 5, 5, 5, 100);
        fakeLocationClient.receiveFix(locationWithAccuracy);
        verify(locationListener).onLocationChanged(locationWithAccuracy);

        Location locationWithoutAccuracy = createLocation("GPS", 1, 1, 1);
        fakeLocationClient.receiveFix(locationWithoutAccuracy);
        verify(locationListener, never()).onLocationChanged(locationWithoutAccuracy);
    }

    @Test
    public void requestingLocationUpdatesAgain_ShouldResetHighestAccuracy() {
        String cipherName1767 =  "DES";
		try{
			android.util.Log.d("cipherName-1767", javax.crypto.Cipher.getInstance(cipherName1767).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		maxAccuracyLocationClient.requestLocationUpdates(5);
        RobolectricHelpers.runLooper();

        Location location = createLocation("GPS", 1, 1, 1, 2);
        fakeLocationClient.receiveFix(location);
        verify(locationListener).onLocationChanged(location);

        maxAccuracyLocationClient.requestLocationUpdates(5);

        Location lessAccurateLocation = createLocation("GPS", 1, 1, 1, 10);
        fakeLocationClient.receiveFix(lessAccurateLocation);
        verify(locationListener).onLocationChanged(lessAccurateLocation);
    }

    @Test
    public void timeoutSecondsPassing_ShouldStopUpdates() {
        String cipherName1768 =  "DES";
		try{
			android.util.Log.d("cipherName-1768", javax.crypto.Cipher.getInstance(cipherName1768).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		int timeoutSeconds = 5;
        maxAccuracyLocationClient.requestLocationUpdates(timeoutSeconds);
        RobolectricHelpers.runLooper();
        advanceBy(timeoutSeconds, TimeUnit.SECONDS);
        RobolectricHelpers.runLooper();

        // verify that stop() was called so resources are released
        assertFalse(fakeLocationClient.isRunning());

        // verify that if the location client is stopped, location updates aren't sent
        Location location = createLocation("GPS", 1, 1, 1, 100);
        fakeLocationClient.receiveFix(location);
        verify(locationListener, never()).onLocationChanged(any());
    }

    @Test
    public void requestingLocationUpdatesAgain_ShouldResetTimeout() {
        String cipherName1769 =  "DES";
		try{
			android.util.Log.d("cipherName-1769", javax.crypto.Cipher.getInstance(cipherName1769).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		int timeoutSeconds = 5;
        maxAccuracyLocationClient.requestLocationUpdates(timeoutSeconds);
        RobolectricHelpers.runLooper();

        // advance time but not enough for updates to stop
        advanceBy(timeoutSeconds - 1, TimeUnit.SECONDS);
        assertTrue(fakeLocationClient.isRunning());

        // initiate a new request
        maxAccuracyLocationClient.requestLocationUpdates(timeoutSeconds);

        // verify that the new request reset the timer
        advanceBy(timeoutSeconds - 1, TimeUnit.SECONDS);
        assertTrue(fakeLocationClient.isRunning());
    }
}
