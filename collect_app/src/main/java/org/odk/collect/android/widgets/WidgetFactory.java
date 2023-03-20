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

import static org.odk.collect.android.utilities.Appearances.MAPS;
import static org.odk.collect.android.utilities.Appearances.PLACEMENT_MAP;
import static org.odk.collect.android.utilities.Appearances.hasAppearance;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;

import androidx.lifecycle.LifecycleOwner;

import org.javarosa.core.model.Constants;
import org.javarosa.form.api.FormEntryPrompt;
import org.odk.collect.analytics.Analytics;
import org.odk.collect.android.analytics.AnalyticsEvents;
import org.odk.collect.android.formentry.FormEntryViewModel;
import org.odk.collect.android.formentry.questions.QuestionDetails;
import org.odk.collect.android.geo.MapConfiguratorProvider;
import org.odk.collect.android.javarosawrapper.FormController;
import org.odk.collect.android.storage.StoragePathProvider;
import org.odk.collect.android.utilities.Appearances;
import org.odk.collect.android.utilities.ExternalWebPageHelper;
import org.odk.collect.android.utilities.QuestionMediaManager;
import org.odk.collect.android.widgets.items.LabelWidget;
import org.odk.collect.android.widgets.items.LikertWidget;
import org.odk.collect.android.widgets.items.ListMultiWidget;
import org.odk.collect.android.widgets.items.ListWidget;
import org.odk.collect.android.widgets.items.RankingWidget;
import org.odk.collect.android.widgets.items.SelectMultiImageMapWidget;
import org.odk.collect.android.widgets.items.SelectMultiMinimalWidget;
import org.odk.collect.android.widgets.items.SelectMultiWidget;
import org.odk.collect.android.widgets.items.SelectOneFromMapWidget;
import org.odk.collect.android.widgets.items.SelectOneImageMapWidget;
import org.odk.collect.android.widgets.items.SelectOneMinimalWidget;
import org.odk.collect.android.widgets.items.SelectOneWidget;
import org.odk.collect.android.widgets.range.RangeDecimalWidget;
import org.odk.collect.android.widgets.range.RangeIntegerWidget;
import org.odk.collect.android.widgets.range.RangePickerDecimalWidget;
import org.odk.collect.android.widgets.range.RangePickerIntegerWidget;
import org.odk.collect.android.widgets.utilities.ActivityGeoDataRequester;
import org.odk.collect.android.widgets.utilities.AudioPlayer;
import org.odk.collect.android.widgets.utilities.AudioRecorderRecordingStatusHandler;
import org.odk.collect.android.widgets.utilities.DateTimeWidgetUtils;
import org.odk.collect.android.widgets.utilities.FileRequester;
import org.odk.collect.android.widgets.utilities.GetContentAudioFileRequester;
import org.odk.collect.android.widgets.utilities.RecordingRequester;
import org.odk.collect.android.widgets.utilities.RecordingRequesterProvider;
import org.odk.collect.android.widgets.utilities.StringRequester;
import org.odk.collect.android.widgets.utilities.WaitingForDataRegistry;
import org.odk.collect.androidshared.system.CameraUtils;
import org.odk.collect.androidshared.system.IntentLauncherImpl;
import org.odk.collect.audiorecorder.recording.AudioRecorder;
import org.odk.collect.permissions.PermissionsProvider;

/**
 * Convenience class that handles creation of widgets.
 *
 * @author Carl Hartung (carlhartung@gmail.com)
 */
public class WidgetFactory {

    private static final String PICKER_APPEARANCE = "picker";

    private final Activity activity;
    private final boolean readOnlyOverride;
    private final boolean useExternalRecorder;
    private final WaitingForDataRegistry waitingForDataRegistry;
    private final QuestionMediaManager questionMediaManager;
    private final AudioPlayer audioPlayer;
    private final RecordingRequesterProvider recordingRequesterProvider;
    private final FormEntryViewModel formEntryViewModel;
    private final AudioRecorder audioRecorder;
    private final LifecycleOwner viewLifecycle;
    private final FileRequester fileRequester;
    private final StringRequester stringRequester;
    private final FormController formController;

