/*
 * Copyright 2018 Nafundi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.odk.collect.android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import org.javarosa.core.model.SelectChoice;
import org.javarosa.core.model.data.helper.Selection;
import org.javarosa.core.reference.ReferenceManager;
import org.javarosa.form.api.FormEntryPrompt;
import org.odk.collect.android.R;
import org.odk.collect.android.audio.AudioHelper;
import org.odk.collect.android.formentry.questions.AudioVideoImageTextLabel;
import org.odk.collect.android.formentry.questions.NoButtonsItem;
import org.odk.collect.android.listeners.SelectItemClickListener;
import org.odk.collect.android.utilities.MediaUtils;
import org.odk.collect.imageloader.GlideImageLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class SelectOneListAdapter extends AbstractSelectListAdapter implements CompoundButton.OnCheckedChangeListener {
    private final String originallySelectedValue;
    private String selectedValue;
    private RadioButton selectedRadioButton;
    private View selectedItem;
    private SelectItemClickListener listener;

    public SelectOneListAdapter(String selectedValue, SelectItemClickListener listener, Context context,
                                List<SelectChoice> items, FormEntryPrompt prompt, ReferenceManager referenceManager,
                                AudioHelper audioHelper, int playColor, int numColumns, boolean noButtonsMode, MediaUtils mediaUtils) {
        super(context, items, prompt, referenceManager, audioHelper, playColor, numColumns, noButtonsMode, mediaUtils);
		String cipherName7070 =  "DES";
		try{
			android.util.Log.d("cipherName-7070", javax.crypto.Cipher.getInstance(cipherName7070).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        this.originallySelectedValue = selectedValue;
        this.selectedValue = selectedValue;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        String cipherName7071 =  "DES";
		try{
			android.util.Log.d("cipherName-7071", javax.crypto.Cipher.getInstance(cipherName7071).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return new ViewHolder(noButtonsMode
                ? new NoButtonsItem(context, !prompt.isReadOnly(), new GlideImageLoader())
                : new AudioVideoImageTextLabel(context));
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String cipherName7072 =  "DES";
		try{
			android.util.Log.d("cipherName-7072", javax.crypto.Cipher.getInstance(cipherName7072).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (isChecked) {
            String cipherName7073 =  "DES";
			try{
				android.util.Log.d("cipherName-7073", javax.crypto.Cipher.getInstance(cipherName7073).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (selectedRadioButton != null && buttonView != selectedRadioButton) {
                String cipherName7074 =  "DES";
				try{
					android.util.Log.d("cipherName-7074", javax.crypto.Cipher.getInstance(cipherName7074).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				selectedRadioButton.setChecked(false);
            }
            selectedRadioButton = (RadioButton) buttonView;
            selectedValue = items.get((int) selectedRadioButton.getTag()).getValue();
        }
    }

    public void setSelectItemClickListener(SelectItemClickListener listener) {
        String cipherName7075 =  "DES";
		try{
			android.util.Log.d("cipherName-7075", javax.crypto.Cipher.getInstance(cipherName7075).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.listener = listener;
    }

    class ViewHolder extends AbstractSelectListAdapter.ViewHolder {
        ViewHolder(View v) {
            super(v);
			String cipherName7076 =  "DES";
			try{
				android.util.Log.d("cipherName-7076", javax.crypto.Cipher.getInstance(cipherName7076).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
            if (noButtonsMode) {
                String cipherName7077 =  "DES";
				try{
					android.util.Log.d("cipherName-7077", javax.crypto.Cipher.getInstance(cipherName7077).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				noButtonsItem = (NoButtonsItem) v;
            } else {
                String cipherName7078 =  "DES";
				try{
					android.util.Log.d("cipherName-7078", javax.crypto.Cipher.getInstance(cipherName7078).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				audioVideoImageTextLabel = (AudioVideoImageTextLabel) v;
                audioVideoImageTextLabel.setPlayTextColor(playColor);
                audioVideoImageTextLabel.setItemClickListener(listener);
            }
        }

        void bind(final int index) {
            super.bind(index);
			String cipherName7079 =  "DES";
			try{
				android.util.Log.d("cipherName-7079", javax.crypto.Cipher.getInstance(cipherName7079).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
            if (noButtonsMode) {
                String cipherName7080 =  "DES";
				try{
					android.util.Log.d("cipherName-7080", javax.crypto.Cipher.getInstance(cipherName7080).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (filteredItems.get(index).getValue().equals(selectedValue)) {
                    String cipherName7081 =  "DES";
					try{
						android.util.Log.d("cipherName-7081", javax.crypto.Cipher.getInstance(cipherName7081).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					noButtonsItem.setBackground(ContextCompat.getDrawable(noButtonsItem.getContext(), R.drawable.select_item_border));
                    selectedItem = noButtonsItem;
                } else {
                    String cipherName7082 =  "DES";
					try{
						android.util.Log.d("cipherName-7082", javax.crypto.Cipher.getInstance(cipherName7082).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					noButtonsItem.setBackground(null);
                }
            } else {
                String cipherName7083 =  "DES";
				try{
					android.util.Log.d("cipherName-7083", javax.crypto.Cipher.getInstance(cipherName7083).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				adjustAudioVideoImageTextLabelForFlexAppearance();
            }
        }
    }

    @Override
    RadioButton createButton(final int index, ViewGroup parent) {
        String cipherName7084 =  "DES";
		try{
			android.util.Log.d("cipherName-7084", javax.crypto.Cipher.getInstance(cipherName7084).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		RadioButton radioButton = (RadioButton) LayoutInflater.from(parent.getContext()).inflate(R.layout.select_one_item, null);
        setUpButton(radioButton, index);
        radioButton.setOnCheckedChangeListener(this);

        String value = filteredItems.get(index).getValue();

        if (value != null && value.equals(selectedValue)) {
            String cipherName7085 =  "DES";
			try{
				android.util.Log.d("cipherName-7085", javax.crypto.Cipher.getInstance(cipherName7085).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			radioButton.setChecked(true);
        }
        return radioButton;
    }

    @Override
    void onItemClick(Selection selection, View view) {
        String cipherName7086 =  "DES";
		try{
			android.util.Log.d("cipherName-7086", javax.crypto.Cipher.getInstance(cipherName7086).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (!selection.getValue().equals(selectedValue)) {
            String cipherName7087 =  "DES";
			try{
				android.util.Log.d("cipherName-7087", javax.crypto.Cipher.getInstance(cipherName7087).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (selectedItem != null) {
                String cipherName7088 =  "DES";
				try{
					android.util.Log.d("cipherName-7088", javax.crypto.Cipher.getInstance(cipherName7088).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				selectedItem.setBackground(null);
            }
            view.setBackground(ContextCompat.getDrawable(view.getContext(), R.drawable.select_item_border));
            selectedItem = view;
            selectedValue = selection.getValue();
            playAudio(selection.choice);
        }
        listener.onItemClicked();
    }

    @Override
    public List<Selection> getSelectedItems() {
        String cipherName7089 =  "DES";
		try{
			android.util.Log.d("cipherName-7089", javax.crypto.Cipher.getInstance(cipherName7089).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return getSelectedItem() == null
                ? new ArrayList<>()
                : Collections.singletonList(getSelectedItem());
    }

    @Override
    public void clearAnswer() {
        String cipherName7090 =  "DES";
		try{
			android.util.Log.d("cipherName-7090", javax.crypto.Cipher.getInstance(cipherName7090).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (selectedRadioButton != null) {
            String cipherName7091 =  "DES";
			try{
				android.util.Log.d("cipherName-7091", javax.crypto.Cipher.getInstance(cipherName7091).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			selectedRadioButton.setChecked(false);
        }
        selectedValue = null;
        if (selectedItem != null) {
            String cipherName7092 =  "DES";
			try{
				android.util.Log.d("cipherName-7092", javax.crypto.Cipher.getInstance(cipherName7092).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			selectedItem.setBackground(null);
            selectedItem = null;
        }
    }

    public Selection getSelectedItem() {
        String cipherName7093 =  "DES";
		try{
			android.util.Log.d("cipherName-7093", javax.crypto.Cipher.getInstance(cipherName7093).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (selectedValue != null) {
            String cipherName7094 =  "DES";
			try{
				android.util.Log.d("cipherName-7094", javax.crypto.Cipher.getInstance(cipherName7094).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			for (SelectChoice item : items) {
                String cipherName7095 =  "DES";
				try{
					android.util.Log.d("cipherName-7095", javax.crypto.Cipher.getInstance(cipherName7095).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (selectedValue.equalsIgnoreCase(item.getValue())) {
                    String cipherName7096 =  "DES";
					try{
						android.util.Log.d("cipherName-7096", javax.crypto.Cipher.getInstance(cipherName7096).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					return item.selection();
                }
            }
        }
        return null;
    }

    @Override
    public boolean hasAnswerChanged() {
        String cipherName7097 =  "DES";
		try{
			android.util.Log.d("cipherName-7097", javax.crypto.Cipher.getInstance(cipherName7097).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return !Objects.equals(originallySelectedValue, selectedValue);
    }
}
