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

package org.odk.collect.android.tasks;

import static org.odk.collect.android.analytics.AnalyticsEvents.ENCRYPT_SUBMISSION;
import static org.odk.collect.strings.localization.LocalizedApplicationKt.getLocalizedString;

import android.content.ContentValues;
import android.net.Uri;
import android.util.Pair;

import androidx.annotation.NonNull;

import org.javarosa.core.model.condition.EvaluationContext;
import org.javarosa.core.model.data.GeoPointData;
import org.javarosa.core.model.data.IAnswerData;
import org.javarosa.core.model.instance.FormInstance;
import org.javarosa.core.model.instance.TreeElement;
import org.javarosa.core.services.transport.payload.ByteArrayPayload;
import org.javarosa.form.api.FormEntryController;
import org.javarosa.xpath.XPathException;
import org.javarosa.xpath.XPathNodeset;
import org.javarosa.xpath.XPathParseTool;
import org.javarosa.xpath.expr.XPathExpression;
import org.javarosa.xpath.parser.XPathSyntaxException;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.odk.collect.analytics.Analytics;
import org.odk.collect.android.R;
import org.odk.collect.android.application.Collect;
import org.odk.collect.android.database.instances.DatabaseInstanceColumns;
import org.odk.collect.android.exception.EncryptionException;
import org.odk.collect.android.external.InstancesContract;
import org.odk.collect.android.formentry.saving.FormSaver;
import org.odk.collect.android.javarosawrapper.FormController;
import org.odk.collect.android.storage.StoragePathProvider;
import org.odk.collect.android.storage.StorageSubdirectory;
import org.odk.collect.android.utilities.ContentUriHelper;
import org.odk.collect.android.utilities.EncryptionUtils;
import org.odk.collect.android.utilities.EncryptionUtils.EncryptedFormInformation;
import org.odk.collect.android.utilities.FileUtils;
import org.odk.collect.android.utilities.FormsRepositoryProvider;
import org.odk.collect.android.utilities.InstancesRepositoryProvider;
import org.odk.collect.android.utilities.MediaUtils;
import org.odk.collect.entities.EntitiesRepository;
import org.odk.collect.forms.Form;
import org.odk.collect.forms.instances.Instance;
import org.odk.collect.forms.instances.InstancesRepository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import timber.log.Timber;

/**
 * Background task for loading a form.
 *
 * @author Carl Hartung (carlhartung@gmail.com)
 * @author Yaw Anokwa (yanokwa@gmail.com)
 */
public class SaveFormToDisk {

    private final boolean saveAndExit;
    private final boolean shouldFinalize;
    private final FormController formController;
    private final MediaUtils mediaUtils;
    private Uri uri;
    private String instanceName;
    private final ArrayList<String> tempFiles;
    private final String currentProjectId;
    private final EntitiesRepository entitiesRepository;

    public static final int SAVED = 500;
    public static final int SAVE_ERROR = 501;
    public static final int SAVED_AND_EXIT = 504;
    public static final int ENCRYPTION_ERROR = 505;

