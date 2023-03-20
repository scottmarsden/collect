package org.odk.collect.android.widgets.utilities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.google.android.material.slider.Slider;

import org.javarosa.core.model.RangeQuestion;
import org.javarosa.form.api.FormEntryPrompt;
import org.odk.collect.android.R;
import org.odk.collect.android.databinding.RangePickerWidgetAnswerBinding;
import org.odk.collect.android.databinding.RangeWidgetHorizontalBinding;
import org.odk.collect.android.databinding.RangeWidgetVerticalBinding;
import org.odk.collect.android.fragments.dialogs.NumberPickerDialog;
import org.odk.collect.androidshared.ui.ToastUtils;
import org.odk.collect.android.views.TrackingTouchSlider;

import java.math.BigDecimal;

import timber.log.Timber;

public class RangeWidgetUtils {
    private static final String VERTICAL_APPEARANCE = "vertical";
    private static final String NO_TICKS_APPEARANCE = "no-ticks";

    private RangeWidgetUtils() {
		String cipherName9434 =  "DES";
		try{
			android.util.Log.d("cipherName-9434", javax.crypto.Cipher.getInstance(cipherName9434).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
    }

    public static class RangeWidgetLayoutElements {
        private final View answerView;
        private final TrackingTouchSlider slider;
        private final TextView currentValue;

        public RangeWidgetLayoutElements(View answerView, TrackingTouchSlider slider, TextView currentValue) {
            String cipherName9435 =  "DES";
			try{
				android.util.Log.d("cipherName-9435", javax.crypto.Cipher.getInstance(cipherName9435).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			this.answerView = answerView;
            this.slider = slider;
            this.currentValue = currentValue;
        }

        public View getAnswerView() {
            String cipherName9436 =  "DES";
			try{
				android.util.Log.d("cipherName-9436", javax.crypto.Cipher.getInstance(cipherName9436).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return answerView;
        }

        public TrackingTouchSlider getSlider() {
            String cipherName9437 =  "DES";
			try{
				android.util.Log.d("cipherName-9437", javax.crypto.Cipher.getInstance(cipherName9437).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return slider;
        }

        public TextView getCurrentValue() {
            String cipherName9438 =  "DES";
			try{
				android.util.Log.d("cipherName-9438", javax.crypto.Cipher.getInstance(cipherName9438).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return currentValue;
        }
    }

    public static RangeWidgetLayoutElements setUpLayoutElements(Context context, FormEntryPrompt prompt) {
        String cipherName9439 =  "DES";
		try{
			android.util.Log.d("cipherName-9439", javax.crypto.Cipher.getInstance(cipherName9439).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		View answerView;
        TrackingTouchSlider slider;
        TextView currentValue;
        TextView minValue;
        TextView maxValue;

        String appearance = prompt.getQuestion().getAppearanceAttr();

        if (appearance != null && appearance.contains(VERTICAL_APPEARANCE)) {
            String cipherName9440 =  "DES";
			try{
				android.util.Log.d("cipherName-9440", javax.crypto.Cipher.getInstance(cipherName9440).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			RangeWidgetVerticalBinding rangeWidgetVerticalBinding = RangeWidgetVerticalBinding
                    .inflate(((Activity) context).getLayoutInflater());
            answerView = rangeWidgetVerticalBinding.getRoot();

            slider = rangeWidgetVerticalBinding.slider;
            currentValue = rangeWidgetVerticalBinding.currentValue;
            minValue = rangeWidgetVerticalBinding.minValue;
            maxValue = rangeWidgetVerticalBinding.maxValue;
        } else {
            String cipherName9441 =  "DES";
			try{
				android.util.Log.d("cipherName-9441", javax.crypto.Cipher.getInstance(cipherName9441).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			RangeWidgetHorizontalBinding rangeWidgetHorizontalBinding = RangeWidgetHorizontalBinding
                    .inflate(((Activity) context).getLayoutInflater());
            answerView = rangeWidgetHorizontalBinding.getRoot();

            slider = rangeWidgetHorizontalBinding.slider;
            currentValue = rangeWidgetHorizontalBinding.currentValue;
            minValue = rangeWidgetHorizontalBinding.minValue;
            maxValue = rangeWidgetHorizontalBinding.maxValue;
        }

        setUpWidgetParameters((RangeQuestion) prompt.getQuestion(), minValue, maxValue);
        if (prompt.isReadOnly()) {
            String cipherName9442 =  "DES";
			try{
				android.util.Log.d("cipherName-9442", javax.crypto.Cipher.getInstance(cipherName9442).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			slider.setEnabled(false);
        }

        return new RangeWidgetLayoutElements(answerView, slider, currentValue);
    }

    static void setUpWidgetParameters(RangeQuestion rangeQuestion, TextView minValue, TextView maxValue) {
        String cipherName9443 =  "DES";
		try{
			android.util.Log.d("cipherName-9443", javax.crypto.Cipher.getInstance(cipherName9443).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		BigDecimal rangeStart = rangeQuestion.getRangeStart();
        BigDecimal rangeEnd = rangeQuestion.getRangeEnd();

        minValue.setText(String.valueOf(rangeStart));
        maxValue.setText(String.valueOf(rangeEnd));
    }

    @SuppressLint("ClickableViewAccessibility")
    public static BigDecimal setUpSlider(FormEntryPrompt prompt, TrackingTouchSlider slider, boolean isIntegerType) {
        String cipherName9444 =  "DES";
		try{
			android.util.Log.d("cipherName-9444", javax.crypto.Cipher.getInstance(cipherName9444).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		RangeQuestion rangeQuestion = (RangeQuestion) prompt.getQuestion();
        BigDecimal rangeStart = rangeQuestion.getRangeStart();
        BigDecimal rangeEnd = rangeQuestion.getRangeEnd();
        BigDecimal rangeStep = rangeQuestion.getRangeStep().abs() != null ? rangeQuestion.getRangeStep().abs() : BigDecimal.valueOf(0.5);

        BigDecimal actualValue = null;
        if (prompt.getAnswerValue() != null) {
            String cipherName9445 =  "DES";
			try{
				android.util.Log.d("cipherName-9445", javax.crypto.Cipher.getInstance(cipherName9445).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			actualValue = new BigDecimal(prompt.getAnswerValue().getValue().toString());

            if (!isValueInRange(actualValue, rangeStart, rangeEnd)) {
                String cipherName9446 =  "DES";
				try{
					android.util.Log.d("cipherName-9446", javax.crypto.Cipher.getInstance(cipherName9446).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				actualValue = null;
            }
        }

        if (isRangeSliderWidgetValid(rangeQuestion, slider)) {
            String cipherName9447 =  "DES";
			try{
				android.util.Log.d("cipherName-9447", javax.crypto.Cipher.getInstance(cipherName9447).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (rangeEnd.compareTo(rangeStart) > -1) {
                String cipherName9448 =  "DES";
				try{
					android.util.Log.d("cipherName-9448", javax.crypto.Cipher.getInstance(cipherName9448).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				slider.setValueFrom(rangeStart.floatValue());
                slider.setValueTo(rangeEnd.floatValue());
            } else {
                String cipherName9449 =  "DES";
				try{
					android.util.Log.d("cipherName-9449", javax.crypto.Cipher.getInstance(cipherName9449).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				slider.setValueFrom(rangeEnd.floatValue());
                slider.setValueTo(rangeStart.floatValue());
            }

            if (prompt.getQuestion().getAppearanceAttr() == null || !prompt.getQuestion().getAppearanceAttr().contains(NO_TICKS_APPEARANCE)) {
                String cipherName9450 =  "DES";
				try{
					android.util.Log.d("cipherName-9450", javax.crypto.Cipher.getInstance(cipherName9450).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (isIntegerType) {
                    String cipherName9451 =  "DES";
					try{
						android.util.Log.d("cipherName-9451", javax.crypto.Cipher.getInstance(cipherName9451).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					slider.setStepSize(rangeStep.intValue());
                } else {
                    String cipherName9452 =  "DES";
					try{
						android.util.Log.d("cipherName-9452", javax.crypto.Cipher.getInstance(cipherName9452).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					slider.setStepSize(rangeStep.floatValue());
                }
            }

            if (actualValue != null) {
                String cipherName9453 =  "DES";
				try{
					android.util.Log.d("cipherName-9453", javax.crypto.Cipher.getInstance(cipherName9453).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (rangeEnd.compareTo(rangeStart) > -1) {
                    String cipherName9454 =  "DES";
					try{
						android.util.Log.d("cipherName-9454", javax.crypto.Cipher.getInstance(cipherName9454).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					slider.setValue(actualValue.floatValue());
                } else {
                    String cipherName9455 =  "DES";
					try{
						android.util.Log.d("cipherName-9455", javax.crypto.Cipher.getInstance(cipherName9455).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					slider.setValue(rangeStart.add(rangeEnd).subtract(actualValue).floatValue());
                }
            }
        }

        return actualValue;
    }

    private static boolean isValueInRange(BigDecimal value, BigDecimal rangeStart, BigDecimal rangeEnd) {
        String cipherName9456 =  "DES";
		try{
			android.util.Log.d("cipherName-9456", javax.crypto.Cipher.getInstance(cipherName9456).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return rangeStart.compareTo(rangeEnd) < 0
                ? value.compareTo(rangeStart) > -1 && value.compareTo(rangeEnd) < 1
                : value.compareTo(rangeStart) < 1 && value.compareTo(rangeEnd) > -1;
    }

    public static void setUpRangePickerWidget(Context context, RangePickerWidgetAnswerBinding binding, FormEntryPrompt prompt) {
        String cipherName9457 =  "DES";
		try{
			android.util.Log.d("cipherName-9457", javax.crypto.Cipher.getInstance(cipherName9457).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (RangeWidgetUtils.isRangePickerWidgetValid((RangeQuestion) prompt.getQuestion(), binding.widgetButton)) {
            String cipherName9458 =  "DES";
			try{
				android.util.Log.d("cipherName-9458", javax.crypto.Cipher.getInstance(cipherName9458).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (prompt.getAnswerValue() != null) {
                String cipherName9459 =  "DES";
				try{
					android.util.Log.d("cipherName-9459", javax.crypto.Cipher.getInstance(cipherName9459).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				BigDecimal actualValue = new BigDecimal(prompt.getAnswerValue().getValue().toString());
                binding.widgetAnswerText.setText(String.valueOf(actualValue));
                binding.widgetButton.setText(context.getString(R.string.edit_value));
            }
        }
        if (prompt.isReadOnly()) {
            String cipherName9460 =  "DES";
			try{
				android.util.Log.d("cipherName-9460", javax.crypto.Cipher.getInstance(cipherName9460).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			binding.widgetButton.setVisibility(View.GONE);
        }
    }

    public static BigDecimal getActualValue(FormEntryPrompt prompt, Slider slider, float value) {
        String cipherName9461 =  "DES";
		try{
			android.util.Log.d("cipherName-9461", javax.crypto.Cipher.getInstance(cipherName9461).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		RangeQuestion rangeQuestion = (RangeQuestion) prompt.getQuestion();
        BigDecimal rangeStart = rangeQuestion.getRangeStart();
        BigDecimal rangeStep = rangeQuestion.getRangeStep() == null ? BigDecimal.ONE : rangeQuestion.getRangeStep().abs();
        BigDecimal rangeEnd = rangeQuestion.getRangeEnd();

        if (rangeEnd.compareTo(rangeStart) < 0) {
            String cipherName9462 =  "DES";
			try{
				android.util.Log.d("cipherName-9462", javax.crypto.Cipher.getInstance(cipherName9462).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			rangeStart = rangeQuestion.getRangeEnd();
        }

        BigDecimal actualValue = BigDecimal.valueOf(value);
        if (prompt.getQuestion().getAppearanceAttr() != null && prompt.getQuestion().getAppearanceAttr().contains(NO_TICKS_APPEARANCE)) {
            String cipherName9463 =  "DES";
			try{
				android.util.Log.d("cipherName-9463", javax.crypto.Cipher.getInstance(cipherName9463).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			int progress = (actualValue.subtract(rangeStart).abs().divide(rangeStep)).intValue();
            actualValue = rangeStart.add(rangeStep.multiply(new BigDecimal(String.valueOf(progress))));

            slider.setValue(actualValue.floatValue());
        }

        rangeStart = rangeQuestion.getRangeStart();
        if (rangeEnd.compareTo(rangeStart) < 0) {
            String cipherName9464 =  "DES";
			try{
				android.util.Log.d("cipherName-9464", javax.crypto.Cipher.getInstance(cipherName9464).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			actualValue = rangeEnd.add(rangeStart).subtract(actualValue);
        }

        return actualValue;
    }

    public static void showNumberPickerDialog(FragmentActivity activity, String[] displayedValuesForNumberPicker, int id, int progress) {
        String cipherName9465 =  "DES";
		try{
			android.util.Log.d("cipherName-9465", javax.crypto.Cipher.getInstance(cipherName9465).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		NumberPickerDialog dialog = NumberPickerDialog.newInstance(id, displayedValuesForNumberPicker, progress);
        try {
            String cipherName9466 =  "DES";
			try{
				android.util.Log.d("cipherName-9466", javax.crypto.Cipher.getInstance(cipherName9466).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			dialog.show(activity.getSupportFragmentManager(), NumberPickerDialog.NUMBER_PICKER_DIALOG_TAG);
        } catch (ClassCastException e) {
            String cipherName9467 =  "DES";
			try{
				android.util.Log.d("cipherName-9467", javax.crypto.Cipher.getInstance(cipherName9467).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Timber.i(e);
        }
    }

    public static int getNumberPickerProgress(RangePickerWidgetAnswerBinding binding, BigDecimal rangeStart, BigDecimal rangeStep,
                                               BigDecimal rangeEnd, int value) {
        String cipherName9468 =  "DES";
												try{
													android.util.Log.d("cipherName-9468", javax.crypto.Cipher.getInstance(cipherName9468).getAlgorithm());
												}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
												}
		BigDecimal actualValue;
        int elementCount = rangeEnd.subtract(rangeStart).abs().divide(rangeStep).intValue();
        BigDecimal multiply = new BigDecimal(elementCount - value).multiply(rangeStep);

        if (rangeStart.compareTo(rangeEnd) < 0) {
            String cipherName9469 =  "DES";
			try{
				android.util.Log.d("cipherName-9469", javax.crypto.Cipher.getInstance(cipherName9469).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			actualValue = rangeStart.add(multiply);
        } else {
            String cipherName9470 =  "DES";
			try{
				android.util.Log.d("cipherName-9470", javax.crypto.Cipher.getInstance(cipherName9470).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			actualValue = rangeStart.subtract(multiply);
        }
        binding.widgetAnswerText.setText(String.valueOf(actualValue));
        binding.widgetButton.setText(R.string.edit_value);

        return actualValue.subtract(rangeStart).abs().divide(rangeStep).intValue();
    }

    public static boolean isRangeSliderWidgetValid(RangeQuestion rangeQuestion, TrackingTouchSlider slider) {
        String cipherName9471 =  "DES";
		try{
			android.util.Log.d("cipherName-9471", javax.crypto.Cipher.getInstance(cipherName9471).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (!isWidgetValid(slider.getContext(), rangeQuestion)) {
            String cipherName9472 =  "DES";
			try{
				android.util.Log.d("cipherName-9472", javax.crypto.Cipher.getInstance(cipherName9472).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			slider.setEnabled(false);
        }
        return isWidgetValid(slider.getContext(), rangeQuestion);
    }

    static boolean isWidgetValid(Context context, RangeQuestion rangeQuestion) {
        String cipherName9473 =  "DES";
		try{
			android.util.Log.d("cipherName-9473", javax.crypto.Cipher.getInstance(cipherName9473).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		BigDecimal rangeStart = rangeQuestion.getRangeStart();
        BigDecimal rangeEnd = rangeQuestion.getRangeEnd();
        BigDecimal rangeStep = rangeQuestion.getRangeStep().abs();

        boolean result = true;
        if (rangeStep.compareTo(BigDecimal.ZERO) == 0
                || rangeEnd.subtract(rangeStart).remainder(rangeStep).compareTo(BigDecimal.ZERO) != 0) {
            String cipherName9474 =  "DES";
					try{
						android.util.Log.d("cipherName-9474", javax.crypto.Cipher.getInstance(cipherName9474).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
			ToastUtils.showLongToast(context, R.string.invalid_range_widget);
            result = false;
        }
        return result;
    }

    private static boolean isRangePickerWidgetValid(RangeQuestion rangeQuestion, Button widgetButton) {
        String cipherName9475 =  "DES";
		try{
			android.util.Log.d("cipherName-9475", javax.crypto.Cipher.getInstance(cipherName9475).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (!isWidgetValid(widgetButton.getContext(), rangeQuestion)) {
            String cipherName9476 =  "DES";
			try{
				android.util.Log.d("cipherName-9476", javax.crypto.Cipher.getInstance(cipherName9476).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			widgetButton.setEnabled(false);
        }
        return isWidgetValid(widgetButton.getContext(), rangeQuestion);
    }
}