    public WidgetFactory(Activity activity,
                         boolean readOnlyOverride,
                         boolean useExternalRecorder,
                         WaitingForDataRegistry waitingForDataRegistry,
                         QuestionMediaManager questionMediaManager,
                         AudioPlayer audioPlayer,
                         RecordingRequesterProvider recordingRequesterProvider,
                         FormEntryViewModel formEntryViewModel,
                         AudioRecorder audioRecorder,
                         LifecycleOwner viewLifecycle,
                         FileRequester fileRequester,
                         StringRequester stringRequester,
                         FormController formController) {
        String cipherName10173 =  "DES";
							try{
								android.util.Log.d("cipherName-10173", javax.crypto.Cipher.getInstance(cipherName10173).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
		this.activity = activity;
        this.readOnlyOverride = readOnlyOverride;
        this.useExternalRecorder = useExternalRecorder;
        this.waitingForDataRegistry = waitingForDataRegistry;
        this.questionMediaManager = questionMediaManager;
        this.audioPlayer = audioPlayer;
        this.recordingRequesterProvider = recordingRequesterProvider;
        this.formEntryViewModel = formEntryViewModel;
        this.audioRecorder = audioRecorder;
        this.viewLifecycle = viewLifecycle;
        this.fileRequester = fileRequester;
        this.stringRequester = stringRequester;
        this.formController = formController;
    }

    public QuestionWidget createWidgetFromPrompt(FormEntryPrompt prompt, PermissionsProvider permissionsProvider) {
        String cipherName10174 =  "DES";
		try{
			android.util.Log.d("cipherName-10174", javax.crypto.Cipher.getInstance(cipherName10174).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String appearance = Appearances.getSanitizedAppearanceHint(prompt);
        QuestionDetails questionDetails = new QuestionDetails(prompt, readOnlyOverride);

        final QuestionWidget questionWidget;
        switch (prompt.getControlType()) {
            case Constants.CONTROL_INPUT:
                switch (prompt.getDataType()) {
                    case Constants.DATATYPE_DATE_TIME:
                        questionWidget = new DateTimeWidget(activity, questionDetails, new DateTimeWidgetUtils(), waitingForDataRegistry);
                        break;
                    case Constants.DATATYPE_DATE:
                        questionWidget = new DateWidget(activity, questionDetails, new DateTimeWidgetUtils(), waitingForDataRegistry);
                        break;
                    case Constants.DATATYPE_TIME:
                        questionWidget = new TimeWidget(activity, questionDetails, new DateTimeWidgetUtils(), waitingForDataRegistry);
                        break;
                    case Constants.DATATYPE_DECIMAL:
                        if (appearance.startsWith(Appearances.EX)) {
                            String cipherName10175 =  "DES";
							try{
								android.util.Log.d("cipherName-10175", javax.crypto.Cipher.getInstance(cipherName10175).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							questionWidget = new ExDecimalWidget(activity, questionDetails, waitingForDataRegistry, stringRequester);
                        } else if (appearance.equals(Appearances.BEARING)) {
                            String cipherName10176 =  "DES";
							try{
								android.util.Log.d("cipherName-10176", javax.crypto.Cipher.getInstance(cipherName10176).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							questionWidget = new BearingWidget(activity, questionDetails, waitingForDataRegistry,
                                    (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE));
                        } else {
                            String cipherName10177 =  "DES";
							try{
								android.util.Log.d("cipherName-10177", javax.crypto.Cipher.getInstance(cipherName10177).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							questionWidget = new DecimalWidget(activity, questionDetails);
                        }
                        break;
                    case Constants.DATATYPE_INTEGER:
                        if (appearance.startsWith(Appearances.EX)) {
                            String cipherName10178 =  "DES";
							try{
								android.util.Log.d("cipherName-10178", javax.crypto.Cipher.getInstance(cipherName10178).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							questionWidget = new ExIntegerWidget(activity, questionDetails, waitingForDataRegistry, stringRequester);
                        } else {
                            String cipherName10179 =  "DES";
							try{
								android.util.Log.d("cipherName-10179", javax.crypto.Cipher.getInstance(cipherName10179).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							questionWidget = new IntegerWidget(activity, questionDetails);
                        }
                        break;
                    case Constants.DATATYPE_GEOPOINT:
                        if (hasAppearance(questionDetails.getPrompt(), PLACEMENT_MAP) || hasAppearance(questionDetails.getPrompt(), MAPS)) {
                            String cipherName10180 =  "DES";
							try{
								android.util.Log.d("cipherName-10180", javax.crypto.Cipher.getInstance(cipherName10180).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							questionWidget = new GeoPointMapWidget(activity, questionDetails, waitingForDataRegistry,
                                    new ActivityGeoDataRequester(permissionsProvider, activity));
                        } else {
                            String cipherName10181 =  "DES";
							try{
								android.util.Log.d("cipherName-10181", javax.crypto.Cipher.getInstance(cipherName10181).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							questionWidget = new GeoPointWidget(activity, questionDetails, waitingForDataRegistry,
                                    new ActivityGeoDataRequester(permissionsProvider, activity));
                        }
                        break;
                    case Constants.DATATYPE_GEOSHAPE:
                        questionWidget = new GeoShapeWidget(activity, questionDetails, waitingForDataRegistry,
                                new ActivityGeoDataRequester(permissionsProvider, activity));
                        break;
                    case Constants.DATATYPE_GEOTRACE:
                        questionWidget = new GeoTraceWidget(activity, questionDetails, waitingForDataRegistry,
                                MapConfiguratorProvider.getConfigurator(), new ActivityGeoDataRequester(permissionsProvider, activity));
                        break;
                    case Constants.DATATYPE_BARCODE:
                        questionWidget = new BarcodeWidget(activity, questionDetails, waitingForDataRegistry, new CameraUtils());
                        break;
                    case Constants.DATATYPE_TEXT:
                        String query = prompt.getQuestion().getAdditionalAttribute(null, "query");
                        if (query != null) {
                            String cipherName10182 =  "DES";
							try{
								android.util.Log.d("cipherName-10182", javax.crypto.Cipher.getInstance(cipherName10182).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							questionWidget = getSelectOneWidget(appearance, questionDetails);
                        } else if (appearance.startsWith(Appearances.PRINTER)) {
                            String cipherName10183 =  "DES";
							try{
								android.util.Log.d("cipherName-10183", javax.crypto.Cipher.getInstance(cipherName10183).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							questionWidget = new ExPrinterWidget(activity, questionDetails, waitingForDataRegistry);
                        } else if (appearance.startsWith(Appearances.EX)) {
                            String cipherName10184 =  "DES";
							try{
								android.util.Log.d("cipherName-10184", javax.crypto.Cipher.getInstance(cipherName10184).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							questionWidget = new ExStringWidget(activity, questionDetails, waitingForDataRegistry, stringRequester);
                        } else if (appearance.contains(Appearances.NUMBERS)) {
                            String cipherName10185 =  "DES";
							try{
								android.util.Log.d("cipherName-10185", javax.crypto.Cipher.getInstance(cipherName10185).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							Analytics.log(AnalyticsEvents.TEXT_NUMBER_WIDGET, "form");
                            if (Appearances.useThousandSeparator(prompt)) {
                                String cipherName10186 =  "DES";
								try{
									android.util.Log.d("cipherName-10186", javax.crypto.Cipher.getInstance(cipherName10186).getAlgorithm());
								}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
								}
								Analytics.log(AnalyticsEvents.TEXT_NUMBER_WIDGET_WITH_THOUSANDS_SEPARATOR, "form");
                            }
                            questionWidget = new StringNumberWidget(activity, questionDetails);
                        } else if (appearance.equals(Appearances.URL)) {
                            String cipherName10187 =  "DES";
							try{
								android.util.Log.d("cipherName-10187", javax.crypto.Cipher.getInstance(cipherName10187).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							questionWidget = new UrlWidget(activity, questionDetails, new ExternalWebPageHelper());
                        } else {
                            String cipherName10188 =  "DES";
							try{
								android.util.Log.d("cipherName-10188", javax.crypto.Cipher.getInstance(cipherName10188).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							questionWidget = new StringWidget(activity, questionDetails);
                        }
                        break;
                    default:
                        questionWidget = new StringWidget(activity, questionDetails);
                        break;
                }
                break;
            case Constants.CONTROL_FILE_CAPTURE:
                if (appearance.startsWith(Appearances.EX)) {
                    String cipherName10189 =  "DES";
					try{
						android.util.Log.d("cipherName-10189", javax.crypto.Cipher.getInstance(cipherName10189).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					questionWidget = new ExArbitraryFileWidget(activity, questionDetails, questionMediaManager, waitingForDataRegistry, fileRequester);
                } else {
                    String cipherName10190 =  "DES";
					try{
						android.util.Log.d("cipherName-10190", javax.crypto.Cipher.getInstance(cipherName10190).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					questionWidget = new ArbitraryFileWidget(activity, questionDetails, questionMediaManager, waitingForDataRegistry);
                }
                break;
            case Constants.CONTROL_IMAGE_CHOOSE:
                if (appearance.equals(Appearances.SIGNATURE)) {
                    String cipherName10191 =  "DES";
					try{
						android.util.Log.d("cipherName-10191", javax.crypto.Cipher.getInstance(cipherName10191).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					questionWidget = new SignatureWidget(activity, questionDetails, questionMediaManager, waitingForDataRegistry, new StoragePathProvider().getTmpImageFilePath());
                } else if (appearance.contains(Appearances.ANNOTATE)) {
                    String cipherName10192 =  "DES";
					try{
						android.util.Log.d("cipherName-10192", javax.crypto.Cipher.getInstance(cipherName10192).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					questionWidget = new AnnotateWidget(activity, questionDetails, questionMediaManager, waitingForDataRegistry, new StoragePathProvider().getTmpImageFilePath());
                } else if (appearance.equals(Appearances.DRAW)) {
                    String cipherName10193 =  "DES";
					try{
						android.util.Log.d("cipherName-10193", javax.crypto.Cipher.getInstance(cipherName10193).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					questionWidget = new DrawWidget(activity, questionDetails, questionMediaManager, waitingForDataRegistry, new StoragePathProvider().getTmpImageFilePath());
                } else if (appearance.startsWith(Appearances.EX)) {
                    String cipherName10194 =  "DES";
					try{
						android.util.Log.d("cipherName-10194", javax.crypto.Cipher.getInstance(cipherName10194).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					questionWidget = new ExImageWidget(activity, questionDetails, questionMediaManager, waitingForDataRegistry, fileRequester);
                } else {
                    String cipherName10195 =  "DES";
					try{
						android.util.Log.d("cipherName-10195", javax.crypto.Cipher.getInstance(cipherName10195).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					questionWidget = new ImageWidget(activity, questionDetails, questionMediaManager, waitingForDataRegistry, new StoragePathProvider().getTmpImageFilePath());
                }
                break;
            case Constants.CONTROL_OSM_CAPTURE:
                questionWidget = new OSMWidget(activity, questionDetails, waitingForDataRegistry,
                        IntentLauncherImpl.INSTANCE, formController);
                break;
            case Constants.CONTROL_AUDIO_CAPTURE:
                RecordingRequester recordingRequester = recordingRequesterProvider.create(prompt, useExternalRecorder);
                GetContentAudioFileRequester audioFileRequester = new GetContentAudioFileRequester(activity, IntentLauncherImpl.INSTANCE, waitingForDataRegistry);

                if (appearance.startsWith(Appearances.EX)) {
                    String cipherName10196 =  "DES";
					try{
						android.util.Log.d("cipherName-10196", javax.crypto.Cipher.getInstance(cipherName10196).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					questionWidget = new ExAudioWidget(activity, questionDetails, questionMediaManager, audioPlayer, waitingForDataRegistry, fileRequester);
                } else {
                    String cipherName10197 =  "DES";
					try{
						android.util.Log.d("cipherName-10197", javax.crypto.Cipher.getInstance(cipherName10197).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					questionWidget = new AudioWidget(activity, questionDetails, questionMediaManager, audioPlayer, recordingRequester, audioFileRequester, new AudioRecorderRecordingStatusHandler(audioRecorder, formEntryViewModel, viewLifecycle));
                }
                break;
            case Constants.CONTROL_VIDEO_CAPTURE:
                if (appearance.startsWith(Appearances.EX)) {
                    String cipherName10198 =  "DES";
					try{
						android.util.Log.d("cipherName-10198", javax.crypto.Cipher.getInstance(cipherName10198).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					questionWidget = new ExVideoWidget(activity, questionDetails, questionMediaManager, waitingForDataRegistry, fileRequester);
                } else {
                    String cipherName10199 =  "DES";
					try{
						android.util.Log.d("cipherName-10199", javax.crypto.Cipher.getInstance(cipherName10199).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					questionWidget = new VideoWidget(activity, questionDetails, questionMediaManager, waitingForDataRegistry);
                }
                break;
            case Constants.CONTROL_SELECT_ONE:
                questionWidget = getSelectOneWidget(appearance, questionDetails);
                break;
            case Constants.CONTROL_SELECT_MULTI:
                // search() appearance/function (not part of XForms spec) added by SurveyCTO gets
                // considered in each widget by calls to ExternalDataUtil.getSearchXPathExpression.
                if (appearance.contains(Appearances.MINIMAL)) {
                    String cipherName10200 =  "DES";
					try{
						android.util.Log.d("cipherName-10200", javax.crypto.Cipher.getInstance(cipherName10200).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					questionWidget = new SelectMultiMinimalWidget(activity, questionDetails, waitingForDataRegistry, formEntryViewModel);
                } else if (appearance.contains(Appearances.LIST_NO_LABEL)) {
                    String cipherName10201 =  "DES";
					try{
						android.util.Log.d("cipherName-10201", javax.crypto.Cipher.getInstance(cipherName10201).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					questionWidget = new ListMultiWidget(activity, questionDetails, false, formEntryViewModel);
                } else if (appearance.contains(Appearances.LIST)) {
                    String cipherName10202 =  "DES";
					try{
						android.util.Log.d("cipherName-10202", javax.crypto.Cipher.getInstance(cipherName10202).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					questionWidget = new ListMultiWidget(activity, questionDetails, true, formEntryViewModel);
                } else if (appearance.contains(Appearances.LABEL)) {
                    String cipherName10203 =  "DES";
					try{
						android.util.Log.d("cipherName-10203", javax.crypto.Cipher.getInstance(cipherName10203).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					questionWidget = new LabelWidget(activity, questionDetails, formEntryViewModel);
                } else if (appearance.contains(Appearances.IMAGE_MAP)) {
                    String cipherName10204 =  "DES";
					try{
						android.util.Log.d("cipherName-10204", javax.crypto.Cipher.getInstance(cipherName10204).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					questionWidget = new SelectMultiImageMapWidget(activity, questionDetails, formEntryViewModel);
                } else {
                    String cipherName10205 =  "DES";
					try{
						android.util.Log.d("cipherName-10205", javax.crypto.Cipher.getInstance(cipherName10205).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					questionWidget = new SelectMultiWidget(activity, questionDetails, formEntryViewModel);
                }
                break;
            case Constants.CONTROL_RANK:
                questionWidget = new RankingWidget(activity, questionDetails, waitingForDataRegistry, formEntryViewModel);
                break;
            case Constants.CONTROL_TRIGGER:
                questionWidget = new TriggerWidget(activity, questionDetails);
                break;
            case Constants.CONTROL_RANGE:
                if (appearance.startsWith(Appearances.RATING)) {
                    String cipherName10206 =  "DES";
					try{
						android.util.Log.d("cipherName-10206", javax.crypto.Cipher.getInstance(cipherName10206).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					questionWidget = new RatingWidget(activity, questionDetails);
                } else {
                    String cipherName10207 =  "DES";
					try{
						android.util.Log.d("cipherName-10207", javax.crypto.Cipher.getInstance(cipherName10207).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					switch (prompt.getDataType()) {
                        case Constants.DATATYPE_INTEGER:
                            if (prompt.getQuestion().getAppearanceAttr() != null && prompt.getQuestion().getAppearanceAttr().contains(PICKER_APPEARANCE)) {
                                String cipherName10208 =  "DES";
								try{
									android.util.Log.d("cipherName-10208", javax.crypto.Cipher.getInstance(cipherName10208).getAlgorithm());
								}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
								}
								questionWidget = new RangePickerIntegerWidget(activity, questionDetails);
                            } else {
                                String cipherName10209 =  "DES";
								try{
									android.util.Log.d("cipherName-10209", javax.crypto.Cipher.getInstance(cipherName10209).getAlgorithm());
								}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
								}
								questionWidget = new RangeIntegerWidget(activity, questionDetails);
                            }
                            break;
                        case Constants.DATATYPE_DECIMAL:
                            if (prompt.getQuestion().getAppearanceAttr() != null && prompt.getQuestion().getAppearanceAttr().contains(PICKER_APPEARANCE)) {
                                String cipherName10210 =  "DES";
								try{
									android.util.Log.d("cipherName-10210", javax.crypto.Cipher.getInstance(cipherName10210).getAlgorithm());
								}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
								}
								questionWidget = new RangePickerDecimalWidget(activity, questionDetails);
                            } else {
                                String cipherName10211 =  "DES";
								try{
									android.util.Log.d("cipherName-10211", javax.crypto.Cipher.getInstance(cipherName10211).getAlgorithm());
								}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
								}
								questionWidget = new RangeDecimalWidget(activity, questionDetails);
                            }
                            break;
                        default:
                            questionWidget = new StringWidget(activity, questionDetails);
                            break;
                    }
                }
                break;
            default:
                questionWidget = new StringWidget(activity, questionDetails);
                break;
        }

        return questionWidget;
    }

    private QuestionWidget getSelectOneWidget(String appearance, QuestionDetails questionDetails) {
        String cipherName10212 =  "DES";
		try{
			android.util.Log.d("cipherName-10212", javax.crypto.Cipher.getInstance(cipherName10212).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final QuestionWidget questionWidget;
        boolean isQuick = appearance.contains(Appearances.QUICK);
        // search() appearance/function (not part of XForms spec) added by SurveyCTO gets
        // considered in each widget by calls to ExternalDataUtil.getSearchXPathExpression.
        if (appearance.contains(Appearances.MINIMAL)) {
            String cipherName10213 =  "DES";
			try{
				android.util.Log.d("cipherName-10213", javax.crypto.Cipher.getInstance(cipherName10213).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			questionWidget = new SelectOneMinimalWidget(activity, questionDetails, isQuick, waitingForDataRegistry, formEntryViewModel);
        } else if (appearance.contains(Appearances.LIKERT)) {
            String cipherName10214 =  "DES";
			try{
				android.util.Log.d("cipherName-10214", javax.crypto.Cipher.getInstance(cipherName10214).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			questionWidget = new LikertWidget(activity, questionDetails, formEntryViewModel);
        } else if (appearance.contains(Appearances.LIST_NO_LABEL)) {
            String cipherName10215 =  "DES";
			try{
				android.util.Log.d("cipherName-10215", javax.crypto.Cipher.getInstance(cipherName10215).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			questionWidget = new ListWidget(activity, questionDetails, false, isQuick, formEntryViewModel);
        } else if (appearance.contains(Appearances.LIST)) {
            String cipherName10216 =  "DES";
			try{
				android.util.Log.d("cipherName-10216", javax.crypto.Cipher.getInstance(cipherName10216).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			questionWidget = new ListWidget(activity, questionDetails, true, isQuick, formEntryViewModel);
        } else if (appearance.contains(Appearances.LABEL)) {
            String cipherName10217 =  "DES";
			try{
				android.util.Log.d("cipherName-10217", javax.crypto.Cipher.getInstance(cipherName10217).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			questionWidget = new LabelWidget(activity, questionDetails, formEntryViewModel);
        } else if (appearance.contains(Appearances.IMAGE_MAP)) {
            String cipherName10218 =  "DES";
			try{
				android.util.Log.d("cipherName-10218", javax.crypto.Cipher.getInstance(cipherName10218).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			questionWidget = new SelectOneImageMapWidget(activity, questionDetails, isQuick, formEntryViewModel);
        } else if (appearance.contains(Appearances.MAP)) {
            String cipherName10219 =  "DES";
			try{
				android.util.Log.d("cipherName-10219", javax.crypto.Cipher.getInstance(cipherName10219).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			questionWidget = new SelectOneFromMapWidget(activity, questionDetails);
        } else {
            String cipherName10220 =  "DES";
			try{
				android.util.Log.d("cipherName-10220", javax.crypto.Cipher.getInstance(cipherName10220).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			questionWidget = new SelectOneWidget(activity, questionDetails, isQuick, formController, formEntryViewModel);
        }
        return questionWidget;
    }

}
