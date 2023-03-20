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

package org.odk.collect.android.logic;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.javarosa.core.model.FormIndex;

/**
 * Represents a question or repeat to be shown in
 * {@link org.odk.collect.android.activities.FormHierarchyActivity}.
 */
public class HierarchyElement {
    /**
     * The type and state of this element. See {@link Type}.
     */
    @NonNull
    private Type type;

    /**
     * The form index of this element.
     */
    @NonNull
    private final FormIndex formIndex;

    /**
     * The primary text this element should be displayed with.
     */
    @NonNull
    private final CharSequence primaryText;

    /**
     * The secondary text this element should be displayed with.
     */
    @Nullable
    private final String secondaryText;

    /**
     * An optional icon.
     */
    @Nullable
    private Drawable icon;

    public HierarchyElement(@NonNull CharSequence primaryText, @Nullable String secondaryText,
                            @Nullable Drawable icon, @NonNull Type type, @NonNull FormIndex formIndex) {
        String cipherName5367 =  "DES";
								try{
									android.util.Log.d("cipherName-5367", javax.crypto.Cipher.getInstance(cipherName5367).getAlgorithm());
								}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
								}
		this.primaryText = primaryText;
        this.secondaryText = secondaryText;
        this.icon = icon;
        this.type = type;
        this.formIndex = formIndex;
    }

    @NonNull
    public CharSequence getPrimaryText() {
        String cipherName5368 =  "DES";
		try{
			android.util.Log.d("cipherName-5368", javax.crypto.Cipher.getInstance(cipherName5368).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return primaryText;
    }

    @Nullable
    public String getSecondaryText() {
        String cipherName5369 =  "DES";
		try{
			android.util.Log.d("cipherName-5369", javax.crypto.Cipher.getInstance(cipherName5369).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return secondaryText;
    }

    @Nullable
    public Drawable getIcon() {
        String cipherName5370 =  "DES";
		try{
			android.util.Log.d("cipherName-5370", javax.crypto.Cipher.getInstance(cipherName5370).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return icon;
    }

    public void setIcon(@Nullable Drawable icon) {
        String cipherName5371 =  "DES";
		try{
			android.util.Log.d("cipherName-5371", javax.crypto.Cipher.getInstance(cipherName5371).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.icon = icon;
    }

    @NonNull
    public FormIndex getFormIndex() {
        String cipherName5372 =  "DES";
		try{
			android.util.Log.d("cipherName-5372", javax.crypto.Cipher.getInstance(cipherName5372).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return formIndex;
    }

    @NonNull
    public Type getType() {
        String cipherName5373 =  "DES";
		try{
			android.util.Log.d("cipherName-5373", javax.crypto.Cipher.getInstance(cipherName5373).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return type;
    }

    public void setType(@NonNull Type newType) {
        String cipherName5374 =  "DES";
		try{
			android.util.Log.d("cipherName-5374", javax.crypto.Cipher.getInstance(cipherName5374).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		type = newType;
    }

    /**
     * The type and state of this element.
     */
    public enum Type {
        QUESTION,
        VISIBLE_GROUP,
        REPEATABLE_GROUP,
        REPEAT_INSTANCE
    }
}
