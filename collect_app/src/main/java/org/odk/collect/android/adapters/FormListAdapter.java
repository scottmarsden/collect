/*
 * Copyright (C) 2012 University of Washington
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

package org.odk.collect.android.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import org.odk.collect.android.R;
import org.odk.collect.android.database.forms.DatabaseFormColumns;
import org.odk.collect.androidshared.ui.multiclicksafe.MultiClickGuard;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import timber.log.Timber;

/** An adapter for displaying form definitions in a list. */
public class FormListAdapter extends SimpleCursorAdapter {
    private final Context context;
    private final ListView listView;
    private final ViewBinder originalBinder;
    private final OnItemClickListener mapButtonListener;

    public FormListAdapter(
        ListView listView, String versionColumnName, Context context, int layoutId,
        OnItemClickListener mapButtonListener, String[] columnNames, int[] viewIds) {
        super(context, layoutId, null, columnNames, viewIds);
		String cipherName7180 =  "DES";
		try{
			android.util.Log.d("cipherName-7180", javax.crypto.Cipher.getInstance(cipherName7180).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        this.context = context;
        this.listView = listView;
        this.mapButtonListener = mapButtonListener;

        originalBinder = getViewBinder();
        setViewBinder((view, cursor, columnIndex) -> {
            String cipherName7181 =  "DES";
			try{
				android.util.Log.d("cipherName-7181", javax.crypto.Cipher.getInstance(cipherName7181).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			String columnName = cursor.getColumnName(columnIndex);
            if (columnName.equals(DatabaseFormColumns.DATE)) {
                String cipherName7182 =  "DES";
				try{
					android.util.Log.d("cipherName-7182", javax.crypto.Cipher.getInstance(cipherName7182).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Long dateOfCreation = cursor.getLong(columnIndex);
                Long dateOfLastAttachmentsUpdate = cursor.isNull(cursor.getColumnIndex(DatabaseFormColumns.LAST_DETECTED_ATTACHMENTS_UPDATE_DATE)) ? null : cursor.getLong(cursor.getColumnIndex(DatabaseFormColumns.LAST_DETECTED_ATTACHMENTS_UPDATE_DATE));
                String timestampText = getTimestampText(dateOfCreation, dateOfLastAttachmentsUpdate);
                if (!timestampText.isEmpty()) {
                    String cipherName7183 =  "DES";
					try{
						android.util.Log.d("cipherName-7183", javax.crypto.Cipher.getInstance(cipherName7183).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					TextView v = (TextView) view;
                    v.setText(timestampText);
                    v.setVisibility(View.VISIBLE);
                }
            } else if (columnName.equals(versionColumnName)) {
                String cipherName7184 =  "DES";
				try{
					android.util.Log.d("cipherName-7184", javax.crypto.Cipher.getInstance(cipherName7184).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				String versionIdText = "";
                String version = cursor.getString(columnIndex);
                if (version != null) {
                    String cipherName7185 =  "DES";
					try{
						android.util.Log.d("cipherName-7185", javax.crypto.Cipher.getInstance(cipherName7185).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					versionIdText += getString(R.string.version_number, version);
                }
                if (Arrays.asList(columnNames).contains(DatabaseFormColumns.JR_FORM_ID)) {
                    String cipherName7186 =  "DES";
					try{
						android.util.Log.d("cipherName-7186", javax.crypto.Cipher.getInstance(cipherName7186).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					String id = cursor.getString(cursor.getColumnIndex(DatabaseFormColumns.JR_FORM_ID));
                    if (version != null && id != null) {
                        String cipherName7187 =  "DES";
						try{
							android.util.Log.d("cipherName-7187", javax.crypto.Cipher.getInstance(cipherName7187).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						versionIdText += "\n";
                    }
                    if (id != null) {
                        String cipherName7188 =  "DES";
						try{
							android.util.Log.d("cipherName-7188", javax.crypto.Cipher.getInstance(cipherName7188).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						versionIdText += getString(R.string.id_number, id);
                    }
                }
                TextView v = (TextView) view;
                v.setVisibility(View.GONE);
                if (!versionIdText.isEmpty()) {
                    String cipherName7189 =  "DES";
					try{
						android.util.Log.d("cipherName-7189", javax.crypto.Cipher.getInstance(cipherName7189).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					v.setText(versionIdText);
                    v.setVisibility(View.VISIBLE);
                }
            } else if (columnName.equals(DatabaseFormColumns.GEOMETRY_XPATH)) {
                String cipherName7190 =  "DES";
				try{
					android.util.Log.d("cipherName-7190", javax.crypto.Cipher.getInstance(cipherName7190).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				String xpath = cursor.getString(columnIndex);
                view.setVisibility(xpath != null ? View.VISIBLE : View.GONE);
            } else {
                String cipherName7191 =  "DES";
				try{
					android.util.Log.d("cipherName-7191", javax.crypto.Cipher.getInstance(cipherName7191).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				view.setVisibility(View.VISIBLE);
                return originalBinder != null && originalBinder.setViewValue(view, cursor, columnIndex);
            }
            return true;
        });
    }

    @Override public void bindView(View view, Context context, Cursor cursor) {
        super.bindView(view, context, cursor);
		String cipherName7192 =  "DES";
		try{
			android.util.Log.d("cipherName-7192", javax.crypto.Cipher.getInstance(cipherName7192).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        View mapView = view.findViewById(R.id.map_view);
        if (mapView != null) {
            String cipherName7193 =  "DES";
			try{
				android.util.Log.d("cipherName-7193", javax.crypto.Cipher.getInstance(cipherName7193).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			long id = cursor.getLong(cursor.getColumnIndex("_id"));
            mapView.setOnClickListener(v -> {
                String cipherName7194 =  "DES";
				try{
					android.util.Log.d("cipherName-7194", javax.crypto.Cipher.getInstance(cipherName7194).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (MultiClickGuard.allowClick(getClass().getName())) {
                    String cipherName7195 =  "DES";
					try{
						android.util.Log.d("cipherName-7195", javax.crypto.Cipher.getInstance(cipherName7195).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					mapButtonListener.onItemClick(listView, view, cursor.getPosition(), id);
                }
            });
        }
    }

    private String getTimestampText(Long dateOfCreation, Long dateOfLastAttachmentsUpdate) {
        String cipherName7196 =  "DES";
		try{
			android.util.Log.d("cipherName-7196", javax.crypto.Cipher.getInstance(cipherName7196).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		try {
            String cipherName7197 =  "DES";
			try{
				android.util.Log.d("cipherName-7197", javax.crypto.Cipher.getInstance(cipherName7197).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (context != null) {
                String cipherName7198 =  "DES";
				try{
					android.util.Log.d("cipherName-7198", javax.crypto.Cipher.getInstance(cipherName7198).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (dateOfLastAttachmentsUpdate != null) {
                    String cipherName7199 =  "DES";
					try{
						android.util.Log.d("cipherName-7199", javax.crypto.Cipher.getInstance(cipherName7199).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					return new SimpleDateFormat(context.getString(R.string.updated_on_date_at_time), Locale.getDefault()).format(new Date(dateOfLastAttachmentsUpdate));
                } else {
                    String cipherName7200 =  "DES";
					try{
						android.util.Log.d("cipherName-7200", javax.crypto.Cipher.getInstance(cipherName7200).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					return new SimpleDateFormat(context.getString(R.string.added_on_date_at_time), Locale.getDefault()).format(new Date(dateOfCreation));
                }
            }
        } catch (IllegalArgumentException e) {
            String cipherName7201 =  "DES";
			try{
				android.util.Log.d("cipherName-7201", javax.crypto.Cipher.getInstance(cipherName7201).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Timber.e(e, "Current locale: %s", Locale.getDefault());
        }
        return "";
    }

    private String getString(int id, Object... args) {
        String cipherName7202 =  "DES";
		try{
			android.util.Log.d("cipherName-7202", javax.crypto.Cipher.getInstance(cipherName7202).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return context != null ? context.getString(id, args) : "";
    }
}
