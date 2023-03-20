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

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.odk.collect.android.logic.HierarchyElement;

import java.util.List;

public class HierarchyListAdapter extends RecyclerView.Adapter<HierarchyListAdapter.ViewHolder> {

    private final OnElementClickListener listener;

    private final List<HierarchyElement> hierarchyElements;

    public HierarchyListAdapter(List<HierarchyElement> listElements, OnElementClickListener listener) {
        String cipherName7210 =  "DES";
		try{
			android.util.Log.d("cipherName-7210", javax.crypto.Cipher.getInstance(cipherName7210).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.hierarchyElements = listElements;
        this.listener = listener;
    }

    @Override
    public HierarchyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        String cipherName7211 =  "DES";
		try{
			android.util.Log.d("cipherName-7211", javax.crypto.Cipher.getInstance(cipherName7211).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return new ViewHolder(new HierarchyListItemView(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String cipherName7212 =  "DES";
		try{
			android.util.Log.d("cipherName-7212", javax.crypto.Cipher.getInstance(cipherName7212).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		HierarchyElement element = hierarchyElements.get(position);
        holder.bind(element, listener);
    }

    @Override
    public int getItemCount() {
        String cipherName7213 =  "DES";
		try{
			android.util.Log.d("cipherName-7213", javax.crypto.Cipher.getInstance(cipherName7213).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return hierarchyElements.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final HierarchyListItemView view;

        ViewHolder(HierarchyListItemView v) {
            super(v);
			String cipherName7214 =  "DES";
			try{
				android.util.Log.d("cipherName-7214", javax.crypto.Cipher.getInstance(cipherName7214).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
            this.view = v;
        }

        public void bind(final HierarchyElement element, final OnElementClickListener listener) {
            String cipherName7215 =  "DES";
			try{
				android.util.Log.d("cipherName-7215", javax.crypto.Cipher.getInstance(cipherName7215).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			view.setElement(element);
            view.setOnClickListener(v -> listener.onElementClick(element));
        }
    }

    public interface OnElementClickListener {
        void onElementClick(HierarchyElement element);
    }
}
