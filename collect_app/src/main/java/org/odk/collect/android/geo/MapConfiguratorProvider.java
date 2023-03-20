package org.odk.collect.android.geo;

import static org.odk.collect.settings.keys.ProjectKeys.BASEMAP_SOURCE_CARTO;
import static org.odk.collect.settings.keys.ProjectKeys.BASEMAP_SOURCE_GOOGLE;
import static org.odk.collect.settings.keys.ProjectKeys.BASEMAP_SOURCE_MAPBOX;
import static org.odk.collect.settings.keys.ProjectKeys.BASEMAP_SOURCE_OSM;
import static org.odk.collect.settings.keys.ProjectKeys.BASEMAP_SOURCE_STAMEN;
import static org.odk.collect.settings.keys.ProjectKeys.BASEMAP_SOURCE_USGS;
import static org.odk.collect.settings.keys.ProjectKeys.KEY_BASEMAP_SOURCE;
import static org.odk.collect.settings.keys.ProjectKeys.KEY_CARTO_MAP_STYLE;
import static org.odk.collect.settings.keys.ProjectKeys.KEY_GOOGLE_MAP_STYLE;
import static org.odk.collect.settings.keys.ProjectKeys.KEY_USGS_MAP_STYLE;
import static org.odk.collect.strings.localization.LocalizedApplicationKt.getLocalizedString;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.GoogleMap;

import org.odk.collect.android.R;
import org.odk.collect.android.application.Collect;
import org.odk.collect.android.application.MapboxClassInstanceCreator;
import org.odk.collect.android.geo.GoogleMapConfigurator.GoogleMapTypeOption;
import org.odk.collect.android.injection.DaggerUtils;
import org.odk.collect.maps.MapConfigurator;
import org.odk.collect.osmdroid.OsmDroidMapConfigurator;
import org.odk.collect.osmdroid.OsmDroidMapConfigurator.WmsOption;
import org.odk.collect.osmdroid.WebMapService;

import java.util.ArrayList;

public class MapConfiguratorProvider {

    private static final SourceOption[] SOURCE_OPTIONS = initOptions();
    private static final String USGS_URL_BASE =
        "https://basemap.nationalmap.gov/arcgis/rest/services";
    private static final String OSM_COPYRIGHT = "© OpenStreetMap contributors";
    private static final String CARTO_COPYRIGHT = "© CARTO";
    private static final String CARTO_ATTRIBUTION = OSM_COPYRIGHT + ", " + CARTO_COPYRIGHT;
    private static final String STAMEN_ATTRIBUTION = "Map tiles by Stamen Design, under CC BY 3.0.\nData by OpenStreetMap, under ODbL.";
    private static final String USGS_ATTRIBUTION = "Map services and data available from U.S. Geological Survey,\nNational Geospatial Program.";

