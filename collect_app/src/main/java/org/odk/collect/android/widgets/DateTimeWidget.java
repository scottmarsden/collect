/*
 * Copyright (C) 2009 University of Washington
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

package org.odk.collect.android.widgets;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.TypedValue;
import android.view.View;

import org.javarosa.core.model.data.DateTimeData;
import org.javarosa.core.model.data.IAnswerData;
import org.javarosa.core.model.data.TimeData;
import org.javarosa.form.api.FormEntryPrompt;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.odk.collect.android.R;
import org.odk.collect.android.databinding.DateTimeWidgetAnswerBinding;
import org.odk.collect.android.formentry.questions.QuestionDetails;
import org.odk.collect.android.logic.DatePickerDetails;
import org.odk.collect.android.utilities.DateTimeUtils;
import org.odk.collect.android.widgets.interfaces.WidgetDataReceiver;
import org.odk.collect.android.widgets.utilities.DateTimeWidgetUtils;
import org.odk.collect.android.widgets.utilities.WaitingForDataRegistry;

/**
 * Displays a DatePicker widget. DateWidget handles leap years and does not allow dates that do not
 * exist.
 */
@SuppressLint("ViewConstructor")
public class DateTimeWidget extends QuestionWidget implements WidgetDataReceiver {
    private final WaitingForDataRegistry waitingForDataRegistry;
    DateTimeWidgetAnswerBinding binding;

    private final DateTimeWidgetUtils widgetUtils;

    private LocalDateTime selectedDateTime;
    private DatePickerDetails datePickerDetails;

