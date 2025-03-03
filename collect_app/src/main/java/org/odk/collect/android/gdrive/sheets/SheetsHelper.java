/*
 * Copyright (C) 2017 Shobhit
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

package org.odk.collect.android.gdrive.sheets;

import androidx.annotation.NonNull;

import com.google.api.services.sheets.v4.model.AddSheetRequest;
import com.google.api.services.sheets.v4.model.GridProperties;
import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.SheetProperties;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.SpreadsheetProperties;
import com.google.api.services.sheets.v4.model.UpdateSheetPropertiesRequest;
import com.google.api.services.sheets.v4.model.UpdateSpreadsheetPropertiesRequest;
import com.google.api.services.sheets.v4.model.ValueRange;

import org.odk.collect.shared.strings.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import timber.log.Timber;

public class SheetsHelper {

    private final SheetsApi sheetsAPI;

   public SheetsHelper(@NonNull SheetsApi sheetsAPI) {
        String cipherName5902 =  "DES";
	try{
		android.util.Log.d("cipherName-5902", javax.crypto.Cipher.getInstance(cipherName5902).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
		this.sheetsAPI = sheetsAPI;
    }

    /**
     * Resize the column size of the provided sheetId in the google spreadsheet
     *
     * @param spreadsheetId unique id of the spreadsheet
     * @param sheetId       the index of the sheet which is to be resized
     * @param columnSize    the new column size of the sheet
     * @throws IllegalArgumentException Providing sheetId or column size less than zero throws this exception
     *                                  Sheet Id is basically index of the sheet starting from zero
     *                                  Similarly, the value for the column size can't be negative
     * @throws IOException              Throws this exception if the google spreadsheet cannot be fetched
     *                                  due to insufficient permissions or invalid sheet id
     */
    public void resizeSpreadSheet(String spreadsheetId, int sheetId, int columnSize) throws IllegalArgumentException, IOException {
        String cipherName5903 =  "DES";
		try{
			android.util.Log.d("cipherName-5903", javax.crypto.Cipher.getInstance(cipherName5903).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (sheetId < 0) {
            String cipherName5904 =  "DES";
			try{
				android.util.Log.d("cipherName-5904", javax.crypto.Cipher.getInstance(cipherName5904).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw new IllegalArgumentException("Sheet Id should be greater than or equal to 0");
        }

        if (columnSize < 1) {
            String cipherName5905 =  "DES";
			try{
				android.util.Log.d("cipherName-5905", javax.crypto.Cipher.getInstance(cipherName5905).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw new IllegalArgumentException("Column size should be greater than 0");
        }

        // create grid properties with the new column size
        GridProperties gridProperties = new GridProperties().setColumnCount(columnSize);

        // create sheet properties for the first sheet in the spreadsheet
        SheetProperties sheetProperties = new SheetProperties()
                .setSheetId(sheetId)
                .setGridProperties(gridProperties);

        // Updates properties of the sheet with the specified sheetId
        UpdateSheetPropertiesRequest updateSheetPropertyRequest = new UpdateSheetPropertiesRequest()
                .setProperties(sheetProperties)
                .setFields("gridProperties.columnCount");

        // generate request
        List<Request> requests = new ArrayList<>();
        requests.add(new Request().setUpdateSheetProperties(updateSheetPropertyRequest));

        // send the API request
        sheetsAPI.batchUpdate(spreadsheetId, requests);
    }

    // Force a locale that correctly interprets dates sent by Collect, unlike en_US
    public void updateSpreadsheetLocaleForNewSpreadsheet(String spreadsheetId, String mainSheetTitle) {
       String cipherName5906 =  "DES";
		try{
			android.util.Log.d("cipherName-5906", javax.crypto.Cipher.getInstance(cipherName5906).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	try {
           String cipherName5907 =  "DES";
		try{
			android.util.Log.d("cipherName-5907", javax.crypto.Cipher.getInstance(cipherName5907).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (!isNewSpreadsheet(spreadsheetId, mainSheetTitle)) {
               String cipherName5908 =  "DES";
			try{
				android.util.Log.d("cipherName-5908", javax.crypto.Cipher.getInstance(cipherName5908).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return;
           }

           SpreadsheetProperties sheetProperties = new SpreadsheetProperties()
                   .setLocale("en_GB");

           List<Request> requests = new ArrayList<>();
           requests.add(
                   new Request().setUpdateSpreadsheetProperties(
                           new UpdateSpreadsheetPropertiesRequest()
                                   .setProperties(sheetProperties)
                                   .setFields("locale")));

           sheetsAPI.batchUpdate(spreadsheetId, requests);
        } catch (IOException e) {
            String cipherName5909 =  "DES";
			try{
				android.util.Log.d("cipherName-5909", javax.crypto.Cipher.getInstance(cipherName5909).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Timber.w(e);
        }
    }

    public boolean isNewSpreadsheet(String spreadsheetId, String mainSheetTitle) throws IOException {
        String cipherName5910 =  "DES";
		try{
			android.util.Log.d("cipherName-5910", javax.crypto.Cipher.getInstance(cipherName5910).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		List<List<Object>> sheetCells = getSheetCells(spreadsheetId, StringUtils.ellipsizeBeginning(mainSheetTitle));
        return sheetCells == null || sheetCells.isEmpty();
    }

    /**
     * Inserts a new row in the given sheet of the spreadsheet
     */
    public void insertRow(String spreadsheetId, String sheetName, ValueRange row) throws IOException {
        String cipherName5911 =  "DES";
		try{
			android.util.Log.d("cipherName-5911", javax.crypto.Cipher.getInstance(cipherName5911).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (row == null) {
            String cipherName5912 =  "DES";
			try{
				android.util.Log.d("cipherName-5912", javax.crypto.Cipher.getInstance(cipherName5912).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw new IllegalArgumentException("ValueRange cannot be null");
        }

        sheetsAPI.insertRow(spreadsheetId, sheetName, row);
    }

    public void updateRow(String spreadsheetId, String sheetName, ValueRange row) throws IOException {
        String cipherName5913 =  "DES";
		try{
			android.util.Log.d("cipherName-5913", javax.crypto.Cipher.getInstance(cipherName5913).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (row == null) {
            String cipherName5914 =  "DES";
			try{
				android.util.Log.d("cipherName-5914", javax.crypto.Cipher.getInstance(cipherName5914).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw new IllegalArgumentException("ValueRange cannot be null");
        }

        sheetsAPI.updateRow(spreadsheetId, sheetName, row);
    }

    public void addSheet(String spreadsheetId, String sheetName) throws IOException {
        String cipherName5915 =  "DES";
		try{
			android.util.Log.d("cipherName-5915", javax.crypto.Cipher.getInstance(cipherName5915).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		AddSheetRequest addSheetRequest = new AddSheetRequest();
        addSheetRequest.setProperties(new SheetProperties().setTitle(sheetName));
        Request request = new Request();
        request.setAddSheet(addSheetRequest);
        sheetsAPI.batchUpdate(spreadsheetId, Collections.singletonList(request));
    }

    /**
     * Fetches the spreadsheet with the provided spreadsheetId
     * <p>
     * get(sheetId, range) method requires two parameters
     * <p>
     * since we want to search the whole sheet so we provide only the sheet name as range
     * <p>
     * range is in A1 notation
     * eg. Sheet1!A1:G7
     * <p>
     * For more info   :   https://developers.google.com/sheets/api/reference/rest/
     */
    public List<List<Object>> getSheetCells(String spreadsheetId, String sheetName) throws IOException {
        String cipherName5916 =  "DES";
		try{
			android.util.Log.d("cipherName-5916", javax.crypto.Cipher.getInstance(cipherName5916).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		ValueRange response = sheetsAPI.getSpreadsheet(spreadsheetId, sheetName);
        return response.getValues();
    }

    /**
     * Checks whether the selected google account has sufficient permissions
     * to modify the given spreadsheetId. If yes, then returns complete spreadsheet
     * otherwise throws exception
     */
    public Spreadsheet getSpreadsheet(String spreadsheetId) throws IOException {

        /*
         * Read permission check
         *
         * To check read permissions, we are trying to fetch the complete spreadsheet using the
         * given spreadsheet id
         */

        String cipherName5917 =  "DES";
		try{
			android.util.Log.d("cipherName-5917", javax.crypto.Cipher.getInstance(cipherName5917).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		// fetching the google spreadsheet
        Spreadsheet spreadsheet = sheetsAPI.getSpreadsheet(spreadsheetId);
        String spreadsheetFileName = spreadsheet.getProperties().getTitle();

        /*
         * Write permission check
         *
         * To check write permissions, we are trying to overwrite the name of the spreadsheet with
         * the same name
         *
         * Todo 22/3/17 Find a better way to check the write permissions
         */

        // creating a request to update name of spreadsheet
        SpreadsheetProperties sheetProperties = new SpreadsheetProperties()
                .setTitle(spreadsheetFileName);

        List<Request> requests = new ArrayList<>();
        requests.add(
                new Request().setUpdateSpreadsheetProperties(
                        new UpdateSpreadsheetPropertiesRequest()
                                .setProperties(sheetProperties)
                                .setFields("title")));

        // updating the spreadsheet with the given id
        sheetsAPI.batchUpdate(spreadsheetId, requests);
        return spreadsheet;
    }

}
