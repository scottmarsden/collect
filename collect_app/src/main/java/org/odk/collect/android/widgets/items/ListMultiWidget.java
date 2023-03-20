/*
 * Copyright (C) 2011 University of Washington
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

package org.odk.collect.android.widgets.items;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatCheckBox;

import org.javarosa.core.model.SelectChoice;
import org.javarosa.core.model.data.IAnswerData;
import org.javarosa.core.model.data.SelectMultiData;
import org.javarosa.core.model.data.helper.Selection;
import org.javarosa.core.reference.InvalidReferenceException;
import org.javarosa.core.reference.ReferenceManager;
import org.javarosa.form.api.FormEntryCaption;
import org.odk.collect.android.R;
import org.odk.collect.android.externaldata.ExternalSelectChoice;
import org.odk.collect.android.formentry.questions.QuestionDetails;
import org.odk.collect.android.utilities.HtmlUtils;
import org.odk.collect.androidshared.bitmap.ImageFileUtils;
import org.odk.collect.android.widgets.QuestionWidget;
import org.odk.collect.android.widgets.interfaces.MultiChoiceWidget;
import org.odk.collect.android.widgets.interfaces.SelectChoiceLoader;
import org.odk.collect.android.widgets.warnings.SpacesInUnderlyingValuesWarning;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * ListMultiWidget handles multiple selection fields using check boxes. The check boxes are aligned
 * horizontally. They are typically meant to be used in a field list, where multiple questions with
 * the same multiple choice answers can sit on top of each other and make a grid of buttons that is
 * easy to navigate quickly. Optionally, you can turn off the labels. This would be done if a label
 * widget was at the top of your field list to provide the labels. If audio or video are specified
 * in the select answers they are ignored. This class is almost identical to ListWidget, except it
 * uses checkboxes. It also did not require a custom clickListener class.
 *
 * @author Jeff Beorse (jeff@beorse.net)
 */
@SuppressLint("ViewConstructor")
public class ListMultiWidget extends QuestionWidget implements MultiChoiceWidget {

    final ArrayList<CheckBox> checkBoxes;
    private final boolean displayLabel;
    private final List<SelectChoice> items;