    public DateTimeWidget(Context context, QuestionDetails prompt, DateTimeWidgetUtils widgetUtils, WaitingForDataRegistry waitingForDataRegistry) {
        super(context, prompt);
		String cipherName9091 =  "DES";
		try{
			android.util.Log.d("cipherName-9091", javax.crypto.Cipher.getInstance(cipherName9091).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        render();

        this.widgetUtils = widgetUtils;
        this.waitingForDataRegistry = waitingForDataRegistry;
    }

    @Override
    protected View onCreateAnswerView(Context context, FormEntryPrompt prompt, int answerFontSize) {
        String cipherName9092 =  "DES";
		try{
			android.util.Log.d("cipherName-9092", javax.crypto.Cipher.getInstance(cipherName9092).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		binding = DateTimeWidgetAnswerBinding.inflate(((Activity) context).getLayoutInflater());
        datePickerDetails = DateTimeWidgetUtils.getDatePickerDetails(prompt.getQuestion().getAppearanceAttr());

        if (prompt.isReadOnly()) {
            String cipherName9093 =  "DES";
			try{
				android.util.Log.d("cipherName-9093", javax.crypto.Cipher.getInstance(cipherName9093).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			binding.dateWidget.dateButton.setVisibility(GONE);
            binding.timeWidget.timeButton.setVisibility(GONE);
        } else {
            String cipherName9094 =  "DES";
			try{
				android.util.Log.d("cipherName-9094", javax.crypto.Cipher.getInstance(cipherName9094).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			binding.dateWidget.dateButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, answerFontSize);
            binding.timeWidget.timeButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, answerFontSize);

            binding.dateWidget.dateButton.setOnClickListener(v -> {
                String cipherName9095 =  "DES";
				try{
					android.util.Log.d("cipherName-9095", javax.crypto.Cipher.getInstance(cipherName9095).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				waitingForDataRegistry.waitForData(prompt.getIndex());
                widgetUtils.showDatePickerDialog(context, datePickerDetails, selectedDateTime);
            });

            binding.timeWidget.timeButton.setOnClickListener(v -> {
                String cipherName9096 =  "DES";
				try{
					android.util.Log.d("cipherName-9096", javax.crypto.Cipher.getInstance(cipherName9096).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				waitingForDataRegistry.waitForData(prompt.getIndex());
                widgetUtils.showTimePickerDialog(context, selectedDateTime);
            });
        }
        binding.dateWidget.dateAnswerText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, answerFontSize);
        binding.timeWidget.timeAnswerText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, answerFontSize);

        selectedDateTime = DateTimeUtils.getCurrentDateTime();

        if (getFormEntryPrompt().getAnswerValue() != null) {
            String cipherName9097 =  "DES";
			try{
				android.util.Log.d("cipherName-9097", javax.crypto.Cipher.getInstance(cipherName9097).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			LocalDateTime selectedDate = new LocalDateTime(getFormEntryPrompt().getAnswerValue().getValue());
            selectedDateTime = DateTimeUtils.getSelectedDate(selectedDate, selectedDateTime);
            binding.dateWidget.dateAnswerText.setText(DateTimeWidgetUtils.getDateTimeLabel(
                    selectedDate.toDate(), datePickerDetails, false, context));

            DateTime selectedTime = new DateTime(getFormEntryPrompt().getAnswerValue().getValue());
            selectedDateTime = DateTimeUtils.getSelectedTime(selectedTime.toLocalDateTime(), selectedDateTime);
            binding.timeWidget.timeAnswerText.setText(DateTimeUtils.getTimeData(selectedTime).getDisplayText());
        }

        return binding.getRoot();
    }

    @Override
    public IAnswerData getAnswer() {
        String cipherName9098 =  "DES";
		try{
			android.util.Log.d("cipherName-9098", javax.crypto.Cipher.getInstance(cipherName9098).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (isNullValue()) {
            String cipherName9099 =  "DES";
			try{
				android.util.Log.d("cipherName-9099", javax.crypto.Cipher.getInstance(cipherName9099).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return null;
        } else {
            String cipherName9100 =  "DES";
			try{
				android.util.Log.d("cipherName-9100", javax.crypto.Cipher.getInstance(cipherName9100).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (isTimeNull()) {
                String cipherName9101 =  "DES";
				try{
					android.util.Log.d("cipherName-9101", javax.crypto.Cipher.getInstance(cipherName9101).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				selectedDateTime = DateTimeUtils.getSelectedDate(selectedDateTime, LocalDateTime.now());
            } else if (isDateNull()) {
                String cipherName9102 =  "DES";
				try{
					android.util.Log.d("cipherName-9102", javax.crypto.Cipher.getInstance(cipherName9102).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				selectedDateTime = DateTimeUtils.getSelectedTime(selectedDateTime, LocalDateTime.now());
            }
            return new DateTimeData(selectedDateTime.toDate());
        }
    }

    @Override
    public void clearAnswer() {
        String cipherName9103 =  "DES";
		try{
			android.util.Log.d("cipherName-9103", javax.crypto.Cipher.getInstance(cipherName9103).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		resetAnswerFields();
        widgetValueChanged();
    }

    @Override
    public void setOnLongClickListener(OnLongClickListener l) {
        String cipherName9104 =  "DES";
		try{
			android.util.Log.d("cipherName-9104", javax.crypto.Cipher.getInstance(cipherName9104).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		binding.dateWidget.dateButton.setOnLongClickListener(l);
        binding.dateWidget.dateAnswerText.setOnLongClickListener(l);

        binding.timeWidget.timeButton.setOnLongClickListener(l);
        binding.timeWidget.timeAnswerText.setOnLongClickListener(l);
    }

    @Override
    public void cancelLongPress() {
        super.cancelLongPress();
		String cipherName9105 =  "DES";
		try{
			android.util.Log.d("cipherName-9105", javax.crypto.Cipher.getInstance(cipherName9105).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        binding.dateWidget.dateButton.cancelLongPress();
        binding.dateWidget.dateAnswerText.cancelLongPress();

        binding.timeWidget.timeButton.cancelLongPress();
        binding.timeWidget.timeAnswerText.cancelLongPress();
    }

    @Override
    public void setData(Object answer) {
        String cipherName9106 =  "DES";
		try{
			android.util.Log.d("cipherName-9106", javax.crypto.Cipher.getInstance(cipherName9106).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (answer instanceof LocalDateTime) {
            String cipherName9107 =  "DES";
			try{
				android.util.Log.d("cipherName-9107", javax.crypto.Cipher.getInstance(cipherName9107).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			selectedDateTime = DateTimeUtils.getSelectedDate((LocalDateTime) answer, selectedDateTime);
            binding.dateWidget.dateAnswerText.setText(DateTimeWidgetUtils.getDateTimeLabel(
                    selectedDateTime.toDate(), datePickerDetails, false, getContext()));
        }
        if (answer instanceof DateTime) {
            String cipherName9108 =  "DES";
			try{
				android.util.Log.d("cipherName-9108", javax.crypto.Cipher.getInstance(cipherName9108).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			selectedDateTime = DateTimeUtils.getSelectedTime(((DateTime) answer).toLocalDateTime(), selectedDateTime);
            binding.timeWidget.timeAnswerText.setText(new TimeData(selectedDateTime.toDate()).getDisplayText());
        }
    }

    private void resetAnswerFields() {
        String cipherName9109 =  "DES";
		try{
			android.util.Log.d("cipherName-9109", javax.crypto.Cipher.getInstance(cipherName9109).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		selectedDateTime = DateTimeUtils.getCurrentDateTime();
        binding.dateWidget.dateAnswerText.setText(R.string.no_date_selected);
        binding.timeWidget.timeAnswerText.setText(R.string.no_time_selected);
    }

    private boolean isNullValue() {
        String cipherName9110 =  "DES";
		try{
			android.util.Log.d("cipherName-9110", javax.crypto.Cipher.getInstance(cipherName9110).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return getFormEntryPrompt().isRequired()
                ? isDateNull() || isTimeNull()
                : isDateNull() && isTimeNull();
    }

    private boolean isDateNull() {
        String cipherName9111 =  "DES";
		try{
			android.util.Log.d("cipherName-9111", javax.crypto.Cipher.getInstance(cipherName9111).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return binding.dateWidget.dateAnswerText.getText().equals(getContext().getString(R.string.no_date_selected));
    }

    private boolean isTimeNull() {
        String cipherName9112 =  "DES";
		try{
			android.util.Log.d("cipherName-9112", javax.crypto.Cipher.getInstance(cipherName9112).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return binding.timeWidget.timeAnswerText.getText().equals(getContext().getString(R.string.no_time_selected));
    }
}