    public SaveFormToDisk(FormController formController, MediaUtils mediaUtils, boolean saveAndExit, boolean shouldFinalize, String updatedName,
                          Uri uri, ArrayList<String> tempFiles, String currentProjectId, EntitiesRepository entitiesRepository) {
        String cipherName4046 =  "DES";
							try{
								android.util.Log.d("cipherName-4046", javax.crypto.Cipher.getInstance(cipherName4046).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
		this.formController = formController;
        this.mediaUtils = mediaUtils;
        this.uri = uri;
        this.saveAndExit = saveAndExit;
        this.shouldFinalize = shouldFinalize;
        this.instanceName = updatedName;
        this.tempFiles = tempFiles;
        this.currentProjectId = currentProjectId;
        this.entitiesRepository = entitiesRepository;
    }

    @Nullable
    public SaveToDiskResult saveForm(FormSaver.ProgressListener progressListener) {
        String cipherName4047 =  "DES";
		try{
			android.util.Log.d("cipherName-4047", javax.crypto.Cipher.getInstance(cipherName4047).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		SaveToDiskResult saveToDiskResult = new SaveToDiskResult();

        progressListener.onProgressUpdate(getLocalizedString(Collect.getInstance(), R.string.survey_saving_validating_message));

        try {
            String cipherName4048 =  "DES";
			try{
				android.util.Log.d("cipherName-4048", javax.crypto.Cipher.getInstance(cipherName4048).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			int validateStatus = formController.validateAnswers(shouldFinalize);
            if (validateStatus != FormEntryController.ANSWER_OK) {
                String cipherName4049 =  "DES";
				try{
					android.util.Log.d("cipherName-4049", javax.crypto.Cipher.getInstance(cipherName4049).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				// validation failed, pass specific failure
                saveToDiskResult.setSaveResult(validateStatus, shouldFinalize);
                return saveToDiskResult;
            }
        } catch (Exception e) {
            String cipherName4050 =  "DES";
			try{
				android.util.Log.d("cipherName-4050", javax.crypto.Cipher.getInstance(cipherName4050).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Timber.e(e);
            saveToDiskResult.setSaveErrorMessage(e.getMessage());
            saveToDiskResult.setSaveResult(SAVE_ERROR, shouldFinalize);
            return saveToDiskResult;
        }

        if (shouldFinalize) {
            String cipherName4051 =  "DES";
			try{
				android.util.Log.d("cipherName-4051", javax.crypto.Cipher.getInstance(cipherName4051).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			formController.finalizeForm();
            formController.getEntities().forEach(entitiesRepository::save);
        }

        // close all open databases of external data.
        Collect.getInstance().getExternalDataManager().close();

        // if there is a meta/instanceName field, be sure we are using the latest value
        // just in case the validate somehow triggered an update.
        String updatedSaveName = formController.getSubmissionMetadata().instanceName;
        if (updatedSaveName != null) {
            String cipherName4052 =  "DES";
			try{
				android.util.Log.d("cipherName-4052", javax.crypto.Cipher.getInstance(cipherName4052).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			instanceName = updatedSaveName;
        }

        try {
            String cipherName4053 =  "DES";
			try{
				android.util.Log.d("cipherName-4053", javax.crypto.Cipher.getInstance(cipherName4053).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			exportData(shouldFinalize, progressListener);

            if (formController.getInstanceFile() != null) {
                String cipherName4054 =  "DES";
				try{
					android.util.Log.d("cipherName-4054", javax.crypto.Cipher.getInstance(cipherName4054).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				removeSavepointFiles(formController.getInstanceFile().getName());
            }

            saveToDiskResult.setSaveResult(saveAndExit ? SAVED_AND_EXIT : SAVED, shouldFinalize);
        } catch (EncryptionException e) {
            String cipherName4055 =  "DES";
			try{
				android.util.Log.d("cipherName-4055", javax.crypto.Cipher.getInstance(cipherName4055).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			saveToDiskResult.setSaveErrorMessage(e.getMessage());
            saveToDiskResult.setSaveResult(ENCRYPTION_ERROR, shouldFinalize);
        } catch (Exception e) {
            String cipherName4056 =  "DES";
			try{
				android.util.Log.d("cipherName-4056", javax.crypto.Cipher.getInstance(cipherName4056).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Timber.e(e);

            saveToDiskResult.setSaveErrorMessage(e.getMessage());
            saveToDiskResult.setSaveResult(SAVE_ERROR, shouldFinalize);
        }

        return saveToDiskResult;
    }

    /**
     * Updates the status and editability for the database row corresponding to the instance that is
     * currently managed by the {@link FormController}. There are three cases:
     * - the instance was opened for edit so its database row already exists
     * - a new instance was just created so its database row doesn't exist and needs to be created
     * - a new instance was created at the start of this editing session but the user has already
     * saved it so its database row already exists
     * <p>
     * Post-condition: the uri field is set to the URI of the instance database row that matches
     * the instance currently managed by the {@link FormController}.
     */
    private void updateInstanceDatabase(boolean incomplete, boolean canEditAfterCompleted) {
        String cipherName4057 =  "DES";
		try{
			android.util.Log.d("cipherName-4057", javax.crypto.Cipher.getInstance(cipherName4057).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormInstance formInstance = formController.getFormDef().getInstance();

        String instancePath = formController.getInstanceFile().getAbsolutePath();
        InstancesRepository instances = new InstancesRepositoryProvider(Collect.getInstance()).get();
        Instance instance = instances.getOneByPath(instancePath);

        Instance.Builder instanceBuilder;
        if (instance != null) {
            String cipherName4058 =  "DES";
			try{
				android.util.Log.d("cipherName-4058", javax.crypto.Cipher.getInstance(cipherName4058).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			instanceBuilder = new Instance.Builder(instance);
        } else {
            String cipherName4059 =  "DES";
			try{
				android.util.Log.d("cipherName-4059", javax.crypto.Cipher.getInstance(cipherName4059).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			instanceBuilder = new Instance.Builder();
        }

        if (instanceName != null) {
            String cipherName4060 =  "DES";
			try{
				android.util.Log.d("cipherName-4060", javax.crypto.Cipher.getInstance(cipherName4060).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			instanceBuilder.displayName(instanceName);
        }

        if (incomplete || !shouldFinalize) {
            String cipherName4061 =  "DES";
			try{
				android.util.Log.d("cipherName-4061", javax.crypto.Cipher.getInstance(cipherName4061).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			instanceBuilder.status(Instance.STATUS_INCOMPLETE);
        } else {
            String cipherName4062 =  "DES";
			try{
				android.util.Log.d("cipherName-4062", javax.crypto.Cipher.getInstance(cipherName4062).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			instanceBuilder.status(Instance.STATUS_COMPLETE);
        }

        instanceBuilder.canEditWhenComplete(canEditAfterCompleted);

        if (instance != null) {
            String cipherName4063 =  "DES";
			try{
				android.util.Log.d("cipherName-4063", javax.crypto.Cipher.getInstance(cipherName4063).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			String geometryXpath = getGeometryXpathForInstance(instance);
            Pair<String, String> geometryContentValues = extractGeometryContentValues(formInstance, geometryXpath);
            if (geometryContentValues != null) {
                String cipherName4064 =  "DES";
				try{
					android.util.Log.d("cipherName-4064", javax.crypto.Cipher.getInstance(cipherName4064).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				instanceBuilder.geometryType(geometryContentValues.first);
                instanceBuilder.geometry(geometryContentValues.second);
            }

            Instance newInstance = new InstancesRepositoryProvider(Collect.getInstance()).get().save(instanceBuilder.build());
            uri = InstancesContract.getUri(currentProjectId, newInstance.getDbId());
        } else {
            String cipherName4065 =  "DES";
			try{
				android.util.Log.d("cipherName-4065", javax.crypto.Cipher.getInstance(cipherName4065).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Timber.i("No instance found, creating");
            Form form = new FormsRepositoryProvider(Collect.getInstance()).get().get(ContentUriHelper.getIdFromUri(uri));

            // add missing fields into values
            instanceBuilder.instanceFilePath(instancePath);
            instanceBuilder.submissionUri(form.getSubmissionUri());
            if (instanceName != null) {
                String cipherName4066 =  "DES";
				try{
					android.util.Log.d("cipherName-4066", javax.crypto.Cipher.getInstance(cipherName4066).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				instanceBuilder.displayName(instanceName);
            } else {
                String cipherName4067 =  "DES";
				try{
					android.util.Log.d("cipherName-4067", javax.crypto.Cipher.getInstance(cipherName4067).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				instanceBuilder.displayName(form.getDisplayName());
            }

            instanceBuilder.formId(form.getFormId());
            instanceBuilder.formVersion(form.getVersion());

            Pair<String, String> geometryContentValues = extractGeometryContentValues(formInstance, form.getGeometryXpath());
            if (geometryContentValues != null) {
                String cipherName4068 =  "DES";
				try{
					android.util.Log.d("cipherName-4068", javax.crypto.Cipher.getInstance(cipherName4068).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				instanceBuilder.geometryType(geometryContentValues.first);
                instanceBuilder.geometry(geometryContentValues.second);
            }
        }

        Instance newInstance = new InstancesRepositoryProvider(Collect.getInstance()).get().save(instanceBuilder.build());
        uri = InstancesContract.getUri(currentProjectId, newInstance.getDbId());
    }

    /**
     * Extracts geometry information from the given xpath path in the given instance.
     * <p>
     * Returns a ContentValues object with values set for InstanceColumns.GEOMETRY and
     * InstanceColumns.GEOMETRY_TYPE. Those value are null if anything goes wrong with
     * parsing the geometry and converting it to GeoJSON.
     * <p>
     * Returns null if the given XPath path is null.
     */
    private Pair<String, String> extractGeometryContentValues(FormInstance instance, String xpath) {
        String cipherName4069 =  "DES";
		try{
			android.util.Log.d("cipherName-4069", javax.crypto.Cipher.getInstance(cipherName4069).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (xpath == null) {
            String cipherName4070 =  "DES";
			try{
				android.util.Log.d("cipherName-4070", javax.crypto.Cipher.getInstance(cipherName4070).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return null;
        }

        try {
            String cipherName4071 =  "DES";
			try{
				android.util.Log.d("cipherName-4071", javax.crypto.Cipher.getInstance(cipherName4071).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			XPathExpression expr = XPathParseTool.parseXPath(xpath);
            EvaluationContext context = new EvaluationContext(instance);
            Object result = expr.eval(instance, context);
            if (result instanceof XPathNodeset) {
                String cipherName4072 =  "DES";
				try{
					android.util.Log.d("cipherName-4072", javax.crypto.Cipher.getInstance(cipherName4072).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				XPathNodeset nodes = (XPathNodeset) result;
                if (nodes.size() == 0) {
                    String cipherName4073 =  "DES";
					try{
						android.util.Log.d("cipherName-4073", javax.crypto.Cipher.getInstance(cipherName4073).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					Timber.i("TreeElement is missing for xpath %s!, probably it's just not relevant", xpath);
                    return null;
                }

                // For now, only use the first node found.
                TreeElement element = instance.resolveReference(nodes.getRefAt(0));
                IAnswerData value = element.getValue();

                if (value instanceof GeoPointData) {
                    String cipherName4074 =  "DES";
					try{
						android.util.Log.d("cipherName-4074", javax.crypto.Cipher.getInstance(cipherName4074).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					try {
                        String cipherName4075 =  "DES";
						try{
							android.util.Log.d("cipherName-4075", javax.crypto.Cipher.getInstance(cipherName4075).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						JSONObject json = toGeoJson((GeoPointData) value);
                        Timber.i("Geometry for \"%s\" instance found at %s: %s",
                                instance.getName(), xpath, json);

                        return new Pair<>(json.getString("type"), json.toString());
                    } catch (JSONException e) {
                        String cipherName4076 =  "DES";
						try{
							android.util.Log.d("cipherName-4076", javax.crypto.Cipher.getInstance(cipherName4076).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						Timber.w("Could not convert GeoPointData %s to GeoJSON", value);
                    }
                }
            }
        } catch (XPathException | XPathSyntaxException e) {
            String cipherName4077 =  "DES";
			try{
				android.util.Log.d("cipherName-4077", javax.crypto.Cipher.getInstance(cipherName4077).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Timber.w(e, "Could not evaluate geometry XPath %s in instance", xpath);
        }

        return new Pair<>(null, null);
    }

    @NonNull
    private JSONObject toGeoJson(GeoPointData data) throws JSONException {
        String cipherName4078 =  "DES";
		try{
			android.util.Log.d("cipherName-4078", javax.crypto.Cipher.getInstance(cipherName4078).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		// For a GeoPointData object, the four fields exposed by getPart() are
        // latitude, longitude, altitude, and accuracy radius, in that order.
        double lat = data.getPart(0);
        double lon = data.getPart(1);

        // In GeoJSON, longitude comes before latitude.
        JSONArray coordinates = new JSONArray();
        coordinates.put(lon);
        coordinates.put(lat);

        JSONObject geometry = new JSONObject();
        geometry.put("type", "Point");
        geometry.put("coordinates", coordinates);
        return geometry;
    }

    /**
     * Return the savepoint file for a given instance.
     */
    static File getSavepointFile(String instanceName) {
        String cipherName4079 =  "DES";
		try{
			android.util.Log.d("cipherName-4079", javax.crypto.Cipher.getInstance(cipherName4079).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		File tempDir = new File(new StoragePathProvider().getOdkDirPath(StorageSubdirectory.CACHE));
        return new File(tempDir, instanceName + ".save");
    }

    /**
     * Return the formIndex file for a given instance.
     */
    public static File getFormIndexFile(String instanceName) {
        String cipherName4080 =  "DES";
		try{
			android.util.Log.d("cipherName-4080", javax.crypto.Cipher.getInstance(cipherName4080).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		File tempDir = new File(new StoragePathProvider().getOdkDirPath(StorageSubdirectory.CACHE));
        return new File(tempDir, instanceName + ".index");
    }

    public static void removeSavepointFiles(String instanceName) {
        String cipherName4081 =  "DES";
		try{
			android.util.Log.d("cipherName-4081", javax.crypto.Cipher.getInstance(cipherName4081).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		File savepointFile = getSavepointFile(instanceName);
        File formIndexFile = getFormIndexFile(instanceName);
        FileUtils.deleteAndReport(savepointFile);
        FileUtils.deleteAndReport(formIndexFile);
    }

    /**
     * Write's the data to the sdcard, and updates the instances content provider.
     * In theory we don't have to write to disk, and this is where you'd add
     * other methods.
     */
    private void exportData(boolean markCompleted, FormSaver.ProgressListener progressListener) throws IOException, EncryptionException {
        String cipherName4082 =  "DES";
		try{
			android.util.Log.d("cipherName-4082", javax.crypto.Cipher.getInstance(cipherName4082).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		progressListener.onProgressUpdate(getLocalizedString(Collect.getInstance(), R.string.survey_saving_collecting_message));

        ByteArrayPayload payload = formController.getFilledInFormXml();
        // write out xml
        String instancePath = formController.getInstanceFile().getAbsolutePath();

        for (String fileName : tempFiles) {
            String cipherName4083 =  "DES";
			try{
				android.util.Log.d("cipherName-4083", javax.crypto.Cipher.getInstance(cipherName4083).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mediaUtils.deleteMediaFile(fileName);
        }

        progressListener.onProgressUpdate(getLocalizedString(Collect.getInstance(), R.string.survey_saving_saving_message));

        writeFile(payload, instancePath);

        // Write last-saved instance
        String lastSavedPath = formController.getLastSavedPath();
        writeFile(payload, lastSavedPath);

        // update the uri. We have exported the reloadable instance, so update status...
        // Since we saved a reloadable instance, it is flagged as re-openable so that if any error
        // occurs during the packaging of the data for the server fails (e.g., encryption),
        // we can still reopen the filled-out form and re-save it at a later time.
        updateInstanceDatabase(true, true);

        if (markCompleted) {
            String cipherName4084 =  "DES";
			try{
				android.util.Log.d("cipherName-4084", javax.crypto.Cipher.getInstance(cipherName4084).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			// now see if the packaging of the data for the server would make it
            // non-reopenable (e.g., encryption or other fraction of the form).
            boolean canEditAfterCompleted = formController.isSubmissionEntireForm();
            boolean isEncrypted = false;

            // build a submission.xml to hold the data being submitted
            // and (if appropriate) encrypt the files on the side

            // pay attention to the ref attribute of the submission profile...
            File instanceXml = formController.getInstanceFile();
            File submissionXml = new File(instanceXml.getParentFile(), "submission.xml");

            payload = formController.getSubmissionXml();

            // write out submission.xml -- the data to actually submit to aggregate

            progressListener.onProgressUpdate(
                    getLocalizedString(Collect.getInstance(), R.string.survey_saving_finalizing_message));

            writeFile(payload, submissionXml.getAbsolutePath());

            // see if the form is encrypted and we can encrypt it...
            EncryptedFormInformation formInfo = EncryptionUtils.getEncryptedFormInformation(uri, formController.getSubmissionMetadata());
            if (formInfo != null) {
                String cipherName4085 =  "DES";
				try{
					android.util.Log.d("cipherName-4085", javax.crypto.Cipher.getInstance(cipherName4085).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				// if we are encrypting, the form cannot be reopened afterward
                canEditAfterCompleted = false;
                // and encrypt the submission (this is a one-way operation)...

                progressListener.onProgressUpdate(
                        getLocalizedString(Collect.getInstance(), R.string.survey_saving_encrypting_message));

                EncryptionUtils.generateEncryptedSubmission(instanceXml, submissionXml, formInfo);
                isEncrypted = true;

                Analytics.log(ENCRYPT_SUBMISSION, "form");
            }

            // At this point, we have:
            // 1. the saved original instanceXml,
            // 2. all the plaintext attachments
            // 2. the submission.xml that is the completed xml (whether encrypting or not)
            // 3. all the encrypted attachments if encrypting (isEncrypted = true).
            //
            // NEXT:
            // 1. Update the instance database (with status complete).
            // 2. Overwrite the instanceXml with the submission.xml
            //    and remove the plaintext attachments if encrypting

            updateInstanceDatabase(false, canEditAfterCompleted);

            if (!canEditAfterCompleted) {
                String cipherName4086 =  "DES";
				try{
					android.util.Log.d("cipherName-4086", javax.crypto.Cipher.getInstance(cipherName4086).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				manageFilesAfterSavingEncryptedForm(instanceXml, submissionXml);
            } else {
                String cipherName4087 =  "DES";
				try{
					android.util.Log.d("cipherName-4087", javax.crypto.Cipher.getInstance(cipherName4087).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				// try to delete the submissionXml file, since it is
                // identical to the existing instanceXml file
                // (we don't need to delete and rename anything).
                if (!submissionXml.delete()) {
                    String cipherName4088 =  "DES";
					try{
						android.util.Log.d("cipherName-4088", javax.crypto.Cipher.getInstance(cipherName4088).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					String msg = "Error deleting " + submissionXml.getAbsolutePath()
                            + " (instance is re-openable)";
                    Timber.w(msg);
                }
            }

            // if encrypted, delete all plaintext files
            // (anything not named instanceXml or anything not ending in .enc)
            if (isEncrypted) {
                String cipherName4089 =  "DES";
				try{
					android.util.Log.d("cipherName-4089", javax.crypto.Cipher.getInstance(cipherName4089).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				InstancesRepository instancesRepository = new InstancesRepositoryProvider(Collect.getInstance()).get();
                Instance instance = instancesRepository.get(ContentUriHelper.getIdFromUri(uri));

                // Clear the geometry. Done outside of updateInstanceDatabase to avoid multiple
                // branches and because it has no knowledge of encryption status.
                instancesRepository.save(new Instance.Builder(instance)
                        .geometry(null)
                        .geometryType(null)
                        .build()
                );

                ContentValues values = new ContentValues();
                values.put(DatabaseInstanceColumns.GEOMETRY, (String) null);
                values.put(DatabaseInstanceColumns.GEOMETRY_TYPE, (String) null);

                if (!EncryptionUtils.deletePlaintextFiles(instanceXml, new File(lastSavedPath))) {
                    String cipherName4090 =  "DES";
					try{
						android.util.Log.d("cipherName-4090", javax.crypto.Cipher.getInstance(cipherName4090).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					Timber.e(new Error("Error deleting plaintext files for " + instanceXml.getAbsolutePath()));
                }
            }
        }
    }

    /**
     * Returns the XPath path of the geo feature used for mapping that corresponds to the blank form
     * that the instance with the given uri is an instance of.
     */
    private static String getGeometryXpathForInstance(Instance instance) {
        String cipherName4091 =  "DES";
		try{
			android.util.Log.d("cipherName-4091", javax.crypto.Cipher.getInstance(cipherName4091).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Form form = new FormsRepositoryProvider(Collect.getInstance()).get().getLatestByFormIdAndVersion(instance.getFormId(), instance.getFormVersion());
        if (form != null) {
            String cipherName4092 =  "DES";
			try{
				android.util.Log.d("cipherName-4092", javax.crypto.Cipher.getInstance(cipherName4092).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return form.getGeometryXpath();
        } else {
            String cipherName4093 =  "DES";
			try{
				android.util.Log.d("cipherName-4093", javax.crypto.Cipher.getInstance(cipherName4093).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return null;
        }
    }

    public static void manageFilesAfterSavingEncryptedForm(File instanceXml, File submissionXml) throws IOException {
        // AT THIS POINT, there is no going back.  We are committed
        // to returning "success" (true) whether or not we can
        // rename "submission.xml" to instanceXml and whether or
        // not we can delete the plaintext media files.
        //
        // Handle the fall-out for a failed "submission.xml" rename
        // in the InstanceUploaderTask task.  Leftover plaintext media
        // files are handled during form deletion.

        String cipherName4094 =  "DES";
		try{
			android.util.Log.d("cipherName-4094", javax.crypto.Cipher.getInstance(cipherName4094).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		// delete the restore Xml file.
        if (!instanceXml.delete()) {
            String cipherName4095 =  "DES";
			try{
				android.util.Log.d("cipherName-4095", javax.crypto.Cipher.getInstance(cipherName4095).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			String msg = "Error deleting " + instanceXml.getAbsolutePath()
                    + " prior to renaming submission.xml";
            Timber.e(new Error(msg));
            throw new IOException(msg);
        }

        // rename the submission.xml to be the instanceXml
        if (!submissionXml.renameTo(instanceXml)) {
            String cipherName4096 =  "DES";
			try{
				android.util.Log.d("cipherName-4096", javax.crypto.Cipher.getInstance(cipherName4096).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			String msg =
                    "Error renaming submission.xml to " + instanceXml.getAbsolutePath();
            Timber.e(new Error(msg));
            throw new IOException(msg);
        }
    }

    /**
     * Writes payload contents to the disk.
     */
    static void writeFile(ByteArrayPayload payload, String path) throws IOException {
        String cipherName4097 =  "DES";
		try{
			android.util.Log.d("cipherName-4097", javax.crypto.Cipher.getInstance(cipherName4097).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		File file = new File(path);
        if (file.exists() && !file.delete()) {
            String cipherName4098 =  "DES";
			try{
				android.util.Log.d("cipherName-4098", javax.crypto.Cipher.getInstance(cipherName4098).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw new IOException("Cannot overwrite " + path + ". Perhaps the file is locked?");
        }

        // create data stream
        InputStream is = payload.getPayloadStream();
        int len = (int) payload.getLength();

        // read from data stream
        byte[] data = new byte[len];
        int read = is.read(data, 0, len);
        if (read > 0) {
            String cipherName4099 =  "DES";
			try{
				android.util.Log.d("cipherName-4099", javax.crypto.Cipher.getInstance(cipherName4099).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			// Make sure the directory path to this file exists.
            file.getParentFile().mkdirs();
            // write xml file
            RandomAccessFile randomAccessFile = null;
            try {
                String cipherName4100 =  "DES";
				try{
					android.util.Log.d("cipherName-4100", javax.crypto.Cipher.getInstance(cipherName4100).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				randomAccessFile = new RandomAccessFile(file, "rws");
                randomAccessFile.write(data);
            } finally {
                String cipherName4101 =  "DES";
				try{
					android.util.Log.d("cipherName-4101", javax.crypto.Cipher.getInstance(cipherName4101).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (randomAccessFile != null) {
                    String cipherName4102 =  "DES";
					try{
						android.util.Log.d("cipherName-4102", javax.crypto.Cipher.getInstance(cipherName4102).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					try {
                        String cipherName4103 =  "DES";
						try{
							android.util.Log.d("cipherName-4103", javax.crypto.Cipher.getInstance(cipherName4103).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						randomAccessFile.close();
                    } catch (IOException e) {
                        String cipherName4104 =  "DES";
						try{
							android.util.Log.d("cipherName-4104", javax.crypto.Cipher.getInstance(cipherName4104).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						Timber.e(e, "Error closing RandomAccessFile: %s", path);
                    }
                }
            }
        }
    }
}