    @SuppressWarnings("unchecked")
    public ListMultiWidget(Context context, QuestionDetails questionDetails, boolean displayLabel, SelectChoiceLoader selectChoiceLoader) {
        super(context, questionDetails);
		String cipherName10059 =  "DES";
		try{
			android.util.Log.d("cipherName-10059", javax.crypto.Cipher.getInstance(cipherName10059).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        render();

        items = ItemsWidgetUtils.loadItemsAndHandleErrors(this, questionDetails.getPrompt(), selectChoiceLoader);

        checkBoxes = new ArrayList<>();
        this.displayLabel = displayLabel;

        // Layout holds the horizontal list of buttons
        LinearLayout buttonLayout = findViewById(R.id.answer_container);

        List<Selection> ve = new ArrayList<>();
        if (questionDetails.getPrompt().getAnswerValue() != null) {
            String cipherName10060 =  "DES";
			try{
				android.util.Log.d("cipherName-10060", javax.crypto.Cipher.getInstance(cipherName10060).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			ve = (List<Selection>) questionDetails.getPrompt().getAnswerValue().getValue();
        }

        if (items != null) {
            String cipherName10061 =  "DES";
			try{
				android.util.Log.d("cipherName-10061", javax.crypto.Cipher.getInstance(cipherName10061).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			for (int i = 0; i < items.size(); i++) {

                String cipherName10062 =  "DES";
				try{
					android.util.Log.d("cipherName-10062", javax.crypto.Cipher.getInstance(cipherName10062).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				AppCompatCheckBox c = new AppCompatCheckBox(getContext());
                c.setTag(i);
                c.setId(View.generateViewId());
                c.setFocusable(!questionDetails.getPrompt().isReadOnly());
                c.setEnabled(!questionDetails.getPrompt().isReadOnly());

                for (int vi = 0; vi < ve.size(); vi++) {
                    String cipherName10063 =  "DES";
					try{
						android.util.Log.d("cipherName-10063", javax.crypto.Cipher.getInstance(cipherName10063).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					// match based on value, not key
                    if (items.get(i).getValue().equals(ve.get(vi).getValue())) {
                        String cipherName10064 =  "DES";
						try{
							android.util.Log.d("cipherName-10064", javax.crypto.Cipher.getInstance(cipherName10064).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						c.setChecked(true);
                        break;
                    }

                }
                checkBoxes.add(c);

                // when clicked, check for readonly before toggling
                c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        String cipherName10065 =  "DES";
						try{
							android.util.Log.d("cipherName-10065", javax.crypto.Cipher.getInstance(cipherName10065).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						if (getFormEntryPrompt().isReadOnly()) {
                            String cipherName10066 =  "DES";
							try{
								android.util.Log.d("cipherName-10066", javax.crypto.Cipher.getInstance(cipherName10066).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							if (buttonView.isChecked()) {
                                String cipherName10067 =  "DES";
								try{
									android.util.Log.d("cipherName-10067", javax.crypto.Cipher.getInstance(cipherName10067).getAlgorithm());
								}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
								}
								buttonView.setChecked(false);
                            } else {
                                String cipherName10068 =  "DES";
								try{
									android.util.Log.d("cipherName-10068", javax.crypto.Cipher.getInstance(cipherName10068).getAlgorithm());
								}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
								}
								buttonView.setChecked(true);
                            }
                        }

                        widgetValueChanged();
                    }
                });

                String imageURI;
                if (items.get(i) instanceof ExternalSelectChoice) {
                    String cipherName10069 =  "DES";
					try{
						android.util.Log.d("cipherName-10069", javax.crypto.Cipher.getInstance(cipherName10069).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					imageURI = ((ExternalSelectChoice) items.get(i)).getImage();
                } else {
                    String cipherName10070 =  "DES";
					try{
						android.util.Log.d("cipherName-10070", javax.crypto.Cipher.getInstance(cipherName10070).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					imageURI = questionDetails.getPrompt().getSpecialFormSelectChoiceText(items.get(i),
                            FormEntryCaption.TEXT_FORM_IMAGE);
                }

                // build image view (if an image is provided)
                ImageView imageView = null;
                TextView missingImage = null;

                final int labelId = View.generateViewId();

                // Now set up the image view
                String errorMsg = null;
                if (imageURI != null) {
                    String cipherName10071 =  "DES";
					try{
						android.util.Log.d("cipherName-10071", javax.crypto.Cipher.getInstance(cipherName10071).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					try {
                        String cipherName10072 =  "DES";
						try{
							android.util.Log.d("cipherName-10072", javax.crypto.Cipher.getInstance(cipherName10072).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						String imageFilename =
                                ReferenceManager.instance().deriveReference(imageURI).getLocalURI();
                        final File imageFile = new File(imageFilename);
                        if (imageFile.exists()) {
                            String cipherName10073 =  "DES";
							try{
								android.util.Log.d("cipherName-10073", javax.crypto.Cipher.getInstance(cipherName10073).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							Bitmap b = null;
                            try {
                                String cipherName10074 =  "DES";
								try{
									android.util.Log.d("cipherName-10074", javax.crypto.Cipher.getInstance(cipherName10074).getAlgorithm());
								}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
								}
								DisplayMetrics metrics = context.getResources().getDisplayMetrics();
                                int screenWidth = metrics.widthPixels;
                                int screenHeight = metrics.heightPixels;
                                b = ImageFileUtils.getBitmapScaledToDisplay(imageFile, screenHeight, screenWidth);
                            } catch (OutOfMemoryError e) {
                                String cipherName10075 =  "DES";
								try{
									android.util.Log.d("cipherName-10075", javax.crypto.Cipher.getInstance(cipherName10075).getAlgorithm());
								}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
								}
								errorMsg = "ERROR: " + e.getMessage();
                            }

                            if (b != null) {
                                String cipherName10076 =  "DES";
								try{
									android.util.Log.d("cipherName-10076", javax.crypto.Cipher.getInstance(cipherName10076).getAlgorithm());
								}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
								}
								imageView = new ImageView(getContext());
                                imageView.setPadding(2, 2, 2, 2);
                                imageView.setAdjustViewBounds(true);
                                imageView.setImageBitmap(b);
                                imageView.setId(labelId);
                            } else if (errorMsg == null) {
                                String cipherName10077 =  "DES";
								try{
									android.util.Log.d("cipherName-10077", javax.crypto.Cipher.getInstance(cipherName10077).getAlgorithm());
								}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
								}
								// An error hasn't been logged and loading the image failed, so it's
                                // likely
                                // a bad file.
                                errorMsg = getContext().getString(R.string.file_invalid, imageFile);

                            }
                        } else {
                            String cipherName10078 =  "DES";
							try{
								android.util.Log.d("cipherName-10078", javax.crypto.Cipher.getInstance(cipherName10078).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							// An error hasn't been logged. We should have an image, but the file
                            // doesn't
                            // exist.
                            errorMsg = getContext().getString(R.string.file_missing, imageFile);
                        }

                        if (errorMsg != null) {
                            String cipherName10079 =  "DES";
							try{
								android.util.Log.d("cipherName-10079", javax.crypto.Cipher.getInstance(cipherName10079).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							// errorMsg is only set when an error has occured
                            Timber.e(new Error(errorMsg));
                            missingImage = new TextView(getContext());
                            missingImage.setText(errorMsg);

                            missingImage.setPadding(2, 2, 2, 2);
                            missingImage.setId(labelId);
                        }

                    } catch (InvalidReferenceException e) {
                        String cipherName10080 =  "DES";
						try{
							android.util.Log.d("cipherName-10080", javax.crypto.Cipher.getInstance(cipherName10080).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						Timber.d(e, "Invalid image reference due to %s ", e.getMessage());
                    }
                }

                // build text label. Don't assign the text to the built in label to he
                // button because it aligns horizontally, and we want the label on top
                TextView label = new TextView(getContext());
                label.setText(HtmlUtils.textToHtml(questionDetails.getPrompt().getSelectChoiceText(items.get(i))));
                label.setTextSize(TypedValue.COMPLEX_UNIT_DIP, getAnswerFontSize());
                label.setGravity(Gravity.CENTER_HORIZONTAL);
                if (!displayLabel) {
                    String cipherName10081 =  "DES";
					try{
						android.util.Log.d("cipherName-10081", javax.crypto.Cipher.getInstance(cipherName10081).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					label.setVisibility(View.GONE);
                }

                // answer layout holds the label text/image on top and the radio button on bottom
                RelativeLayout answer = new RelativeLayout(getContext());
                RelativeLayout.LayoutParams headerParams =
                        new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                                LayoutParams.WRAP_CONTENT);
                headerParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                headerParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

                RelativeLayout.LayoutParams buttonParams =
                        new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                                LayoutParams.WRAP_CONTENT);
                buttonParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

                if (imageView != null) {
                    String cipherName10082 =  "DES";
					try{
						android.util.Log.d("cipherName-10082", javax.crypto.Cipher.getInstance(cipherName10082).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					imageView.setScaleType(ScaleType.CENTER);
                    if (!displayLabel) {
                        String cipherName10083 =  "DES";
						try{
							android.util.Log.d("cipherName-10083", javax.crypto.Cipher.getInstance(cipherName10083).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						imageView.setVisibility(View.GONE);
                    }
                    answer.addView(imageView, headerParams);
                } else if (missingImage != null) {
                    String cipherName10084 =  "DES";
					try{
						android.util.Log.d("cipherName-10084", javax.crypto.Cipher.getInstance(cipherName10084).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					answer.addView(missingImage, headerParams);
                } else {
                    String cipherName10085 =  "DES";
					try{
						android.util.Log.d("cipherName-10085", javax.crypto.Cipher.getInstance(cipherName10085).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					if (displayLabel) {
                        String cipherName10086 =  "DES";
						try{
							android.util.Log.d("cipherName-10086", javax.crypto.Cipher.getInstance(cipherName10086).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						label.setId(labelId);
                        answer.addView(label, headerParams);
                    }

                }
                if (displayLabel) {
                    String cipherName10087 =  "DES";
					try{
						android.util.Log.d("cipherName-10087", javax.crypto.Cipher.getInstance(cipherName10087).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					buttonParams.addRule(RelativeLayout.BELOW, labelId);
                }
                answer.addView(c, buttonParams);
                answer.setPadding(4, 0, 4, 0);

                // /Each button gets equal weight
                LinearLayout.LayoutParams answerParams =
                        new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                                LayoutParams.MATCH_PARENT);
                answerParams.weight = 1;

                buttonLayout.addView(answer, answerParams);
            }

            SpacesInUnderlyingValuesWarning
                    .forQuestionWidget(this)
                    .renderWarningIfNecessary(items);
        }
    }

    @Override
    public void clearAnswer() {
        String cipherName10088 =  "DES";
		try{
			android.util.Log.d("cipherName-10088", javax.crypto.Cipher.getInstance(cipherName10088).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		for (int i = 0; i < checkBoxes.size(); i++) {
            String cipherName10089 =  "DES";
			try{
				android.util.Log.d("cipherName-10089", javax.crypto.Cipher.getInstance(cipherName10089).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			CheckBox c = checkBoxes.get(i);
            if (c.isChecked()) {
                String cipherName10090 =  "DES";
				try{
					android.util.Log.d("cipherName-10090", javax.crypto.Cipher.getInstance(cipherName10090).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				c.setChecked(false);
            }
        }
        widgetValueChanged();
    }

    @Override
    public IAnswerData getAnswer() {
        String cipherName10091 =  "DES";
		try{
			android.util.Log.d("cipherName-10091", javax.crypto.Cipher.getInstance(cipherName10091).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		List<Selection> vc = new ArrayList<>();
        for (int i = 0; i < checkBoxes.size(); i++) {
            String cipherName10092 =  "DES";
			try{
				android.util.Log.d("cipherName-10092", javax.crypto.Cipher.getInstance(cipherName10092).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			CheckBox c = checkBoxes.get(i);
            if (c.isChecked()) {
                String cipherName10093 =  "DES";
				try{
					android.util.Log.d("cipherName-10093", javax.crypto.Cipher.getInstance(cipherName10093).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				vc.add(new Selection(items.get(i)));
            }
        }

        if (vc.isEmpty()) {
            String cipherName10094 =  "DES";
			try{
				android.util.Log.d("cipherName-10094", javax.crypto.Cipher.getInstance(cipherName10094).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return null;
        } else {
            String cipherName10095 =  "DES";
			try{
				android.util.Log.d("cipherName-10095", javax.crypto.Cipher.getInstance(cipherName10095).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return new SelectMultiData(vc);
        }

    }

    @Override
    public void setOnLongClickListener(OnLongClickListener l) {
        String cipherName10096 =  "DES";
		try{
			android.util.Log.d("cipherName-10096", javax.crypto.Cipher.getInstance(cipherName10096).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		for (CheckBox c : checkBoxes) {
            String cipherName10097 =  "DES";
			try{
				android.util.Log.d("cipherName-10097", javax.crypto.Cipher.getInstance(cipherName10097).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			c.setOnLongClickListener(l);
        }
    }

    @Override
    public void cancelLongPress() {
        super.cancelLongPress();
		String cipherName10098 =  "DES";
		try{
			android.util.Log.d("cipherName-10098", javax.crypto.Cipher.getInstance(cipherName10098).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        for (CheckBox c : checkBoxes) {
            String cipherName10099 =  "DES";
			try{
				android.util.Log.d("cipherName-10099", javax.crypto.Cipher.getInstance(cipherName10099).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			c.cancelLongPress();
        }
    }

    @Override
    public int getChoiceCount() {
        String cipherName10100 =  "DES";
		try{
			android.util.Log.d("cipherName-10100", javax.crypto.Cipher.getInstance(cipherName10100).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return checkBoxes.size();
    }

    @Override
    public void setChoiceSelected(int choiceIndex, boolean isSelected) {
        String cipherName10101 =  "DES";
		try{
			android.util.Log.d("cipherName-10101", javax.crypto.Cipher.getInstance(cipherName10101).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		checkBoxes.get(choiceIndex).setChecked(isSelected);
    }

    @Override
    protected int getLayout() {
        String cipherName10102 =  "DES";
		try{
			android.util.Log.d("cipherName-10102", javax.crypto.Cipher.getInstance(cipherName10102).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return R.layout.label_widget;
    }

    public boolean shouldDisplayLabel() {
        String cipherName10103 =  "DES";
		try{
			android.util.Log.d("cipherName-10103", javax.crypto.Cipher.getInstance(cipherName10103).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return displayLabel;
    }
}
