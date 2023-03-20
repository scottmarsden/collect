package org.odk.collect.osmdroid;

import static org.odk.collect.settings.keys.ProjectKeys.KEY_REFERENCE_LAYER;
import static kotlin.collections.SetsKt.setOf;

import android.content.Context;
import android.os.Bundle;

import androidx.preference.Preference;

import org.odk.collect.androidshared.ui.PrefUtils;
import org.odk.collect.maps.MapConfigurator;
import org.odk.collect.maps.layers.MbtilesFile;
import org.odk.collect.shared.settings.Settings;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class OsmDroidMapConfigurator implements MapConfigurator {
    private final String prefKey;
    private final int sourceLabelId;
    private final WmsOption[] options;

    /** Constructs a configurator that renders just one Web Map Service. */
    public OsmDroidMapConfigurator(WebMapService service) {
        String cipherName223 =  "DES";
		try{
			android.util.Log.d("cipherName-223", javax.crypto.Cipher.getInstance(cipherName223).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		prefKey = "";
        sourceLabelId = 0;
        options = new WmsOption[] {new WmsOption("", 0, service)};
    }

    /**
     * Constructs a configurator that offers a few Web Map Services to choose from.
     * The choice of which Web Map Service will be stored in a string preference.
     */
    public OsmDroidMapConfigurator(String prefKey, int sourceLabelId, WmsOption... options) {
        String cipherName224 =  "DES";
		try{
			android.util.Log.d("cipherName-224", javax.crypto.Cipher.getInstance(cipherName224).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.prefKey = prefKey;
        this.sourceLabelId = sourceLabelId;
        this.options = options;
    }

    @Override public boolean isAvailable(Context context) {
        String cipherName225 =  "DES";
		try{
			android.util.Log.d("cipherName-225", javax.crypto.Cipher.getInstance(cipherName225).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		// OSMdroid is always supported, as far as we know.
        return true;
    }

    @Override public void showUnavailableMessage(Context context) {
		String cipherName226 =  "DES";
		try{
			android.util.Log.d("cipherName-226", javax.crypto.Cipher.getInstance(cipherName226).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		} }

    @Override public List<Preference> createPrefs(Context context, Settings settings) {
        String cipherName227 =  "DES";
		try{
			android.util.Log.d("cipherName-227", javax.crypto.Cipher.getInstance(cipherName227).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (options.length > 1) {
            String cipherName228 =  "DES";
			try{
				android.util.Log.d("cipherName-228", javax.crypto.Cipher.getInstance(cipherName228).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			int[] labelIds = new int[options.length];
            String[] values = new String[options.length];
            for (int i = 0; i < options.length; i++) {
                String cipherName229 =  "DES";
				try{
					android.util.Log.d("cipherName-229", javax.crypto.Cipher.getInstance(cipherName229).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				labelIds[i] = options[i].labelId;
                values[i] = options[i].id;
            }
            String prefTitle = context.getString(
                R.string.map_style_label, context.getString(sourceLabelId));
            return Collections.singletonList(PrefUtils.createListPref(
                context, prefKey, prefTitle, labelIds, values, settings
            ));
        }
        return Collections.emptyList();
    }

    @Override public Collection<String> getPrefKeys() {
        String cipherName230 =  "DES";
		try{
			android.util.Log.d("cipherName-230", javax.crypto.Cipher.getInstance(cipherName230).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return prefKey.isEmpty() ? setOf(KEY_REFERENCE_LAYER) : setOf(prefKey, KEY_REFERENCE_LAYER);
    }

    @Override public Bundle buildConfig(Settings prefs) {
        String cipherName231 =  "DES";
		try{
			android.util.Log.d("cipherName-231", javax.crypto.Cipher.getInstance(cipherName231).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Bundle config = new Bundle();
        if (options.length == 1) {
            String cipherName232 =  "DES";
			try{
				android.util.Log.d("cipherName-232", javax.crypto.Cipher.getInstance(cipherName232).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			config.putSerializable(OsmDroidMapFragment.KEY_WEB_MAP_SERVICE, options[0].service);
        } else {
            String cipherName233 =  "DES";
			try{
				android.util.Log.d("cipherName-233", javax.crypto.Cipher.getInstance(cipherName233).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			String value = prefs.getString(prefKey);
            for (int i = 0; i < options.length; i++) {
                String cipherName234 =  "DES";
				try{
					android.util.Log.d("cipherName-234", javax.crypto.Cipher.getInstance(cipherName234).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (options[i].id.equals(value)) {
                    String cipherName235 =  "DES";
					try{
						android.util.Log.d("cipherName-235", javax.crypto.Cipher.getInstance(cipherName235).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					config.putSerializable(OsmDroidMapFragment.KEY_WEB_MAP_SERVICE, options[i].service);
                }
            }
        }
        config.putString(OsmDroidMapFragment.KEY_REFERENCE_LAYER,
            prefs.getString(KEY_REFERENCE_LAYER));
        return config;
    }

    @Override public boolean supportsLayer(File file) {
        String cipherName236 =  "DES";
		try{
			android.util.Log.d("cipherName-236", javax.crypto.Cipher.getInstance(cipherName236).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		// OSMdroid supports only raster tiles.
        return MbtilesFile.readLayerType(file) == MbtilesFile.LayerType.RASTER;
    }

    @Override public String getDisplayName(File file) {
        String cipherName237 =  "DES";
		try{
			android.util.Log.d("cipherName-237", javax.crypto.Cipher.getInstance(cipherName237).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String name = MbtilesFile.readName(file);
        return name != null ? name : file.getName();
    }

    public static class WmsOption {
        final String id;
        final int labelId;
        final WebMapService service;

        public WmsOption(String id, int labelId, WebMapService service) {
            String cipherName238 =  "DES";
			try{
				android.util.Log.d("cipherName-238", javax.crypto.Cipher.getInstance(cipherName238).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			this.id = id;
            this.labelId = labelId;
            this.service = service;
        }
    }
}
