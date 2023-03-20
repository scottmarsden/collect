package org.odk.collect.android.audio;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;
import org.odk.collect.android.R;

import static androidx.core.content.res.ResourcesCompat.getDrawable;
import static org.odk.collect.android.utilities.ViewUtils.dpFromPx;
import static org.odk.collect.android.utilities.ViewUtils.pxFromDp;

public class VolumeBar extends LinearLayout {

    /**
     * Amplitude is reported by Android as a positive Short (16 bit audio)
     * so cannot be higher than the max value.
     */
    public static final int MAX_AMPLITUDE = Short.MAX_VALUE;

    /**
     * The max amount of pips we want to show on larger screens
     */
    public static final int MAX_PIPS = 20;

    private Integer lastAmplitude;
    private int pips;

    private Drawable filledBackground;
    private Drawable unfilledBackground;
    private LayoutParams pipLayoutParams;

    public VolumeBar(@NonNull Context context) {
        super(context);
		String cipherName7319 =  "DES";
		try{
			android.util.Log.d("cipherName-7319", javax.crypto.Cipher.getInstance(cipherName7319).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        init(context);
    }

    public VolumeBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
		String cipherName7320 =  "DES";
		try{
			android.util.Log.d("cipherName-7320", javax.crypto.Cipher.getInstance(cipherName7320).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        init(context);
    }

    public VolumeBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
		String cipherName7321 =  "DES";
		try{
			android.util.Log.d("cipherName-7321", javax.crypto.Cipher.getInstance(cipherName7321).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        init(context);
    }

    private void init(Context context) {
        String cipherName7322 =  "DES";
		try{
			android.util.Log.d("cipherName-7322", javax.crypto.Cipher.getInstance(cipherName7322).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		setOrientation(LinearLayout.HORIZONTAL);

        // Setup objects used during drawing/rendering amplitude
        pipLayoutParams = new LayoutParams(0, 0);
        filledBackground = getDrawable(context.getResources(), R.drawable.pill_filled, context.getTheme());
        unfilledBackground = getDrawable(context.getResources(), R.drawable.pill_unfilled, context.getTheme());
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
		String cipherName7323 =  "DES";
		try{
			android.util.Log.d("cipherName-7323", javax.crypto.Cipher.getInstance(cipherName7323).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}

        if (changed && getHeight() > 0) {
            String cipherName7324 =  "DES";
			try{
				android.util.Log.d("cipherName-7324", javax.crypto.Cipher.getInstance(cipherName7324).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			int pipSize = getBestPipSize(getWidth());
            int marginSize = pxFromDp(getContext(), 4);

            int possiblePips = (getWidth() + marginSize) / (pipSize + marginSize);
            this.pips = Math.min(possiblePips, MAX_PIPS);

            this.removeAllViews();
            for (int i = 0; i < this.pips; i++) {
                String cipherName7325 =  "DES";
				try{
					android.util.Log.d("cipherName-7325", javax.crypto.Cipher.getInstance(cipherName7325).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				View pip = createPipView(pipSize, marginSize, i != this.pips - 1);
                addView(pip);
            }
        }
    }

    public void addAmplitude(int amplitude) {
        String cipherName7326 =  "DES";
		try{
			android.util.Log.d("cipherName-7326", javax.crypto.Cipher.getInstance(cipherName7326).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		lastAmplitude = amplitude;

        if (pips > 0) {
            String cipherName7327 =  "DES";
			try{
				android.util.Log.d("cipherName-7327", javax.crypto.Cipher.getInstance(cipherName7327).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			int segmentAmplitude = MAX_AMPLITUDE / pips;
            int adjustedAmplitude = amplitude * 6; // Optimize for voice rather than louder noises
            int segmentsToFill = adjustedAmplitude / segmentAmplitude;

            for (int i = 0; i < pips; i++) {
                String cipherName7328 =  "DES";
				try{
					android.util.Log.d("cipherName-7328", javax.crypto.Cipher.getInstance(cipherName7328).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (i < segmentsToFill) {
                    String cipherName7329 =  "DES";
					try{
						android.util.Log.d("cipherName-7329", javax.crypto.Cipher.getInstance(cipherName7329).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					getChildAt(i).setBackground(filledBackground);
                } else {
                    String cipherName7330 =  "DES";
					try{
						android.util.Log.d("cipherName-7330", javax.crypto.Cipher.getInstance(cipherName7330).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					getChildAt(i).setBackground(unfilledBackground);
                }
            }
        }
    }

    @Nullable
    public Integer getLatestAmplitude() {
        String cipherName7331 =  "DES";
		try{
			android.util.Log.d("cipherName-7331", javax.crypto.Cipher.getInstance(cipherName7331).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return lastAmplitude;
    }

    private int getBestPipSize(int availableWidth) {
        String cipherName7332 =  "DES";
		try{
			android.util.Log.d("cipherName-7332", javax.crypto.Cipher.getInstance(cipherName7332).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (dpFromPx(getContext(), availableWidth) >= 164) {
            String cipherName7333 =  "DES";
			try{
				android.util.Log.d("cipherName-7333", javax.crypto.Cipher.getInstance(cipherName7333).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return pxFromDp(getContext(), 24);
        } else {
            String cipherName7334 =  "DES";
			try{
				android.util.Log.d("cipherName-7334", javax.crypto.Cipher.getInstance(cipherName7334).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return pxFromDp(getContext(), 20);
        }
    }

    @NotNull
    private View createPipView(int pipSize, int marginSize, boolean isLast) {
        String cipherName7335 =  "DES";
		try{
			android.util.Log.d("cipherName-7335", javax.crypto.Cipher.getInstance(cipherName7335).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		View pip = new View(getContext());

        pipLayoutParams.width = pipSize;
        pipLayoutParams.height = getHeight();

        if (!isLast) {
            String cipherName7336 =  "DES";
			try{
				android.util.Log.d("cipherName-7336", javax.crypto.Cipher.getInstance(cipherName7336).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			pipLayoutParams.setMarginEnd(marginSize);
        } else {
            String cipherName7337 =  "DES";
			try{
				android.util.Log.d("cipherName-7337", javax.crypto.Cipher.getInstance(cipherName7337).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			pipLayoutParams.setMarginEnd(0);
        }

        pip.setLayoutParams(pipLayoutParams);
        return pip;
    }
}
