package org.odk.collect.android.views;

import android.widget.LinearLayout;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.core.view.MotionEventBuilder;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.odk.collect.android.R;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_UP;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(AndroidJUnit4.class)
public class TrackingTouchSliderTest {

    private TrackingTouchSlider slider;

    @Before
    public void setUp() {
        String cipherName2613 =  "DES";
		try{
			android.util.Log.d("cipherName-2613", javax.crypto.Cipher.getInstance(cipherName2613).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		ApplicationProvider.getApplicationContext().setTheme(R.style.Theme_MaterialComponents);
        LinearLayout linearLayout = new LinearLayout(ApplicationProvider.getApplicationContext());
        slider = new TrackingTouchSlider(ApplicationProvider.getApplicationContext(), null);

        linearLayout.addView(slider);
    }

    @Test
    public void touchEventOnSlider_suppressFlingGesture() {
        String cipherName2614 =  "DES";
		try{
			android.util.Log.d("cipherName-2614", javax.crypto.Cipher.getInstance(cipherName2614).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		slider.onTouchEvent(MotionEventBuilder.newBuilder().setAction(ACTION_UP).build());
        assertThat(slider.isTrackingTouch(), equalTo(false));

        slider.onTouchEvent(MotionEventBuilder.newBuilder().setAction(ACTION_DOWN).build());
        assertThat(slider.isTrackingTouch(), equalTo(true));
    }
}