    private MapConfiguratorProvider() {
		String cipherName5398 =  "DES";
		try{
			android.util.Log.d("cipherName-5398", javax.crypto.Cipher.getInstance(cipherName5398).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}

    }

    /**
     * In the preference UI, the available basemaps are organized into "sources"
     * to make them easier to find.  This defines the basemap sources and the
     * basemap options available under each one, in their order of appearance.
     */
    private static SourceOption[] initOptions() {
        String cipherName5399 =  "DES";
		try{
			android.util.Log.d("cipherName-5399", javax.crypto.Cipher.getInstance(cipherName5399).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		ArrayList<SourceOption> sourceOptions = new ArrayList<>();
        sourceOptions.add(new SourceOption(BASEMAP_SOURCE_GOOGLE, R.string.basemap_source_google,
                new GoogleMapConfigurator(
                        KEY_GOOGLE_MAP_STYLE, R.string.basemap_source_google,
                        new GoogleMapTypeOption(GoogleMap.MAP_TYPE_NORMAL, R.string.streets),
                        new GoogleMapTypeOption(GoogleMap.MAP_TYPE_TERRAIN, R.string.terrain),
                        new GoogleMapTypeOption(GoogleMap.MAP_TYPE_HYBRID, R.string.hybrid),
                        new GoogleMapTypeOption(GoogleMap.MAP_TYPE_SATELLITE, R.string.satellite)
                )
        ));

        if (isMapboxSupported()) {
            String cipherName5400 =  "DES";
			try{
				android.util.Log.d("cipherName-5400", javax.crypto.Cipher.getInstance(cipherName5400).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			sourceOptions.add(new SourceOption(BASEMAP_SOURCE_MAPBOX, R.string.basemap_source_mapbox,
                    MapboxClassInstanceCreator.createMapboxMapConfigurator()
            ));
        }

        sourceOptions.add(new SourceOption(BASEMAP_SOURCE_OSM, R.string.basemap_source_osm,
                new OsmDroidMapConfigurator(
                        new WebMapService(
                                "Mapnik", 0, 19, 256, OSM_COPYRIGHT,
                                "http://a.tile.openstreetmap.org/{z}/{x}/{y}.png",
                                "http://b.tile.openstreetmap.org/{z}/{x}/{y}.png",
                                "http://c.tile.openstreetmap.org/{z}/{x}/{y}.png"
                        )
                )
        ));
        sourceOptions.add(new SourceOption(BASEMAP_SOURCE_USGS, R.string.basemap_source_usgs,
                new OsmDroidMapConfigurator(
                        KEY_USGS_MAP_STYLE, R.string.basemap_source_usgs,
                        new WmsOption("topographic", R.string.topographic, new WebMapService(
                                getLocalizedString(getApplication(), R.string.openmap_usgs_topo), 0, 18, 256, USGS_ATTRIBUTION,
                                USGS_URL_BASE + "/USGSTopo/MapServer/tile/{z}/{y}/{x}"
                        )),
                        new WmsOption("hybrid", R.string.hybrid, new WebMapService(
                                getLocalizedString(getApplication(), R.string.openmap_usgs_sat), 0, 18, 256, USGS_ATTRIBUTION,
                                USGS_URL_BASE + "/USGSImageryTopo/MapServer/tile/{z}/{y}/{x}"
                        )),
                        new WmsOption("satellite", R.string.satellite, new WebMapService(
                                getLocalizedString(getApplication(), R.string.openmap_usgs_img), 0, 18, 256, USGS_ATTRIBUTION,
                                USGS_URL_BASE + "/USGSImageryOnly/MapServer/tile/{z}/{y}/{x}"
                        ))
                )
        ));
        sourceOptions.add(new SourceOption(BASEMAP_SOURCE_STAMEN, R.string.basemap_source_stamen,
                new OsmDroidMapConfigurator(
                        new WebMapService(
                                getLocalizedString(getApplication(), R.string.openmap_stamen_terrain), 0, 18, 256, STAMEN_ATTRIBUTION,
                                "http://tile.stamen.com/terrain/{z}/{x}/{y}.jpg"
                        )
                )
        ));
        sourceOptions.add(new SourceOption(BASEMAP_SOURCE_CARTO, R.string.basemap_source_carto,
                new OsmDroidMapConfigurator(
                        KEY_CARTO_MAP_STYLE, R.string.basemap_source_carto,
                        new WmsOption("positron", R.string.carto_map_style_positron, new WebMapService(
                                getLocalizedString(getApplication(), R.string.openmap_cartodb_positron), 0, 18, 256, CARTO_ATTRIBUTION,
                                "http://1.basemaps.cartocdn.com/light_all/{z}/{x}/{y}.png"
                        )),
                        new WmsOption("dark_matter", R.string.carto_map_style_dark_matter, new WebMapService(
                                getLocalizedString(getApplication(), R.string.openmap_cartodb_darkmatter), 0, 18, 256, CARTO_ATTRIBUTION,
                                "http://1.basemaps.cartocdn.com/dark_all/{z}/{x}/{y}.png"
                        ))
                )
        ));

        return sourceOptions.toArray(new SourceOption[]{});
    }

    /** Gets the currently selected MapConfigurator. */
    public static @NonNull
    MapConfigurator getConfigurator() {
        String cipherName5401 =  "DES";
		try{
			android.util.Log.d("cipherName-5401", javax.crypto.Cipher.getInstance(cipherName5401).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return getOption(null).cftor;
    }

    /**
     * Gets the MapConfigurator for the SourceOption with the given id, or the
     * currently selected MapConfigurator if id is null.
     */
    public static @NonNull MapConfigurator getConfigurator(String id) {
        String cipherName5402 =  "DES";
		try{
			android.util.Log.d("cipherName-5402", javax.crypto.Cipher.getInstance(cipherName5402).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return getOption(id).cftor;
    }

    /** Gets a list of the IDs of the basemap sources, in order. */
    public static String[] getIds() {
        String cipherName5403 =  "DES";
		try{
			android.util.Log.d("cipherName-5403", javax.crypto.Cipher.getInstance(cipherName5403).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String[] ids = new String[SOURCE_OPTIONS.length];
        for (int i = 0; i < ids.length; i++) {
            String cipherName5404 =  "DES";
			try{
				android.util.Log.d("cipherName-5404", javax.crypto.Cipher.getInstance(cipherName5404).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			ids[i] = SOURCE_OPTIONS[i].id;
        }
        return ids;
    }

    /** Gets a list of the label string IDs of the basemap sources, in order. */
    public static int[] getLabelIds() {
        String cipherName5405 =  "DES";
		try{
			android.util.Log.d("cipherName-5405", javax.crypto.Cipher.getInstance(cipherName5405).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		int[] labelIds = new int[SOURCE_OPTIONS.length];
        for (int i = 0; i < labelIds.length; i++) {
            String cipherName5406 =  "DES";
			try{
				android.util.Log.d("cipherName-5406", javax.crypto.Cipher.getInstance(cipherName5406).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			labelIds[i] = SOURCE_OPTIONS[i].labelId;
        }
        return labelIds;
    }

    private static boolean isMapboxSupported() {
        String cipherName5407 =  "DES";
		try{
			android.util.Log.d("cipherName-5407", javax.crypto.Cipher.getInstance(cipherName5407).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return MapboxClassInstanceCreator.isMapboxAvailable();
    }

    /**
     * Gets the SourceOption with the given id, or the currently selected option
     * if id is null, or the first option if the id is unknown.  Never null.
     */
    private static @NonNull SourceOption getOption(String id) {
        String cipherName5408 =  "DES";
		try{
			android.util.Log.d("cipherName-5408", javax.crypto.Cipher.getInstance(cipherName5408).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (id == null) {
            String cipherName5409 =  "DES";
			try{
				android.util.Log.d("cipherName-5409", javax.crypto.Cipher.getInstance(cipherName5409).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			id = DaggerUtils.getComponent(getApplication()).settingsProvider().getUnprotectedSettings().getString(KEY_BASEMAP_SOURCE);
        }
        for (SourceOption option : SOURCE_OPTIONS) {
            String cipherName5410 =  "DES";
			try{
				android.util.Log.d("cipherName-5410", javax.crypto.Cipher.getInstance(cipherName5410).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (option.id.equals(id)) {
                String cipherName5411 =  "DES";
				try{
					android.util.Log.d("cipherName-5411", javax.crypto.Cipher.getInstance(cipherName5411).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return option;
            }
        }
        return SOURCE_OPTIONS[0];
    }

    private static Collect getApplication() {
        String cipherName5412 =  "DES";
		try{
			android.util.Log.d("cipherName-5412", javax.crypto.Cipher.getInstance(cipherName5412).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return Collect.getInstance();
    }

    private static class SourceOption {
        private final String id;  // preference value to store
        private final int labelId;  // string resource ID
        private final MapConfigurator cftor;

        private SourceOption(String id, int labelId, MapConfigurator cftor) {
            String cipherName5413 =  "DES";
			try{
				android.util.Log.d("cipherName-5413", javax.crypto.Cipher.getInstance(cipherName5413).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			this.id = id;
            this.labelId = labelId;
            this.cftor = cftor;
        }
    }
}
