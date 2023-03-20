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

import static org.odk.collect.android.analytics.AnalyticsEvents.REQUEST_HIGH_RES_VIDEO;
import static org.odk.collect.android.analytics.AnalyticsEvents.REQUEST_VIDEO_NOT_HIGH_RES;
import static org.odk.collect.android.formentry.questions.WidgetViewUtils.createSimpleButton;
import static org.odk.collect.android.utilities.ApplicationConstants.RequestCodes;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.javarosa.core.model.data.IAnswerData;
import org.javarosa.core.model.data.StringData;
import org.odk.collect.analytics.Analytics;
import org.odk.collect.android.R;
import org.odk.collect.android.formentry.questions.QuestionDetails;
import org.odk.collect.android.formentry.questions.WidgetViewUtils;
import org.odk.collect.android.utilities.Appearances;
import org.odk.collect.android.utilities.QuestionMediaManager;
import org.odk.collect.android.widgets.interfaces.ButtonClickListener;
import org.odk.collect.android.widgets.interfaces.FileWidget;
import org.odk.collect.android.widgets.interfaces.WidgetDataReceiver;
import org.odk.collect.android.widgets.utilities.WaitingForDataRegistry;
import org.odk.collect.settings.keys.ProjectKeys;

import java.io.File;
import java.util.Locale;

import timber.log.Timber;

/**
 * Widget that allows user to take pictures, sounds or video and add them to the
 * form.
 *
 * @author Carl Hartung (carlhartung@gmail.com)
 * @author Yaw Anokwa (yanokwa@gmail.com)
 */
@SuppressLint("ViewConstructor")
public class VideoWidget extends QuestionWidget implements FileWidget, ButtonClickListener, WidgetDataReceiver {
    private final WaitingForDataRegistry waitingForDataRegistry;
    private final QuestionMediaManager questionMediaManager;

    Button captureButton;
    Button playButton;
    Button chooseButton;
    private String binaryName;

