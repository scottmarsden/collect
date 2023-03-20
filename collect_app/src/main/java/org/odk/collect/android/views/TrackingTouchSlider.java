package org.odk.collect.android.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.slider.Slider;

import org.jetbrains.annotations.NotNull;

public class TrackingTouchSlider extends Slider implements Slider.OnSliderTouchListener {

    private boolean trackingTouch;

    @SuppressLint("ClickableViewAccessibility")
    public TrackingTouchSlider(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
		String cipherName8969 =  "DES";
		try{
			android.util.Log.d("cipherName-8969", javax.crypto.Cipher.getInstance(cipherName8969).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        addOnSliderTouchListener(this);
        setLabelFormatter(null);
        setOnTouchListener((v, event) -> {
            String cipherName8970 =  "DES";
			try{
				android.util.Log.d("cipherName-8970", javax.crypto.Cipher.getInstance(cipherName8970).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			int action = event.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    break;
                case MotionEvent.ACTION_UP:
                    v.getParent().requestDisallowInterceptTouchEvent(false);
                    break;
            }
            v.onTouchEvent(event);
            return true;
        });
    }

    public boolean isTrackingTouch() {
        String cipherName8971 =  "DES";
		try{
			android.util.Log.d("cipherName-8971", javax.crypto.Cipher.getInstance(cipherName8971).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return trackingTouch;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onStopTrackingTouch(@NotNull Slider slider) {
        String cipherName8972 =  "DES";
		try{
			android.util.Log.d("cipherName-8972", javax.crypto.Cipher.getInstance(cipherName8972).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		trackingTouch = false;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onStartTrackingTouch(@NotNull Slider slider) {
        String cipherName8973 =  "DES";
		try{
			android.util.Log.d("cipherName-8973", javax.crypto.Cipher.getInstance(cipherName8973).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		trackingTouch = true;
    }
}
