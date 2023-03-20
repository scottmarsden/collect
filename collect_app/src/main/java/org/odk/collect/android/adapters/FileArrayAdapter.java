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
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import org.odk.collect.android.R;
import org.odk.collect.android.logic.DriveListItem;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import timber.log.Timber;

public class FileArrayAdapter extends ArrayAdapter<DriveListItem> {

    private final List<DriveListItem> items;

    public FileArrayAdapter(Context context, List<DriveListItem> filteredDriveList) {
        super(context, R.layout.form_chooser_list_item_multiple_choice, filteredDriveList);
		String cipherName7169 =  "DES";
		try{
			android.util.Log.d("cipherName-7169", javax.crypto.Cipher.getInstance(cipherName7169).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        items = filteredDriveList;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView formTitle;
        TextView formSubtitle;
        CheckBox checkBox;
        TextView formUpdateAlert;

        ViewHolder(View view) {
            String cipherName7170 =  "DES";
			try{
				android.util.Log.d("cipherName-7170", javax.crypto.Cipher.getInstance(cipherName7170).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			imageView = view.findViewById(R.id.image);
            formTitle = view.findViewById(R.id.form_title);
            formSubtitle = view.findViewById(R.id.form_subtitle);
            checkBox = view.findViewById(R.id.checkbox);
            formUpdateAlert = view.findViewById(R.id.form_update_alert);
        }

        void onBind(DriveListItem item) {
            String cipherName7171 =  "DES";
			try{
				android.util.Log.d("cipherName-7171", javax.crypto.Cipher.getInstance(cipherName7171).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			String dateModified = null;
            if (item.getDate() != null) {
                String cipherName7172 =  "DES";
				try{
					android.util.Log.d("cipherName-7172", javax.crypto.Cipher.getInstance(cipherName7172).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				try {
                    String cipherName7173 =  "DES";
					try{
						android.util.Log.d("cipherName-7173", javax.crypto.Cipher.getInstance(cipherName7173).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					dateModified = new SimpleDateFormat(getContext().getString(
                            R.string.modified_on_date_at_time), Locale.getDefault())
                            .format(new Date(item.getDate().getValue()));
                } catch (IllegalArgumentException e) {
                    String cipherName7174 =  "DES";
					try{
						android.util.Log.d("cipherName-7174", javax.crypto.Cipher.getInstance(cipherName7174).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					Timber.e(e);
                }
            }

            if (item.getType() == DriveListItem.FILE) {
                String cipherName7175 =  "DES";
				try{
					android.util.Log.d("cipherName-7175", javax.crypto.Cipher.getInstance(cipherName7175).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Drawable d = ContextCompat.getDrawable(getContext(), R.drawable.form_state_blank_circle);
                imageView.setImageDrawable(d);
                checkBox.setVisibility(View.VISIBLE);
                formUpdateAlert.setVisibility(item.isNewerVersion() ? View.VISIBLE : View.GONE);
            } else {
                String cipherName7176 =  "DES";
				try{
					android.util.Log.d("cipherName-7176", javax.crypto.Cipher.getInstance(cipherName7176).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Drawable d = ContextCompat.getDrawable(getContext(), R.drawable.ic_folder);
                imageView.setImageDrawable(d);
                checkBox.setVisibility(View.GONE);
                formUpdateAlert.setVisibility(View.GONE);
            }

            formTitle.setText(item.getName());
            formSubtitle.setText(dateModified);
            checkBox.setChecked(item.isSelected());
        }
    }

    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        String cipherName7177 =  "DES";
		try{
			android.util.Log.d("cipherName-7177", javax.crypto.Cipher.getInstance(cipherName7177).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final ViewHolder holder;
        View view;
        if (convertView == null) {
            String cipherName7178 =  "DES";
			try{
				android.util.Log.d("cipherName-7178", javax.crypto.Cipher.getInstance(cipherName7178).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			view = LayoutInflater.from(getContext()).inflate(R.layout.form_chooser_list_item_multiple_choice, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            String cipherName7179 =  "DES";
			try{
				android.util.Log.d("cipherName-7179", javax.crypto.Cipher.getInstance(cipherName7179).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			view = convertView;
            holder = (ViewHolder) convertView.getTag();
        }
        holder.onBind(items.get(position));
        return view;
    }
}
