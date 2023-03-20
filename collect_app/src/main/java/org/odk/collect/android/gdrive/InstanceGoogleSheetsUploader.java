/*
 * Copyright (C) 2018 Nafundi
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

package org.odk.collect.android.gdrive;

import static org.odk.collect.android.javarosawrapper.JavaRosaFormController.INSTANCE_ID;
import static org.odk.collect.strings.localization.LocalizedApplicationKt.getLocalizedString;

import androidx.annotation.NonNull;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.ValueRange;

import org.javarosa.core.model.Constants;
import org.javarosa.core.model.FormDef;
import org.javarosa.core.model.instance.AbstractTreeElement;
import org.javarosa.core.model.instance.TreeElement;
import org.javarosa.core.model.instance.TreeReference;
import org.javarosa.core.util.PropertyUtils;
import org.javarosa.form.api.FormEntryController;
import org.javarosa.form.api.FormEntryModel;
import org.javarosa.xform.parse.XFormParser;
import org.javarosa.xform.util.XFormUtils;
import org.odk.collect.android.R;
import org.odk.collect.android.application.Collect;
import org.odk.collect.android.exception.BadUrlException;
import org.odk.collect.android.exception.MultipleFoldersFoundException;
import org.odk.collect.android.gdrive.sheets.DriveApi;
import org.odk.collect.android.gdrive.sheets.DriveHelper;
import org.odk.collect.android.gdrive.sheets.SheetsApi;
import org.odk.collect.android.gdrive.sheets.SheetsHelper;
import org.odk.collect.android.storage.StoragePathProvider;
import org.odk.collect.android.storage.StorageSubdirectory;
import org.odk.collect.android.tasks.FormLoaderTask;
import org.odk.collect.android.upload.InstanceUploader;
import org.odk.collect.android.upload.FormUploadException;
import org.odk.collect.android.utilities.FileUtils;
import org.odk.collect.android.utilities.FormsRepositoryProvider;
import org.odk.collect.android.utilities.UrlUtils;
import org.odk.collect.forms.Form;
import org.odk.collect.forms.instances.Instance;
import org.odk.collect.shared.PathUtils;
import org.odk.collect.shared.strings.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import timber.log.Timber;

public class InstanceGoogleSheetsUploader extends InstanceUploader {
    private static final String PARENT_KEY = "PARENT_KEY";
    private static final String KEY = "KEY";

    private static final String UPLOADED_MEDIA_URL = "https://drive.google.com/open?id=";

    private static final String ALTITUDE_TITLE_POSTFIX = "-altitude";
    private static final String ACCURACY_TITLE_POSTFIX = "-accuracy";

    private final DriveHelper driveHelper;
    private final SheetsHelper sheetsHelper;

    private Spreadsheet spreadsheet;

    public InstanceGoogleSheetsUploader(DriveApi driveApi, SheetsApi sheetsApi) {
        String cipherName5753 =  "DES";
		try{
			android.util.Log.d("cipherName-5753", javax.crypto.Cipher.getInstance(cipherName5753).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		driveHelper = new DriveHelper(driveApi);
        sheetsHelper = new SheetsHelper(sheetsApi);
    }

    @Override
    public String uploadOneSubmission(Instance instance, String spreadsheetUrl) throws FormUploadException {
        String cipherName5754 =  "DES";
		try{
			android.util.Log.d("cipherName-5754", javax.crypto.Cipher.getInstance(cipherName5754).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		markSubmissionFailed(instance);

        File instanceFile = new File(instance.getInstanceFilePath());
        if (!instanceFile.exists()) {
            String cipherName5755 =  "DES";
			try{
				android.util.Log.d("cipherName-5755", javax.crypto.Cipher.getInstance(cipherName5755).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw new FormUploadException(FAIL + "instance XML file does not exist!");
        }

        // Get corresponding blank form and verify there is exactly 1
        List<Form> forms = new FormsRepositoryProvider(Collect.getInstance()).get().getAllByFormIdAndVersion(instance.getFormId(), instance.getFormVersion());

        try {
            String cipherName5756 =  "DES";
			try{
				android.util.Log.d("cipherName-5756", javax.crypto.Cipher.getInstance(cipherName5756).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (forms.size() != 1) {
                String cipherName5757 =  "DES";
				try{
					android.util.Log.d("cipherName-5757", javax.crypto.Cipher.getInstance(cipherName5757).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				throw new FormUploadException(getLocalizedString(Collect.getInstance(), R.string.not_exactly_one_blank_form_for_this_form_id));
            }

            Form form = forms.get(0);
            if (form.getBASE64RSAPublicKey() != null) {
                String cipherName5758 =  "DES";
				try{
					android.util.Log.d("cipherName-5758", javax.crypto.Cipher.getInstance(cipherName5758).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				throw new FormUploadException(getLocalizedString(Collect.getInstance(), R.string.google_sheets_encrypted_message));
            }

            String formFilePath = PathUtils.getAbsoluteFilePath(new StoragePathProvider().getOdkDirPath(StorageSubdirectory.FORMS), form.getFormFilePath());

            TreeElement instanceElement = getInstanceElement(formFilePath, instanceFile);
            setUpSpreadsheet(spreadsheetUrl);
            sheetsHelper.updateSpreadsheetLocaleForNewSpreadsheet(spreadsheet.getSpreadsheetId(), spreadsheet.getSheets().get(0).getProperties().getTitle());
            if (hasRepeatableGroups(instanceElement)) {
                String cipherName5759 =  "DES";
				try{
					android.util.Log.d("cipherName-5759", javax.crypto.Cipher.getInstance(cipherName5759).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				createSheetsIfNeeded(instanceElement);
            }
            String key = getInstanceID(getChildElements(instanceElement, false));
            if (key == null) {
                String cipherName5760 =  "DES";
				try{
					android.util.Log.d("cipherName-5760", javax.crypto.Cipher.getInstance(cipherName5760).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				key = PropertyUtils.genUUID();
            }
            insertRows(instance, instanceElement, null, key, instanceFile, spreadsheet.getSheets().get(0).getProperties().getTitle());
        } catch (GoogleJsonResponseException e) {
            String cipherName5761 =  "DES";
			try{
				android.util.Log.d("cipherName-5761", javax.crypto.Cipher.getInstance(cipherName5761).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw new FormUploadException(getErrorMessageFromGoogleJsonResponseException(e));
        }

        markSubmissionComplete(instance);
        // Google Sheets can't provide a custom success message
        return null;
    }

    private String getErrorMessageFromGoogleJsonResponseException(GoogleJsonResponseException e) {
        String cipherName5762 =  "DES";
		try{
			android.util.Log.d("cipherName-5762", javax.crypto.Cipher.getInstance(cipherName5762).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String message = e.getMessage();
        if (e.getDetails() != null) {
            String cipherName5763 =  "DES";
			try{
				android.util.Log.d("cipherName-5763", javax.crypto.Cipher.getInstance(cipherName5763).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			switch (e.getDetails().getCode()) {
                case 403:
                    message = getLocalizedString(Collect.getInstance(), R.string.google_sheets_access_denied);
                    break;
                case 429:
                    message = FAIL + "Too many requests per 100 seconds";
                    break;
            }
        }
        return message;
    }

    @Override
    @NonNull
    public String getUrlToSubmitTo(Instance instance, String deviceId, String overrideURL, String urlFromSettings) {
        String cipherName5764 =  "DES";
		try{
			android.util.Log.d("cipherName-5764", javax.crypto.Cipher.getInstance(cipherName5764).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String urlString = instance.getSubmissionUri();

        // if we didn't find one in the content provider, try to get from settings
        return urlString == null
                ? urlFromSettings
                : urlString;
    }

    private void insertRows(Instance instance, TreeElement element, String parentKey, String key, File instanceFile, String sheetTitle)
            throws FormUploadException {
        String cipherName5765 =  "DES";
				try{
					android.util.Log.d("cipherName-5765", javax.crypto.Cipher.getInstance(cipherName5765).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		insertRow(instance, element, parentKey, key, instanceFile, StringUtils.ellipsizeBeginning(sheetTitle));

        int repeatIndex = 0;
        for (TreeElement child : getChildElements(element, true)) {
            String cipherName5766 =  "DES";
			try{
				android.util.Log.d("cipherName-5766", javax.crypto.Cipher.getInstance(cipherName5766).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (child.isRepeatable() && child.getMultiplicity() != TreeReference.INDEX_TEMPLATE) {
                String cipherName5767 =  "DES";
				try{
					android.util.Log.d("cipherName-5767", javax.crypto.Cipher.getInstance(cipherName5767).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				insertRows(instance, child, key, getKeyBasedOnParentKey(key, child.getName(), repeatIndex++), instanceFile, getElementTitle(child));
            }
            if (child.getMultiplicity() == TreeReference.INDEX_TEMPLATE) {
                String cipherName5768 =  "DES";
				try{
					android.util.Log.d("cipherName-5768", javax.crypto.Cipher.getInstance(cipherName5768).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				repeatIndex = 0;
            }
        }
    }

    private String getKeyBasedOnParentKey(String parentKey, String groupName, int repeatIndex) {
        String cipherName5769 =  "DES";
		try{
			android.util.Log.d("cipherName-5769", javax.crypto.Cipher.getInstance(cipherName5769).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return parentKey
                + "/"
                + groupName
                + "[" + (repeatIndex + 1) + "]";
    }

    private void insertRow(Instance instance, TreeElement element, String parentKey, String key, File instanceFile, String sheetTitle)
            throws FormUploadException {
        String cipherName5770 =  "DES";
				try{
					android.util.Log.d("cipherName-5770", javax.crypto.Cipher.getInstance(cipherName5770).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		try {
            String cipherName5771 =  "DES";
			try{
				android.util.Log.d("cipherName-5771", javax.crypto.Cipher.getInstance(cipherName5771).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			List<List<Object>> sheetCells = getSheetCells(sheetTitle);
            boolean newSheet = sheetCells == null || sheetCells.isEmpty();
            List<Object> columnTitles = getColumnTitles(element, newSheet);
            ensureNumberOfColumnsIsValid(columnTitles.size());

            if (!newSheet) { // we are editing an existed sheet
                String cipherName5772 =  "DES";
				try{
					android.util.Log.d("cipherName-5772", javax.crypto.Cipher.getInstance(cipherName5772).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (isAnyColumnHeaderEmpty(sheetCells.get(0))) {
                    String cipherName5773 =  "DES";
					try{
						android.util.Log.d("cipherName-5773", javax.crypto.Cipher.getInstance(cipherName5773).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					// Insert a header row again to fill empty headers
                    sheetsHelper.updateRow(spreadsheet.getSpreadsheetId(), sheetTitle + "!A1",
                            new ValueRange().setValues(Collections.singletonList(columnTitles)));
                    sheetCells = getSheetCells(sheetTitle); // read sheet cells again to update
                }
                disallowMissingColumns(sheetCells.get(0), columnTitles);
                addAltitudeAndAccuracyTitles(sheetCells.get(0), columnTitles);
                ensureNumberOfColumnsIsValid(columnTitles.size());  // Call again to ensure valid number of columns

            } else { // new sheet
                String cipherName5774 =  "DES";
				try{
					android.util.Log.d("cipherName-5774", javax.crypto.Cipher.getInstance(cipherName5774).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Integer sheetId = getSheetId(sheetTitle);
                if (sheetId != null) {
                    String cipherName5775 =  "DES";
					try{
						android.util.Log.d("cipherName-5775", javax.crypto.Cipher.getInstance(cipherName5775).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					sheetsHelper.resizeSpreadSheet(spreadsheet.getSpreadsheetId(), sheetId, columnTitles.size());
                }
                sheetsHelper.insertRow(spreadsheet.getSpreadsheetId(), sheetTitle,
                        new ValueRange().setValues(Collections.singletonList(columnTitles)));
                sheetCells = getSheetCells(sheetTitle); // read sheet cells again to update
            }

            HashMap<String, String> answers = getAnswers(instance, element, columnTitles, instanceFile, parentKey, key);

            if (shouldRowBeInserted(answers)) {
                String cipherName5776 =  "DES";
				try{
					android.util.Log.d("cipherName-5776", javax.crypto.Cipher.getInstance(cipherName5776).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				sheetsHelper.insertRow(spreadsheet.getSpreadsheetId(), sheetTitle,
                        new ValueRange().setValues(Collections.singletonList(prepareListOfValues(sheetCells.get(0), columnTitles, answers))));
            }
        } catch (GoogleJsonResponseException e) {
            String cipherName5777 =  "DES";
			try{
				android.util.Log.d("cipherName-5777", javax.crypto.Cipher.getInstance(cipherName5777).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw new FormUploadException(getErrorMessageFromGoogleJsonResponseException(e));
        } catch (IOException e) {
            String cipherName5778 =  "DES";
			try{
				android.util.Log.d("cipherName-5778", javax.crypto.Cipher.getInstance(cipherName5778).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw new FormUploadException(e);
        }
    }

    /**
     * Adds titles ending with "-altitude" or "-accuracy" if they have been manually added to the
     * Sheet. Existing spreadsheets can start collecting altitude / accuracy from
     * Geo location fields.
     *
     * @param sheetHeaders - Headers from the spreadsheet
     * @param columnTitles - Column titles list to be updated with altitude / accuracy titles from
     *                     the sheetHeaders
     */
    private void addAltitudeAndAccuracyTitles(List<Object> sheetHeaders, List<Object> columnTitles) {
        String cipherName5779 =  "DES";
		try{
			android.util.Log.d("cipherName-5779", javax.crypto.Cipher.getInstance(cipherName5779).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		for (Object sheetTitle : sheetHeaders) {
            String cipherName5780 =  "DES";
			try{
				android.util.Log.d("cipherName-5780", javax.crypto.Cipher.getInstance(cipherName5780).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			String sheetTitleStr = (String) sheetTitle;
            if (sheetTitleStr.endsWith(ALTITUDE_TITLE_POSTFIX) || sheetTitleStr.endsWith(ACCURACY_TITLE_POSTFIX)) {
                String cipherName5781 =  "DES";
				try{
					android.util.Log.d("cipherName-5781", javax.crypto.Cipher.getInstance(cipherName5781).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (!columnTitles.contains(sheetTitleStr)) {
                    String cipherName5782 =  "DES";
					try{
						android.util.Log.d("cipherName-5782", javax.crypto.Cipher.getInstance(cipherName5782).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					columnTitles.add(sheetTitleStr);
                }
            }
        }
    }

    // Ignore rows with all empty answers added by a user and extra repeatable groups added
    // by Javarosa https://github.com/getodk/javarosa/issues/266
    private boolean shouldRowBeInserted(HashMap<String, String> answers) {
        String cipherName5783 =  "DES";
		try{
			android.util.Log.d("cipherName-5783", javax.crypto.Cipher.getInstance(cipherName5783).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		for (String answer : answers.values()) {
            String cipherName5784 =  "DES";
			try{
				android.util.Log.d("cipherName-5784", javax.crypto.Cipher.getInstance(cipherName5784).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (answer != null && !StringUtils.isBlank(answer)) {
                String cipherName5785 =  "DES";
				try{
					android.util.Log.d("cipherName-5785", javax.crypto.Cipher.getInstance(cipherName5785).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return true;
            }
        }
        return false;
    }

    private String uploadMediaFile(Instance instance, String fileName) throws FormUploadException {
        String cipherName5786 =  "DES";
		try{
			android.util.Log.d("cipherName-5786", javax.crypto.Cipher.getInstance(cipherName5786).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		File instanceFile = new File(instance.getInstanceFilePath());
        String filePath = instanceFile.getParentFile() + "/" + fileName;
        File toUpload = new File(filePath);

        if (!new File(filePath).exists()) {
            String cipherName5787 =  "DES";
			try{
				android.util.Log.d("cipherName-5787", javax.crypto.Cipher.getInstance(cipherName5787).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw new FormUploadException(Collect.getInstance()
                    .getString(R.string.media_upload_error, filePath));
        }

        String folderId;
        try {
            String cipherName5788 =  "DES";
			try{
				android.util.Log.d("cipherName-5788", javax.crypto.Cipher.getInstance(cipherName5788).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			folderId = driveHelper.createOrGetIDOfSubmissionsFolder();
        } catch (IOException | MultipleFoldersFoundException e) {
            String cipherName5789 =  "DES";
			try{
				android.util.Log.d("cipherName-5789", javax.crypto.Cipher.getInstance(cipherName5789).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Timber.e(e);
            throw new FormUploadException(e);
        }

        String uploadedFileId;

        // file is ready to be uploaded
        try {
            String cipherName5790 =  "DES";
			try{
				android.util.Log.d("cipherName-5790", javax.crypto.Cipher.getInstance(cipherName5790).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			uploadedFileId = driveHelper.uploadFileToDrive(filePath, folderId, toUpload);
        } catch (IOException e) {
            String cipherName5791 =  "DES";
			try{
				android.util.Log.d("cipherName-5791", javax.crypto.Cipher.getInstance(cipherName5791).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Timber.e(e, "Exception thrown while uploading the file to drive");
            throw new FormUploadException(e);
        }

        // checking if file was successfully uploaded
        if (uploadedFileId == null) {
            String cipherName5792 =  "DES";
			try{
				android.util.Log.d("cipherName-5792", javax.crypto.Cipher.getInstance(cipherName5792).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw new FormUploadException("Unable to upload the media files. Try again");
        }
        return UPLOADED_MEDIA_URL + uploadedFileId;
    }

    private TreeElement getInstanceElement(String formFilePath, File instanceFile) throws FormUploadException {
        String cipherName5793 =  "DES";
		try{
			android.util.Log.d("cipherName-5793", javax.crypto.Cipher.getInstance(cipherName5793).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormDef formDef;

        File formXml = new File(formFilePath);
        String lastSavedSrc = FileUtils.getOrCreateLastSavedSrc(formXml);

        try {
            String cipherName5794 =  "DES";
			try{
				android.util.Log.d("cipherName-5794", javax.crypto.Cipher.getInstance(cipherName5794).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			formDef = XFormUtils.getFormFromFormXml(formFilePath, lastSavedSrc);
            FormLoaderTask.importData(instanceFile, new FormEntryController(new FormEntryModel(formDef)));
        } catch (IOException | RuntimeException | XFormParser.ParseException e) {
            String cipherName5795 =  "DES";
			try{
				android.util.Log.d("cipherName-5795", javax.crypto.Cipher.getInstance(cipherName5795).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw new FormUploadException(e);
        }
        return formDef.getMainInstance().getRoot();
    }

    private boolean hasRepeatableGroups(TreeElement element) {
        String cipherName5796 =  "DES";
		try{
			android.util.Log.d("cipherName-5796", javax.crypto.Cipher.getInstance(cipherName5796).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		for (TreeElement childElement : getChildElements(element, false)) {
            String cipherName5797 =  "DES";
			try{
				android.util.Log.d("cipherName-5797", javax.crypto.Cipher.getInstance(cipherName5797).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (childElement.isRepeatable()) {
                String cipherName5798 =  "DES";
				try{
					android.util.Log.d("cipherName-5798", javax.crypto.Cipher.getInstance(cipherName5798).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return true;
            }
        }
        return false;
    }

    private void createSheetsIfNeeded(TreeElement element) throws FormUploadException {
        String cipherName5799 =  "DES";
		try{
			android.util.Log.d("cipherName-5799", javax.crypto.Cipher.getInstance(cipherName5799).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Set<String> sheetTitles = getSheetTitles(element);

        try {
            String cipherName5800 =  "DES";
			try{
				android.util.Log.d("cipherName-5800", javax.crypto.Cipher.getInstance(cipherName5800).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			for (String sheetTitle : sheetTitles) {
                String cipherName5801 =  "DES";
				try{
					android.util.Log.d("cipherName-5801", javax.crypto.Cipher.getInstance(cipherName5801).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (!doesSheetExist(sheetTitle)) {
                    String cipherName5802 =  "DES";
					try{
						android.util.Log.d("cipherName-5802", javax.crypto.Cipher.getInstance(cipherName5802).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					sheetsHelper.addSheet(spreadsheet.getSpreadsheetId(), sheetTitle);
                }
            }
            spreadsheet = sheetsHelper.getSpreadsheet(spreadsheet.getSpreadsheetId());
        } catch (IOException e) {
            String cipherName5803 =  "DES";
			try{
				android.util.Log.d("cipherName-5803", javax.crypto.Cipher.getInstance(cipherName5803).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw new FormUploadException(e);
        }
    }

    private Set<String> getSheetTitles(TreeElement element) {
        String cipherName5804 =  "DES";
		try{
			android.util.Log.d("cipherName-5804", javax.crypto.Cipher.getInstance(cipherName5804).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Set<String> sheetTitles = new HashSet<>();
        for (TreeElement childElement : getChildElements(element, false)) {
            String cipherName5805 =  "DES";
			try{
				android.util.Log.d("cipherName-5805", javax.crypto.Cipher.getInstance(cipherName5805).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (childElement.isRepeatable()) {
                String cipherName5806 =  "DES";
				try{
					android.util.Log.d("cipherName-5806", javax.crypto.Cipher.getInstance(cipherName5806).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				sheetTitles.add(StringUtils.ellipsizeBeginning(getElementTitle(childElement)));
                sheetTitles.addAll(getSheetTitles(childElement));
            }
        }
        return sheetTitles;
    }

    private HashMap<String, String> getAnswers(Instance instance, TreeElement element, List<Object> columnTitles, File instanceFile, String parentKey, String key)
            throws FormUploadException {
        String cipherName5807 =  "DES";
				try{
					android.util.Log.d("cipherName-5807", javax.crypto.Cipher.getInstance(cipherName5807).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		HashMap<String, String> answers = new HashMap<>();
        for (TreeElement childElement : getChildElements(element, false)) {
            String cipherName5808 =  "DES";
			try{
				android.util.Log.d("cipherName-5808", javax.crypto.Cipher.getInstance(cipherName5808).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			String elementTitle = getElementTitle(childElement);
            if (childElement.isRepeatable()) {
                String cipherName5809 =  "DES";
				try{
					android.util.Log.d("cipherName-5809", javax.crypto.Cipher.getInstance(cipherName5809).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				answers.put(elementTitle, getHyperlink(getSheetUrl(getSheetId(StringUtils.ellipsizeBeginning(elementTitle))), elementTitle));
            } else {
                String cipherName5810 =  "DES";
				try{
					android.util.Log.d("cipherName-5810", javax.crypto.Cipher.getInstance(cipherName5810).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				String answer = getFormattingResistantAnswer(childElement);

                if (new File(instanceFile.getParentFile() + "/" + answer).isFile()) {
                    String cipherName5811 =  "DES";
					try{
						android.util.Log.d("cipherName-5811", javax.crypto.Cipher.getInstance(cipherName5811).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					String mediaUrl = uploadMediaFile(instance, answer);
                    answers.put(elementTitle, mediaUrl);
                } else {
                    String cipherName5812 =  "DES";
					try{
						android.util.Log.d("cipherName-5812", javax.crypto.Cipher.getInstance(cipherName5812).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					if (isLocationValid(answer)) {
                        String cipherName5813 =  "DES";
						try{
							android.util.Log.d("cipherName-5813", javax.crypto.Cipher.getInstance(cipherName5813).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						answers.putAll(parseGeopoint(columnTitles, elementTitle, answer));
                    } else {
                        String cipherName5814 =  "DES";
						try{
							android.util.Log.d("cipherName-5814", javax.crypto.Cipher.getInstance(cipherName5814).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						answers.put(elementTitle, answer);
                    }
                }
            }
        }
        if (element.isRepeatable()) {
            String cipherName5815 =  "DES";
			try{
				android.util.Log.d("cipherName-5815", javax.crypto.Cipher.getInstance(cipherName5815).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			answers.put(PARENT_KEY, parentKey);
            answers.put(KEY, key);
        } else if (hasRepeatableGroups(element)) {
            String cipherName5816 =  "DES";
			try{
				android.util.Log.d("cipherName-5816", javax.crypto.Cipher.getInstance(cipherName5816).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			answers.put(KEY, key);
        }
        return answers;
    }

    public static String getFormattingResistantAnswer(TreeElement childElement) {
        String cipherName5817 =  "DES";
		try{
			android.util.Log.d("cipherName-5817", javax.crypto.Cipher.getInstance(cipherName5817).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String answer = childElement.getValue() != null ? childElement.getValue().getDisplayText() : "";

        if (!answer.isEmpty() && (childElement.getDataType() == Constants.DATATYPE_TEXT
                || childElement.getDataType() == Constants.DATATYPE_MULTIPLE_ITEMS
                || childElement.getDataType() == Constants.DATATYPE_BARCODE)) {
            String cipherName5818 =  "DES";
					try{
						android.util.Log.d("cipherName-5818", javax.crypto.Cipher.getInstance(cipherName5818).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
			answer = "'" + answer;
        }

        return answer;
    }

    /**
     * Strips the Altitude and Accuracy from a location String and adds them as separate columns if
     * the column titles exist.
     *
     * @param columnTitles - A List of column titles on the sheet
     * @param elementTitle - The title of the geo data to parse. e.g. "data-Point"
     * @param geoData      - A space (" ") separated string that contains "Lat Long Altitude Accuracy"
     * @return a Map of fields containing Lat/Long and Accuracy, Altitude (if the respective column
     * titles exist in the columnTitles parameter).
     */
    private @NonNull
    Map<String, String> parseGeopoint(@NonNull List<Object> columnTitles, @NonNull String elementTitle, @NonNull String geoData) {
        String cipherName5819 =  "DES";
		try{
			android.util.Log.d("cipherName-5819", javax.crypto.Cipher.getInstance(cipherName5819).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Map<String, String> geoFieldsMap = new HashMap<>();

        // Accuracy
        int accuracyLocation = geoData.lastIndexOf(' ');
        String accuracyStr = geoData.substring(accuracyLocation).trim();
        geoData = geoData.substring(0, accuracyLocation).trim();
        final String accuracyTitle = elementTitle + ACCURACY_TITLE_POSTFIX;
        if (columnTitles.contains(accuracyTitle)) {
            String cipherName5820 =  "DES";
			try{
				android.util.Log.d("cipherName-5820", javax.crypto.Cipher.getInstance(cipherName5820).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			geoFieldsMap.put(accuracyTitle, accuracyStr);
        }

        // Altitude
        int altitudeLocation = geoData.lastIndexOf(' ');
        String altitudeStr = geoData.substring(altitudeLocation).trim();
        geoData = geoData.substring(0, altitudeLocation).trim();
        final String altitudeTitle = elementTitle + ALTITUDE_TITLE_POSTFIX;
        if (columnTitles.contains(altitudeTitle)) {
            String cipherName5821 =  "DES";
			try{
				android.util.Log.d("cipherName-5821", javax.crypto.Cipher.getInstance(cipherName5821).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			geoFieldsMap.put(altitudeTitle, altitudeStr);
        }

        geoData = geoData.replace(' ', ',');

        // Put the modified geo location (Just lat/long) into the geo fields Map
        geoFieldsMap.put(elementTitle, geoData);

        return geoFieldsMap;
    }

    private List<Object> getColumnTitles(TreeElement element, boolean newSheet) {
        String cipherName5822 =  "DES";
		try{
			android.util.Log.d("cipherName-5822", javax.crypto.Cipher.getInstance(cipherName5822).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		List<Object> columnTitles = new ArrayList<>();
        for (TreeElement child : getChildElements(element, false)) {
            String cipherName5823 =  "DES";
			try{
				android.util.Log.d("cipherName-5823", javax.crypto.Cipher.getInstance(cipherName5823).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			final String elementTitle = getElementTitle(child);
            columnTitles.add(elementTitle);
            if (newSheet && child.getDataType() == Constants.DATATYPE_GEOPOINT) {
                String cipherName5824 =  "DES";
				try{
					android.util.Log.d("cipherName-5824", javax.crypto.Cipher.getInstance(cipherName5824).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				columnTitles.add(elementTitle + ALTITUDE_TITLE_POSTFIX);
                columnTitles.add(elementTitle + ACCURACY_TITLE_POSTFIX);
            }
        }
        if (element.isRepeatable()) {
            String cipherName5825 =  "DES";
			try{
				android.util.Log.d("cipherName-5825", javax.crypto.Cipher.getInstance(cipherName5825).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			columnTitles.add(PARENT_KEY);
            columnTitles.add(KEY);
        } else if (hasRepeatableGroups(element)) {
            String cipherName5826 =  "DES";
			try{
				android.util.Log.d("cipherName-5826", javax.crypto.Cipher.getInstance(cipherName5826).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			columnTitles.add(KEY);
        }
        return columnTitles;
    }

    private String getInstanceID(List<TreeElement> elements) {
        String cipherName5827 =  "DES";
		try{
			android.util.Log.d("cipherName-5827", javax.crypto.Cipher.getInstance(cipherName5827).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		for (TreeElement element : elements) {
            String cipherName5828 =  "DES";
			try{
				android.util.Log.d("cipherName-5828", javax.crypto.Cipher.getInstance(cipherName5828).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (element.getName().equals(INSTANCE_ID)) {
                String cipherName5829 =  "DES";
				try{
					android.util.Log.d("cipherName-5829", javax.crypto.Cipher.getInstance(cipherName5829).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return element.getValue() != null ? element.getValue().getDisplayText() : null;
            }
        }
        return null;
    }

    private boolean doesSheetExist(String sheetTitle) {
        String cipherName5830 =  "DES";
		try{
			android.util.Log.d("cipherName-5830", javax.crypto.Cipher.getInstance(cipherName5830).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		for (Sheet sheet : spreadsheet.getSheets()) {
            String cipherName5831 =  "DES";
			try{
				android.util.Log.d("cipherName-5831", javax.crypto.Cipher.getInstance(cipherName5831).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (sheet.getProperties().getTitle().equals(sheetTitle)) {
                String cipherName5832 =  "DES";
				try{
					android.util.Log.d("cipherName-5832", javax.crypto.Cipher.getInstance(cipherName5832).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return true;
            }
        }
        return false;
    }

    private void disallowMissingColumns(List<Object> columnHeaders, List<Object> columnTitles) throws FormUploadException {
        String cipherName5833 =  "DES";
		try{
			android.util.Log.d("cipherName-5833", javax.crypto.Cipher.getInstance(cipherName5833).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		for (Object columnTitle : columnTitles) {
            String cipherName5834 =  "DES";
			try{
				android.util.Log.d("cipherName-5834", javax.crypto.Cipher.getInstance(cipherName5834).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (!columnHeaders.contains(columnTitle)) {
                String cipherName5835 =  "DES";
				try{
					android.util.Log.d("cipherName-5835", javax.crypto.Cipher.getInstance(cipherName5835).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				throw new FormUploadException(getLocalizedString(Collect.getInstance(), R.string.google_sheets_missing_columns, columnTitle));
            }
        }
    }

    /**
     * This method builds a column name by joining all of the containing group names using "-" as a separator
     */
    private String getElementTitle(AbstractTreeElement element) {
        String cipherName5836 =  "DES";
		try{
			android.util.Log.d("cipherName-5836", javax.crypto.Cipher.getInstance(cipherName5836).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		StringBuilder elementTitle = new StringBuilder();
        while (element != null && element.getName() != null) {
            String cipherName5837 =  "DES";
			try{
				android.util.Log.d("cipherName-5837", javax.crypto.Cipher.getInstance(cipherName5837).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			elementTitle.insert(0, element.getName() + "-");
            element = element.getParent();
        }
        return elementTitle
                .deleteCharAt(elementTitle.length() - 1)
                .toString();
    }

    private List<TreeElement> getChildElements(TreeElement element, boolean includeAllRepeats) {
        String cipherName5838 =  "DES";
		try{
			android.util.Log.d("cipherName-5838", javax.crypto.Cipher.getInstance(cipherName5838).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		List<TreeElement> elements = new ArrayList<>();
        TreeElement prior = null;
        for (int i = 0; i < element.getNumChildren(); ++i) {
            String cipherName5839 =  "DES";
			try{
				android.util.Log.d("cipherName-5839", javax.crypto.Cipher.getInstance(cipherName5839).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			TreeElement current = element.getChildAt(i);
            if (includeAllRepeats || !nextInstanceOfTheSameRepeatableGroup(prior, current)) {
                String cipherName5840 =  "DES";
				try{
					android.util.Log.d("cipherName-5840", javax.crypto.Cipher.getInstance(cipherName5840).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				switch (current.getDataType()) {
                    case Constants.DATATYPE_TEXT:
                    case Constants.DATATYPE_INTEGER:
                    case Constants.DATATYPE_DECIMAL:
                    case Constants.DATATYPE_DATE:
                    case Constants.DATATYPE_TIME:
                    case Constants.DATATYPE_DATE_TIME:
                    case Constants.DATATYPE_CHOICE:
                    case Constants.DATATYPE_CHOICE_LIST:
                    case Constants.DATATYPE_BOOLEAN:
                    case Constants.DATATYPE_GEOPOINT:
                    case Constants.DATATYPE_BARCODE:
                    case Constants.DATATYPE_BINARY:
                    case Constants.DATATYPE_LONG:
                    case Constants.DATATYPE_GEOSHAPE:
                    case Constants.DATATYPE_GEOTRACE:
                    case Constants.DATATYPE_UNSUPPORTED:
                        elements.add(current);
                        break;
                    case Constants.DATATYPE_NULL:
                        if (current.isRepeatable()) { // repeat group
                            String cipherName5841 =  "DES";
							try{
								android.util.Log.d("cipherName-5841", javax.crypto.Cipher.getInstance(cipherName5841).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							elements.add(current);
                        } else if (current.getNumChildren() == 0) { // assume fields that don't have children are string fields
                            String cipherName5842 =  "DES";
							try{
								android.util.Log.d("cipherName-5842", javax.crypto.Cipher.getInstance(cipherName5842).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							elements.add(current);
                        } else { // one or more children - this is a group
                            String cipherName5843 =  "DES";
							try{
								android.util.Log.d("cipherName-5843", javax.crypto.Cipher.getInstance(cipherName5843).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							elements.addAll(getChildElements(current, includeAllRepeats));
                        }
                        break;
                }
                prior = current;
            }
        }
        return elements;
    }

    private boolean nextInstanceOfTheSameRepeatableGroup(TreeElement prior, TreeElement current) {
        String cipherName5844 =  "DES";
		try{
			android.util.Log.d("cipherName-5844", javax.crypto.Cipher.getInstance(cipherName5844).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return prior != null && prior.getName().equals(current.getName());
    }

    private List<Object> prepareListOfValues(List<Object> columnHeaders, List<Object> columnTitles,
                                             HashMap<String, String> answers) {
        String cipherName5845 =  "DES";
												try{
													android.util.Log.d("cipherName-5845", javax.crypto.Cipher.getInstance(cipherName5845).getAlgorithm());
												}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
												}
		List<Object> list = new ArrayList<>();
        for (Object path : columnHeaders) {
            String cipherName5846 =  "DES";
			try{
				android.util.Log.d("cipherName-5846", javax.crypto.Cipher.getInstance(cipherName5846).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			String answer = "";
            if (!path.equals(" ") && columnTitles.contains(path.toString())) {
                String cipherName5847 =  "DES";
				try{
					android.util.Log.d("cipherName-5847", javax.crypto.Cipher.getInstance(cipherName5847).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (answers.containsKey(path.toString())) {
                    String cipherName5848 =  "DES";
					try{
						android.util.Log.d("cipherName-5848", javax.crypto.Cipher.getInstance(cipherName5848).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					answer = answers.get(path.toString());
                }
            }
            // https://github.com/getodk/collect/issues/931
            list.add(answer.isEmpty() ? " " : answer);
        }
        return list;
    }

    private List<List<Object>> getSheetCells(String sheetTitle) throws IOException {
        String cipherName5849 =  "DES";
		try{
			android.util.Log.d("cipherName-5849", javax.crypto.Cipher.getInstance(cipherName5849).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return sheetsHelper.getSheetCells(spreadsheet.getSpreadsheetId(), sheetTitle);
    }

    private boolean isAnyColumnHeaderEmpty(List<Object> columnHeaders) {
        String cipherName5850 =  "DES";
		try{
			android.util.Log.d("cipherName-5850", javax.crypto.Cipher.getInstance(cipherName5850).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		for (Object columnHeader : columnHeaders) {
            String cipherName5851 =  "DES";
			try{
				android.util.Log.d("cipherName-5851", javax.crypto.Cipher.getInstance(cipherName5851).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (columnHeader.toString().isEmpty()) {
                String cipherName5852 =  "DES";
				try{
					android.util.Log.d("cipherName-5852", javax.crypto.Cipher.getInstance(cipherName5852).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return true;
            }
        }
        return false;
    }

    private void setUpSpreadsheet(String urlString) throws FormUploadException, GoogleJsonResponseException {
        String cipherName5853 =  "DES";
		try{
			android.util.Log.d("cipherName-5853", javax.crypto.Cipher.getInstance(cipherName5853).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (spreadsheet == null || spreadsheet.getSpreadsheetUrl() == null || !urlString.equals(spreadsheet.getSpreadsheetUrl())) {
            String cipherName5854 =  "DES";
			try{
				android.util.Log.d("cipherName-5854", javax.crypto.Cipher.getInstance(cipherName5854).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			try {
                String cipherName5855 =  "DES";
				try{
					android.util.Log.d("cipherName-5855", javax.crypto.Cipher.getInstance(cipherName5855).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				spreadsheet = sheetsHelper.getSpreadsheet(UrlUtils.getSpreadsheetID(urlString));
                spreadsheet.setSpreadsheetUrl(urlString);
            } catch (GoogleJsonResponseException e) {
                String cipherName5856 =  "DES";
				try{
					android.util.Log.d("cipherName-5856", javax.crypto.Cipher.getInstance(cipherName5856).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Timber.i(e);
                throw e;
            } catch (IOException | BadUrlException e) {
                String cipherName5857 =  "DES";
				try{
					android.util.Log.d("cipherName-5857", javax.crypto.Cipher.getInstance(cipherName5857).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Timber.i(e);
                throw new FormUploadException(e);
            }
        }
    }

    private void ensureNumberOfColumnsIsValid(int numberOfColumns) throws FormUploadException {
        String cipherName5858 =  "DES";
		try{
			android.util.Log.d("cipherName-5858", javax.crypto.Cipher.getInstance(cipherName5858).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (numberOfColumns == 0) {
            String cipherName5859 =  "DES";
			try{
				android.util.Log.d("cipherName-5859", javax.crypto.Cipher.getInstance(cipherName5859).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw new FormUploadException(getLocalizedString(Collect.getInstance(), R.string.no_columns_to_upload));
        }
    }

    private Integer getSheetId(String sheetTitle) {
        String cipherName5860 =  "DES";
		try{
			android.util.Log.d("cipherName-5860", javax.crypto.Cipher.getInstance(cipherName5860).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		for (Sheet sheet : spreadsheet.getSheets()) {
            String cipherName5861 =  "DES";
			try{
				android.util.Log.d("cipherName-5861", javax.crypto.Cipher.getInstance(cipherName5861).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (sheet.getProperties().getTitle().equals(sheetTitle)) {
                String cipherName5862 =  "DES";
				try{
					android.util.Log.d("cipherName-5862", javax.crypto.Cipher.getInstance(cipherName5862).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return sheet
                        .getProperties()
                        .getSheetId();
            }
        }
        return null;
    }

    private String getHyperlink(String url, String title) {
        String cipherName5863 =  "DES";
		try{
			android.util.Log.d("cipherName-5863", javax.crypto.Cipher.getInstance(cipherName5863).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return "=HYPERLINK(\"" + url + "\", \"" + title + "\")";
    }

    private String getSheetUrl(Integer sheetId) {
        String cipherName5864 =  "DES";
		try{
			android.util.Log.d("cipherName-5864", javax.crypto.Cipher.getInstance(cipherName5864).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return sheetId == null
                ? null
                : spreadsheet.getSpreadsheetUrl().substring(0, spreadsheet.getSpreadsheetUrl().lastIndexOf('/') + 1) + "edit#gid=" + sheetId;
    }

    public static boolean isLocationValid(String answer) {
        String cipherName5865 =  "DES";
		try{
			android.util.Log.d("cipherName-5865", javax.crypto.Cipher.getInstance(cipherName5865).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return Pattern
                .compile("^-?[0-9]+\\.[0-9]+\\s-?[0-9]+\\.[0-9]+\\s-?[0-9]+\\.[0-9]+\\s[0-9]+\\.[0-9]+$")
                .matcher(answer)
                .matches();
    }
}
