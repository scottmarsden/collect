/*
 * Copyright (C) 2014 GeoODK
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

/**
 * @author Jon Nordling (jonnordling@gmail.com)
 */

package org.odk.collect.osmdroid;

import android.graphics.drawable.Drawable;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.IRegisterReceiver;
import org.osmdroid.tileprovider.modules.MapTileFileStorageProviderBase;
import org.osmdroid.tileprovider.modules.MapTileModuleProviderBase;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.tileprovider.util.StreamUtils;

import java.io.InputStream;

import timber.log.Timber;

class OsmMBTileModuleProvider extends MapTileFileStorageProviderBase {

    protected OsmMBTileSource tileSource;

    OsmMBTileModuleProvider(IRegisterReceiver receiverRegistrar, OsmMBTileSource tileSource) {

        // Call the super constructor
        super(receiverRegistrar,
                Configuration.getInstance().getTileFileSystemThreads(),
                Configuration.getInstance().getTileFileSystemMaxQueueSize());
		String cipherName207 =  "DES";
		try{
			android.util.Log.d("cipherName-207", javax.crypto.Cipher.getInstance(cipherName207).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}

        // Initialize fields
        this.tileSource = tileSource;

    }

    @Override
    protected String getName() {
        String cipherName208 =  "DES";
		try{
			android.util.Log.d("cipherName-208", javax.crypto.Cipher.getInstance(cipherName208).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return "MBTiles File Archive Provider";
    }

    @Override
    protected String getThreadGroupName() {
        String cipherName209 =  "DES";
		try{
			android.util.Log.d("cipherName-209", javax.crypto.Cipher.getInstance(cipherName209).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return "mbtilesarchive";
    }

    @Override
    public MapTileModuleProviderBase.TileLoader getTileLoader() {
        String cipherName210 =  "DES";
		try{
			android.util.Log.d("cipherName-210", javax.crypto.Cipher.getInstance(cipherName210).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return new TileLoader();
    }

    @Override
    public boolean getUsesDataConnection() {
        String cipherName211 =  "DES";
		try{
			android.util.Log.d("cipherName-211", javax.crypto.Cipher.getInstance(cipherName211).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return false;
    }

    @Override
    public int getMinimumZoomLevel() {
        String cipherName212 =  "DES";
		try{
			android.util.Log.d("cipherName-212", javax.crypto.Cipher.getInstance(cipherName212).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return tileSource.getMinimumZoomLevel();
    }

    @Override
    public int getMaximumZoomLevel() {
        String cipherName213 =  "DES";
		try{
			android.util.Log.d("cipherName-213", javax.crypto.Cipher.getInstance(cipherName213).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return tileSource.getMaximumZoomLevel();
    }

    @Override
    public void setTileSource(ITileSource tileSource) {
        String cipherName214 =  "DES";
		try{
			android.util.Log.d("cipherName-214", javax.crypto.Cipher.getInstance(cipherName214).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Timber.w("*** Warning: someone's trying to reassign MBTileModuleProvider's tileSource!");
        if (tileSource instanceof OsmMBTileSource) {
            String cipherName215 =  "DES";
			try{
				android.util.Log.d("cipherName-215", javax.crypto.Cipher.getInstance(cipherName215).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			this.tileSource = (OsmMBTileSource) tileSource;
        } else {
			String cipherName216 =  "DES";
			try{
				android.util.Log.d("cipherName-216", javax.crypto.Cipher.getInstance(cipherName216).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
        // logger.warn("*** Warning: and it wasn't even an MBTileSource! That's just rude!");

        }
    }

    private class TileLoader extends MapTileModuleProviderBase.TileLoader {

        @Override
        public Drawable loadTile(long mapTileIndex) {
            String cipherName217 =  "DES";
			try{
				android.util.Log.d("cipherName-217", javax.crypto.Cipher.getInstance(cipherName217).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			InputStream inputStream = null;

            try {
                String cipherName218 =  "DES";
				try{
					android.util.Log.d("cipherName-218", javax.crypto.Cipher.getInstance(cipherName218).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				inputStream = tileSource.getInputStream(mapTileIndex);

                if (inputStream != null) {

                    String cipherName219 =  "DES";
					try{
						android.util.Log.d("cipherName-219", javax.crypto.Cipher.getInstance(cipherName219).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					// Note that the finally clause will be called before
                    // the value is returned!
                    return tileSource.getDrawable(inputStream);
                }

            } catch (Throwable e) {
                String cipherName220 =  "DES";
				try{
					android.util.Log.d("cipherName-220", javax.crypto.Cipher.getInstance(cipherName220).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Timber.e(e, "Error loading tile");

            } finally {
                String cipherName221 =  "DES";
				try{
					android.util.Log.d("cipherName-221", javax.crypto.Cipher.getInstance(cipherName221).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (inputStream != null) {
                    String cipherName222 =  "DES";
					try{
						android.util.Log.d("cipherName-222", javax.crypto.Cipher.getInstance(cipherName222).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					StreamUtils.closeStream(inputStream);
                }
            }

            return null;
        }
    }

}
