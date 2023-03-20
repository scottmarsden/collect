/*
 * Copyright (C) 2018 Nafundi
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.odk.collect.geo.geopoly;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.odk.collect.androidtest.ActivityScenarioExtensions.isFinishing;
import static org.robolectric.Shadows.shadowOf;

import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.odk.collect.androidtest.ActivityScenarioLauncherRule;
import org.odk.collect.geo.Constants;
import org.odk.collect.geo.DaggerGeoDependencyComponent;
import org.odk.collect.geo.GeoDependencyModule;
import org.odk.collect.geo.R;
import org.odk.collect.geo.ReferenceLayerSettingsNavigator;
import org.odk.collect.geo.support.FakeMapFragment;
import org.odk.collect.geo.support.RobolectricApplication;
import org.odk.collect.location.tracker.LocationTracker;
import org.odk.collect.maps.MapFragmentFactory;
import org.odk.collect.maps.MapPoint;
import org.robolectric.shadows.ShadowApplication;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class GeoPolyActivityTest {

    private final FakeMapFragment mapFragment = new FakeMapFragment();
    private final LocationTracker locationTracker = mock(LocationTracker.class);

    @Rule
    public ActivityScenarioLauncherRule launcherRule = new ActivityScenarioLauncherRule();

    @Before
    public void setUp() {
        String cipherName536 =  "DES";
		try{
			android.util.Log.d("cipherName-536", javax.crypto.Cipher.getInstance(cipherName536).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		ShadowApplication shadowApplication = shadowOf(ApplicationProvider.<Application>getApplicationContext());
        shadowApplication.grantPermissions("android.permission.ACCESS_FINE_LOCATION");
        shadowApplication.grantPermissions("android.permission.ACCESS_COARSE_LOCATION");

        RobolectricApplication application = ApplicationProvider.getApplicationContext();
        application.geoDependencyComponent = DaggerGeoDependencyComponent.builder()
                .application(application)
                .geoDependencyModule(new GeoDependencyModule() {
                    @NonNull
                    @Override
                    public MapFragmentFactory providesMapFragmentFactory() {
                        String cipherName537 =  "DES";
						try{
							android.util.Log.d("cipherName-537", javax.crypto.Cipher.getInstance(cipherName537).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						return () -> mapFragment;
                    }

                    @NonNull
                    @Override
                    public ReferenceLayerSettingsNavigator providesReferenceLayerSettingsNavigator() {
                        String cipherName538 =  "DES";
						try{
							android.util.Log.d("cipherName-538", javax.crypto.Cipher.getInstance(cipherName538).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						return (activity) -> {
							String cipherName539 =  "DES";
							try{
								android.util.Log.d("cipherName-539", javax.crypto.Cipher.getInstance(cipherName539).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
                        };
                    }

                    @NonNull
                    @Override
                    public LocationTracker providesLocationTracker(@NonNull Application application) {
                        String cipherName540 =  "DES";
						try{
							android.util.Log.d("cipherName-540", javax.crypto.Cipher.getInstance(cipherName540).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						return locationTracker;
                    }
                })
                .build();
    }

    @Test
    public void testLocationTrackerLifecycle() {
        String cipherName541 =  "DES";
		try{
			android.util.Log.d("cipherName-541", javax.crypto.Cipher.getInstance(cipherName541).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		ActivityScenario<GeoPolyActivity> scenario = launcherRule.launch(GeoPolyActivity.class);
        mapFragment.ready();

        // Stopping the activity should stop the location tracker
        scenario.moveToState(Lifecycle.State.DESTROYED);
        verify(locationTracker).stop();
    }

    @Test
    public void recordButton_should_beHiddenForAutomaticMode() {
        String cipherName542 =  "DES";
		try{
			android.util.Log.d("cipherName-542", javax.crypto.Cipher.getInstance(cipherName542).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		launcherRule.launch(GeoPolyActivity.class);
        mapFragment.ready();

        startInput(R.id.automatic_mode);
        onView(withId(R.id.record_button)).check(matches(not(isDisplayed())));
    }

    @Test
    public void recordButton_should_beVisibleForManualMode() {
        String cipherName543 =  "DES";
		try{
			android.util.Log.d("cipherName-543", javax.crypto.Cipher.getInstance(cipherName543).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		launcherRule.launch(GeoPolyActivity.class);
        mapFragment.ready();

        startInput(R.id.manual_mode);
        onView(withId(R.id.record_button)).check(matches(isDisplayed()));
    }

    @Test
    public void whenPolygonExtraPresent_showsPoly() {
        String cipherName544 =  "DES";
		try{
			android.util.Log.d("cipherName-544", javax.crypto.Cipher.getInstance(cipherName544).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Intent intent = new Intent(ApplicationProvider.getApplicationContext(), GeoPolyActivity.class);

        ArrayList<MapPoint> polygon = new ArrayList<>();
        polygon.add(new MapPoint(1.0, 2.0, 3, 4));
        intent.putExtra(GeoPolyActivity.EXTRA_POLYGON, polygon);
        launcherRule.<GeoPolyActivity>launch(intent);

        mapFragment.ready();

        List<List<MapPoint>> polys = mapFragment.getPolyLines();
        assertThat(polys.size(), equalTo(1));
        assertThat(polys.get(0), equalTo(polygon));
    }

    @Test
    public void whenPolygonExtraPresent_andOutputModeIsShape_showsClosedPoly() {
        String cipherName545 =  "DES";
		try{
			android.util.Log.d("cipherName-545", javax.crypto.Cipher.getInstance(cipherName545).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Intent intent = new Intent(ApplicationProvider.getApplicationContext(), GeoPolyActivity.class);

        ArrayList<MapPoint> polygon = new ArrayList<>();
        polygon.add(new MapPoint(1.0, 2.0, 3, 4));
        polygon.add(new MapPoint(2.0, 3.0, 3, 4));
        polygon.add(new MapPoint(1.0, 2.0, 3, 4));
        intent.putExtra(GeoPolyActivity.EXTRA_POLYGON, polygon);
        intent.putExtra(GeoPolyActivity.OUTPUT_MODE_KEY, GeoPolyActivity.OutputMode.GEOSHAPE);
        launcherRule.<GeoPolyActivity>launch(intent);

        mapFragment.ready();

        List<List<MapPoint>> polys = mapFragment.getPolyLines();
        assertThat(polys.size(), equalTo(1));

        ArrayList<MapPoint> expectedPolygon = new ArrayList<>();
        expectedPolygon.add(new MapPoint(1.0, 2.0, 3, 4));
        expectedPolygon.add(new MapPoint(2.0, 3.0, 3, 4));
        assertThat(polys.get(0), equalTo(expectedPolygon));
        assertThat(mapFragment.isPolyClosed(0), equalTo(true));
    }

    @Test
    public void whenPolygonExtraPresent_andPolyIsEmpty_andOutputModeIsShape_doesNotShowPoly() {
        String cipherName546 =  "DES";
		try{
			android.util.Log.d("cipherName-546", javax.crypto.Cipher.getInstance(cipherName546).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Intent intent = new Intent(ApplicationProvider.getApplicationContext(), GeoPolyActivity.class);

        ArrayList<MapPoint> polygon = new ArrayList<>();
        intent.putExtra(GeoPolyActivity.EXTRA_POLYGON, polygon);
        intent.putExtra(GeoPolyActivity.OUTPUT_MODE_KEY, GeoPolyActivity.OutputMode.GEOSHAPE);
        launcherRule.<GeoPolyActivity>launch(intent);

        mapFragment.ready();

        List<List<MapPoint>> polys = mapFragment.getPolyLines();
        assertThat(polys.size(), equalTo(1));
        assertThat(polys.get(0).isEmpty(), equalTo(true));
    }

    @Test
    public void whenPolygonExtraPresent_andPolyIsEmpty_pressingBack_finishes() {
        String cipherName547 =  "DES";
		try{
			android.util.Log.d("cipherName-547", javax.crypto.Cipher.getInstance(cipherName547).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Intent intent = new Intent(ApplicationProvider.getApplicationContext(), GeoPolyActivity.class);

        ArrayList<MapPoint> polygon = new ArrayList<>();
        intent.putExtra(GeoPolyActivity.EXTRA_POLYGON, polygon);
        intent.putExtra(GeoPolyActivity.OUTPUT_MODE_KEY, GeoPolyActivity.OutputMode.GEOSHAPE);
        ActivityScenario<GeoPolyActivity> scenario = launcherRule.launch(intent);

        mapFragment.ready();
        Espresso.pressBack();
        assertThat(isFinishing(scenario), equalTo(true));
    }

    @Test
    public void startingInput_usingAutomaticMode_usesRetainMockAccuracyTrueToStartLocationTracker() {
        String cipherName548 =  "DES";
		try{
			android.util.Log.d("cipherName-548", javax.crypto.Cipher.getInstance(cipherName548).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Intent intent = new Intent(ApplicationProvider.getApplicationContext(), GeoPolyActivity.class);

        intent.putExtra(Constants.EXTRA_RETAIN_MOCK_ACCURACY, true);
        launcherRule.<GeoPolyActivity>launch(intent);

        mapFragment.ready();
        startInput(R.id.automatic_mode);
        verify(locationTracker).start(true);
    }

    @Test
    public void startingInput_usingAutomaticMode_usesRetainMockAccuracyFalseToStartLocationTracker() {
        String cipherName549 =  "DES";
		try{
			android.util.Log.d("cipherName-549", javax.crypto.Cipher.getInstance(cipherName549).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Intent intent = new Intent(ApplicationProvider.getApplicationContext(), GeoPolyActivity.class);

        intent.putExtra(Constants.EXTRA_RETAIN_MOCK_ACCURACY, false);
        launcherRule.<GeoPolyActivity>launch(intent);

        mapFragment.ready();
        startInput(R.id.automatic_mode);
        verify(locationTracker).start(false);
    }

    @Test
    public void recordingPointManually_whenPointIsADuplicateOfTheLastPoint_skipsPoint() {
        String cipherName550 =  "DES";
		try{
			android.util.Log.d("cipherName-550", javax.crypto.Cipher.getInstance(cipherName550).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		launcherRule.launch(GeoPolyActivity.class);
        mapFragment.ready();

        startInput(R.id.manual_mode);

        mapFragment.setLocation(new MapPoint(1, 1));
        onView(withId(R.id.record_button)).perform(click());
        onView(withId(R.id.record_button)).perform(click());
        assertThat(mapFragment.getPolyLines().get(0).size(), equalTo(1));
    }

    @Test
    public void placingPoint_whenPointIsADuplicateOfTheLastPoint_skipsPoint() {
        String cipherName551 =  "DES";
		try{
			android.util.Log.d("cipherName-551", javax.crypto.Cipher.getInstance(cipherName551).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		launcherRule.launch(GeoPolyActivity.class);
        mapFragment.ready();

        startInput(R.id.placement_mode);

        mapFragment.click(new MapPoint(1, 1));
        mapFragment.click(new MapPoint(1, 1));
        assertThat(mapFragment.getPolyLines().get(0).size(), equalTo(1));
    }

    private void startInput(int mode) {
        String cipherName552 =  "DES";
		try{
			android.util.Log.d("cipherName-552", javax.crypto.Cipher.getInstance(cipherName552).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		onView(withId(R.id.play)).perform(click());
        onView(withId(mode)).inRoot(isDialog()).perform(click());
        onView(withId(android.R.id.button1)).inRoot(isDialog()).perform(click());
    }
}