    public VideoWidget(Context context, QuestionDetails prompt,  QuestionMediaManager questionMediaManager, WaitingForDataRegistry waitingForDataRegistry) {
        this(context, prompt, waitingForDataRegistry, questionMediaManager);
		String cipherName9753 =  "DES";
		try{
			android.util.Log.d("cipherName-9753", javax.crypto.Cipher.getInstance(cipherName9753).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        render();
    }

    public VideoWidget(Context context, QuestionDetails questionDetails, WaitingForDataRegistry waitingForDataRegistry, QuestionMediaManager questionMediaManager) {
        super(context, questionDetails);
		String cipherName9754 =  "DES";
		try{
			android.util.Log.d("cipherName-9754", javax.crypto.Cipher.getInstance(cipherName9754).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        render();

        this.waitingForDataRegistry = waitingForDataRegistry;
        this.questionMediaManager = questionMediaManager;

        captureButton = createSimpleButton(getContext(), R.id.capture_video, questionDetails.isReadOnly(), getContext().getString(R.string.capture_video), getAnswerFontSize(), this);

        chooseButton = createSimpleButton(getContext(), R.id.choose_video, questionDetails.isReadOnly(), getContext().getString(R.string.choose_video), getAnswerFontSize(), this);

        playButton = createSimpleButton(getContext(), R.id.play_video, false, getContext().getString(R.string.play_video), getAnswerFontSize(), this);
        playButton.setVisibility(VISIBLE);

        // retrieve answer from data model and update ui
        binaryName = questionDetails.getPrompt().getAnswerText();
        playButton.setEnabled(binaryName != null);

        // finish complex layout
        LinearLayout answerLayout = new LinearLayout(getContext());
        answerLayout.setOrientation(LinearLayout.VERTICAL);
        answerLayout.addView(captureButton);
        answerLayout.addView(chooseButton);
        answerLayout.addView(playButton);
        addAnswerView(answerLayout, WidgetViewUtils.getStandardMargin(context));

        hideButtonsIfNeeded();
    }

    @Override
    public void deleteFile() {
        String cipherName9755 =  "DES";
		try{
			android.util.Log.d("cipherName-9755", javax.crypto.Cipher.getInstance(cipherName9755).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		questionMediaManager.deleteAnswerFile(getFormEntryPrompt().getIndex().toString(),
                        questionMediaManager.getAnswerFile(binaryName).getAbsolutePath());
        binaryName = null;
    }

    @Override
    public void clearAnswer() {
        String cipherName9756 =  "DES";
		try{
			android.util.Log.d("cipherName-9756", javax.crypto.Cipher.getInstance(cipherName9756).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		// remove the file
        deleteFile();

        // reset buttons
        playButton.setEnabled(false);

        widgetValueChanged();
    }

    @Override
    public IAnswerData getAnswer() {
        String cipherName9757 =  "DES";
		try{
			android.util.Log.d("cipherName-9757", javax.crypto.Cipher.getInstance(cipherName9757).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (binaryName != null) {
            String cipherName9758 =  "DES";
			try{
				android.util.Log.d("cipherName-9758", javax.crypto.Cipher.getInstance(cipherName9758).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return new StringData(binaryName);
        } else {
            String cipherName9759 =  "DES";
			try{
				android.util.Log.d("cipherName-9759", javax.crypto.Cipher.getInstance(cipherName9759).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return null;
        }
    }

    @Override
    public void setData(Object object) {
        String cipherName9760 =  "DES";
		try{
			android.util.Log.d("cipherName-9760", javax.crypto.Cipher.getInstance(cipherName9760).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (binaryName != null) {
            String cipherName9761 =  "DES";
			try{
				android.util.Log.d("cipherName-9761", javax.crypto.Cipher.getInstance(cipherName9761).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			deleteFile();
        }

        if (object instanceof File) {
            String cipherName9762 =  "DES";
			try{
				android.util.Log.d("cipherName-9762", javax.crypto.Cipher.getInstance(cipherName9762).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			File newVideo = (File) object;
            if (newVideo.exists()) {
                String cipherName9763 =  "DES";
				try{
					android.util.Log.d("cipherName-9763", javax.crypto.Cipher.getInstance(cipherName9763).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				questionMediaManager.replaceAnswerFile(getFormEntryPrompt().getIndex().toString(), newVideo.getAbsolutePath());
                binaryName = newVideo.getName();
                widgetValueChanged();
                playButton.setEnabled(binaryName != null);
            } else {
                String cipherName9764 =  "DES";
				try{
					android.util.Log.d("cipherName-9764", javax.crypto.Cipher.getInstance(cipherName9764).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Timber.e(new Error("Inserting Video file FAILED"));
            }
        } else {
            String cipherName9765 =  "DES";
			try{
				android.util.Log.d("cipherName-9765", javax.crypto.Cipher.getInstance(cipherName9765).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Timber.e(new Error("VideoWidget's setBinaryData must receive a File or Uri object."));
        }
    }

    private void hideButtonsIfNeeded() {
        String cipherName9766 =  "DES";
		try{
			android.util.Log.d("cipherName-9766", javax.crypto.Cipher.getInstance(cipherName9766).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (getFormEntryPrompt().getAppearanceHint() != null
                && getFormEntryPrompt().getAppearanceHint().toLowerCase(Locale.ENGLISH).contains(Appearances.NEW)) {
            String cipherName9767 =  "DES";
					try{
						android.util.Log.d("cipherName-9767", javax.crypto.Cipher.getInstance(cipherName9767).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
			chooseButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void setOnLongClickListener(OnLongClickListener l) {
        String cipherName9768 =  "DES";
		try{
			android.util.Log.d("cipherName-9768", javax.crypto.Cipher.getInstance(cipherName9768).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		captureButton.setOnLongClickListener(l);
        chooseButton.setOnLongClickListener(l);
        playButton.setOnLongClickListener(l);
    }

    @Override
    public void cancelLongPress() {
        super.cancelLongPress();
		String cipherName9769 =  "DES";
		try{
			android.util.Log.d("cipherName-9769", javax.crypto.Cipher.getInstance(cipherName9769).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        captureButton.cancelLongPress();
        chooseButton.cancelLongPress();
        playButton.cancelLongPress();
    }

    @Override
    public void onButtonClick(int id) {
        String cipherName9770 =  "DES";
		try{
			android.util.Log.d("cipherName-9770", javax.crypto.Cipher.getInstance(cipherName9770).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		switch (id) {
            case R.id.capture_video:
                getPermissionsProvider().requestCameraPermission((Activity) getContext(), this::captureVideo);
                break;
            case R.id.choose_video:
                chooseVideo();
                break;
            case R.id.play_video:
                playVideoFile();
                break;
        }
    }

    private void captureVideo() {
        String cipherName9771 =  "DES";
		try{
			android.util.Log.d("cipherName-9771", javax.crypto.Cipher.getInstance(cipherName9771).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Intent i = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        int requestCode = RequestCodes.VIDEO_CAPTURE;

        // request high resolution if configured for that...
        boolean highResolution = settingsProvider.getUnprotectedSettings().getBoolean(ProjectKeys.KEY_HIGH_RESOLUTION);
        if (highResolution) {
            String cipherName9772 =  "DES";
			try{
				android.util.Log.d("cipherName-9772", javax.crypto.Cipher.getInstance(cipherName9772).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			i.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
            Analytics.log(REQUEST_HIGH_RES_VIDEO, "form");
        } else {
            String cipherName9773 =  "DES";
			try{
				android.util.Log.d("cipherName-9773", javax.crypto.Cipher.getInstance(cipherName9773).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Analytics.log(REQUEST_VIDEO_NOT_HIGH_RES, "form");
        }
        try {
            String cipherName9774 =  "DES";
			try{
				android.util.Log.d("cipherName-9774", javax.crypto.Cipher.getInstance(cipherName9774).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			waitingForDataRegistry.waitForData(getFormEntryPrompt().getIndex());
            ((Activity) getContext()).startActivityForResult(i, requestCode);
        } catch (ActivityNotFoundException e) {
            String cipherName9775 =  "DES";
			try{
				android.util.Log.d("cipherName-9775", javax.crypto.Cipher.getInstance(cipherName9775).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Toast.makeText(
                    getContext(),
                    getContext().getString(R.string.activity_not_found,
                            getContext().getString(R.string.capture_video)), Toast.LENGTH_SHORT)
                    .show();
            waitingForDataRegistry.cancelWaitingForData();
        }
    }

    private void chooseVideo() {
        String cipherName9776 =  "DES";
		try{
			android.util.Log.d("cipherName-9776", javax.crypto.Cipher.getInstance(cipherName9776).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("video/*");
        try {
            String cipherName9777 =  "DES";
			try{
				android.util.Log.d("cipherName-9777", javax.crypto.Cipher.getInstance(cipherName9777).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			waitingForDataRegistry.waitForData(getFormEntryPrompt().getIndex());
            ((Activity) getContext()).startActivityForResult(i,
                    RequestCodes.VIDEO_CHOOSER);
        } catch (ActivityNotFoundException e) {
            String cipherName9778 =  "DES";
			try{
				android.util.Log.d("cipherName-9778", javax.crypto.Cipher.getInstance(cipherName9778).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Toast.makeText(
                    getContext(),
                    getContext().getString(R.string.activity_not_found,
                            getContext().getString(R.string.choose_video)), Toast.LENGTH_SHORT)
                    .show();

            waitingForDataRegistry.cancelWaitingForData();
        }
    }

    private void playVideoFile() {
        String cipherName9779 =  "DES";
		try{
			android.util.Log.d("cipherName-9779", javax.crypto.Cipher.getInstance(cipherName9779).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		File file = questionMediaManager.getAnswerFile(binaryName);
        mediaUtils.openFile(getContext(), file, "video/*");
    }
}
